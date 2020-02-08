package com.zihua.webspider.handler;

import us.codecraft.webmagic.Page;

public interface ChannelHandler {

    public boolean condition(Page page);

    public void handler(Page page);

    public String nextTarget(Page page);
}
