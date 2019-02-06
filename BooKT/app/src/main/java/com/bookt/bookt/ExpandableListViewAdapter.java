package com.bookt.bookt;

import android.app.Activity;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckedTextView;
import android.widget.ExpandableListView;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

@SuppressWarnings("unchecked")
public class ExpandableListViewAdapter extends BaseExpandableListAdapter {

    private ArrayList<String> groupItem, tempChild;
    private ArrayList<Object> Childtem = new ArrayList<Object>();
    private LayoutInflater minflater;
    private Activity activity;
    private ExpandableListView expandableListView;

    public ExpandableListViewAdapter(ArrayList<String> grList, ArrayList<Object> childItem, ExpandableListView expandableListView) {
        groupItem = grList;
        this.Childtem = childItem;
        this.expandableListView = expandableListView;
    }

    public void setInflater(LayoutInflater mInflater, Activity act) {
        this.minflater = mInflater;
        activity = act;
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
        tempChild = (ArrayList<String>) Childtem.get(groupPosition);
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

        foodName.setText(tempChild.get(childPosition));
        foodDescription.setText(tempChild.get(childPosition));
        foodPrice.setText(tempChild.get(childPosition));
        // END OF EXPANDED CHILD INFO


        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(activity, tempChild.get(childPosition),
                        Toast.LENGTH_SHORT).show();
            }
        });
        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return ((ArrayList<String>) Childtem.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return null;
    }

    @Override
    public int getGroupCount() {
        return groupItem.size();
    }

    @Override
    public void onGroupCollapsed(int groupPosition) {
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

        ((TextView) view).setText(groupItem.get(groupPosition));

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