package com.coffeeforest.commons.aws;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class S3FileUploader {

  @Value("${cloud.aws.credentials.accessKey}")
  private String accessKey;

  @Value("${cloud.aws.credentials.secretKey}")
  private String secretKey;

  @Value("${cloud.aws.s3.bucket}")
  private String bucket;

  @Value("${cloud.aws.region.static}")
  private String region;

  private AmazonS3 s3Client;

  @PostConstruct
  public void setS3Client() {
    AWSCredentials credentials = new BasicAWSCredentials(this.accessKey, this.secretKey);

    s3Client =
        AmazonS3ClientBuilder.standard()
            .withCredentials(new AWSStaticCredentialsProvider(credentials))
            .withRegion(this.region)
            .build();
  }

  public void delete(Long userIndex, List<Long> deleteImageList) {
    List<S3ObjectSummary> s3ObjectSummaryList =
            s3Client.listObjects(bucket, String.valueOf(userIndex)).getObjectSummaries();

    for (Long index : deleteImageList) {
      String key = userIndex + "/" + index;
      if (s3ObjectSummaryList.stream()
              .anyMatch((s3ObjectSummary -> s3ObjectSummary.getKey().equals(key)))) {
        s3Client.deleteObject(new DeleteObjectRequest(bucket, key));
      }
    }
  }

  public List<String> upload(Long userIndex, List<MultipartFile> multipartFileList) {

    List<String> pathList = reArrange(userIndex);
    Long fileIndex = getLastIndex(userIndex);

    for (MultipartFile file : multipartFileList) {
      if(file.getSize() <= 0) {
        continue;
      }
      fileIndex += 1;
      String key = userIndex + "/" + fileIndex;
      try {
        pathList.add(putObject(key, file));
      } catch (Exception e) {
        e.printStackTrace();
      }
    }

    return pathList;
  }

  private List<String> reArrange(Long userIndex) {
    List<S3ObjectSummary> s3ObjectSummaryList =
            s3Client.listObjects(bucket, String.valueOf(userIndex)).getObjectSummaries();

    List<String> pathList = new LinkedList<>();
    Long fileName = 0L;
    for (S3ObjectSummary s3ObjectSummary : s3ObjectSummaryList) {
      fileName += 1;
      String newKey = userIndex + "/" + fileName;
      String originKey = s3ObjectSummary.getKey();

      if (originKey.equals(newKey)) {
        pathList.add(s3Client.getUrl(bucket, newKey).toString());
        continue;
      }
      rename(originKey, newKey);
      pathList.add(s3Client.getUrl(bucket, newKey).toString());
    }

    return pathList;
  }

  private void rename(String originKey, String newKey) {
    CopyObjectRequest copyObjectRequest =
            new CopyObjectRequest(bucket, originKey, bucket, newKey)
                    .withCannedAccessControlList(CannedAccessControlList.PublicRead)
                    .withMetadataDirective("REPLACE");

    s3Client.copyObject(copyObjectRequest);
    s3Client.deleteObject(new DeleteObjectRequest(bucket, originKey));
  }

  private Long getLastIndex(Long userIndex) {
    List<S3ObjectSummary> s3ObjectSummaryList =
        s3Client.listObjects(bucket, String.valueOf(userIndex)).getObjectSummaries();

    Long index = 0L;
    for (S3ObjectSummary s3ObjectSummary : s3ObjectSummaryList) {
      Long compareIndex = findIndex(s3ObjectSummary.getKey());

      if(index < compareIndex) {
        index = compareIndex;
      }
    }
    return index;
  }

  private Long findIndex(String key) {
    Pattern pattern = Pattern.compile("^.*/(.*)\\.*.*$");
    Matcher matcher = pattern.matcher(key);
    if (matcher.matches()) {
      return Long.valueOf(matcher.group(1));
    }
    return 0L;
  }

  private String putObject(String key, MultipartFile file) throws Exception {
    ObjectMetadata objectMetadata = new ObjectMetadata();
    objectMetadata.setContentLength(file.getSize());
    objectMetadata.setContentType(MediaType.IMAGE_JPEG_VALUE);

    PutObjectRequest putObjectRequest =
        new PutObjectRequest(bucket, key, file.getInputStream(), objectMetadata)
            .withCannedAcl(CannedAccessControlList.PublicRead);

    s3Client.putObject(putObjectRequest);
    return s3Client.getUrl(bucket, key).toString();
  }
}
