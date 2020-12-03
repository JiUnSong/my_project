package org.techtown.scheduledetail2;

public class Surround_Data {
    private String store_name;
    private String star;
    private String img_url;

    public Surround_Data(String store_name, String star, String img_url){
        this.store_name = store_name;
        this.star = star;
        this. img_url = img_url;
    }
    public String getStore_name(){
        return store_name;
    }
    public String getStar(){
        return star;
    }
    public String getImg_url(){
        return img_url;
    }
}