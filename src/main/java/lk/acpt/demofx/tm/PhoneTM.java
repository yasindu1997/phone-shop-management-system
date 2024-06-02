package lk.acpt.demofx.tm;

public class PhoneTM {
    private int pid;
    private String brand;
    private String model;
    private double price;
    private int qty;

    public PhoneTM(int pid, String brand, String model, double price, int qty) {
        this.pid = pid;
        this.brand = brand;
        this.model = model;
        this.price = price;
        this.qty = qty;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
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
}
