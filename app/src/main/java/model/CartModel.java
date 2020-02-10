package model;

public class CartModel {
    private String id;
    private String uname;
    private String uid;
    private String iid;
    private String iname;
    private String iprice;
    private String icategory;
    private String idescription;
    private String city;
    private String street;
    private String quantity;

    public CartModel(String uname, String uid, String iid, String iname, String iprice, String icategory, String idescription, String city, String street, String quantity) {
        this.uname = uname;
        this.uid = uid;
        this.iid = iid;
        this.iname = iname;
        this.iprice = iprice;
        this.icategory = icategory;
        this.idescription = idescription;
        this.city = city;
        this.street = street;
        this.quantity = quantity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getIid() {
        return iid;
    }

    public void setIid(String iid) {
        this.iid = iid;
    }

    public String getIname() {
        return iname;
    }

    public void setIname(String iname) {
        this.iname = iname;
    }

    public String getIprice() {
        return iprice;
    }

    public void setIprice(String iprice) {
        this.iprice = iprice;
    }

    public String getIcategory() {
        return icategory;
    }

    public void setIcategory(String icategory) {
        this.icategory = icategory;
    }

    public String getIdescription() {
        return idescription;
    }

    public void setIdescription(String idescription) {
        this.idescription = idescription;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
}
