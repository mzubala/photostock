package pl.com.bottega.photostock.sales.model;

public abstract class AbstractProduct implements Product {

    protected String number;
    private String name;
    protected Money catalogPrice;
    private boolean active;
    private Client reservationOwner;
    private Client buyer;

    public AbstractProduct(Money catalogPrice, boolean active, String number, String name) {
        this.catalogPrice = catalogPrice;
        this.active = active;
        this.number = number;
        this.name = name;
    }

    public abstract Money calculatePrice(Client client);

    @Override
    public boolean isAvailable() {
        return active && !isSold() && !isReserved();
    }

    private boolean isReserved() {
        return reservationOwner != null;
    }

    private boolean isSold() {
        return buyer != null;
    }

    @Override
    public void reservedPer(Client client) {
        ensureAvailable();
        reservationOwner = client;
    }

    @Override
    public void unreservedPer(Client client) {
        ensureReservedBy(client);
        reservationOwner = null;
    }

    private boolean isReservedBy(Client client) {
        return isReserved() && client.equals(reservationOwner);
    }

    @Override
    public void soldPer(Client client) {
        ensureReservedBy(client);
        buyer = client;
        unreservedPer(client);
    }

    private void ensureReservedBy(Client client) {
        if (!isReservedBy(client))
            throw new IllegalArgumentException(String.format("Picture %s is not reserved by %s", getNumber(), client.getName()));
    }

    @Override
    public String getNumber() {
        return number;
    }

    @Override
    public boolean isActive() {
        return active;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void deactivate() {
        active = false;
    }

    /*@Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || !(other instanceof Picture))
            return false;
        Picture otherPicture = (Picture) other;

        return (otherPicture.number == null && number == null) ||
                (number != null && number.equals(otherPicture.number));
    }*/

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AbstractProduct product = (AbstractProduct) o;

        return number != null ? number.equals(product.number) : product.number == null;

    }

    @Override
    public int hashCode() {
        return number != null ? number.hashCode() : 0;
    }

}
