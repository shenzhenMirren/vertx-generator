package com.common;

import java.util.ArrayList;
import java.util.List;

/**
 * AbstractSQL用于生成SQL语句的工具
 * 
 * @author github.shenzhenMirren
 */
public class SqlAssist {
	// 去重
	private String distinct;
	// 自定义排序
	private String order;
	// 数据分页开始行
	private Integer startRow;
	// 每次取多少行数据
	private Integer rowSize;
	// 设置自定义返回列
	private String resultColumn;
	// 条件集
	private List<SqlWhereCondition<?>> condition = null;
	
	/**
	 * 添加查询条件,参数为Assist的内部类whereRequire,推荐使用Assist的静态条件方法添加条件;
	 * 
	 * @param require
	 *            示例:Assist.and_lt("id",10),...
	 */
	public SqlAssist setConditions(SqlWhereCondition<?>... require) {
		if (this.condition == null) {
			this.condition = new ArrayList<SqlWhereCondition<?>>();
		}
		for (int i = 0; i < require.length; i++) {
			if (i == 0 && this.condition.size() == 0) {
				require[i].setRequire(require[i].getRequire().substring(require[i].getRequire().indexOf(" ") + 1));
			}
			this.condition.add(require[i]);
		}
		return this;
	}

	/**
	 * 参数(列名)1 = 参数(条件)2 ;如果表中存在相同列名使用表名.列名,如果不存在相同列名可以直接列名
	 * 
	 * @param column
	 * @param req
	 * @return
	 */
	public static <T> SqlWhereCondition<T> andEq(String column, T req) {
		return new SqlWhereCondition<T>("and " + column + " = ? ", req);
	}

	/**
	 * 参数(列名)1 = 参数(条件)2 ;如果表中存在相同列名使用表名.列名,如果不存在相同列名可以直接列名
	 * 
	 * @param column
	 * @param req
	 * @return
	 */
	public static <T> SqlWhereCondition<T> orEq(String column, T req) {
		return new SqlWhereCondition<T>("or " + column + " = ? ", req);
	}

	/**
	 * 参数(列名)1 <>(不等于) 参数(条件)2 ;如果表中存在相同列名使用表名.列名,如果不存在相同列名可以直接列名
	 * 
	 * @param column
	 * @param req
	 * @return
	 */
	public static <T> SqlWhereCondition<T> andNeq(String column, T req) {
		return new SqlWhereCondition<T>("and " + column + " <> ? ", req);
	}

	/**
	 * 参数(列名)1 <>(不等于) 参数(条件)2 ;如果表中存在相同列名使用表名.列名,如果不存在相同列名可以直接列名
	 * 
	 * @param column
	 * @param req
	 * @return
	 */
	public static <T> SqlWhereCondition<T> orNeq(String column, T req) {
		return new SqlWhereCondition<T>("or " + column + " <> ? ", req);
	}

	/**
	 * 参数(列名)1 < 参数(条件)2 ;如果表中存在相同列名使用表名.列名,如果不存在相同列名可以直接列名
	 * 
	 * @param column
	 * @param req
	 * @return
	 */
	public static <T> SqlWhereCondition<T> andLt(String column, T req) {
		return new SqlWhereCondition<T>("and " + column + " < ? ", req);
	}

	/**
	 * 参数(列名)1 < 参数(条件)2 ;如果表中存在相同列名使用表名.列名,如果不存在相同列名可以直接列名
	 * 
	 * @param column
	 * @param req
	 * @return
	 */
	public static <T> SqlWhereCondition<T> orLt(String column, T req) {
		return new SqlWhereCondition<T>("or " + column + " < ? ", req);
	}

	/**
	 * 参数(列名)1 <= 参数(条件)2 ;如果表中存在相同列名使用表名.列名,如果不存在相同列名可以直接列名
	 * 
	 * @param column
	 * @param req
	 * @return
	 */
	public static <T> SqlWhereCondition<T> andLte(String column, T req) {
		return new SqlWhereCondition<T>("and " + column + " <= ? ", req);
	}

	/**
	 * 参数(列名)1 <= 参数(条件)2 ;如果表中存在相同列名使用表名.列名,如果不存在相同列名可以直接列名
	 * 
	 * @param column
	 * @param req
	 * @return
	 */
	public static <T> SqlWhereCondition<T> orLte(String column, T req) {
		return new SqlWhereCondition<T>("or " + column + " <= ? ", req);
	}

	/**
	 * 参数(列名)1 > 参数(条件)2 ;如果表中存在相同列名使用表名.列名,如果不存在相同列名可以直接列名
	 * 
	 * @param column
	 * @param req
	 * @return
	 */
	public static <T> SqlWhereCondition<T> andGt(String column, T req) {
		return new SqlWhereCondition<T>("and " + column + " > ? ", req);
	}

	/**
	 * 参数(列名)1 > 参数(条件)2 ;如果表中存在相同列名使用表名.列名,如果不存在相同列名可以直接列名
	 * 
	 * @param column
	 * @param req
	 * @return
	 */
	public static <T> SqlWhereCondition<T> orGt(String column, T req) {
		return new SqlWhereCondition<T>("or " + column + " > ? ", req);
	}

