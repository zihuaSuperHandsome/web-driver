# Crawler
通用爬虫，自带持久化功能，自动建表，只需要配置部分参数即可爬相同架构下的网站。

# 技术选型

Beatl模板引擎

HuTool工具包

Googole Guava工具包

WebMagic爬虫框架

JDK 1.8

SpringBoot 2.X

MyBatis-Plus持久层

lombok

# 开发日志

```java
- 2020年2月2日 实现了对@TableName和@TableId注解的规范化生成。
- 2020年2月5日 实现了@DatabaseName注解，作用在为每一个实体对象可以选取不同的数据源生成，如果不指定，则会选取配置中的数据源
- 2020年2月6日00:40:52 对@FielName注解进行实现，可以对数据库字段进行标识，可标识的参数有：字段长度、字段注释、是否是主键。
    允许和@TableId一起作用，也可以单独作用，也可以不作用。如果不使用用，则该字段信息均为缺省值。
    @FieldName允许字段自定义长度、自定义注释，但主键只允许有一个。
- 2020年2月6日01:45:20 表扫描生成模式实现完毕
- 2020年2月6日02:48:54 自定义表注释
- 2020年2月7日20:02:52 如果length指定为0，则生成后的数据类型会是TEXT。

## 待完成(不分先后)：

1. 完善Prop类。
2. 如何处理Handler之间的通信问题？(redis、设计模式？)
3. 如何使用外部配置文件？并且是否可以尽量简化到只用application.yml或application.properties不需要用户额外配置config.properties。
4. 在表生成工具中，关于java字段类型和数据库类型的转换，希望可以更加灵活。 
5. 实现登陆抓取
6. 代码生成工具(自动生成Mapper、IService、ServiceImpl、Handler)
```

## 2020年2月8日19:03:39

重构代码，模块化开发。

项目名取为`web-driver`，`webdriver-core`模块是主代码，`spider-test`是使用主代码的一个案例。

运行方法：

导入IDEA，运行spider-test模块下的Application，爬虫便会自动抓取某个小说网的内容，爬取如下：

![image.png](https://iblog-zihua.oss-cn-beijing.aliyuncs.com/image_1581163719827.png?x-oss-process=style/iBlog)

小说网占地面积略大一点，目测整个站2W本小说，25GB左右。

# 自动生成说明

## 简易使用
调用 tableUtils.tableCreateByClass 即可，例如：

```java
tableUtils.tableCreateByClass(VideoBean.class);
```
## 注意

1. 必须要使用@TableName指定表名字段，表明可选，如不指定表明则默认为Bean的UnderScore格式。
2. 必须要使用@TableId指定主键Id字段，字段名可选，如不指定则默认为字段名的UnderScore格式，目前仅支持整形（int,long...)。
3. 所有的字段类型必须使用包装类型而不能使用基本数据类型和其他引用类型，例如：int，bit，User，Bean。


# 使用lombok实现链式调用

##  @RequiredArgsConstructor
     
## @Builder

在bean上使用`@Builder`注解即可开启链式调用。

```java
User user = User.toBuilder().name("zhangsan").age(18).build();
```

### 坑点

使用@Builder注解后需要手动加上无参构造方法和全参构造方法

所以，这里推荐与`@AllArgsConstructor`和`@NoArgsConstructor`一并使用，自动生成全参构造方法和无参构造方法。

# 构建docker并放至阿里云运行

## Dockerfile构建

1. Dockerfile编写

```shell script
FROM java:8
ENV LANG C.UTF-8
ADD  javdb4-1.0-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-Dfile.encoding=utf-8","-jar","/app.jar"]
```

2. 打包 

```shell script
docker build -t spider .
```


3. 运行
```shell script
docker run -d -p 8585:8585 spider
```

## 编码问题

https://blog.csdn.net/gaoqiwang/article/details/46550931

因为一个很蠢的错误，找bug找了半天，后来经过多方测试发现是一个最基本的错误——没有加编码格式。

