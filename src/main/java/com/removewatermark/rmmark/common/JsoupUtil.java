package com.removewatermark.rmmark.common;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author SemperFi
 * @Title: null.java
 * @Package rmmark
 * @Description:
 * @date 2021-03-24 23:07
 */
public class JsoupUtil {

    //从URL加载HTML
    public static Document getDocFromUrl(String url) throws IOException {
        return Jsoup.connect(url).get();
    }

    //从字符串加载HTML
    public static Document getDocFromHTMLString(String htmlStr) throws IOException {
        return Jsoup.parse(htmlStr);
    }

    /**
     * 获取页面文档字串(等待异步JS执行)
     *
     * @param url 页面URL
     * @return HTML String
     * @throws IOException
     */
    public static String getHtmlPageResponse(String url) throws IOException {
        final WebClient webClient = new WebClient(BrowserVersion.CHROME);
        webClient.getOptions().setThrowExceptionOnScriptError(false);//当JS执行出错的时候是否抛出异常
        webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);//当HTTP的状态非200时是否抛出异常
        webClient.getOptions().setActiveXNative(false);
        webClient.getOptions().setCssEnabled(false);//是否启用CSS
        webClient.getOptions().setJavaScriptEnabled(true); //很重要，启用JS
        webClient.setAjaxController(new NicelyResynchronizingAjaxController());//很重要，设置支持AJAX
        webClient.getOptions().setTimeout(30000);//设置“浏览器”的请求超时时间
        webClient.setJavaScriptTimeout(30000);//设置JS执行的超时时间
        HtmlPage page = webClient.getPage(url);
        webClient.waitForBackgroundJavaScript(30000);//该方法阻塞线程

        String result = page.asXml();
        webClient.close();

        return result;
    }
}
