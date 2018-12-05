package cn.longface.toolbox.ui.text;

/**
 * 你需要这段文字是什么颜色 大小多少
 * Created by Administrator on 2016/11/10.
 */
public class Need {

    /**
     * 放大的倍数 1为放大一次  2是放大2次 0为正常 不放大
     */
    public int big = 0;

    public String str = "";

    public String color = "#ffffff";

    public Need() {
    }

    public Need(String str, int big, String color) {
        this.str = str;
        this.big = big;
        this.color = color;
    }
}
