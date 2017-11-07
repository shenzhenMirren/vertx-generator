# 基本介绍
官方QQ交流群号:99135252;<br/>
vertx-generator是一款基于javafx8开发的一款图形界面的vert.x逆向工程;<br/>
该工具支持Oracle , SqlServer , MySQL , PostgreSql数据库的逆向生成;<br/>
生成才用模板的方式,用户自定义模板,工具根据模板进行生成<br/>
# 软件使用说明
[vertx-generator的使用帮助文档](https://github.com/shenzhenMirren/vertx-generator-doc/)<br/>

# 工具主页<br/>
![index](https://raw.githubusercontent.com/shenzhenMirren/vertx-generator-doc/master/resource/images/index.png)

# 基本常用功能：<br/>
<ol>
<li>生成实体类(可以自定义：get/set,有参无参构造方法,自定义类型与属性,序列化等);</li>
<li>生成dao层接口(查询全部信息，通过ID查询信息,批量插入数据,插入全部属性,插入不为空的属性,通过ID更新全部属性,通过ID更新不为空的属性,通过Assist更新全部属性,通过Assist更新不为空的属性,通过ID删除信息,通过Assist删除信息);</li>
<li>生成Mapper映射文件(dao层接口SQL语句,支持生成3表关联(比如：A表关联B表,B表关联C表,一次便可获得3张表的数据),支持主键策略;&lt;如果选择创建Assist支持分页,去重,排序,无注入动态查询等&gt;);</li>
<li>生成service层接口(与dao接口一致)可选项;</li>
<li>生成service层实现类(实现service层接口)可选项;</li>
<li>生成查询工具Assist(Assist为CMEU特别定制的查询工具,使用该工具一切操作都变得超简单,比如：分页通过Assist只需要设置2个参数就可以实现比如参数1=10,参数2=5,查询出来就是第10行到15行的数据,同时也可以防注入动态添加查询添加,去重,排序,自定义返回列等)可选项;</li>
<li>生成mybatis配置文件(mybatis的主配置文件,系统会自动识别使用的数据库，并创建其连接,同时更新mapper映射文件的资源路径)可选项;</li>
<li>生成mybatisUtil(用于获得SqlSession等操作,当与mybatis配置文件一同创建时系统会自动识别配置文件路径并设置)可选项;</li>
<li>更新现有配置文件的mapper映射文件的资源路径(当生成新的信息时自动更新mybatis配置文件的Mapper映射资源路径)可选项;</li>
</ol>

  


