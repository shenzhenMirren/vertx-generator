package com.common;

import java.util.Arrays;

/**
 * SqlAssist的条件类,require属性为列的条件,value和values为条件值
 * 
 * @author github.shenzhenMirren
 */
public class SqlWhereCondition<T> {
	private String require;
	private T value;
	private Object[] values;

	public SqlWhereCondition() {
		super();
	}

	public SqlWhereCondition(String require, T value) {
		super();
		this.require = require;
		this.value = value;
	}

	public SqlWhereCondition(String require, Object... values) {
		super();
		this.require = require;
		this.values = values;
	}

	public String getRequire() {
		return require;
	}

	public void setRequire(String require) {
		this.require = require;
	}

	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
	}

	public Object[] getValues() {
		return values;
	}

	public void setValues(Object[] values) {
		this.values = values;
	}

	@Override
	public String toString() {
		return "WhereCondition [require=" + require + ", value=" + value + ", values=" + Arrays.toString(values) + "]";
	}

}
