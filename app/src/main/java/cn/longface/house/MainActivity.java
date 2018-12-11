package cn.longface.house;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import cn.longface.toolbox.ui.danm.DanmDataAdapter;
import cn.longface.toolbox.ui.danm.DanmView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DanmView<String> danmView = findViewById(R.id.danm);
        final DanmDataAdapter<String> danmDataAdapter = new DanmDataAdapter<String>() {
            @Override
            public DanmItemHolder<String> createHolder(String data) {
                TextView textView = new TextView(MainActivity.this);
                textView.setTextSize(30);
                textView.setTextColor(Color.BLACK);
                return new DanmItemHolder<String>(textView) {
                    @Override
                    protected void initView() {

                    }

                    @Override
                    public void onBind(String s) {
                        TextView textView1 = (TextView) rootView;
                        textView1.setText(s);
                    }
                };
            }
        };
        danmView.bindAdapter(danmDataAdapter);
        new Thread(new Runnable() {
            @Override
            public void run() {
                SystemClock.sleep(2000);
                for (int i = 0; i < 10; i++) {
                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                        @Override
                        public void run() {
                            danmDataAdapter.addOneData("asdfasdf");
                        }
                    });
                    SystemClock.sleep(1000);
                }
            }
        }).start();
    }
}
