package cn.longface.toolbox.ui.danm;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.FrameLayout;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

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

    /**
     * 正在显示的弹幕任务
     */
    private List<PopTask> runningPopTasks = new ArrayList<>();

    private LinkedList<DanmDataAdapter.DanmItemHolder<D>> unShowDanm = new LinkedList<>();

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

    public DanmConfig getConfig() {
        return mConfig;
    }

    /* 绑定适配器 */
    public void bindAdapter(DanmDataAdapter<D> adapter) {
        mAdapter = adapter;
        mAdapter.setDanmView(this);
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
    void popMsg(DanmDataAdapter.DanmItemHolder<D> holder) {
        // 获取一个可用的空间
        int availabalLine = getAvailabalLine();
        if (availabalLine >= 0) {
            // 有空间 直接显示
            show(holder , availabalLine);
        } else {
            // 没有空间 , 根据策略显示新的弹幕
            if (mConfig.stratigy == DanmConfig.STRATIGY_IN_TURN) {
                // 顺序策略 添加到待显示消息集合中
                if (!unShowDanm.contains(holder)) {
                    unShowDanm.add(holder);
                }
            } else {
                // 叠加策略 直接显示
                show(holder , -1);
            }
        }
    }

    /**
     * 显示这条弹幕到屏幕上
     * @param holder 控件持有者
     * @param availabalLine 在第几行显示 , 如果是-1就随机一个数
     */
    private void show(DanmDataAdapter.DanmItemHolder holder, int availabalLine) {
        View view = holder.getView();
        if (availabalLine == -1) {
            float randomF = new Random().nextFloat();
            this.addView(view);
            view.setX(getWidth());
            view.setY(getHeight() * (randomF > 0.8f ? randomF - 0.2f : randomF));
        } else {
            mAvailabal[availabalLine] = false;
            this.addView(view);
            view.setX(getWidth());
            view.setY(availabalLine * view.getHeight());
        }
        PopTask popTask = new PopTask(view, availabalLine, mConfig, callback);
        popTask.start();
        runningPopTasks.add(popTask);
    }

    /**
     * 一条弹幕滚动完毕后会调用该回调
     */
    OnFinishCallback callback = new OnFinishCallback() {
        @Override
        public void onFinish(PopTask task , View view, int line) {
            removeView(view);
            runningPopTasks.remove(task);
            if (mAvailabal.length > line && line > 0) {
                mAvailabal[line] = true;
            }
            if (mConfig.stratigy == DanmConfig.STRATIGY_IN_TURN && unShowDanm.size() > 0) {
                popMsg(unShowDanm.remove(0));
            }
        }
    };

    /**
     * 弹幕滚动任务
     */
    public static class PopTask
            extends SimpleAnimatorListener
            implements ValueAnimator.AnimatorUpdateListener {

        View view;
        int line;
        ValueAnimator animator;
        OnFinishCallback callback;

        /**
         * @param view 需要显示出来的View
         * @param line 在第几行显示
         * @param config 配置
         * @param callback 在任务完成后执行该回调
         */
        PopTask(View view , int line , DanmConfig config , OnFinishCallback callback) {
            this.view = view;
            this.line = line;
            this.callback = callback;
            animator = ObjectAnimator.ofFloat(0 , 1);
            animator.setInterpolator(new LinearInterpolator());
            animator.setDuration(config.getAnimDuration());
            animator.addUpdateListener(this);
            animator.addListener(this);
        }

        public void start() {
            animator.start();
        }

        public void clean() {
            animator.end();
        }

        @Override
        public void onAnimationUpdate(ValueAnimator valueAnimator) {

        }

        @Override
        public void onAnimationEnd(Animator animation) {
            super.onAnimationEnd(animation);
            // 在动画播放完成后释放一切
            if (callback != null) {
                callback.onFinish(this , view , line);
                callback = null;
            }
            this.animator.cancel();
            this.animator = null;
            this.view = null;
        }
    }

    /**
     * 单条动画完毕回调
     */
    interface OnFinishCallback {
        void onFinish(PopTask task , View view , int line);
    }

}
