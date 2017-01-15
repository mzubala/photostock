package pl.com.bottega.photostock.sales.model.client;

public enum ClientStatus {

    STANDARD("Standardowy"),
    VIP("Vip");

    private String statusName;

    ClientStatus(String name) {
        this.statusName = name;
    }

    public String getStatusName() {
        return statusName;
    }

}
