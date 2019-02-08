package com.bookt.bookt;

import java.util.ArrayList;

public class ExpandableGroupItem {
    String menuCategory;
    ArrayList<ExpandableChildItem> expandableChildItemArrayList;

    ExpandableGroupItem(String menuCategory, ArrayList<ExpandableChildItem> expandableChildItemArrayList){
        this.expandableChildItemArrayList = expandableChildItemArrayList;
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
