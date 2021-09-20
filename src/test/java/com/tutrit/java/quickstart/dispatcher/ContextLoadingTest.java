package com.tutrit.java.quickstart.dispatcher;

import com.tutrit.java.ioc.service.Context;
import org.junit.Test;

public class ContextLoadingTest {


  @Test
  public void run() {
    Context.loadContext();
  }
}
