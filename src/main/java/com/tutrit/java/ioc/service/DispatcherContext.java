package com.tutrit.java.ioc.service;

import com.tutrit.java.ioc.annotation.MyComponent;
import com.tutrit.java.ioc.annotation.MyInjection;
import com.tutrit.java.quickstart.Application;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

//TODO Все методы в этом классе просто скопированы из класса Context!!!!!!
public class DispatcherContext {
    //  IoC (Inversion Of Control) container
    private static Map<String, Object> ctx = new HashMap<>();

    public static Map<String, Object> loadContext() {
        // 1. create classes that annotated as MyComponent
        List<String> classNames = getClassNamesAsString();
        Map<String, Class<?>> classMap = createClassMap(classNames);

        // 2. create objects and put in context
        createObjects(List.copyOf(classMap.values()));

        // 3. inject values into context components
        List<DispatcherContext.ClassObjectPair> fields = makeFieldMap(classMap);
        injectValues(fields);

        return ctx;
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

    private static void createObjects(List<Class> classNames) {
        classNames.forEach(className -> createObject(className));
    }

    private static void createObject(Class clazz) {
        try {
            if(isMyComponent(clazz)) {
                ctx.put(clazz.getName(), clazz.getConstructor().newInstance());
            }
        } catch (Exception e) {
            Application.log.warn("could not create class {}, maybe no args constructor is not present",
                    clazz.getName());
        }
    }

    private static Map<String, Class<?>> createClassMap(List<String> classNames) {
        Map<String, Class<?>> classMap = new HashMap<>();
        classNames
                .stream()
                .forEach(name -> createClass(name)
                        .ifPresent(clazz -> classMap.put(name, clazz)));
        return classMap;
    }

    private static Optional<Class<?>> createClass(String objectClassName) {
        try {
            return Optional.of(Class.forName(objectClassName));
        } catch (Exception e) {
            Application.log.warn("could not load class {}", objectClassName);
        }
        return Optional.empty();
    }

    private static List<DispatcherContext.ClassObjectPair> makeFieldMap(Map<String, Class<?>> classMap) {
        return classMap.entrySet().stream()
                .filter(entry -> isMyComponent(entry.getValue()))
                .map(entry -> new ClassObjectPair(entry.getKey(), entry.getValue().getDeclaredFields()))
                .map(pair -> pair.setClassObject(ctx.get(pair.className)))
                .collect(Collectors.toList());
    }

    public static void injectValues(List<DispatcherContext.ClassObjectPair> classObjectPair) {
        classObjectPair.forEach(pair -> injectValues(pair));
    }

    public static void injectValues(DispatcherContext.ClassObjectPair classObjectPair) {
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
        if(clazz.isAnnotationPresent(MyComponent.class)) {
            return true;
        }
        Annotation[] annotations = clazz.getDeclaredAnnotations();
        if (annotations.length != 0) {
            for(Annotation annotation : annotations) {
                Annotation[] parentAnnotations = annotation.annotationType().getAnnotations();
                for(Annotation parentAnnotation : parentAnnotations) {
                    //Находим те которые помечены нашей аннотацией
                    if (parentAnnotation.annotationType().getName().equals("com.tutrit.java.ioc.annotation.MyComponent")) {
                        return true;
                    }
                }
            }
        }
        return false;
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

        public DispatcherContext.ClassObjectPair setClassObject(Object classObject) {
            this.classObject = classObject;
            return this;
        }
    }
}
