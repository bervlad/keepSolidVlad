package com.example.app.base;

import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.app.R;
import com.example.app.ThirdActivity;
import com.example.app.app.App;
import com.example.app.database.AppDatabase;
import com.example.app.utils.listeners.Constants;
import com.example.app.utils.listeners.OnHistoryForResultListener;

public class BaseActivity extends AppCompatActivity {

    private Toolbar toolbar;


    OnHistoryForResultListener listener;


    public void initToolBar (String title) {
        toolbar=findViewById(R.id.toolbar);
        toolbar.setTitle(title);
    }

    public void initToolBarWithNav (String title) {
        toolbar=findViewById(R.id.toolbar);
        toolbar.setTitle(title);
        toolbar.inflateMenu(R.menu.main);

        toolbar.setNavigationIcon(R.drawable.ic_keyboard_backspace);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int id = item.getItemId();
                if (id==R.id.action_item_history) {
                    if (listener!=null) {
                        listener.historyIconSelected();
                    }
                }
                return true;
            }
        });
    }

    public void initToolBarWithHistory (String title) {
        toolbar=findViewById(R.id.toolbar);
        toolbar.setTitle(title);

        toolbar.setNavigationIcon(R.drawable.ic_alarm_multiple);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.historyIconSelected();
            }
        });
    }


    public void setListener(OnHistoryForResultListener listener) {
        this.listener = listener;
    }

    public AppDatabase getDatabase() {
        return ((App)getApplication()).getDatabase();
    }

}
