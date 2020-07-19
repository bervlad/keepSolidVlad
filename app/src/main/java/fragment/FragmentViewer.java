package fragment;

import android.os.Bundle;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.app.R;


public class FragmentViewer extends Fragment {

    private AppCompatTextView textView;
    private String textInput;


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


        textView = v.findViewById(R.id.intent_data_text);

        return v;

    }

    public void setText (String textInput) {
        textView.setText(textInput);
    }


}