一开始我认为是linux端编码环境的问题，测试无果后认为是maven打包产生的编码问题，数据库编码也检查了没有异样。

最终发现是jdbc连接层的问题，我忽略的数据源的创建阶段。

举个例子，我现在的想要连接的数据库是utf8，连接者（客户端）的电脑是utf-8，连接层通过数据库连接池创建数据源（底层实现就是JDBC）的过程使用的是默认的ISO8859-1（这里我假设不指定characterEncoding参数）我们在客户端发送`中文`这个字符集，客户端将发送一串utf-8格式的二进制编码给连接层，连接层会以ISO8859-1发送给数据库。ISO8859-1不包含中文，连接层会将能解析的部分解析，不能解析的变成乱码发给数据库。而数据库得到这串文本的时候已经变成了乱码，存进数据库里还会是乱码

# 封装的爬虫框架

## 我想要实如何使用

在设计这个框架的模式之前,得思考想要别人如何使用我的框架?

由于我已经习惯了链式类的语法,所以我希望是类似与builder的方式来实例化和使用,例如:Spider.create().run() 这般。

我还希望能有一个处理程序类,这个类有2个方法`boolean condition(Page page);`用来判断是否需要进行处理。`void handler(Page page);`用来处理,比如持久化数据。

我这样设计的目的是因为，进入一个网站的入口页面后，一定会有一个列表页和一个详情页，而列表页和详情页一定会有不同的格局，
比如列表页可能多了某个栅栏div用来存放列表，详情页可能就没有这个div，根据这种情况我便可以制定不同的处理程序，
如果找到了列表的某个div，那么就交给列表的处理程序来做，找到详情页的div就交给详情页的处理程序来做。

于是，我定义了一个接口类`ChannelHandler`，只需要创建一个Handler实现ChannelHandler的两个方法即可。

```java
Grasp.create().addListen(new ChannelHandler1()).addListen(new ChannelHandler2()).thread(5).run();
```

// 1. 实现于ChannelHandler的handler方法
        // 2. Grasp.addListen(new SimpleChannelHandler()).run();
        ChannelHandler handler = new SimpleChannelHandler();

        // 设置了一个首页处理器的情况下
//        Grasp.create().addListen(handler).addUrl("https://www.bilibili.com/").run();

        // 一个处理器都没有设置
        Grasp.create(handler).addUrl("https://www.bilibili.com/").run();

## 爬虫功能记录
### 2020年2月5日

日志收集如下，某站点：

```java
抓取耗时：1小时54分46秒
抓取页面数量：422
抓取视频数量：23464
抓取预览视频数量：23464
抓取预览图数量：213628
抓取封面数量：23464
抓取磁力数量：85455
丢失页面：14
失败率：0.1%
```

# Prop配置类

由于使用系统的Properties采用默认的ISO编码，无法读写中文，于是我自己手动封装一个Prop类兼容于Properties。
目前由于时间紧迫，只实现了部分功能，往后慢慢实现。


# Java基础
abstract:

如果不加上抽象方法的话，A extends B，A可以不用重写B的一些方法，如果加上abstract则A必须重写B的所有方法，被abstract标记的类可以不用实现方法。

代理：`ProxyUtil.proxy(handle, TimeIntervalAspect.class);`

mvn clean install -Dmaven.test.skip=true


# 正则语法

`?=`,`?!`,`?<=`,`?<!`，用于限定它前后的表达式，本身没有作用。

?=()  匹配后面有且不包含
?!()  匹配后面没有且不包含
()?<= 匹配前面有且不包含
()?<! 匹配前面没有且不包含


比如，一个文本 "aaaabccccd" 我想匹配b-d中间的内容。

b.*d 匹配结果为 bccccd

如果我想匹配b-d中间的内容但是不包含b和d。

(?<=b).*(?=d)

如何理解？

我想匹配的文本是 (b)cccc(d) 不包含的地方我打了括号，问题在于如何去掉括号里面的元素。

后面是 ?=和?! 前面是 ?<=和?<!
