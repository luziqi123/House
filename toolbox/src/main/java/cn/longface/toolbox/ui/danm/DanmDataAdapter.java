package cn.longface.toolbox.ui.danm;

import android.view.View;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 弹幕数据适配器
 * 主要负责弹幕消息的调度和存储
 * @param <D>
 */
public abstract class DanmDataAdapter<D> {

    /**
     * 需要显示的弹幕数据集合
     * 如果是实时显示的话这个集合用不到
     */
    private List<D> mDanmData = new ArrayList<>();

    /**
     * Holder的缓存
     */
    private LinkedList<DanmItemHolder<D>> mDanmHolderCache = new LinkedList<>();

    /**
     * 弹幕显示控件
     */
    private DanmView<D> mDanmView;

    void setDanmView(DanmView<D> danmView) {
        mDanmView = danmView;
    }

    /**
     * 添加一条消息
     * 根据设置
     * 如果是实时显示 , 则addOneData的优先级最高
     * 如果不是 , 则该消息会加在消息队列的末尾 , 依次显示
     * @param data
     */
    public void addOneData(D data) {
        DanmItemHolder<D> holder = getOrCreateHolder(data);
        holder.onBind(data);
        mDanmView.popMsg(holder);
    }

    DanmItemHolder<D> getOrCreateHolder(D data) {
        if (mDanmHolderCache.size() > 0) {
            return mDanmHolderCache.remove();
        } else {
            return createHolder(data);
        }
    }

    /**
     * 创建DanmHolder的时候调用
     * @param data
     */
    protected abstract DanmItemHolder<D> createHolder(D data);

    /**
     * 有消息被Pop出去的时候调用
     * @param holder 正要显示的ViewHolder
     * @param data 对应的数据对象
     */
//    public abstract void onPop(DanmItemHolder holder , D data);

    /**
     * 下一条消息显示出来的时间
     * @param d
     * @return
     */
//    public abstract long getNextTime(D d);





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
