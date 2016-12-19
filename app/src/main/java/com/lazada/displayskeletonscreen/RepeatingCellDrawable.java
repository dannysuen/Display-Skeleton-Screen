package com.lazada.displayskeletonscreen;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.graphics.drawable.DrawableWrapper;


public class RepeatingCellDrawable extends DrawableWrapper {
    private final int cellHeight;

    public RepeatingCellDrawable(Drawable dr, int cellHeight) {
        super(dr);
        this.cellHeight = cellHeight;
    }

    @Override
    public void draw(@NonNull Canvas canvas) {
        Drawable drawable = getWrappedDrawable();
        if (drawable == null) {
            return;
        }
        for (int i = 0; i < getCellCount(); i++) {
            drawable.setBounds(getCellRect(i));
            drawable.draw(canvas);
        }
    }

    @NonNull
    private Rect getCellRect(int i) {
        Rect rect = new Rect(getBounds());
        rect.bottom = rect.top + cellHeight * (i + 1);
        rect.top += cellHeight * i;
        return rect;
    }

    private int getCellCount() {
        if (getBounds().height() % cellHeight == 0) {
            return getBounds().height() / cellHeight;
        } else {
            return getBounds().height() / cellHeight + 1;
        }
    }
}