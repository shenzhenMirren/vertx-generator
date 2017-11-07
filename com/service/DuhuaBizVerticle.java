package com.service;

import com.common.SqlAssist;
import com.entity.Duhua;


import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.Message;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

public class DuhuaBizVerticle extends AbstractVerticle {

	@Override
	public void start() throws Exception {
		super.start();
		vertx.eventBus().consumer("biz://Duhua/getDuhuaCount", this::getDuhuaCount);
		vertx.eventBus().consumer("biz://Duhua/findDuhua", this::findDuhua);
		vertx.eventBus().consumer("biz://Duhua/findDuhuaByPage", this::findDuhuaByPage);
		vertx.eventBus().consumer("biz://Duhua/getDuhuaByObj", this::getDuhuaByObj);
		vertx.eventBus().consumer("biz://Duhua/getDuhuaById", this::getDuhuaById);
		vertx.eventBus().consumer("biz://Duhua/addDuhua", this::addDuhua);
		vertx.eventBus().consumer("biz://Duhua/updtDuhua", this::updtDuhua);
		vertx.eventBus().consumer("biz://Duhua/delDuhua", this::delDuhuaById);
	    vertx.eventBus().consumer("biz://Duhua/addDuhuaBatch", this::addDuhuaBatch);
	}

	/**
	 * 获得Duhua数据总行数
	 * 
	 * @param msg
	 */
	public void getDuhuaCount(Message<JsonObject> msg) {
		// TODO 你可以在这里做点条件判断,比如type=1
		// Integer type = msg.body().getInteger("type");
		// SqlAssist assist = new
		// SqlAssist().setConditions(SqlAssist.andEq("type", type));
		// String message = assist.toJsonStr();
		// send中的null改为message
		vertx.eventBus().<Long>send("dao://Duhua/getDuhuaCount", null, res -> {
			if (res.succeeded()) {
				Long result = res.result().body();
				msg.reply(result);
			} else {
				// TODO 在这里做点什么事情,比如日志记录,你可以直接修改模板做相应的异常处理
				msg.fail(500, "操作失败!");
			}
		});
	}

	/**
	 * 获得所有Duhua
	 * 
	 * @param msg
	 */
	public void findDuhua(Message<JsonObject> msg) {
		// TODO 你可以在这里做点事情比如排序,将id正序排序,同时设置条件id大于5小于10
		// SqlAssist assist = new SqlAssist();
		// assist.setOrders(SqlAssist.order("id", true));
		// assist.setConditions(SqlAssist.andGt("id", 5),
		// SqlAssist.andLt("id",10));
		// String message = assist.toJsonStr();
		// send中的null改为message
		vertx.eventBus().<JsonArray>send("dao://Duhua/findDuhua", null, res -> {
			if (res.succeeded()) {
				JsonArray result = res.result().body();
				msg.reply(result);
			} else {
				// TODO 在这里做点什么事情,比如日志记录,你可以直接修改模板做相应的异常处理
				msg.fail(500, "操作失败!");
			}
		});
	}

	/**
	 * 获得所有Duhua
	 * 
	 * @param msg
	 */
	public void findDuhuaByPage(Message<JsonObject> msg) {
		// TODO 你可以在这里做点事情比如:从第10行开始取取数据,取15条数据,
		SqlAssist assist = new SqlAssist();
		assist.setStartRow(10).setRowSize(3);
		// 你还可以继续设置条件,排序等
		String message = assist.toJsonStr();
		// send中的null改为message
		vertx.eventBus().<JsonObject>send("dao://Duhua/findDuhuaByPage", message, res -> {
			if (res.succeeded()) {
				JsonObject result = res.result().body();
				msg.reply(result);
			} else {
				// TODO 在这里做点什么事情,比如日志记录,你可以直接修改模板做相应的异常处理
				msg.fail(500, "操作失败!");
			}
		});
	}

