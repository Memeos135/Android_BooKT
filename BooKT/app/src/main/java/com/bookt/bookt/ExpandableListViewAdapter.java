package com.bookt.bookt;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.ArrayList;

@SuppressWarnings("unchecked")
public class ExpandableListViewAdapter extends BaseExpandableListAdapter {

    private ArrayList<ExpandableGroupItem> expandableGroupItem;
    private LayoutInflater minflater;
    private ExpandableListView expandableListView;

    public ExpandableListViewAdapter(ArrayList<ExpandableGroupItem> expandableGroupItem, ExpandableListView expandableListView) {
        this.expandableGroupItem = expandableGroupItem;
        this.expandableListView = expandableListView;
    }

    public void setInflater(LayoutInflater mInflater) {
        this.minflater = mInflater;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return null;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

        ExpandableChildItem tempChild = expandableGroupItem.get(groupPosition).expandableChildItemArrayList.get(childPosition);

        TextView foodName = null;
        TextView foodDescription = null;
        TextView foodPrice = null;
        if (convertView == null) {
            convertView = minflater.inflate(R.layout.expandable_listview_child, null);
        }
        // SET EXPANDED CHILD INFO
        foodName = (TextView) convertView.findViewById(R.id.foodTitle);
        foodDescription = (TextView) convertView.findViewById(R.id.foodDescription);
        foodPrice = (TextView) convertView.findViewById(R.id.foodPrice);

        foodName.setText(tempChild.getFoodTitle());
        foodDescription.setText(tempChild.getFoodDescription());
        foodPrice.setText(tempChild.getFoodPrice());
        // END OF EXPANDED CHILD INFO

        return convertView;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return null;
    }

    @Override
    public int getGroupCount() {
        return expandableGroupItem.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return expandableGroupItem.get(i).expandableChildItemArrayList.size();
    }

    @Override
    public void onGroupCollapsed(int groupPosition) {
        super.onGroupCollapsed(groupPosition);
        ListAdapter listadp = expandableListView.getAdapter();
        if (listadp != null) {
            int totalHeight = 0;
            for (int j = 0; j < listadp.getCount(); j++) {
                View listItem = listadp.getView(j, null, expandableListView);
                listItem.measure(0, 0);
                totalHeight += listItem.getMeasuredHeight();
            }
            ViewGroup.LayoutParams params = expandableListView.getLayoutParams();
            params.height = totalHeight + (expandableListView.getDividerHeight() * (listadp.getCount() - 1));
            expandableListView.setLayoutParams(params);
            expandableListView.requestLayout();
        }
    }

    @Override
    public void onGroupExpanded(int groupPosition) {
        super.onGroupExpanded(groupPosition);
        ListAdapter listadp = expandableListView.getAdapter();
        if (listadp != null) {
            int totalHeight = 0;
            for (int j = 0; j < listadp.getCount(); j++) {
                View listItem = listadp.getView(j, null, expandableListView);
                listItem.measure(0, 0);
                totalHeight += listItem.getMeasuredHeight();
            }
            ViewGroup.LayoutParams params = expandableListView.getLayoutParams();
            params.height = totalHeight + (expandableListView.getDividerHeight() * (listadp.getCount() - 1));
            expandableListView.setLayoutParams(params);
            expandableListView.requestLayout();
        }
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = minflater.inflate(R.layout.expandable_listview_group, null);
        }
        View view = (TextView) convertView.findViewById(R.id.expandedListHeader);

        ((TextView) view).setText(expandableGroupItem.get(groupPosition).getMenuCategory());
        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

}