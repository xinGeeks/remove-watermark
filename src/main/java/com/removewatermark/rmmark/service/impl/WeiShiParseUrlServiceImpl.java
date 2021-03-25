package com.removewatermark.rmmark.service.impl;

import com.removewatermark.rmmark.bean.RmMarkResponse;
import com.removewatermark.rmmark.common.JsoupUtil;
import com.removewatermark.rmmark.common.ParseCommonUtil;
import com.removewatermark.rmmark.service.VideoParseUrlService;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

/**
 * @author SemperFi
 * @Title: null.java
 * @Package rmmark
 * @Description: 腾讯微视 https://isee.weishi.qq.com/ws/app-pages/share/index.html?wxplay=1%26id=71KCkYvrL1LnCHYzJ%26spid=1669145127375630336%26qua=v1_and_weishi_8.14.0_588_312026001_d%26chid=100081014%26pkg=%26attach=cp_reserves3_1000370011
 * 真实的视频页面的地址其实就是在url后面加上查询字符串 “&from=pc&orifrom=”
 * @date 2021-03-24 22:16
 */
@Service
public class WeiShiParseUrlServiceImpl implements VideoParseUrlService {

    private final static String urlParam = "&from=pc&orifrom=";

    @Override
    public RmMarkResponse parseUrl(String url) throws Exception {
        String realUrl = url + urlParam;

        String get = ParseCommonUtil.okHttpGet(url);
        return null;
    }

}
