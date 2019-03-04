package com.bookt.bookt;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.RadioButton;

import java.util.ArrayList;
import java.util.List;

public class GalleryFilterCustomListAdapter extends ArrayAdapter<GalleryFilterSetter> {

    private List<GalleryFilterSetter> list;

    public GalleryFilterCustomListAdapter(@NonNull Context context, @NonNull ArrayList<GalleryFilterSetter> objects) {
        super(context, 0,  objects);
        list = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if(view == null){
            view = LayoutInflater.from(getContext())
                    .inflate(R.layout.gallery_custom_filter_listview, parent, false);
        }

        final RadioButton radioButton = view.findViewById(R.id.checkBox);

        radioButton.setText(list.get(position).getFilterText());

//        // check if CLEAR ALL button has been pressed or if the adapter has been called on DataChangedNotify
//        if(!list.get(position).getChecked()){
//            radioButton.setChecked(false);
//        }

        radioButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (radioButton.isChecked()){
                    radioButton.setTextColor(radioButton.getResources().getColor(R.color.red_app));
                }else{
                    radioButton.setTextColor(radioButton.getResources().getColor(R.color.gray));
                }
            }
        });

        return view;
    }
}