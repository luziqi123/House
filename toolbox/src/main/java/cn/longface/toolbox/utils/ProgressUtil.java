package cn.longface.toolbox.utils;

/**
 * 用于计算进度
 *
 * 在需要计算进度的地方创建一个实例
 * 创建的时候传入最大值 如100
 * 通过updateProgress()方法更新进度 0.0f- 1.0f
 * Created by luziqi on 2018/8/16.
 */
public class ProgressUtil {

    private float mMaxProgress;

    private float currentProgressF = 0.0f;

    public ProgressUtil(float maxProgress) {
        mMaxProgress = maxProgress;
    }

    /**
     * 通过外部更新进度
     * @param currentProgress 当前进度
     * @return 0 - 1的进度
     */
    public float updateProgress(float currentProgress) {
        if (mMaxProgress == 0) {
            return 0.0f;
        } else {
            currentProgressF = currentProgress / mMaxProgress;
            currentProgressF = currentProgressF <= 0.0f ? 0.0f : currentProgressF;
            currentProgressF = currentProgressF >= 1.0f ? 1.0f : currentProgressF;
            return currentProgressF;
        }
    }

    /**
     * 只获取当前进度
     * @return
     */
    public float getCurrentPorgress() {
        return currentProgressF;
    }

}
