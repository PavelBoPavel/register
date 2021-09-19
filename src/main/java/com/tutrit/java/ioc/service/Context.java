package com.tutrit.java.ioc.service;

import com.tutrit.java.ioc.annotation.MyComponent;
import com.tutrit.java.ioc.annotation.MyInjection;
import com.tutrit.java.quickstart.Application;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Context {

  private static Map<String, Object> ctx = new HashMap<>();
//  IoC (Inversion Of Control) container
  private static Map<String, Object> myContext = new HashMap<>();
  private static Map<String, Class<?>> classMap = new HashMap<>();

  public static Map<String, Object> loadContext() {
    List<String> classNames = getClassNamesAsString();

    // TODO: 9/17/21 change order:
    createObjects(classNames);      //firstly should filter my components
    myContext = makeComponentMap(); //and only then create their objects

    List<ClassObjectPair> fields = makeFieldMap();
    injectValues(fields);
    return myContext;
  }

  private static List<String> getClassNamesAsString() {
    try {
      return Files.walk(Paths.get("./"))
          .map(path -> path.toString())
          .filter(filename -> filename.endsWith(".java"))
          .filter(filename -> !filename.contains("/test/"))
          .map(f -> f.substring("./src/main/java/".length(), f.indexOf(".java")))
          .map(f -> f.replaceAll("/", "."))
          .collect(Collectors.toList());
    } catch (Exception e) {
      Application.log.error("Error on reading files", e);
      return Collections.EMPTY_LIST;
    }
  }

  private static void createObjects(List<String> classNames) {
    classNames.forEach(className -> createObject(className));
  }

  private static void createObject(String objectClassName) {
    try {
      Class<?> clazz = Class.forName(objectClassName);
      classMap.put(objectClassName, clazz);
      ctx.put(objectClassName, clazz.getConstructor().newInstance());
    } catch (Exception e) {
      Application.log.warn("could not create class {}, maybe no args constructor is not present",
          objectClassName);
    }
  }

  private static Map<String, Object> makeComponentMap() {
    return classMap.entrySet().stream()
        .filter(entry -> isMyComponent(entry.getValue()))
        .map(entry -> entry.getKey())
        .map(key -> ctx.get(key))
        .collect(Collectors.toMap(obj -> obj.getClass().getName(), Function.identity()));
  }

  private static List<ClassObjectPair> makeFieldMap() {
    return classMap.entrySet().stream()
        .filter(entry -> isMyComponent(entry.getValue()))
        .map(entry -> new ClassObjectPair(entry.getKey(), entry.getValue().getDeclaredFields()))
        .map(pair -> pair.setClassObject(ctx.get(pair.className)))
        .collect(Collectors.toList());
  }

  public static void injectValues(List<ClassObjectPair> classObjectPair) {
    classObjectPair.forEach(pair -> injectValues(pair));
  }

  public static void injectValues(ClassObjectPair classObjectPair) {
    Arrays.stream(classObjectPair.objectField)
        .filter(field -> isFieldInjectable(field))
        .map(field -> setAccessible(field))
        .forEach(field -> saveSet(field, classObjectPair.classObject, findByClass(field)));
  }

  private static void saveSet(Field field, Object obj, Object value) {
    try {
      field.set(obj, value);
    } catch (Exception e) {

    }
  }

  private static Object findByClass(Field field) {
    return ctx.get(field.getType().getName());
  }

  private static boolean isMyComponent(Class clazz) {
    return clazz.isAnnotationPresent(MyComponent.class);
  }

  private static boolean isFieldInjectable(Field field) {
    return field.isAnnotationPresent(MyInjection.class);
  }

  private static Field setAccessible(Field field) {
    field.setAccessible(true);
    return field;
  }

  private static class ClassObjectPair {

    String className;
    Object classObject;
    Field[] objectField;

    public ClassObjectPair(String className, Field[] objectField) {
      this.className = className;
      this.objectField = objectField;
    }

    public ClassObjectPair setClassObject(Object classObject) {
      this.classObject = classObject;
      return this;
    }
  }
}
