# 知乎爬虫导航（https://www.zhihu.com）

## 验证是否登陆

地址：https://www.zhihu.com/settings/profile

请求header：
```json
headers = {
    "User-Agent": "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.143 Safari/537.36",
    "Accept": "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8",
    "Host": "www.zhihu.com",
    "Referer": "https://www.zhihu.com/",
    "Origin": "https://www.zhihu.com/",
    "Upgrade-Insecure-Requests": "1",
    "Content-Type": "application/x-www-form-urlencoded; charset=UTF-8",
    "Pragma": "no-cache",
    "Accept-Encoding": "gzip, deflate, br",
    'Connection': 'close'
}
```



