package com.entity;

import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
public class Duhua {
    private Integer id;
    private String testTest;
    private Double nameNameName;
    private String thisName;
    private JsonObject jsonJson;
    private Long thisName;
    public Duhua() {
        super();
    }
    public Duhua(JsonObject obj) {
        super();
        this.id = obj.getInteger("id");
        this.testTest = obj.getString("testTest");
        this.nameNameName = obj.getDouble("nameNameName");
        this.thisName = obj.getString("thisName");
        this.jsonJson = obj.getJsonObject("jsonJson");
        this.thisName = obj.getLong("thisName");
    }
    public Duhua(Integer id,String testTest,Double nameNameName,String thisName,JsonObject jsonJson,Long thisName) {
        super();
        this.id = id;
        this.testTest = testTest;
        this.nameNameName = nameNameName;
        this.thisName = thisName;
        this.jsonJson = jsonJson;
        this.thisName = thisName;
    }
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTestTest() {
        return this.testTest;
    }

    public void setTestTest(String testTest) {
        this.testTest = testTest;
    }

    public Double getNameNameName() {
        return this.nameNameName;
    }

    public void setNameNameName(Double nameNameName) {
        this.nameNameName = nameNameName;
    }

    public String getThisName() {
        return this.thisName;
    }

    public void setThisName(String thisName) {
        this.thisName = thisName;
    }

    public JsonObject getJsonJson() {
        return this.jsonJson;
    }

    public void setJsonJson(JsonObject jsonJson) {
        this.jsonJson = jsonJson;
    }

    public Long getThisName() {
        return this.thisName;
    }

    public void setThisName(Long thisName) {
        this.thisName = thisName;
    }

    /**
     * 将本类转换为Json字符串 
     * @return
     */
   public String toJsonStr(){
        return Json.encode(this);
    }

    /**
     * 将本类转换为JsonObject 
     * @return
     */
   public JsonObject toJson(){
        JsonObject result = new JsonObject();
        result.put("id",this.id);
        result.put("testTest",this.testTest);
        result.put("nameNameName",this.nameNameName);
        result.put("thisName",this.thisName);
        result.put("jsonJson",this.jsonJson);
        result.put("thisName",this.thisName);
    return result;
    }

    /**
     * 通过json字符串创建一个类 
     * @return
     */
    public static Duhua fromJson(String jsonStr){
         return Json.decodeValue(jsonStr, Duhua.class);
    }


}
