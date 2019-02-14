package com.bookt.bookt;

import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.ArrayList;

class ExpandableGroupItem extends ExpandableGroup<ExpandableChildItem> {
    String menuCategory;
    ArrayList<ExpandableChildItem> expandableChildItemArrayList;

    public ExpandableGroupItem(String menuCategory, ArrayList<ExpandableChildItem> childItemArrayList) {
        super(menuCategory, childItemArrayList);
        this.expandableChildItemArrayList = childItemArrayList;
        this.menuCategory = menuCategory;
    }

    public void setMenuCategory(String menuCategory){
        this.menuCategory = menuCategory;
    }

    public void setExpandableChildItemArrayList(ArrayList<ExpandableChildItem> expandableChildItemArrayList){
        this.expandableChildItemArrayList = expandableChildItemArrayList;
    }

    public ArrayList<ExpandableChildItem> getExpandableChildItemArrayList(){
        return expandableChildItemArrayList;
    }

    public String getMenuCategory(){
        return menuCategory;
    }
}
