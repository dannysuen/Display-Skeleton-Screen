package com.lazada.displayskeletonscreen;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;

public class PlaceholderView extends View {

    private Paint mPaint;
    private Context mContext;

    private float mLeftPaddingPixels;
    private float mTopAndBottomPixels;

    private float mRightMarginPixels;

    private float mRectWidthPixels;
    private float mRectHeightPixels;

    private float mSecondaryRectWidthPixels;

    private float mItemHeightPixels;

    private float mDividerHeightPixels;

    private int mWidth;
    private int mHeight;

    

    Shader mShader = new LinearGradient(0, 0, 0, 40, Color.WHITE, Color.BLACK, Shader.TileMode.CLAMP);

    public PlaceholderView(Context context) {
        this(context, null);
    }

    public PlaceholderView(Context context, AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public PlaceholderView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mContext = context;

        init();
    }

    private void init() {
        mPaint = new Paint();

        mPaint.setColor(mContext.getResources().getColor(R.color.grey));

        mLeftPaddingPixels = mContext.getResources().getDimensionPixelSize(R.dimen.placeholder_left_padding);
        mTopAndBottomPixels = mContext.getResources().getDimensionPixelSize(R.dimen.placeholder_top_padding);

        mRectWidthPixels = getResources().getDimensionPixelSize(R.dimen.rect_width);
        mRectHeightPixels = mContext.getResources().getDimensionPixelSize(R.dimen.rect_height);

        mSecondaryRectWidthPixels = getResources().getDimensionPixelSize(R.dimen.secondary_rect_width);

        mItemHeightPixels = getResources().getDimensionPixelSize(R.dimen.item_height);

        mRightMarginPixels = getResources().getDimensionPixelSize(R.dimen.placeholder_right_margin);

        mDividerHeightPixels = getResources().getDimensionPixelSize(R.dimen.divider_height);
    }

    private void setUpPaint() {

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mWidth = getWidth();
        mHeight = getHeight();

        int itemCount = (int) (mHeight / mItemHeightPixels);

        for (int i = 0; i < itemCount; i++) {
            drawItemPlaceholder(canvas, i * mItemHeightPixels);

            drawItemDivider(canvas, i * mItemHeightPixels);
        }
    }

    private void drawItemPlaceholder(Canvas canvas, float startingHeight) {
        float top = startingHeight + mTopAndBottomPixels;
        float bottom = startingHeight + mRectHeightPixels + mTopAndBottomPixels;
        float right = mRectWidthPixels + mLeftPaddingPixels;
        float left = mLeftPaddingPixels;

        Shader primaryShader = new LinearGradient(left, top, right, top,
                getResources().getColor(R.color.grey_start),
                getResources().getColor(R.color.grey_end),
                Shader.TileMode.CLAMP);
        Paint primaryPaint = new Paint();
        primaryPaint.setShader(primaryShader);



        canvas.drawRect(mLeftPaddingPixels, top, mRectWidthPixels + mLeftPaddingPixels, bottom, primaryPaint);

        float secondaryLeft = mWidth - mRightMarginPixels - mSecondaryRectWidthPixels;

        canvas.drawRect(secondaryLeft, top, secondaryLeft + mSecondaryRectWidthPixels, bottom, mPaint);
    }

    private void drawItemDivider(Canvas canvas, float startingHeight) {
        canvas.drawRect(0, startingHeight + mItemHeightPixels - mDividerHeightPixels,
                mWidth, startingHeight + mItemHeightPixels, mPaint);
    }
}
