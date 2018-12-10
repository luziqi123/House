package cn.longface.toolbox.ui.danm;

import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * 弹幕数据适配器
 * 主要负责弹幕消息的调度和存储
 * @param <D>
 */
public abstract class DanmDataAdapter<D> {

    private List<D> mDanmData = new ArrayList<>();
    private DanmConfig mConfig;

    public DanmDataAdapter() {
    }

    void setConfig(DanmConfig config) {
        mConfig = config;
    }

    public void addData(List<D> data) {
        mDanmData.addAll(data);
    }

    public void setData(List<D> data) {
        mDanmData.clear();
        mDanmData.addAll(data);
    }

    /**
     * 添加一条消息
     * 根据设置
     * 如果是实时显示 , 则addOneData的优先级最高
     * 如果不是 , 则该消息会加在消息队列的末尾 , 依次显示
     * @param data
     */
    public void addOneData(D data) {
        if (mConfig.realTime) {
            // TODO 马上显示
        } else {
            // TODO 加入消息集合
        }
    }


    /**
     * 有消息被Pop出去的时候调用
     * @param holder 正要显示的ViewHolder
     * @param data 对应的数据对象
     * @param position 第几个
     */
    public abstract void onPop(DanmItemHolder holder , D data , int position);

    /**
     * 下一条消息显示出来的时间
     * @param d
     * @return
     */
    public abstract long getNextTime(D d);





    /**
     * Holder类 , 用于对Item进行处理
     * @param <D>
     */
    public static abstract class DanmItemHolder<D> {

        protected View rootView;

        public DanmItemHolder(View rootView) {
            this.rootView = rootView;
            initView();
        }

        public View getView() {
            return rootView;
        }

        /**
         * 初始化控件
         * 注意使用rootView.findViewById()
         */
        protected abstract void initView();

        /**
         * 当Holder类出现在屏幕上的时候调用
         * @param d
         */
        public abstract void onBind(D d);

    }
}
