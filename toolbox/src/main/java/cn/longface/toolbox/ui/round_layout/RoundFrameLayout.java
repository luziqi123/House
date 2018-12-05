package cn.longface.toolbox.ui.round_layout;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import cn.longface.toolbox.R;


/**
 * 圆角的FrameLayout
 * Created by luziqi on 2018/4/11.
 */
public class RoundFrameLayout extends FrameLayout{

    private Path mPath;
    private RectF mRect;

    private float leftTopRound, rightTopRound, leftBottomRound, rightBottomRound;

    public RoundFrameLayout(@NonNull Context context) {
        this(context, null);
    }

    public RoundFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RoundFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPath = new Path();
        mRect = new RectF();
        TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.RoundLayout);
        float round = ta.getDimension(R.styleable.RoundLayout_round, 20);
        leftTopRound = ta.getDimension(R.styleable.RoundLayout_left_top_round, round);
        rightTopRound = ta.getDimension(R.styleable.RoundLayout_right_top_round, round);
        leftBottomRound = ta.getDimension(R.styleable.RoundLayout_left_bottom_round, round);
        rightBottomRound = ta.getDimension(R.styleable.RoundLayout_right_bottom_round, round);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mPath.reset();
        mRect.set(0, 0, w, h);
        float[] radiusArray = {
                leftTopRound, leftTopRound,
                rightTopRound, rightTopRound,
                leftBottomRound, leftBottomRound,
                rightBottomRound, rightBottomRound};
        mPath.addRoundRect(mRect, radiusArray, Path.Direction.CW);
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        canvas.save();
        canvas.clipPath(mPath);
        super.dispatchDraw(canvas);
        canvas.restore();
    }

    public float getLeftTopRound() {
        return leftTopRound;
    }

    public RoundFrameLayout setLeftTopRound(float leftTopRound) {
        this.leftTopRound = leftTopRound;
        return this;
    }

    public float getRightTopRound() {
        return rightTopRound;
    }

    public RoundFrameLayout setRightTopRound(float rightTopRound) {
        this.rightTopRound = rightTopRound;
        return this;
    }

    public float getLeftBottomRound() {
        return leftBottomRound;
    }

    public RoundFrameLayout setLeftBottomRound(float leftBottomRound) {
        this.leftBottomRound = leftBottomRound;
        return this;
    }

    public float getRightBottomRound() {
        return rightBottomRound;
    }

    public RoundFrameLayout setRightBottomRound(float rightBottomRound) {
        this.rightBottomRound = rightBottomRound;
        return this;
    }
}
