package com.kevin.scalehelper;

import android.app.Activity;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * 版权所有：XXX有限公司
 *
 * ScaleHelper
 *
 * @author zhou.wenkai ,Created on 2016-6-14 11:17:54
 * 		   Major Function：<b>布局适配帮助类</b>
 *
 *         注:如果您修改了本类请填写以下内容作为记录，如非本人操作劳烦通知，谢谢！！！
 * @author mender，Modified Date Modify Content:
 */
public class ScaleHelper {

    // 默认屏幕宽度基准
    private float screenWidthDp = 320.0f;
    // 缩放比例
    private float scale;

    public ScaleHelper(Activity activity) {
        if(null == activity) {
            throw new IllegalArgumentException("activity must`t be null!");
        }
        scale = getScreenWidthDp(activity) / screenWidthDp;
    }

    /**
     * 获取屏幕可操作区域宽度dp数目
     *
     * @param activity
     * @return
     */
    private float getScreenWidthDp(Activity activity) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.widthPixels / displayMetrics.density;
    }

    /**
     * 缩放View
     * @param view
     */
    public void scaleView(View view) {
        if(null != view && scale != 1.0f) {
            resetViewSize(view);
            if(view instanceof ViewGroup) {
                scaleView((ViewGroup) view);
            }
        }
    }

    /**
     * 缩放ViewGroup
     * @param viewGroup
     */
    private void scaleView(ViewGroup viewGroup) {
        if(null != viewGroup && scale != 1.0f) {
            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                scaleView(viewGroup.getChildAt(i));
            }
        }
    }

    /**
     * 重新设置View大小
     * @param view
     */
    private void resetViewSize(View view) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if(null != layoutParams) {
            // margin缩放
            if(layoutParams instanceof ViewGroup.MarginLayoutParams) {
                ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
                marginLayoutParams.leftMargin = resetWidth(marginLayoutParams.leftMargin);
                marginLayoutParams.topMargin = resetHeight(marginLayoutParams.topMargin);
                marginLayoutParams.rightMargin = resetWidth(marginLayoutParams.rightMargin);
                marginLayoutParams.bottomMargin = resetHeight(marginLayoutParams.bottomMargin);
            }
            // 宽度为精确值时进行缩放
            if(layoutParams.width != ViewGroup.LayoutParams.MATCH_PARENT
                    && layoutParams.width != ViewGroup.LayoutParams.WRAP_CONTENT) {
                layoutParams.width = resetWidth(layoutParams.width);
            }
            // 高度为精确值时进行缩放
            if(layoutParams.height != ViewGroup.LayoutParams.MATCH_PARENT
                    && layoutParams.height != ViewGroup.LayoutParams.WRAP_CONTENT) {
                layoutParams.height = resetHeight(layoutParams.height);
            }

            view.setLayoutParams(layoutParams);
        }

        // padding缩放
        view.setPadding(
                resetWidth(view.getPaddingLeft()),
                resetHeight(view.getPaddingTop()),
                resetWidth(view.getPaddingRight()),
                resetHeight(view.getPaddingBottom())
        );

        // 缩放字体大小
        if(view instanceof TextView) {
            ((TextView)view).setTextSize(0, resetTextSize(((TextView) view).getTextSize()));
        }
    }

    private int resetWidth(int width) {
        return (int) (width * scale);
    }

    private int resetHeight(int height) {
        return (int) (height * scale);
    }

    private float resetTextSize(float textSize) {
        return textSize * scale;
    }

}
