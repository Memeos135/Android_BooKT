package com.bookt.myapplication;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.thoughtbot.expandablerecyclerview.ExpandableRecyclerViewAdapter;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;
import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder;
import com.thoughtbot.expandablerecyclerview.viewholders.GroupViewHolder;

import java.util.ArrayList;
import java.util.List;

public class MenuAdapter extends ExpandableRecyclerViewAdapter<MenuAdapter.TypeHolder, MenuAdapter.ItemsHolder> {

    private Context context;
    private ArrayList<Type> typeArrayList;
    private ArrayList<Item> itemArrayList;



    public MenuAdapter(List<? extends ExpandableGroup> groups, Context context, ArrayList<Type> typeArrayList, ArrayList<Item> itemArrayList) {
        super(groups);
        this.context = context;
        this.typeArrayList = typeArrayList;
        this.itemArrayList = itemArrayList;


    }




    @Override
    public TypeHolder onCreateGroupViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.type_cardview, parent, false);
        return new TypeHolder(view);
    }

    @Override
    public ItemsHolder onCreateChildViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cardview, parent, false);
        return new ItemsHolder(view);
    }

    @Override
    public void onBindChildViewHolder(ItemsHolder holder, int flatPosition, ExpandableGroup group, int childIndex) {



    }

    @Override
    public void onBindGroupViewHolder(final TypeHolder holder, int flatPosition, ExpandableGroup group) {

    }









    public class TypeHolder extends GroupViewHolder{
        ConstraintLayout constraintLayout;
        ImageView imageView;

        public TypeHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.drop_icon);
            constraintLayout = itemView.findViewById(R.id.type_layout);
        }




    }

    public class ItemsHolder extends ChildViewHolder{

        public ItemsHolder(View itemView) {
            super(itemView);
        }
    }
}
