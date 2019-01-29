package com.bookt.bookt;

import android.content.Context;
import android.content.res.ColorStateList;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import java.util.ArrayList;

public class GalleryFilterCustomListAdapter extends ArrayAdapter<GalleryFilterSetter> {

    ArrayList<GalleryFilterSetter> list;

    public GalleryFilterCustomListAdapter(@NonNull Context context, @NonNull ArrayList<GalleryFilterSetter> objects) {
        super(context, 0,  objects);
        this.list = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if(view == null){
            view = LayoutInflater.from(getContext()).inflate(R.layout.gallery_custom_filter_listview, parent, false);
        }

        final CheckBox checkBox = view.findViewById(R.id.checkBox);

        checkBox.setText(list.get(position).getFilterText());

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (checkBox.isChecked()){
                    // DO SOMETHING
                }
            }
        });

        return view;
    }
}