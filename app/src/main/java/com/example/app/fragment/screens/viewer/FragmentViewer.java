package com.example.app.fragment.screens.viewer;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.app.R;
import com.example.app.base.BaseFragment;


public class FragmentViewer extends BaseFragment implements ViewerContract.View {

    private AppCompatTextView textView;
    private String textInput;
    private AppCompatButton btnLink;
    private ViewerContract.Presenter presenter;


    public FragmentViewer() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View v = inflater.inflate(R.layout.fragment_viewer, container, false);
        v.findViewById(R.id.btn_link).setVisibility(View.GONE);

      //  v.findViewById(R.id.btn_link).setVisibility(View.VISIBLE);
        btnLink=v.findViewById(R.id.btn_link);
        textView = v.findViewById(R.id.intent_data_text);

        presenter.takeView(this);

        return v;

    }

    public void assignLink (Uri link) {
        btnLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openBook(link);
            }
        });
    }

    private void openBook(Uri url) {
        try {
            Intent myIntent = new Intent(Intent.ACTION_VIEW, url);
            startActivity(myIntent);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(getContext(), "No application can handle this request."
                    + " Please install a webbrowser", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }

    public void setText (String textInput) {
        textView.setText(textInput);
    }

    public void clearScreen () {
        textView.setText("");
        btnLink.setVisibility(View.GONE);
    }


    @Override
    public void setPresenter(ViewerContract.Presenter presenter) {
        this.presenter=presenter;
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void hideKeyboard() {

    }
}