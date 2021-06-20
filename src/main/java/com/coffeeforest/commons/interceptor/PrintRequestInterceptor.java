package com.coffeeforest.commons.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class PrintRequestInterceptor implements HandlerInterceptor {
  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {
    System.out.println(">> Request URI    : " + request.getRequestURI());
    System.out.println(">> Query String   : " + request.getQueryString());
    System.out.println(">> Request Method : " + request.getMethod());
    return true;
  }
}