	/**
	 * 获得通过Duhua查询Duhua,可用于登录等
	 * 
	 * @param msg
	 */
	public void getDuhuaByObj(Message<JsonObject> msg) {
		// TODO 你可以在这里做点事情比如,判断字段是否为空
		vertx.eventBus().<JsonObject>send("dao://Duhua/getDuhuaByObj", null, res -> {
			if (res.succeeded()) {
				JsonObject result = res.result().body();
				msg.reply(result);
			} else {
				// TODO 在这里做点什么事情,比如日志记录,你可以直接修改模板做相应的异常处理
				msg.fail(500, "操作失败!");
			}
		});
	}

	/**
	 * 通过主键查询出Duhua
	 * 
	 * @param msg
	 */
	public void getDuhuaById(Message<JsonObject> msg) {
		// TODO 你可以在这里做点事情比如,判断字段是否为空
		// if (msg.body() == null || msg.body().getString("id") == null)
		// {//做点事情因为dao不能接受通过id查询却不传id值}
		vertx.eventBus().<JsonObject>send("dao://Duhua/getDuhuaById", null, res -> {
			if (res.succeeded()) {
				JsonObject result = res.result().body();
				msg.reply(result);
			} else {
				// TODO 在这里做点什么事情,比如日志记录,你可以直接修改模板做相应的异常处理
				msg.fail(500, "操作失败!");
			}
		});
	}

	/**
	 * 通过主键查询出Duhua
	 * 
	 * @param msg
	 */
	public void addDuhua(Message<JsonObject> msg) {
		// TODO 可以在这里做点事情比如判断,不为空跟长度,
		JsonObject body = msg.body();
		if (body == null) {
			// 做点事情
		} else if (body.getString("") == null) {
			// 做点事情
		} else if (body.getString("").length() > 22) {
			// 做点事情
		} else {
			vertx.eventBus().<Integer>send("dao://Duhua/addDuhua", msg.body(), res -> {
				if (res.succeeded()) {
					Integer result = res.result().body();
					msg.reply(result);
				} else {
					// TODO 在这里做点什么事情,比如日志记录,你可以直接修改模板做相应的异常处理
					msg.fail(500, "操作失败!");
				}
			});
		}
	}

	/**
	 * 将通过主键更新Duhua
	 * 
	 * @param msg
	 */
	public void updtDuhua(Message<JsonObject> msg) {
		// TODO 可以在这里做点事情比如判断,id不能为空
		JsonObject body = msg.body();
		if (body == null) {
			// 做点事情
		} else if (body.getString("") == null) {
			// 做点事情
		} else {
			vertx.eventBus().<Integer>send("dao://Duhua/updtDuhua", msg.body(), res -> {
				if (res.succeeded()) {
					Integer result = res.result().body();
					msg.reply(result);
				} else {
					// TODO 在这里做点什么事情,比如日志记录,你可以直接修改模板做相应的异常处理
					msg.fail(500, "操作失败!");
				}
			});
		}
	}

	/**
	 * 通过主键将Duhua删除
	 * 
	 * @param msg
	 */
	public void delDuhuaById(Message<JsonObject> msg) {
		// TODO 可以在这里做点事情比如判断,id不能为空
		JsonObject body = msg.body();
		if (body == null) {
			// 做点事情
		} else if (body.getString("") == null) {
			// 做点事情
		} else {
			vertx.eventBus().<Integer>send("dao://Duhua/delDuhua", msg.body(), res -> {
				if (res.succeeded()) {
					Integer result = res.result().body();
					msg.reply(result);
				} else {
					// TODO 在这里做点什么事情,比如日志记录,你可以直接修改模板做相应的异常处理
					msg.fail(500, "操作失败!");
				}
			});
		}
	}

	/**
	 * 批量插入Duhua
	 * 
	 * @param msg
	 */
	public void addDuhuaBatch(Message<JsonObject> msg) {
		// TODO 在这里做点什么
		JsonObject body = msg.body();
		if (body == null) {
			// TODO 做点事情
		} else {
			vertx.eventBus().<JsonArray>send("dao://Duhua/insertDuhuaBatch", msg.body(), res -> {
				if (res.succeeded()) {
					JsonArray result = res.result().body();
					msg.reply(result);
				} else {
					// TODO 在这里做点什么事情,比如日志记录,你可以直接修改模板做相应的异常处理
					msg.fail(500, "操作失败!");
				}
			});
		}
	}


}
