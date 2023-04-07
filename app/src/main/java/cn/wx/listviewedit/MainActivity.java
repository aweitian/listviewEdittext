package cn.wx.listviewedit;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public Activity getActivity() {
        return this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listView = (ListView) findViewById(R.id.list_view);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(), "toast: " + position, Toast.LENGTH_SHORT).show();
            }
        });

        List<ItemTest> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            ItemTest itemTest = new ItemTest();
            itemTest.setText(i + "");
            list.add(itemTest);
        }

        MyAdapter adapter = new MyAdapter(this, list);
        listView.setAdapter(adapter);
    }
}