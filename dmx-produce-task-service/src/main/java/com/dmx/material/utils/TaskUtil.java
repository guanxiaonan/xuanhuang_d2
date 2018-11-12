package com.dmx.material.utils;

import java.util.UUID;

public class TaskUtil {
    public static String getjobName() {
        String str = UUID.randomUUID().toString();
        return "j-"+str.replace("-", "");
    }
    public static String getjobGroupName() {
        String str = UUID.randomUUID().toString();
        return "jG-"+str.replace("-", "");
    }
    public static String getTriggerName() {
        String str = UUID.randomUUID().toString();
        return "t-"+str.replace("-", "");
    }
    public static String getTriggerGroupName() {
        String str = UUID.randomUUID().toString();
        return "tG-"+str.replace("-", "");
    }
    public static String getProduceRuleUUID() {
        String str = UUID.randomUUID().toString();
        return str.replace("-", "");
    }
}
