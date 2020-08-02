package com.example.app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.example.app.base.BaseActivity;
import com.example.app.utils.adapter.HistoryRecyclerAdapter;
import com.example.app.utils.listeners.ApplicationManager;
import com.example.app.utils.listeners.Constants;
import com.example.app.utils.listeners.OnHistoryRecyclerItemClickListener;

import java.util.ArrayList;

public class ThirdActivity extends BaseActivity {
    private RecyclerView recycler;
    private ArrayList<String> items;
    private HistoryRecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        initToolBarWithNav ("Third Activity");

        items=new ArrayList<String>();
        if (ApplicationManager.getCachedItems(this)!=null) {
            items= ApplicationManager.getCachedItems(this);
        }

        adapter=new HistoryRecyclerAdapter(items, this, new OnHistoryRecyclerItemClickListener() {
            @Override
            public void onItemClick(View v, int position, String title) {

                Intent explicitIntent = new Intent(ThirdActivity.this, MainActivity.class);
                explicitIntent.putExtra(Constants.EXTRA_TITLE, title);
                startActivity(explicitIntent);

//                Intent intent = new Intent();
//                intent.putExtra(Constants.EXTRA_TITLE, title);
//                setResult(RESULT_OK, intent);
//                finish();
            }
        });
        recycler=findViewById(R.id.rv_recycler_history);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.setAdapter(adapter);

    }
}