	/**
	 * 参数(列名)1 >= 参数(条件)2 ;如果表中存在相同列名使用表名.列名,如果不存在相同列名可以直接列名
	 * 
	 * @param column
	 * @param req
	 * @return
	 */
	public static <T> SqlWhereCondition<T> andGte(String column, T req) {
		return new SqlWhereCondition<T>("and " + column + " >= ? ", req);
	}

	/**
	 * 参数(列名)1 >= 参数(条件)2 ;如果表中存在相同列名使用表名.列名,如果不存在相同列名可以直接列名
	 * 
	 * @param column
	 * @param req
	 * @return
	 */
	public static <T> SqlWhereCondition<T> orGte(String column, T req) {
		return new SqlWhereCondition<T>("or " + column + " >= ? ", req);
	}

	/**
	 * 参数(列名)1 like '参数(条件)2' ;如果表中存在相同列名使用表名.列名,如果不存在相同列名可以直接列名
	 * 
	 * @param column
	 * @param req
	 * @return
	 */
	public static <T> SqlWhereCondition<T> andLike(String column, T req) {
		return new SqlWhereCondition<T>("and " + column + " like ? ", req);
	}

	/**
	 * 参数(列名)1 like '参数(条件)2' ;如果表中存在相同列名,使用表名.列名,如果不存在相同列名可以直接列名
	 * 
	 * @param column
	 * @param req
	 * @return
	 */
	public static <T> SqlWhereCondition<T> orLike(String column, T req) {
		return new SqlWhereCondition<T>("or " + column + " like ? ", req);
	}

	/**
	 * 自定义查询条件 :<br>
	 * 查询示例Assist.customRequire("[and/or] id in(?,?,?)",1,2,3);
	 * 
	 * @param prefix
	 * @param value
	 * @param suffix
	 * @return
	 */
	public static SqlWhereCondition<?> customCondition(String prefix, Object... value) {
		return new SqlWhereCondition<Object>(prefix, value);
	}

	/**
	 * 获得一个排序对象,将(列名)参数1 按 参数2排序(true=ASC/false=DESC)<br>
	 * ;如果表中存在相同列名使用表名.列名,如果不存在相同列名可以直接列名<br>
	 * 
	 * @param column
	 *            列名
	 * @param mode
	 *            排序类型,true=asc,false=desc
	 * @return
	 */
	public static String order(String column, boolean mode) {
		if (mode) {
			return column + " asc ";
		} else {
			return column + " desc ";
		}
	}

	/**
	 * 设置排序,通过Assist.order(列名,排序方式)<br>
	 * 示例:assist.setOrder(Assist.order("id",true))//将id正序排序
	 * 
	 * @param column
	 * @param mode
	 */
	public SqlAssist setOrders(String... order) {
		if (order == null || order.length == 0) {
			this.order = null;
			return this;
		}
		if (this.order == null) {
			this.order = " order by ";
		}
		StringBuffer buffer = new StringBuffer();
		for (String od : order) {
			buffer.append(od);
		}
		this.order += buffer;
		return this;
	}

	/**
	 * 获得是否去重
	 * 
	 * @return
	 */
	public String getDistinct() {
		return distinct;
	}

	/**
	 * 设置是否去重,true去除
	 * 
	 * @param distinct
	 */
	public SqlAssist setDistincts(boolean distinct) {
		if (distinct) {
			this.distinct = "distinct";
			return this;
		}
		return this;
	}

	/**
	 * 获得排序
	 * 
	 * @return
	 */
	public String getOrder() {
		return order;
	}

	/**
	 * 获得开始分页行
	 * 
	 * @return
	 */
	public Integer getStartRow() {
		return startRow;
	}

	/**
	 * 设置从第几行开始取数据
	 * 
	 * @param startRow
	 */
	public SqlAssist setStartRow(Integer startRow) {
		this.startRow = startRow;
		return this;
	}

	/**
	 * 获得每次取多少行数据
	 * 
	 * @return
	 */
	public Integer getRowSize() {
		return rowSize;
	}

	/**
	 * 设置每次取多少很数据
	 * 
	 * @param rowSize
	 */
	public SqlAssist setRowSize(Integer rowSize) {
		this.rowSize = rowSize;
		return this;
	}

	/**
	 * 获得返回指定列
	 * 
	 * @return
	 */
	public String getResultColumn() {
		return resultColumn;
	}

	/**
	 * 设置返回指定列多个列以,逗号隔开;需要特别注意的是返回列需要起别名,别名以mapper里面的resultMap的column为准;
	 * 一般是类名加上属性的顺序号,
	 * 
	 * @return
	 */
	public SqlAssist setResultColumn(String resultColumn) {
		this.resultColumn = resultColumn;
		return this;
	}

	/**
	 * 获得条件集
	 * 
	 * @return
	 */
	public List<SqlWhereCondition<?>> getCondition() {
		return condition;
	}

	public SqlAssist() {
		super();
	}

	/**
	 * 该构造方法用于使用Assist的静态条件方法,动态添加条件
	 * 
	 * @param require
	 *            示例:Assist.lt("A.ID",10)...
	 */
	public SqlAssist(SqlWhereCondition<?>... require) {
		this.setConditions(require);
	}
}
