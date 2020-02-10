package model;

public class ItemModel {
    private String id;
    private String itemname;
    private String itemprice;
    private String itemdescription;
    private String category;
    private String image;

    public ItemModel(String itemname, String itemprice, String itemdescription, String category, String image) {
        this.itemname = itemname;
        this.itemprice = itemprice;
        this.itemdescription = itemdescription;
        this.category = category;
        this.image = image;
    }

    public String getItemname() {
        return itemname;
    }

    public void setItemname(String itemname) {
        this.itemname = itemname;
    }

    public String getItemprice() {
        return itemprice;
    }

    public void setItemprice(String itemprice) {
        this.itemprice = itemprice;
    }

    public String getItemdescription() {
        return itemdescription;
    }

    public void setItemdescription(String itemdescription) {
        this.itemdescription = itemdescription;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
