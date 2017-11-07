package com.sql;

import java.util.ArrayList;
import java.util.List;

import com.common.AbstractSQL;
import com.common.SqlPropertyValue;
import io.vertx.core.json.JsonObject;
import io.vertx.core.json.JsonArray;
import com.common.SqlAndParams;


public class DuhuaSQL extends AbstractSQL<JsonObject> {
	@Override
	protected String tableName() {
		return "duhua";
	}

	@Override
	protected String primaryId() {
		return "id";
	}

	@Override
	protected String columns() {
		return " id AS id , test_test AS testTest , NameNameName AS nameNameName , this_name AS thisName , Json_json AS jsonJson , thisName AS thisName ";
	}

	@Override
	protected List<SqlPropertyValue<?>> propertyValue(JsonObject obj) {
		List<SqlPropertyValue<?>> result = new ArrayList<>();
		result.add(new SqlPropertyValue<>("test_test", obj.getString("testTest"))); 
        result.add(new SqlPropertyValue<>("NameNameName", obj.getDouble("nameNameName"))); 
        result.add(new SqlPropertyValue<>("this_name", obj.getString("thisName"))); 
        result.add(new SqlPropertyValue<>("Json_json", obj.getJsonObject("jsonJson"))); 
        result.add(new SqlPropertyValue<>("thisName", obj.getLong("thisName"))); 
        result.add(new SqlPropertyValue<>("id", obj.getInteger("id"))); 
		return result;
	}
	
	/**
	 * 批量插入数据到表中 <br>
	 * 返回:sql or sql与batchParams
	 * 
	 * @param batch
	 * @return
	 */
	public SqlAndParams insertDuhuaBatch(JsonArray batch) {
		List<JsonArray> batchParams = new ArrayList<>();// 批量参数
				for (int i = 0; i < batch.size(); i++) {
			JsonArray param = new JsonArray();
			JsonObject obj = batch.getJsonObject(i);
			if (obj.getInteger("id") != null) {param.add(obj.getInteger("id"));} else {param.addNull();}
            if (obj.getString("testTest") != null) {param.add(obj.getString("testTest"));} else {param.addNull();}
            if (obj.getDouble("nameNameName") != null) {param.add(obj.getDouble("nameNameName"));} else {param.addNull();}
            if (obj.getString("thisName") != null) {param.add(obj.getString("thisName"));} else {param.addNull();}
            if (obj.getJsonObject("jsonJson") != null) {param.add(obj.getJsonObject("jsonJson"));} else {param.addNull();}
            if (obj.getLong("thisName") != null) {param.add(obj.getLong("thisName"));} else {param.addNull();}
			batchParams.add(param);
		}

		String sql = "insert into " + tableName() + " (id,test_test,NameNameName,this_name,Json_json,thisName) values (?,?,?,?,?,?)";
		return new SqlAndParams(sql, batchParams);
	}
}
