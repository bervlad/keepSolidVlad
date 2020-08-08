package com.example.app.base;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.example.app.R;
import com.example.app.app.App;
import com.example.app.database.AppDatabase;
import com.example.app.utils.listeners.OnHistoryForResultListener;

public abstract class BaseFragment extends Fragment {

    private Toolbar toolbar;
    OnHistoryForResultListener listener;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void initToolBar (String title, View parentView) {
        toolbar=parentView.findViewById(R.id.toolbar);
        toolbar.setTitle(title);
    }

    public void initToolBarWithNav (String title, View parentView) {
        toolbar=parentView.findViewById(R.id.toolbar);
        toolbar.setTitle(title);
        toolbar.inflateMenu(R.menu.main);

        toolbar.setNavigationIcon(R.drawable.ic_keyboard_backspace);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
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

    public void initToolBarWithHistory (String title, View parentView) {
        toolbar=parentView.findViewById(R.id.toolbar);
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

    public Toolbar getToolbar() {
        return toolbar;
    }
}
