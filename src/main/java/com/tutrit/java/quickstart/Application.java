package com.tutrit.java.quickstart;

import com.tutrit.java.quickstart.dispatcher.BaseDispatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Application {
    public static Logger log = LoggerFactory.getLogger("main");

    public static void main(String[] args) {
        log.info("Hello dear client!");
        BaseDispatcher.run(args);
    }
}

