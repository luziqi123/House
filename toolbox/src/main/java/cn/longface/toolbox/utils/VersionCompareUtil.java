package cn.longface.toolbox.utils;

/**
 * 用于对比版本号
 *
 * Created by luziqi on 2018/8/17.
 */
public class VersionCompareUtil {

    /**
     * 当前版本是否最新 版本格式为 x.x.x 不限制双方位数
     * @param currentVersion 当前版本
     * @param version 服务器版本
     * @return true 是最新的   false 不是最新的
     */
    public boolean currentVersionIsLatest(String currentVersion , String version) {
        String[] serverV = version.split("\\.");
        String[] localV = currentVersion.split("\\.");
        int length = serverV.length > localV.length ? serverV.length : localV.length;
        for (int i = 0; i < length; i++) {
            String sv = i >= serverV.length ? "0" : serverV[i];
            String lv = i >= localV.length ? "0" : localV[i];
            if (Integer.valueOf(sv) > Integer.valueOf(lv)) {
                return false;
            }else if (Integer.valueOf(sv) == Integer.valueOf(lv)){
                continue;
            }else {
                return true;
            }
        }
        return true;
    }
}
