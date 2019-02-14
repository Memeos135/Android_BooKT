package com.bookt.bookt;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.thoughtbot.expandablerecyclerview.ExpandableRecyclerViewAdapter;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;
import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder;
import com.thoughtbot.expandablerecyclerview.viewholders.GroupViewHolder;

import java.util.ArrayList;
import java.util.List;

public class ExpandableRecyclerAdapter extends ExpandableRecyclerViewAdapter<ExpandableRecyclerAdapter.GroupItemHolder, ExpandableRecyclerAdapter.ChildItemHolder> {

    private Context context;
    private ArrayList<ExpandableGroupItem> groupItemsArrayList;
    private ArrayList<ExpandableChildItem> childItemsArrayList;

    public ExpandableRecyclerAdapter(List<? extends ExpandableGroup> groups, Context context) {
        super(groups);
        this.context = context;
        this.groupItemsArrayList = (ArrayList<ExpandableGroupItem>)groups;
    }

    @Override
    public ExpandableRecyclerAdapter.GroupItemHolder onCreateGroupViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.expandable_listview_group, parent, false);
        return new GroupItemHolder(view);
    }

    @Override
    public ExpandableRecyclerAdapter.ChildItemHolder onCreateChildViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.expandable_listview_child, parent, false);
        return new ChildItemHolder(view);
    }

    @Override
    public void onBindChildViewHolder(ExpandableRecyclerAdapter.ChildItemHolder holder, int flatPosition, ExpandableGroup group, int childIndex) {

        childItemsArrayList = groupItemsArrayList.get(childIndex).expandableChildItemArrayList;

        holder.foodTitle.setText(childItemsArrayList.get(childIndex).getFoodTitle());
        holder.foodDescription.setText(childItemsArrayList.get(childIndex).getFoodDescription());
        holder.foodPrice.setText(childItemsArrayList.get(childIndex).getFoodPrice());

    }

    @Override
    public void onBindGroupViewHolder(ExpandableRecyclerAdapter.GroupItemHolder holder, int flatPosition, ExpandableGroup group) {

        holder.menuCategory.setText(groupItemsArrayList.get(flatPosition).getMenuCategory());

    }

    public class GroupItemHolder extends GroupViewHolder {

        TextView menuCategory;

        public GroupItemHolder(View itemView) {
            super(itemView);
            menuCategory = itemView.findViewById(R.id.expandedListHeader);
        }

        @Override
        public void expand() {
            super.expand();

            ImageView imageView = itemView.findViewById(R.id.arrowImage);
            imageView.setImageResource(R.drawable.ic_arrow_drop_up_black_24dp);

        }

        @Override
        public void collapse() {
            super.collapse();

            ImageView imageView = itemView.findViewById(R.id.arrowImage);
            imageView.setImageResource(R.drawable.ic_arrow_drop_down_black_24dp);
        }
    }

    public class ChildItemHolder extends ChildViewHolder {

        TextView foodTitle;
        TextView foodDescription;
        TextView foodPrice;

        public ChildItemHolder(View itemView) {
            super(itemView);

            foodTitle = itemView.findViewById(R.id.foodTitle);
            foodDescription = itemView.findViewById(R.id.foodDescription);
            foodPrice = itemView.findViewById(R.id.foodPrice);
        }
    }
}