package cn.longface.toolbox.ui.danm;

import java.util.Random;

/**
 * 弹幕的设置类
 */
public class DanmConfig {

    /**
     * 弹幕滚动的速度
     */
    public int animDuration = 5000;

    /**
     * 滚动速度是否匀速
     */
    public boolean uniform = true;

    /**
     * 弹幕的行数
     */
    public int lineNumber = 4;

    /**
     * 是否实时显示弹幕
     * 如果是实时显示 , 则addOneData的优先级最高 , 调用后会马上显示
     * 如果不是 , 则该消息会加在消息队列的末尾 , 依次显示
     */
    public boolean realTime = true;

    /**
     * 在弹幕特别多的情况下使用哪种形式显示更多的弹幕
     * STRATIGY_OVERLAP = 重叠显示
     * STRATIGY_IN_TURN = 仍旧保持依次显示
     */
    public int stratigy = 1;
    // 重叠显示
    public static int STRATIGY_OVERLAP = 0;
    // 仍旧保持依次显示
    public static int STRATIGY_IN_TURN = 1;

    public int getAnimDuration() {
        if (uniform) {
            return animDuration;
        } else {
            return animDuration + new Random().nextInt(1000);
        }
    }

}
