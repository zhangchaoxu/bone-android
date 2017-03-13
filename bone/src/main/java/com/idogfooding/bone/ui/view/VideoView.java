package com.idogfooding.bone.ui.view;

import android.content.Context;
import android.util.AttributeSet;

/**
 * VideoView
 *
 * @author Charles
 */
public class VideoView extends android.widget.VideoView {

    private int measuredWidth;
    private int measuredHeight;

    public VideoView(Context context) {
        super(context);
    }

    public VideoView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public VideoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        measuredWidth = getDefaultSize(measuredWidth, widthMeasureSpec);
        measuredHeight = getDefaultSize(measuredHeight, heightMeasureSpec);
        setMeasuredDimension(measuredWidth, measuredHeight);
    }
}
