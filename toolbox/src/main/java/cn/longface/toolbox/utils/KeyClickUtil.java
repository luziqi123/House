package cn.longface.toolbox.utils;

import android.view.KeyEvent;

/**
 * 双击监测工具
 * Created by luziqi on 2018/8/21.
 */
public class KeyClickUtil {

    private long firstTime;

    protected KeyClickUtil() {
    }

    private volatile static KeyClickUtil instance;

    public static KeyClickUtil getInstance() {
        if (instance == null) {
            synchronized (KeyClickUtil.class) {
                if (instance == null) {
                    instance = new KeyClickUtil();
                }
            }
        }
        return instance;
    }

    /**
     * 在需要监测双击的地方调用
     *
     * @return true 触发了双击 , false 没有触发双击
     */
    public boolean clickDouble() {
        if (System.currentTimeMillis() - firstTime > 2000) {
            firstTime = System.currentTimeMillis();
            return false;
        } else {
            return true;
        }
    }

    /**
     * 仅仅判断是否是点击了返回键
     *
     * @param keyCode
     * @param event
     * @return
     */
    public boolean isOnKeyBack(int keyCode, KeyEvent event) {
        return keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN;
    }
}
