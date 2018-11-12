package com.dmx.one.Constant;

/**
 * @Description:
 * @Date: Create at 19:42, 2017/12/18
 * @Author: Matthew
 */
public class Constant {
    /**
     * 说明：
     * 1xxx :   传参报错
     * 2xxx :   成功提示
     * 3xxx :   警告信息
     * 4xxx :   失败报错
     */
    public static int ID_NOT_NULL_CODE = 1001;
    public static String ID_NOT_NULL_MESSAGE = "id不能为null";

    public static int SUCCESS_CODE = 2000;
    public static String SUCCESS_MESSAGE = "操作成功";

    public static int FAIL_CODE = 4000;
    public static String FAIL_MESSAGE = "操作失败";

    public static int RES_NOT_EXIST_CODE = 3001;
    public static String RES_NOT_EXIST_MESSAGE = "资源不存在";

    public static int SERVICE_NOT_ENABLE_CODE = 3002;
    public static String SERVICE_NOT_ENABLE_MESSAGE = "该服务不可用";

    public static int USER_NOT_LOGIN_CODE = 4004;
    public static String USER_NOT_LOGIN_MSG = "用户未登录,请登录！！";

    public static String USER_SERVICE = "dmx-user-service";
    public static String MATERIAL_SERVICE = "dmx-material-service";
    public static String INVENTORY_SERVICE = "dmx-inventory-service";
    public static String PRODUCE_TASK_SERVICE = "dmx-produce-task-service";
}
