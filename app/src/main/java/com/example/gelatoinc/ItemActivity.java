package com.example.gelatoinc;

public class ItemActivity {

    private String id;
    private String item;
    private String price;
    private String pic;
    private String ing;

    public ItemActivity(){

    }
    public ItemActivity(String item, String pic, String price) {
        this.item = item;
        this.price = price;
        this.pic = pic;
    }

    public String getIng() {
        return ing;
    }

    public void setIng(String ing) {
        this.ing = ing;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }
}
