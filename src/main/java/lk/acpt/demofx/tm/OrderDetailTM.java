package lk.acpt.demofx.tm;

public class OrderDetailTM {
    private int id;
    private String brand;
    private String model;
    private double price;
    private int qty;
    private double total;

    public OrderDetailTM(int id, String brand, String model, double price, int qty, double total) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.price = price;
        this.qty = qty;
        this.total = total;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
