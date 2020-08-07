package com.example.mylibrary;

public class ListItem {
    private String name;
    private String imageId;

    public ListItem(String name,String imageId){
        this.name=name;
        this.imageId=imageId;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }
}
