package com.tutrit.java.ioc.service;

import static com.tutrit.java.ioc.service.Context.ctx;
import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toMap;

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

public class VeryEasyCommands {

  public static Map<String, Method> commands;

  public static Map<LocalDateTime, Slot> execute(String[] arg) throws InvocationTargetException, IllegalAccessException {
    String command = arg[0];
    String[] commandArgs = Arrays.copyOfRange(arg, 1, arg.length-1);
    Method method = VeryEasyCommands.commands.get(command);
    String objectName = method.getDeclaringClass().getName();
    Object obj = ctx.get(objectName);
    Object result = method.invoke(obj, commandArgs);
    return (Map<LocalDateTime, Slot>) result;
  }

  public static boolean containsCommand(String command) {
    if(commands == null) {
      VeryEasyCommands.loadCommands();
    }
    return commands.containsKey(command);
  }

  private static void loadCommands() {
    commands = ctx.values().stream()
        .map(obj ->obj.getClass())
        .flatMap(clazz -> stream(clazz.getDeclaredMethods()))
        .filter(method -> method.isAnnotationPresent(MyCommand.class))
        .collect(toMap(method -> method.getAnnotation(MyCommand.class).value(), method -> method));
  }
}
