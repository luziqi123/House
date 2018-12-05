package cn.longface.toolbox.utils.auto_updata;

import android.content.Context;
import android.text.TextUtils;

import cn.longface.toolbox.utils.VersionCompareUtil;


public class AppUpdateHelper {

    private String mDownloadPath;
    private Context mContext;

    public AppUpdateHelper(Context context, String downloadPath) {
        mContext = context.getApplicationContext();
        if (TextUtils.isEmpty(downloadPath)) {
//            mDownloadPath =
        }
        mDownloadPath = downloadPath;
    }

    /**
     * 是否需要更新
     *
     * @return
     */
    public boolean needUpdate(String localVersion, String serverVersion) {
        return new VersionCompareUtil().currentVersionIsLatest(localVersion, serverVersion);
    }

    /**
     * 是否已经下载过新的安装包
     *
     * @return
     */
    public boolean hasNewApk() {
        return true;
    }
}
