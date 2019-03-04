package com.bookt.bookt;

class A2_RestaurantFilterSetter {

    private String filterText;
    private boolean checked;

    A2_RestaurantFilterSetter(String filterText, boolean checked) {
        this.filterText = filterText;
        this.checked = checked;
    }

    public void setFilterText(String filterText){
        this.filterText = filterText;
    }

    public void setChecked(boolean checked){
        this.checked = checked;
    }

    public boolean getChecked(){
        return checked;
    }

    public String getFilterText(){
        return filterText;
    }

}
