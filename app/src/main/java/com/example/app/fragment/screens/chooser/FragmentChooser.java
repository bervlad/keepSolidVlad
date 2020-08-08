package com.example.app.fragment.screens.chooser;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.app.MainActivity;
import com.example.app.R;
import com.example.app.SecondActivity;
import com.example.app.ThirdActivity;
import com.example.app.api.ApiCallback;
import com.example.app.api.RestClient;

import java.util.ArrayList;
import java.util.List;

import com.example.app.base.BaseFragment;
import com.example.app.database.AppDatabase;
import com.example.app.fragment.screens.viewer.FragmentViewer;
import com.example.app.model.ParcableModel;
import com.example.app.model.VolumeErrorItem;
import com.example.app.model.VolumeModelItem;
import com.example.app.model.VolumeResponse;
import com.example.app.utils.listeners.ApplicationManager;
import com.example.app.utils.listeners.Constants;
import com.example.app.utils.listeners.KeyboardUtils;
import com.example.app.utils.adapter.VolumeRecyclerAdapter;
import com.example.app.utils.listeners.ObjectSelectListener;
import com.example.app.utils.listeners.OnHistoryForResultListener;
import com.example.app.utils.listeners.OnVolumeItemRecyclerItemClickListener;

import retrofit2.Response;


public class FragmentChooser extends BaseFragment implements ChooserContract.View {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    private RecyclerView recyclerView;
    private View loaderBlock;
    private AppCompatEditText booknameInput;
    private AppCompatButton goButton;
    private ArrayList <VolumeModelItem> items;
    VolumeRecyclerAdapter volumeRecyclerAdapter;
    private ObjectSelectListener objectSelectListener;
    private FragmentViewer fragmentViewer;
    private MainActivity activity;
    //AppDatabase database;

    private ChooserContract.Presenter presenter;

    public FragmentChooser() {
        // Required empty public constructor
    }

    @Override
    public void setPresenter(ChooserContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void showProgress() {
        loaderBlock.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        loaderBlock.setVisibility(View.GONE);
    }

    @Override
    public void hideKeyboard() {
        KeyboardUtils.hide(booknameInput);
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

        activity = (MainActivity) getActivity();
        activity.initToolBarWithNav(getString(R.string.toolbar_title_main_activity));
       // if (presenter==null) {activity.getChooserPresenter();}

        OnHistoryForResultListener onHistoryForResultListener = new OnHistoryForResultListener() {

            @Override
            public void historyIconSelected() {
                Intent explicitIntent = new Intent(activity, ThirdActivity.class);
                startActivity(explicitIntent);
            }
        };
        activity.setListener(onHistoryForResultListener);

        if (activity.getIntent().getExtras() != null) {
            String title = activity.getIntent().getStringExtra(Constants.EXTRA_TITLE);
            booknameInput.setText(title);

            presenter.takeView(this);
            presenter.searchVolumes(title, false);
        }

        adapterInit(v);
        if (activity.isInLandscapeMode()) {
            fragmentViewer = (FragmentViewer) getActivity().getSupportFragmentManager().findFragmentById(R.id.fragment_container_two);
        }

//
//
//        database =((MainActivity)getActivity()).getDatabase();
//        if (database != null) {
//            database.repoItemDao().getAll().observe(this, (List<VolumeModelItem> volumeModelItems) -> {
//                items.clear();
//                items.addAll(volumeModelItems);
//                volumeRecyclerAdapter.notifyDataSetChanged();
//            });
//        }

        goButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              //  objectSelectListener.buttonSelected();
                if (activity.isInLandscapeMode()) {
                    fragmentViewer = (FragmentViewer) getActivity().getSupportFragmentManager().findFragmentById(R.id.fragment_container_two);
                    fragmentViewer.clearScreen();
                }

                presenter.searchVolumes(booknameInput.getText().toString(), true);
               // handleSearchAction();
            }
        });

      presenter.takeView(this);

        return v;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.dropView();
    }

    //    private void handleSearchAction() {
