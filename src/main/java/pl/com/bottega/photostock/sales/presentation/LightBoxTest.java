package pl.com.bottega.photostock.sales.presentation;

import pl.com.bottega.photostock.sales.infrastructure.InMemoryProductRepository;
import pl.com.bottega.photostock.sales.model.client.Address;
import pl.com.bottega.photostock.sales.model.client.Client;
import pl.com.bottega.photostock.sales.model.lightbox.LightBox;
import pl.com.bottega.photostock.sales.model.money.Money;
import pl.com.bottega.photostock.sales.model.product.Product;
import pl.com.bottega.photostock.sales.model.product.ProductRepository;

public class LightBoxTest {

    public static void main(String[] args) {
        ProductRepository productRepository = new InMemoryProductRepository();
        Product product1 = productRepository.get("1");
        Product product2 = productRepository.get("2");
        Product product3 = productRepository.get("3");
        Client client = new Client("Johny X", new Address(), Money.valueOf(100));
        Client danny = new Client("Danny X", new Address(), Money.valueOf(100));

        LightBox l1 = new LightBox(client, "Samochody");
        LightBox l2 = new LightBox(client, "Bmw");
        LightBox l3 = new LightBox(danny, "Wyścigowe Samochody");

        l1.add(product1);
        l1.add(product2);
        l1.add(product3);

        l2.add(product1);

        l3.add(product3);

        product1.deactivate();

        printLightboxes(l1, l2, l3);

        LightBox l = LightBox.joined(client, "Joined lightbox", l1, l2, l3);
        System.out.println("Jooined lightbox");
        printLightbox(l);
    }

    private static void printLightboxes(LightBox... lightboxes) {
        int nr = 1;
        for (LightBox lightbox : lightboxes) {
            System.out.println(String.format("%d. %s - %s", nr, lightbox.getName(), lightbox.getOwner().getName()));
            printLightbox(lightbox);
            nr++;
        }
    }

    private static void printLightbox(LightBox ligthbox) {
        for (Product product : ligthbox) {
            System.out.println(
                    String.format("%s%s | %s",
                            (product.isActive() ? "" : "X "),
                            product.getNumber(),
                            product.calculatePrice(ligthbox.getOwner())
                    ));
        }
    }

}
