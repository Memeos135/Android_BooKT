package com.bookt.bookt;

class GalleryFilterSetter {

    String filterText;

    GalleryFilterSetter(String filterText){
        this.filterText = filterText;
    }

    public void setFilterText(String filterText){
        this.filterText = filterText;
    }

    public String getFilterText (){
        return filterText;
    }

}
