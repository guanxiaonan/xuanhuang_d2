package com.dmx.one.utils;

import com.dmx.one.pojo.Material;
import com.dmx.one.pojo.MaterialToJson;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 数据库映射实体(Entity)到向前端返回json数据,有一部分字段相同,通过该工具类减少
 * 从Entity中get出来,然后向json实体set的代码
 * @Date: Create at 10:21, 2017/12/21
 * @Author: Matthew
 */
public class EntityToJsonUtil<T>{

//    public Object toJson(Class< ? > e, Object t) {
//        Field[] fieldEntity = t.getClass().getDeclaredFields();
//        Field[] fieldTojson = e.getDeclaredFields();
//
//        String entityName = e.getSimpleName();
//        String toJsonName = t.getClass().getSimpleName();
//
//        Map map = new HashMap<String,Object>();
//
//        for (Field field : fieldEntity) {
//            // 如果不为空，设置可见性，然后返回
//            field.setAccessible(true);
//            System.out.println("name:"+field.getName());
//
//
//            try {
//                System.out.println("get:"+ field.get(t));
//                map.put(field.getName(),field.get(t));
//            } catch (IllegalAccessException e1) {
//                e1.printStackTrace();
//            }
//
//        }
//
//        return t;
//
//
//    }

    /**
     * 获取对象的所有键值对
     * @param obj
     * @return Map
     */
    public static Map<String, Object> getKeyAndValue(Object obj) {
        Map<String, Object> map = new HashMap<String, Object>();

        // 得到类对象
        Class userCla = obj.getClass();

        // 得到类中的所有属性数组
        Field[] fields = userCla.getDeclaredFields();

        for (Field field : fields) {
            // 设置些属性是可以访问的,对于private属性
            field.setAccessible(true);

            //创建一个内存空间,用来存储属性值
            Object val = new Object();
            try {
                // 得到此属性的值,传入需要取出属性值的对象
                val = field.get(obj);

                // 设置键值
                map.put(field.getName(), val);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

            /*
             * String type = f.getType().toString();//得到此属性的类型 if
             * (type.endsWith("String")) {
             * System.out.println(f.getType()+"\t是String"); f.set(obj,"12") ;
             * //给属性设值 }else if(type.endsWith("int") ||
             * type.endsWith("Integer")){
             * System.out.println(f.getType()+"\t是int"); f.set(obj,12) ; //给属性设值
             * }else{ System.out.println(f.getType()+"\t"); }
             */

        }
        return map;
    }

    public T toJsonEntity(Map map,T t) {
        Field[] fields = t.getClass().getDeclaredFields();
        for (Field field : fields) {
            String name = field.getName();
            try {
                if (map.get(field.getName()) != null) {
                    field.setAccessible(true);
                    String type = field.getGenericType().toString();
                    System.out.println("type:"+type);
                    System.out.println("name:"+name);
                    //类型对应
                    if ("class java.lang.Long".equals(type)) {
                        field.set(t,Long.valueOf(map.get(name).toString()));
                    }else if ("class java.lang.Integer".equals(type)) {
                        field.set(t,(Integer)map.get(name));
                    }else if ("class java.util.Date".equals(type)) {
                        Date date = new Date((Long)map.get(name));
                        field.set(t,date);
                    }else if (type.equals("class java.lang.String")) {
                        field.set(t,(String)map.get(name));
                    }else {
                        field.set(t,(Object) map.get(name));
                    }
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return t;
    }

    public static void main(String[] args) {
//        Material material = new Material();
//        System.out.println(material);
//        material.setName("李四");
//        material.setBarcode("dasdasas");
//        material.setId(5L);
////        Object fields = new EntityToJsonUtil().toJson(material.getClass(), new MaterialToJson());
////        System.out.println(fields.toString());
        //Map map = new HashMap<>();
        //map.put("id",5L);
        //map.put()
        //MaterialToJson materialToJson = new EntityToJsonUtil<MaterialToJson>().toJsonEntity(material, new MaterialToJson());
        //System.out.println(materialToJson.toString());


    }

}
