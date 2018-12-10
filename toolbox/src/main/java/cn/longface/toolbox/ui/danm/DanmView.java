package cn.longface.toolbox.ui.danm;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.FrameLayout;

import cn.longface.toolbox.ui.SimpleAnimatorListener;

/**
 * 负责显示弹幕
 * 由Adapter分发消息
 */
public class DanmView<D> extends FrameLayout{

    /**
     * 相关配置
     */
    private DanmConfig mConfig;

    /**
     * 适配器 , 负责调度弹幕的显示和管理弹幕的内容
     */
    private DanmDataAdapter<D> mAdapter;

    /**
     * 行状态数组 , 一个元素代表一行 , 可用的为true , 不可用的为false
     */
    private boolean[] mAvailabal;

    public DanmView(@NonNull Context context) {
        this(context , null);
    }

    public DanmView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs , 0);
    }

    public DanmView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    /* 初始化 */
    private void init() {
        setConfig(new DanmConfig());
        mAvailabal = new boolean[mConfig.lineNumber];
    }

    /* 设置配置 */
    public void setConfig(DanmConfig config) {
        mConfig = config;
    }

    /* 绑定适配器 */
    public void bindAdapter(DanmDataAdapter<D> adapter) {
        mAdapter = adapter;
    }

    /* 获取可用行 */
    private int getAvailabalLine() {
        for (int i = 0; i < mAvailabal.length; i++) {
            if (mAvailabal[i]) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 显示一条弹幕
     * 这个方法由适配器调用
     * @param holder
     */
    void popMsg(DanmDataAdapter.DanmItemHolder holder) {
        // 获取一个可用的空间
        int availabalLine = getAvailabalLine();
        if (availabalLine >= 0) {
            // 有空间 直接显示
        } else {
            // 没有空间 , 根据策略显示新的弹幕
            if (mConfig.stratigy == DanmConfig.STRATIGY_IN_TURN) {
                // 顺序策略 添加到待显示消息集合中
            } else {
                // 叠加策略 直接显示
            }
        }
    }

    private void show(DanmDataAdapter.DanmItemHolder holder , int availabalLine) {
        mAvailabal[availabalLine] = false;
        View view = holder.getView();
        this.addView(view);
        view.setX(getWidth());
        view.setY(availabalLine * view.getHeight());
        view.animate()
                .translationX(-view.getWidth())
                .setInterpolator(new LinearInterpolator())
                .setDuration(mRandom.nextInt(animDuration[1] - animDuration[0]) +
                        animDuration[0] +
                        msgStr.length() * 300)
                .setUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator valueAnimator) {
                        
                    }
                })
                .start();
    }
}
