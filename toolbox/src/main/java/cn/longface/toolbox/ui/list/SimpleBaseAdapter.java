package cn.longface.toolbox.ui.list;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by luziqi on 2017/12/23.
 */

public abstract class SimpleBaseAdapter<VH extends SimpleBaseHolder<D> , D> extends
        RecyclerView.Adapter<VH> {

    protected Context mContext;

    protected ArrayList<D> mList;

    protected ItemClickCallback mOnItemClickCall;

    public SimpleBaseAdapter(Context context) {
        mContext = context;
        mList = new ArrayList<>();
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(mContext).inflate(getLayoutRec(viewType), parent, false);
        return getViewHolder(inflate , viewType);
    }

    @Override
    public void onBindViewHolder(@NonNull VH vh, int i) {
        if (mList != null && mList.size() > i) {
            vh.setData(mList.get(i) , i);
        }
        if (mOnItemClickCall != null) {
            vh.setItemClick(mOnItemClickCall);
        }
    }

    @Override
    public int getItemCount() {
        return mList.size() + footOrHead();
    }

    /**
     * 设置Item点击事件
     * @param onItemClickCall
     */
    public void setItemClickCall(ItemClickCallback<D> onItemClickCall) {
        mOnItemClickCall = onItemClickCall;
    }

    /**
     * 是否添加除数据元素外其他多余的item
     * 默认为0
     * @return
     */
    public int footOrHead() {
        return 0;
    }

    public void setData(List<D> list) {
        mList.clear();
        mList.addAll(list);
        notifyDataSetChanged();
    }

    public void addDataOnFirst(List<D> list) {
        mList.addAll(0 , list);
        notifyDataSetChanged();
    }

    public void addDataOnLast(List<D> list) {
        mList.addAll(list);
        notifyDataSetChanged();
    }

    protected abstract int getLayoutRec(int viewType);

    protected abstract VH getViewHolder(View inflate, int viewType);

}
