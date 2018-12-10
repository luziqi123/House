package cn.longface.toolbox.ui.list;

import android.view.View;

/**
 * Created by luziqi on 2018/1/22.
 */

public interface ItemClickCallback<D> {

    void onItemClick(int position, View view , D data);

}
