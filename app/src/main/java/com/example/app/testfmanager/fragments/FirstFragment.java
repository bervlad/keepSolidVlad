package com.example.app.testfmanager.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.app.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FirstFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FirstFragment extends Fragment {

    private final static String NAME_ARG = "NAME_ARG";
    private final static String SURNAME_ARG = "SURNAME_ARG";

    private String receivedName;
    private String receivedSurname;

    public FirstFragment() {
        // Required empty public constructor
    }

    public static FirstFragment newInstance(String name, String surname) {
        FirstFragment fragment = new FirstFragment();
        Bundle args = new Bundle();
        args.putString(NAME_ARG, name);
        args.putString(SURNAME_ARG, surname);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            receivedName = getArguments().getString(NAME_ARG);
            receivedSurname = getArguments().getString(SURNAME_ARG);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Toast.makeText(getContext(), "Name = " + receivedName + "; Surname = " + receivedSurname, Toast.LENGTH_SHORT).show();

        return inflater.inflate(R.layout.fragment_first, container, false);
    }
}