package pl.com.bottega.photostock.sales.model.product;

import pl.com.bottega.photostock.sales.model.client.Client;
import pl.com.bottega.photostock.sales.model.money.Money;

import java.util.Collection;
import java.util.HashSet;

public class Picture extends AbstractProduct {

    private Collection<String> tags;

    public Picture(String number, String name, Collection<String> tags, Money catalogPrice, boolean active) {
        super(catalogPrice, active, number, name);
        this.tags = new HashSet<String>(tags);
    }

    public Picture(String number, String name, Collection<String> tags, Money catalogPrice) {
        this(number, name, tags, catalogPrice, true);
    }

    @Override
    public Money calculatePrice(Client client) {
        return catalogPrice;
    }

    public boolean hasTag(String tag) {
        return tags.contains(tag);
    }

}
