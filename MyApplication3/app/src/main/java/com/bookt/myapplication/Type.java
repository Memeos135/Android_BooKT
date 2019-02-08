package com.bookt.myapplication;

import android.os.Parcel;

import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.List;



public class Type extends ExpandableGroup<Item> {


    public Type(String title, List<Item> items) {
        super(title, items);
    }


}
