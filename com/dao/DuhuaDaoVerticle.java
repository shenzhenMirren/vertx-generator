package com.dao;

import java.util.List;

import com.common.SqlAndParams;
import com.common.SqlAssist;
import com.sql.DuhuaSQL;


import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.Message;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.jdbc.JDBCClient;
import io.vertx.ext.sql.SQLConnection;

public class DuhuaDaoVerticle extends AbstractVerticle {
	JDBCClient jdbcClient;
	DuhuaSQL  duhuaSQL;

	@Override
	public void start() throws Exception {
		jdbcClient = JDBCClient.createShared(vertx, config());
		duhuaSQL = new DuhuaSQL();
		vertx.eventBus().consumer("dao://Duhua/getDuhuaCount", this::getDuhuaCount);
		vertx.eventBus().consumer("dao://Duhua/findDuhua", this::selectDuhua);
		vertx.eventBus().consumer("dao://Duhua/findDuhuaByPage", this::selectDuhuaByPage);
		vertx.eventBus().consumer("dao://Duhua/getDuhuaByObj", this::selectDuhuaByObj);
		vertx.eventBus().consumer("dao://Duhua/getDuhuaById", this::selectDuhuaById);
		vertx.eventBus().consumer("dao://Duhua/addDuhua", this::insertNonEmptyDuhua);
		vertx.eventBus().consumer("dao://Duhua/updtDuhua", this::updateNonEmptyDuhua);
		vertx.eventBus().consumer("dao://Duhua/delDuhua", this::deleteDuhuaById);
	    vertx.eventBus().consumer("dao://Duhua/insertDuhuaBatch", this::insertDuhuaBatch);
	}
	
	/**
	 * 获得Duhua数据总行数,接收SqlAssist的Json字符串,查询全部可以传入null
	 * 
	 * @param msg
	 */
	public void getDuhuaCount(Message<String> msg) {
		SqlAssist assist = null;
		if (msg.body() != null) {
			assist = Json.decodeValue(msg.body(), SqlAssist.class);
		}
		SqlAndParams sp = duhuaSQL.getCount(assist);
		jdbcClient.queryWithParams(sp.getSql(), sp.getParams(), res -> {
			if (res.succeeded()) {
				Long result = res.result().getResults().get(0).getLong(0);
				msg.reply(result);
			} else {
				// TODO 做异常处理
				// LOG.error("执行查询Duhua数据总行数-->失败:" + res.cause());
				// msg.fail(500, res.cause().toString());
			}
		});
	}
	
		/**
	 * 获得所有Duhua通过分页,接收SqlAssist的Json字符串,查询全部可以传入null
	 * 
	 * @param msg
	 */
	public void selectDuhuaByPage(Message<String> msg) {
		SqlAssist assist = null;
		if (msg.body() != null) {
			assist = Json.decodeValue(msg.body(), SqlAssist.class);
		}
		SqlAndParams sp = duhuaSQL.selectAllByPage(assist);
		jdbcClient.queryWithParams(sp.getBatchSql().get(0), sp.getBatchParams().get(0), getCount -> {
			if (getCount.succeeded()) {
				// 返回结果
				JsonObject result = new JsonObject();
				// 获得数据总行数
				Long count = getCount.result().getResults().get(0).getLong(0);
				// 执行获得数据结果
				jdbcClient.queryWithParams(sp.getBatchSql().get(1), sp.getBatchParams().get(1), getData -> {
					if (getData.succeeded()) {
						List<JsonObject> rows = getData.result().getRows();// 获得数据
						result.put("count", count);
						result.put("data", new JsonArray(rows));
						msg.reply(result);
					} else {
						// TODO 做异常处理
						// LOG.error("执行获得所有Duhua通过分页--获取数据-->失败:" +
						// getCount.cause());
						msg.fail(500, getData.cause().toString());
					}
				});
			} else {
				// TODO 做异常处理
				// LOG.error("执行获得所有Duhua通过分页--获取总行数-->失败:" +
				// getCount.cause());
				msg.fail(500, getCount.cause().toString());
			}
		});
	}
	
	/**
	 * 获得所有Duhua,接收SqlAssist的Json字符串,查询全部可以传入null
	 * 
	 * @param msg
	 */
	public void selectDuhua(Message<String> msg) {
		SqlAssist assist = null;
		if (msg.body() != null) {
			assist = Json.decodeValue(msg.body(), SqlAssist.class);
		}
		SqlAndParams sp = duhuaSQL.selectAll(assist);
		jdbcClient.queryWithParams(sp.getSql(), sp.getParams(), res -> {
			if (res.succeeded()) {
				List<JsonObject> rows = res.result().getRows();
				msg.reply(new JsonArray(rows));
			} else {
				// TODO 做异常处理
				// LOG.error("执行查询所有Duhua-->失败:" + res.cause());
				// msg.fail(500, res.cause().toString());
			}
		});
	}
	
