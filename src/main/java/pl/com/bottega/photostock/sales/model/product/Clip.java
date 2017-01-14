package pl.com.bottega.photostock.sales.model.product;

import pl.com.bottega.photostock.sales.model.client.Client;
import pl.com.bottega.photostock.sales.model.money.Money;

public class Clip extends AbstractProduct {

    private static final Long FIVE_MINUTES = 1000l * 60 * 5;
    private Long length;

    public Clip(String number, String name, Long length, Money catalogPrice, boolean active) {
        super(catalogPrice, active, number, name);
        this.length = length;
    }

    public Clip(String number, String name, Long length, Money catalogPrice) {
        this(number, name, length, catalogPrice, true);
    }

    @Override
    public Money calculatePrice(Client client) {
        if (length > FIVE_MINUTES)
            return catalogPrice.multiply(2);
        return catalogPrice;
    }
}
