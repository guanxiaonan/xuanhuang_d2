package com.dmx.material.pojo;

public class ProduceRule {
    private Long id;
    private String ruleId;
    private String inputMaterialAndNum;
    private String outputMaterial;
    private Integer outputNum;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRuleId() {
        return ruleId;
    }

    public void setRuleId(String ruleId) {
        this.ruleId = ruleId;
    }

    public String getInputMaterialAndNum() {
        return inputMaterialAndNum;
    }

    public void setInputMaterialAndNum(String inputMaterialAndNum) {
        this.inputMaterialAndNum = inputMaterialAndNum;
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
}