	/**
	 * 获得通过Duhua查询Duhua,可用于登录等
	 * 
	 * @param msg
	 */
	public void selectDuhuaByObj(Message<JsonObject> msg) {
		if (msg.body() != null) {
			SqlAndParams sp = duhuaSQL.selectByObj(msg.body());
			jdbcClient.queryWithParams(sp.getSql(), sp.getParams(), res -> {
				if (res.succeeded()) {
					List<JsonObject> rows = res.result().getRows();
					msg.reply(new JsonArray(rows));
				} else {
					// TODO 做异常处理
					// LOG.error("执行通过Duhua查询Duhua-->失败:" +
					// res.cause());
				}
			});
		} else {
			// TODO 做异常处理
			// LOG.debug("执行通过Duhua查询Duhua-->失败:对象不能为空");
			// msg.fail(412, "对象不能为空");
		}
	}
	
	/**
	 * 通过主键查询出Duhua
	 * 
	 * @param msg
	 */
	public void selectDuhuaById(Message<Object> msg) {
		if (msg.body() != null) {
			SqlAndParams sp = duhuaSQL.selectById(msg.body());
			jdbcClient.queryWithParams(sp.getSql(), sp.getParams(), res -> {
				if (res.succeeded()) {
					JsonObject result = null;
					List<JsonObject> rows = res.result().getRows();
					if (rows.size() > 0) {
						result = rows.get(0);
					}
					msg.reply(result);
				} else {
					// TODO 做异常处理
					// LOG.error("执行通过主键查询Duhua-->失败:" + res.cause());
				}
			});
		} else {
			// TODO 做异常处理
			// LOG.debug("执行通过主键查询Duhua-->失败:id不能为空!");
			// msg.fail(412, "id不能为空!");
		}
	}
	
	/**
	 * 将Duhua保存到数据库
	 * 
	 * @param msg
	 */
	public void insertNonEmptyDuhua(Message<JsonObject> msg) {
		if (msg.body() != null) {
			SqlAndParams sp = duhuaSQL.insertNonEnpty(msg.body());
			jdbcClient.updateWithParams(sp.getSql(), sp.getParams(), res -> {
				if (res.succeeded()) {
					int result = res.result().getUpdated();
					msg.reply(result);
				} else {
					// TODO 做异常处理
					// LOG.error("执行将Duhua保存到数据库-->失败:" + res.cause());
					msg.fail(500, res.cause().toString());
				}
			});
		} else {
			msg.fail(412, "对象不能为空");
		}
	}
	
	/**
	 * 将通过主键更新Duhua
	 * 
	 * @param msg
	 */
	public void updateNonEmptyDuhua(Message<JsonObject> msg) {
		if (msg.body() != null) {
			SqlAndParams sp = duhuaSQL.updateNonEmptyById(msg.body());
			jdbcClient.updateWithParams(sp.getSql(), sp.getParams(), res -> {
				if (res.succeeded()) {
					int result = res.result().getUpdated();
					msg.reply(result);
				} else {
					// TODO 做异常处理
					// LOG.error("执行通过主键更新Duhua-->失败:" + res.cause());
					msg.fail(500, res.cause().toString());
				}
			});
		} else {
			msg.fail(412, "对象不能为空");
		}
	}
	
	/**
	 * 通过主键将Duhua删除
	 * 
	 * @param msg
	 */
	public void deleteDuhuaById(Message<Object> msg) {
		if (msg.body() != null) {
			SqlAndParams sp = duhuaSQL.deleteById(msg.body());
			jdbcClient.updateWithParams(sp.getSql(), sp.getParams(), res -> {
				if (res.succeeded()) {
					int result = res.result().getUpdated();
					msg.reply(result);
				} else {
					// TODO 做异常处理
					// LOG.error("执行通过id删除Duhua-->失败:" + res.cause());
					msg.fail(500, res.cause().toString());
				}
			});
		} else {
			msg.fail(412, "id不能为空");
		}
	}
	
	/**
	 * 批量插入{*className*}到数据库
	 * 
	 * @param msg
	 */
	public void insertDuhuaBatch(Message<JsonArray> msg) {
		if (msg.body() != null) {
			jdbcClient.getConnection(connection -> {
				if (connection.succeeded()) {
					SQLConnection conn = connection.result();
					SqlAndParams sp = duhuaSQL.insertDuhuaBatch(msg.body());
					conn.batchWithParams(sp.getSql(), sp.getBatchParams(), res -> {
						if (res.succeeded()) {
							List<Integer> result = res.result();
							msg.reply(new JsonArray(result));
						} else {
							// TODO 做异常处理
							// LOG.error("执行批量插入数据-->失败:" +
							// res.cause());
							msg.fail(500, res.cause().toString());
						}
					});
				} else {
					// TODO 做异常处理
					// LOG.error("执行批量插入数据-->获取数据库连接-->失败:" +
					// connection.cause());
					msg.fail(500, connection.cause().toString());
				}
			});
		} else {
			msg.fail(412, "对象不能为空");
		}
	}
}
	