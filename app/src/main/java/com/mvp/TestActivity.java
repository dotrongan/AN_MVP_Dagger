package com.mvp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.mvp.Adapter.DataAdapter;
import com.mvp.Model.Data;

import java.util.List;

public class TestActivity extends AppCompatActivity {

    private ListView lvData;
    private Bundle bundle;
    private DataAdapter dataAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        lvData = (ListView) findViewById(R.id.lvData);
        bundle = getIntent().getExtras();
        if(bundle != null && bundle.containsKey("data")) {
            List<Data> data = bundle.getParcelableArrayList("data");
            dataAdapter = new DataAdapter(this, data);
            lvData.setAdapter(dataAdapter);
            dataAdapter.notifyDataSetChanged();
        }
    }
}
