package cn.longface.toolbox.ui.text;

import android.text.Html;
import android.text.Spanned;

/**
 * Created by R on 2015/8/6.
 */
public class TextUtil {

    /**
     * 获取一个Spanned
     * @param strs
     * @return
     */
    public static Spanned getHtmlString(Need ...strs){
        if (strs == null || strs.length == 0) {
            return null;
        }
        String str = "";
        for (int i = 0; i < strs.length; i++) {
            Need need = strs[i];
            String s = "<font color=\"" + need.color + "\">" + need.str +"</font>";
            if (need.big > 0) {
                for (int i1 = 1; i1 <= need.big; i1++) {
                    s = "<big>" + s + "</big>";
                }
            }
            str += s;
        }
        return Html.fromHtml(str);
    }

}
