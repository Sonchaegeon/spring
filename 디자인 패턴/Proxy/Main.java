package Proxy;

import Proxy.src.Browser;
import Proxy.src.BrowserProxy;
import Proxy.src.IBrowser;

public class Main {
    public static void main(String[] args) {
//        Browser browser = new Browser("www.naver.com");
//        browser.show();
//        browser.show();
//        browser.show();
//        browser.show();
//        browser.show();

        IBrowser browser = new BrowserProxy("www.naver.com");
        browser.show();
        browser.show();
        browser.show();
        browser.show();
    }
}
