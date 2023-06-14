package com.example.fragmentprac;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BlankFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BlankFragment extends Fragment {

    private Button btnDeliverDataToActivity;
    private MainActivity myMainActivity;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public BlankFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BlankFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BlankFragment newInstance(String param1, String param2) {
        BlankFragment fragment = new BlankFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_blank, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView tv1 = view.findViewById(R.id.tv_1);
        btnDeliverDataToActivity = view.findViewById(R.id.btn_deliver_data_to_activity);


        ((MainActivity)getActivity()).setOnDataChangeListener(new MainActivity.OnDataChangeListener() {
            @Override
            public void onDataChange(String data) {
                if (!TextUtils.isEmpty(data)) {
                    tv1.setText(data);
                }
            }
        });
        //========通过类传数据，失败===========
        /*myMainActivity = new MainActivity();
        ((MainActivity)getActivity()).setOnStringChangeListener(myMainActivity.new onStringChangeListener());*/
        //========通过类传数据，失败===========



        btnDeliverDataToActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //BlankFragment.this.getActivity()
                ((MainActivity) getActivity()).setTvActivity("还行，fragment通过普通方法向它依附的activity传递消息");
            }
        });
    }

    private onDataChangeListenerToFrac mListenerToFrac;

    public void setOnDataChangeListener(onDataChangeListenerToFrac changeListenerToFrac){
        mListenerToFrac = changeListenerToFrac;
    }

    public interface onDataChangeListenerToFrac{
        void onDataChange(String data);
    }

}