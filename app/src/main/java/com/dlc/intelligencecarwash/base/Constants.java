package com.dlc.intelligencecarwash.base;

/**
 * Created by YoungeTao on 2017/3/22.
 */

public class Constants {

    /* 请求码 */
    public interface REQUESTCODE{
        int REQUESTZXING = 1;
        int SEARCHLOCATION = 2;
    }

    /* 区分底部刷新和item */
    public interface ITEMTYPE{
        int FOOTER = 0;
        int ITEM = 1;
    }
}
