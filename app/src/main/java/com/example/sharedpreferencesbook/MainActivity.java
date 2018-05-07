package com.example.sharedpreferencesbook;

import android.app.Dialog;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.*;//主要是Window和WindowManager类
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity{

    SharedPreferences pref;
    SharedPreferences.Editor editor;
    public int layoutbackground,textViewColor,textViewSize;
    private int chose;

    private Dialog dialog;

    LinearLayout linearLayout01;
    TextView textView001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pref= PreferenceManager.getDefaultSharedPreferences(this);

        editor = pref.edit();
        linearLayout01=(LinearLayout)findViewById(R.id.linearlayout01);
        Button button01=(Button)findViewById(R.id.button01);
        Button button02=(Button)findViewById(R.id.button02);
        Button button03=(Button)findViewById(R.id.button03);
        textView001=(TextView)findViewById(R.id.textView001);

        //以下6行完成对保存数据的恢复
        linearLayout01.setBackgroundColor(pref.getInt("LayoutBackground",getResources().getColor(R.color.white)));
        layoutbackground=pref.getInt("LayoutBackground",getResources().getColor(R.color.white));//不设置这一行就会如果：第二次进入没点击颜色，那么第三次就会恢复默认的白色（第二次deitor.apply()一个空的int值）
        textView001.setTextColor(pref.getInt("textView001_textColor",getResources().getColor(R.color.defaultcolor)));
        textViewColor=pref.getInt("textView001_textColor",getResources().getColor(R.color.defaultcolor));
        textView001.setTextSize(TypedValue.COMPLEX_UNIT_SP,pref.getInt("textView001_textSize",15));//默认字体大小就是15sp,前边的参数是把单位设置为sp
        textViewSize=pref.getInt("textView001_textSize",15);

        button01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chose=0;
                showmyDialog();
            }
        });

        button02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chose=1;
                showmyDialog();
            }
        });

        button03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chose=2;
                showmyDialog();
            }
        });
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();

        editor.putInt("LayoutBackground",layoutbackground);
        editor.putInt("textView001_textColor",textViewColor);
        editor.putInt("textView001_textSize",textViewSize);
        editor.apply();//这个保存
    }


    public void showmyDialog(){
        dialog =new Dialog(this,R.style.dialog);
        final View dialogLayout=getLayoutInflater().inflate(R.layout.dialog,null);

        TextView textView01,textView02,textView03,textView04;
        textView01=(TextView)dialogLayout.findViewById(R.id.textView01);
        textView02=(TextView)dialogLayout.findViewById(R.id.textView02);
        textView03=(TextView)dialogLayout.findViewById(R.id.textView03);
        textView04=(TextView)dialogLayout.findViewById(R.id.textView04);
        switch (chose){
            case 1:
                textView01.setText("大号");
                textView02.setText("中号");
                textView03.setText("小号");
                break;
            case 2:
                textView01.setText("蓝色");
                textView02.setText("白色");
                textView03.setText("黄色");
                break;
        }

        dialog.setContentView(dialogLayout);//将布局设置给Dialog
        Window dialogWindow =dialog.getWindow();//获取当前Activity所在的窗体
        dialogWindow.setGravity(Gravity.BOTTOM);//设置窗口显示在底部
        WindowManager.LayoutParams layoutParams = dialogWindow.getAttributes();//获得窗体的属性
        layoutParams.y=10;//设置Dialog距离底部的距离
        dialogWindow.setAttributes(layoutParams);
        dialog.show();

        textView01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch(chose){
                    case 0:
                        int i=getResources().getColor(R.color.green);
                        linearLayout01.setBackgroundColor(i);
                        layoutbackground=i;
                        break;
                    case 1:
                        textView001.setTextSize(TypedValue.COMPLEX_UNIT_SP,18);
                        textViewSize=18;
                        break;
                    case 2:
                        int k=getResources().getColor(R.color.blue);
                        textView001.setTextColor(k);
                        textViewColor=k;
                        break;
                }

            }
        });

        textView02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                switch(chose){
                    case 0:
                        int i=getResources().getColor(R.color.blue);
                        linearLayout01.setBackgroundColor(i);
                        layoutbackground=i;
                        break;
                    case 1:
                        textView001.setTextSize(TypedValue.COMPLEX_UNIT_SP,15);
                        textViewSize=15;
                        break;
                    case 2:
                        int k=getResources().getColor(R.color.white);
                        textView001.setTextColor(k);
                        textViewColor=k;
                        break;
                }
            }
        });

        textView03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch(chose){
                    case 0:
                        int i=getResources().getColor(R.color.white);
                        linearLayout01.setBackgroundColor(i);
                        layoutbackground=i;
                        break;
                    case 1:
                        textView001.setTextSize(TypedValue.COMPLEX_UNIT_SP,12);
                        textViewSize=12;
                        break;
                    case 2:
                        int k=getResources().getColor(R.color.yellow);
                        textView001.setTextColor(k);
                        textViewColor=k;
                        break;
                }
            }
        });

        textView04.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               dialog.dismiss();
            }
        });
    }


//    @Override
//    public Resources getResources() {
//        Resources res = super.getResources();
//        Configuration config = res.getConfiguration();
//        config.fontScale = f; //1 设置正常字体大小的倍数
//        res.updateConfiguration(config, res.getDisplayMetrics());
//        return res;
//    }
}
