package cn.longface.toolbox.ui.list;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public abstract class SimpleBaseHolder<D> extends RecyclerView.ViewHolder {

    private View mRoot;
    private D mData;
    private int mPosition;
    private ItemClickCallback<D> mItemClickCallback;

    public SimpleBaseHolder(@NonNull View itemView) {
        super(itemView);
        mRoot = itemView;
        initView();
    }

    public void setData(D data, int position) {
        mData = data;
        mPosition = position;
        inflaterData();
    }

    public void setItemClick(ItemClickCallback itemClick) {
        mItemClickCallback = itemClick;
        mRoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mItemClickCallback != null) {
                    mItemClickCallback.onItemClick(mPosition , view , mData);
                }
            }
        });
    }

    public View getRootView() {
        return mRoot;
    }

    /**
     * 初始化View
     * 此时mRoot已经不为空了
     */
    protected abstract void initView();

    /**
     * 设置数据
     * 此时mData已经有数据了
     */
    protected abstract void inflaterData();

}
