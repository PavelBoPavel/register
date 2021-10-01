package com.tutrit.java.quickstart;

import com.tutrit.java.ioc.service.Context;
import com.tutrit.java.quickstart.dispatcher.BaseDispatcher;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Application {
    public static Logger log = LoggerFactory.getLogger("main");

    public static void main(String[] args) {
        Map<String, Object> ctx = Context.loadContext();
        BaseDispatcher dispatch = (BaseDispatcher) ctx.get("com.tutrit.java.quickstart.dispatcher.BaseDispatcher");
        dispatch.dispatch(args);
    }
}

