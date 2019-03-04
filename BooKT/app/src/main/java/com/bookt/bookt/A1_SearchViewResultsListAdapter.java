package com.bookt.bookt;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class A1_SearchViewResultsListAdapter extends ArrayAdapter<A1_SearchViewResultsSetter> {

    private List<A1_SearchViewResultsSetter> list;
    private Context context;

    public A1_SearchViewResultsListAdapter(@NonNull Context context, @NonNull ArrayList<A1_SearchViewResultsSetter> objects) {
        super(context,  0, objects);
        this.context = context;
        list = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if(view == null){
            view = LayoutInflater.from(getContext())
                    .inflate(R.layout.a1_search_view_custom_listview, parent, false);
        }

        TextView restaurantName = view.findViewById(R.id.searchRestName);
        TextView restaurantDistrict = view.findViewById(R.id.searchDistName);

        restaurantName.setText(list.get(position).getRestaurantName());
        restaurantDistrict.setText(list.get(position).getDistrict());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "CELL PRESSED", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}