package com.dmx;

import com.dmx.material.pojo.TaskInfo;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Test {
    @org.junit.Test
    public void test1() {
//        TaskInfo taskInfo = new TaskInfo();
//        Field[] declaredFields = taskInfo.getClass().getDeclaredFields();
//        System.out.println(Arrays.deepToString(declaredFields));
//        for (Field field : declaredFields) {
//            System.out.println(field.getName());
//        }

        Map<String,Long> map = new HashMap<>();
        map.put("aaa",1L);
        map.put("bbb",2L);
        map.put("ccc",3L);

        System.out.println(map);




    }
}
