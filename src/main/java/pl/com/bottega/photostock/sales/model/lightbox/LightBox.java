package pl.com.bottega.photostock.sales.model.lightbox;

import pl.com.bottega.photostock.sales.model.client.Client;
import pl.com.bottega.photostock.sales.model.product.Product;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

public class LightBox implements Iterable<Product> {

    private Client client;

    private String name;

    private Collection<Product> items = new LinkedList<>();

    public LightBox(Client client, String name) {
        this.name = name;
        this.client = client;
    }

    public void add(Product product) {
        if (items.contains(product)) {
            throw new IllegalArgumentException(String.format("Product %s is already in this LightBox", product.getNumber()));
        }
        product.ensureAvailable();
        items.add(product);
    }

    public void remove(Product product) {
        if (!items.contains(product)) {
            throw new IllegalArgumentException(String.format("Product %s is not in this LightBox", product.getNumber()));
        }
        items.remove(product);
    }

    public void rename(String newName) {
        this.name = newName;
    }

    @Override
    public Iterator<Product> iterator() {
        return items.iterator();
    }

    public String getName() {
        return name;
    }

    public Client getOwner() {
        return client;
    }

    public static LightBox joined(Client client, String name, LightBox... lightboxes) {
        LightBox output = new LightBox(client, name);
        output.join(lightboxes);
        return output;
    }

    public void join(LightBox... lightBoxes) {
        for (LightBox lightBox : lightBoxes)
            for (Product product : lightBox) {
                if (!items.contains(product) && product.isAvailable())
                    items.add(product);
            }
    }
}
