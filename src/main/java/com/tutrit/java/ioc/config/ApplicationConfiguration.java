package com.tutrit.java.ioc.config;

import com.tutrit.java.ioc.annotation.MyConfiguration;
import com.tutrit.java.ioc.annotation.MyValue;

@MyConfiguration
public class ApplicationConfiguration {
  // credential.properties
  @MyValue
  private String sonarToken;
  // application.properties
  @MyValue
  private String botToken;
  @MyValue
  private String botUserName;
}
