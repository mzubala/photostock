package pl.com.bottega.photostock.sales.model;

import java.util.*;

public class Purchase {

    private Client client;
    private Date purchaseDate = new Date();
    private List<Product> items;

    private String number;

    public Purchase(Client client, Collection<Product> items) {
        this.client = client;
        this.items = new LinkedList<>(items);
        this.number = UUID.randomUUID().toString();
        sortProductsByNumberAsc();
        markProductsAsSold();
    }

    private void markProductsAsSold() {
        for (Product product : items) {
            product.soldPer(client);
        }
    }

    public Purchase(Client client, Product... items) {
        this(client, Arrays.asList(items));
    }

    public int getItemsCount() {
        return items.size();
    }

    private void sortProductsByNumberAsc() {
        this.items.sort(new Comparator<Product>() {
            @Override
            public int compare(Product o1, Product o2) {
                String n1 = o1.getNumber();
                String n2 = o2.getNumber();
                return n1.compareTo(n2);
            }
        });
    }

    public String getNumber() {
        return number;
    }

}
