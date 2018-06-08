package com.example.android.android_me.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.android.android_me.R;
import com.example.android.android_me.data.AndroidImageAssets;

public class MasterListFragment extends Fragment {

    public MasterListFragment() {
    }

    private OnImageClickListener mCallback;

    public interface OnImageClickListener {
        void onImageSelected(int position);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            // to context einai to periexomeno tou activity. an ola exoyn paei kala tote exei context
            // kanontas cast to context me to interface pairnoyme OnImageClickListener alliws null.
            mCallback = (OnImageClickListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "must implement interface");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.fragment_master_list, container, false);

        MasterListAdapter masterListAdapter = new MasterListAdapter(getContext(), AndroidImageAssets.getAll());

        GridView gridView = rootView.findViewById(R.id.grid_view);

        gridView.setAdapter(masterListAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // kalese mesw ths metablitis tou interface thn methodo pernontas tin 8esi .
                // i methodos onImageSelected den exei ilopoi8ei akoma ayto 8a ginei stin class
                //poy 8a to kanei implement.
                mCallback.onImageSelected(position);
            }
        });

        return rootView;
    }
}