//        if (TextUtils.isEmpty(booknameInput.getText().toString().trim())) {
//           booknameInput.requestFocus();
//           makeErrorToast("Please input text");
//        } else {
//            ApplicationManager.updateCachedItems(getContext(), booknameInput.getText().toString());
//            KeyboardUtils.hide(booknameInput);
//            loadVolumes(booknameInput.getText().toString());
//        }
//    }

//    private void loadVolumes(String bookName) {
//        showProgressBlock();
//        RestClient.getInstance().getService().getVolumes("intitle:"+ bookName).enqueue(new ApiCallback<VolumeResponse>() {
//
//            @Override
//            public void success(Response<VolumeResponse> response) {
//
//                if (response.body().getItems()!=null) {
//
//                   updateList(response.body().getItems());
//                } else makeErrorToast("No books found");
//                hideProgressBlock();
//            }
//
//            @Override
//            public void failure(VolumeErrorItem volumeError) {
//                handleError(volumeError);
//                hideProgressBlock();
//            }
//
//        });
//    }

//    private void handleError(VolumeErrorItem volumeError) {
//        if (TextUtils.isEmpty(volumeError.getDocumentation_url())) {
//            makeErrorToast(volumeError.getMessage());
//        } else {
//            makeErrorToast(volumeError.getMessage() + ", Details: " + volumeError.getDocumentation_url());
//        }
//    }
//
//    private void showProgressBlock() {
//        if (loaderBlock != null) {
//            loaderBlock.setVisibility(View.VISIBLE);
//        }
//    }
//
//    private void hideProgressBlock() {
//        if (loaderBlock != null) {
//            loaderBlock.setVisibility(View.GONE);
//        }
//    }

    @Override
    public void showInputError() {
        booknameInput.requestFocus();
        makeErrorToast("Please input text");
    }

    @Override
    public void showRequestError(@NonNull String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    public void makeErrorToast(String errorMessage) {
        Toast.makeText(getContext(), errorMessage, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void observeItems(LiveData<List<VolumeModelItem>> itemsLiveData) {
        itemsLiveData.observe(FragmentChooser.this, new Observer<List<VolumeModelItem>>() {
            @Override
            public void onChanged(List<VolumeModelItem> volumeModelItems) {
                items.clear();
                items.addAll(volumeModelItems);
                volumeRecyclerAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void stopObserving(LiveData<List<VolumeModelItem>> liveRepoData) {
        liveRepoData.removeObservers(FragmentChooser.this);
    }

    private void adapterInit(View v) {
        volumeRecyclerAdapter =new VolumeRecyclerAdapter(items, this);

        volumeRecyclerAdapter.setListener(new OnVolumeItemRecyclerItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                //presenter.getListener().selected(position);

                if (activity.isInLandscapeMode())
                {
                    fragmentViewer.setText(items.get(position).toString());
                    fragmentViewer.assignLink(items.get(position).getSelflink());
//                    v.findViewById(R.id.btn_link).setVisibility(View.VISIBLE);
//                    v.findViewById(R.id.intent_data_text).setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);
                    fragmentViewer.prepareForSearchedItem();
                } else {
                    ParcableModel pmodel = new ParcableModel(
                            items.get(position).getTitle(),
                            items.get(position).getAuthors(),
                            items.get(position).getPublisher(),
                            items.get(position).getDescription(),
                            items.get(position).getSelflink());

                    Intent explicitIntent = new Intent(activity, SecondActivity.class);
                    explicitIntent.putExtra(Constants.EXTRA_MODEL, pmodel);
                    startActivity(explicitIntent);
                }

            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(volumeRecyclerAdapter);
    }

//    public ArrayList<VolumeModelItem> getModels () {
//        return items;
//    }



//    public void setDatabase(AppDatabase database) {
//        this.database = database;
//    }
//
//    public void setObjectSelectListener(ObjectSelectListener listener) {
//        this.objectSelectListener = listener;
//    }

//    public void performSearch (String title) {
//        booknameInput.setText(title);
//        loadVolumes(title);
//    }
//
//    private void updateList(List<VolumeModelItem> itemsToUpdate) {
//        database.repoItemDao().deleteAll();
//        database.repoItemDao().insert(itemsToUpdate);
//    }

}