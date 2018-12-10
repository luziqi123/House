package cn.longface.house;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import cn.longface.toolbox.ui.T;
import cn.longface.toolbox.ui.list.ItemClickCallback;
import cn.longface.toolbox.ui.list.SimpleBaseAdapter;
import cn.longface.toolbox.ui.list.SimpleBaseHolder;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.rl);
        SimpleBaseAdapter<SimpleBaseHolder<String>, String> adapter =
                new SimpleBaseAdapter<SimpleBaseHolder<String>, String>(this) {
                    @Override
                    protected int getLayoutRec(int viewType) {
                        return R.layout.item_room;
                    }

                    @Override
                    protected SimpleBaseHolder getViewHolder(View inflate, int viewType) {
                        return new SimpleBaseHolder(inflate) {
                            @Override
                            protected void initView() {

                            }

                            @Override
                            protected void inflaterData() {

                            }
                        };
                    }

                };

        adapter.setItemClickCall(new ItemClickCallback<String>() {
            @Override
            public void onItemClick(int position, View view, String data) {
                T.showShort(MainActivity.this , position + " == " + data);
            }
        });
        recyclerView.setAdapter(adapter);

        List<String> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            list.add(new String("asdfasdf"));
        }
        adapter.setData(list);
    }
}
