package com.zihua.webspider.handler;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;

public interface ChannelHandler {

    public boolean condition(Page page);

    public void handler(Page page);

    public String nextTarget(Page page);
}
