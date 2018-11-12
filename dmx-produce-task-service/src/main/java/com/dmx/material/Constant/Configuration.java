package com.dmx.material.Constant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;

@Configurable
public class Configuration {
    @Value("${test}")
    private String test;

    @Value("${task.check.new.cron}")
    private String checkNewTaskCron;

    public String getTest() {
        return test;
    }

    public String getCheckNewTaskCron() {
        return checkNewTaskCron;
    }
}
