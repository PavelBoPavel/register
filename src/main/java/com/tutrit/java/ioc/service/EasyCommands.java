package com.tutrit.java.ioc.service;

import com.tutrit.java.ioc.annotation.MyCommand;
import com.tutrit.java.quickstart.bean.Slot;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class EasyCommands {

  public static Map<String, Method> commands;

  public static void loadCommands() {
    List<Class> classes = getClasses(Context.ctx.values());
    List<Method> methods = getMethods(classes);
    List<Method> myCommandMethods = filterMyCommand(methods);
    commands = createCommandNamesMap(myCommandMethods);
  }

  protected static boolean isMyCommand(Method method) {
    return method.isAnnotationPresent(MyCommand.class);
  }

  public static Map<LocalDateTime, Slot> execute(String[] arg) throws InvocationTargetException, IllegalAccessException {
    String command = arg[0];
    String[] commandArgs = Arrays.copyOfRange(arg, 1, arg.length-1);
    Method method = EasyCommands.commands.get(command);
    String objectName = method.getDeclaringClass().getName();
    Object obj = Context.ctx.get(objectName);
    Object result = method.invoke(obj, commandArgs);
    return (Map<LocalDateTime, Slot>) result;
  }

  protected static String readMyCommand(Method method) {
    return method.getAnnotation(MyCommand.class).value();
  }

  public static List<Class> getClasses(Collection<Object> objs) {
    return objs.stream()
        .map(obj ->obj.getClass())
        .collect(Collectors.toList());
  }

  public static List<Method> getMethods(Collection<Class> classes) {
    return classes.stream()
        .flatMap(clazz -> Arrays.stream(clazz.getDeclaredMethods()))
        .collect(Collectors.toList());
  }

  public static List<Method> filterMyCommand(List<Method> methods) {
    return methods.stream()
        .filter(method -> isMyCommand(method))
        .collect(Collectors.toList());
  }

  public static Map<String, Method> createCommandNamesMap(List<Method> myCommandMethods) {
    return myCommandMethods.stream()
        .collect(Collectors.toMap(entry -> readMyCommand(entry), entry -> entry));
  }

  public static boolean containsCommand(String command) {
    if(commands == null) {
      EasyCommands.loadCommands();
    }
    return commands.containsKey(command);
  }
}
