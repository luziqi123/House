package cn.longface.toolbox.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * 弹出的基类
 */
public class SimBaseDialog extends Dialog{

    public SimBaseDialog(@NonNull Context context) {
        super(context);
    }

    public SimBaseDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected SimBaseDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }
}
