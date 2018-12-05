package cn.longface.toolbox.utils;

import java.util.Collection;

/**
 * 监测类
 * Created by luziqi on 2018/1/21.
 */
public class Check {

    /**
     * 监测对象是否为空或者没有值
     * @param value 对象 / 字符串 / 数组 , 如果传入多个, 有一个没有通过就不再监测之后的了
     * @return true 监测通过     false 空或者没有值
     */
    public static boolean isPass(Object...value) {
        for (Object o : value) {
            if (o == null) {
                return false;
            }
            if (o instanceof String) {
                if ("".equals(o)) {
                    return false;
                }
            }
            if (o instanceof Collection) {
                if (((Collection) o).size() <= 0) {
                    return false;
                }
            }
        }
        return true;
    }
}
