package com.dmx.material.Constant;

/**
 * @Description:
 * @Date: Create at 19:42, 2017/12/18
 * @Author: Matthew
 */
public class Constant {
    public static final String JOB_GROUP = "group";
    /**
     * 说明：
     * 1xxx :   传参报错
     * 2xxx :   成功提示
     * 3xxx :   警告信息
     * 4xxx :   失败报错
     */
    public static int ID_NOT_NULL_CODE = 1001;
    public static String ID_NOT_NULL_MESSAGE = "id不能为null";

    public static int PARAM_ERROR_CODE = 1002;
    public static String PARAM_ERROR_MESSAGE = "参数错误";

    public static int SUCCESS_CODE = 2000;
    public static String SUCCESS_MESSAGE = "操作成功";

    public static int FAIL_CODE = 4000;
    public static String FAIL_MESSAGE = "操作失败";

    public static int RES_NOT_EXIST_CODE = 3001;
    public static String RES_NOT_EXIST_MESSAGE = "资源不存在";

    //任务状态 1禁用 2新建未运行 3正常运行 4暂停 5运行结束 6任务异常 7堵塞'
    public static int FORBID = 1;// 新建的默认状态
    public static int NEW = 2;
    public static int RUNNING = 3;
    public static int PAUSED = 4;
    public static int FINISHED = 5;
    public static int ERROR = 6;
    public static int BLOCK = 7;


}
