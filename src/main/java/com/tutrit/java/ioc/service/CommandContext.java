package com.tutrit.java.ioc.service;

import com.tutrit.java.ioc.annotation.MyCommand;
import com.tutrit.java.ioc.annotation.MyComponent;
import com.tutrit.java.quickstart.Application;

import com.tutrit.java.quickstart.bean.Slot;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class CommandContext {

  protected static Map<String, Method> filterMyCommand(Map<String, Method> methodMap) {
    return methodMap.entrySet().stream()
        .filter(entry -> isMyCommand(entry.getValue()))
        .collect(Collectors.toMap(entry -> entry.getKey(), entry -> entry.getValue()));
  }

  protected static Map<String, Method> createMethodsMap(Map<String, Class<?>> classMap) {
    Map<String, Method> methodsMap = new HashMap<>();
    for (Map.Entry entry: classMap.entrySet()) {
      Class clazz = (Class) entry.getValue();
      Method[] methods = clazz.getDeclaredMethods();
      for(Method method : methods) {
        methodsMap.put(method.getDeclaringClass().getName() +"."+ method.getName(), method);
      }
    }
    return methodsMap;
  }

  protected static boolean isMyCommand(Method method) {
    return method.isAnnotationPresent(MyCommand.class);
  }

  public static Map<LocalDateTime, Slot> execute(String[] arg)
      throws InvocationTargetException, IllegalAccessException {
    Method method = Context.commands.get(arg[0]);
    String objectName = method.getDeclaringClass().getName();
    Object obj = Context.ctx.get(objectName);
    Object result = method.invoke(obj, arg[1], arg[2]);
    return (Map<LocalDateTime, Slot>) result;
  }

  protected static String readMyCommand(Method method) {
    return method.getAnnotation(MyCommand.class).value();
  }

  protected static Map<String, Method> createCommandNamesMap(Map<String, Method> myCommandMap) {
    return myCommandMap.entrySet().stream()
        .collect(Collectors.toMap(entry -> readMyCommand(entry.getValue()), entry -> entry.getValue()));
  }
}
