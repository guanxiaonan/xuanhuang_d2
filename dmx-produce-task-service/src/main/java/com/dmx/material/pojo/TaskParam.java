package com.dmx.material.pojo;

public class TaskParam {
    private String createDesc;  //任务详情
    private String cron;        //定时表达式
    private String email;       //报警邮箱
    private String input;       //输入物料及数量
    private String outputMaterial;  // 输出物料名称
    private Integer outputNum;     // 输出物料数量

    public String getCreateDesc() {
        return createDesc;
    }

    public void setCreateDesc(String createDesc) {
        this.createDesc = createDesc;
    }

    public String getCron() {
        return cron;
    }

    public void setCron(String cron) {
        this.cron = cron;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public String getOutputMaterial() {
        return outputMaterial;
    }

    public void setOutputMaterial(String outputMaterial) {
        this.outputMaterial = outputMaterial;
    }

    public Integer getOutputNum() {
        return outputNum;
    }

    public void setOutputNum(Integer outputNum) {
        this.outputNum = outputNum;
    }

    @Override
    public String toString() {
        return "TaskParam{" +
                "createDesc='" + createDesc + '\'' +
                ", cron='" + cron + '\'' +
                ", email='" + email + '\'' +
                ", input='" + input + '\'' +
                ", outputMaterial='" + outputMaterial + '\'' +
                ", outputNum=" + outputNum +
                '}';
    }
}
