# 拉狗网

首先抓取一个网站之前先遵守爬虫规则，拉勾网的robots.txt如下：

```xml
User-agent: Jobuispider
Disallow: /

User-agent: *
Disallow:/*?*
Sitemap:https://www.lagou.com/gongsir-sitemap/gongsir-sitemap.xml
Sitemap:https://www.lagou.com/search-sitemap/search-sitemap.xml
```

对于爬虫源为`Jobuispider`的，不允许爬任何页面，其他的爬虫则不允许爬动态页面。

站点地图也给出了，方面爬虫抓取。

所以这个项目就依据 https://www.lagou.com/gongsir-sitemap/gongsir-sitemap.xml 页面完成爬取


## 格式

首页(https://www.lagou.com/)可以抓取到招聘的标签类别信息，比如Java、C++等岗位(其实就是动态搜索)。

抓取首页的标签后，标签的子链接下的标签页继续投放到抓取队列中。

于是，构造一个IndexHandler用来处理首页的数据。

标签页格式：https://www.lagou.com/zhaopin/{tagName}/{page}

tagName：标签名
page：页码

比如：https://www.lagou.com/zhaopin/Java/15

由此

构造一个PageHandler用于处理当前页面的职位信息
