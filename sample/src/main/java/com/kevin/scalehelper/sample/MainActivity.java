package com.kevin.scalehelper.sample;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kevin.scalehelper.ScaleHelper;

/**
 * 版权所有：XXX有限公司
 *
 * MainActivity
 *
 * @author zhou.wenkai ,Created on 2016-6-28 10:09:38
 * 		   Major Function：<b>布局适配帮助类示例</b>
 *
 *         注:如果您修改了本类请填写以下内容作为记录，如非本人操作劳烦通知，谢谢！！！
 * @author mender，Modified Date Modify Content:
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LinearLayout layout1 = (LinearLayout) findViewById(R.id.ll_card_root1);
        LinearLayout layout2 = (LinearLayout) findViewById(R.id.ll_card_root2);

        // 设置字体
        TextView cardNumber1 = (TextView) layout1.findViewById(R.id.tv_card_cardnumber);
        TextView cardNumber2 = (TextView) layout2.findViewById(R.id.tv_card_cardnumber);
        setCardNumberTypeFace(cardNumber1);
        setCardNumberTypeFace(cardNumber2);

        // 适配下面卡样
        ScaleHelper scaleHelper = new ScaleHelper(MainActivity.this);
        scaleHelper.scaleView(layout2);
    }

    /**
     * 设置卡号外部字体 ,typeface加载外部字体只能通过代码的方式
     */
    private void setCardNumberTypeFace(TextView textView) {
        Typeface typeFace = Typeface.createFromAsset(this.getAssets(),"fonts/Farrington-7B-Qiqi.ttf");
        textView.setTypeface(typeFace);
    }
}
