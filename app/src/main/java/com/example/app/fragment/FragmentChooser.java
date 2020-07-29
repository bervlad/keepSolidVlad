package com.example.app.fragment;

import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.app.R;
import com.example.app.api.ApiCallback;
import com.example.app.api.RestClient;
import com.example.app.collections.Car;

import java.util.ArrayList;

import com.example.app.model.VolumeErrorItem;
import com.example.app.model.VolumeModelItem;
import com.example.app.model.VolumeResponse;
import com.example.app.utils.KeyboardUtils;
import com.example.app.utils.adapter.VolumeRecyclerAdapter;
import com.example.app.utils.listeners.ObjectSelectListener;
import com.example.app.utils.listeners.OnVolumeItemRecyclerItemClickListener;

import org.jetbrains.annotations.NotNull;

import retrofit2.Response;


public class FragmentChooser extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    private RecyclerView recyclerView;
    private View loaderBlock;
    private AppCompatEditText booknameInput;
    private AppCompatButton goButton;
    private ArrayList <VolumeModelItem> items;
    VolumeRecyclerAdapter volumeRecyclerAdapter;
    private ObjectSelectListener objectSelectListener;



    public FragmentChooser() {
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

        View v = inflater.inflate(R.layout.fragment_chooser, container, false);

        loaderBlock = v.findViewById(R.id.loader_block);
        recyclerView=v.findViewById(R.id.rv_recycler);
        booknameInput=v.findViewById(R.id.et_bookname_input);
        goButton=v.findViewById(R.id.btn_go);

        items=new ArrayList<> ();

        adapterInit();

        goButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleSearchAction();
            }
        });

//        addBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (models != null && volumeRecyclerAdapter != null) {
//                    objectSelectListener.buttonSelected();
//                }
//            }
//        });
        return v;
    }

    private void handleSearchAction() {
        if (TextUtils.isEmpty(booknameInput.getText().toString())) {
           booknameInput.requestFocus();
        } else {
            KeyboardUtils.hide(booknameInput);
            loadVolumes(booknameInput.getText().toString());
        }
    }

    private void loadVolumes(String bookName) {
        showProgressBlock();
        RestClient.getInstance().getService().getVolumes(bookName).enqueue(new ApiCallback<VolumeResponse>() {

            @Override
            public void success(@NotNull Response<VolumeResponse> response) {

                if (!response.isSuccessful()) {
                    items.clear();
                    items.addAll(response.body().getItems());
                    volumeRecyclerAdapter.notifyDataSetChanged();
                    hideProgressBlock();
                }

                items.clear();
                items.addAll(response.body().getItems());
                volumeRecyclerAdapter.notifyDataSetChanged();
                hideProgressBlock();
            }


            @Override
            public void failure(VolumeErrorItem gitRepoError) {
                if (TextUtils.isEmpty(gitRepoError.getDocumentation_url())) {
                    makeErrorToast(gitRepoError.getMessage());
                } else {
                    makeErrorToast(gitRepoError.getMessage() + ", Details: " + gitRepoError.getDocumentation_url());
                }
                hideProgressBlock();
            }
        });
    }

    private void showProgressBlock() {
        if (loaderBlock != null) {
            loaderBlock.setVisibility(View.VISIBLE);
        }
    }

    private void hideProgressBlock() {
        if (loaderBlock != null) {
            loaderBlock.setVisibility(View.GONE);
        }
    }

    private void makeErrorToast(String errorMessage) {
        Toast.makeText(getContext(), errorMessage, Toast.LENGTH_SHORT).show();
    }

    private void adapterInit() {
        volumeRecyclerAdapter =new VolumeRecyclerAdapter(items, this);

        volumeRecyclerAdapter.setListener(new OnVolumeItemRecyclerItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                objectSelectListener.selected(position);
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(volumeRecyclerAdapter);
    }

    public ArrayList<VolumeModelItem> getModels () {
        return items;
    }

//    public void addModel (Car newCar) {
//        models.add (newCar);
//        volumeRecyclerAdapter.notifyDataSetChanged();
//        recyclerView.smoothScrollToPosition(recyclerView.getBottom());
//    }


    public void setObjectSelectListener(ObjectSelectListener listener) {
        this.objectSelectListener = listener;
    }

}