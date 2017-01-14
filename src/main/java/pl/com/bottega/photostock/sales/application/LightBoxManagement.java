package pl.com.bottega.photostock.sales.application;

import pl.com.bottega.photostock.sales.model.*;

import java.util.Collection;

public class LightBoxManagement {

    private final LightBoxRepository lightBoxRepository;
    private final ProductRepository productRepository;
    private final ClientRepository clientRepository;

    public LightBoxManagement(LightBoxRepository lightBoxRepository, ProductRepository productRepository,
                              ClientRepository clientRepository) {
        this.lightBoxRepository = lightBoxRepository;
        this.productRepository = productRepository;
        this.clientRepository = clientRepository;
    }

    public Collection<String> getLightBoxNames(String customerNumber) {
        Client client = getClient(customerNumber);
        return lightBoxRepository.getLightBoxNames(client);
    }

    private Client getClient(String customerNumber) {
        Client client = clientRepository.get(customerNumber);
        if (client == null)
            throw new IllegalArgumentException(String.format("No client with number %s", customerNumber));
        return client;
    }

    public LightBox getLightBox(String customerNumber, String lightBoxName) {
        Client client = getClient(customerNumber);
        LightBox lightBox = lightBoxRepository.findLightBox(client, lightBoxName);
        ensureLightBoxFound(lightBoxName, lightBox);
        return lightBox;
    }

    public void addProduct(String customerNumber, String lightBoxName, String productNumber) {
        Client client = getClient(customerNumber);
        Product product = productRepository.get(productNumber);
        if (product == null) {
            throw new IllegalArgumentException(String.format("No product with number %s", customerNumber));
        }
        LightBox lightBox = getOrCreateLightBox(lightBoxName, client);
        lightBox.add(product);
    }

    private LightBox getOrCreateLightBox(String lightBoxName, Client client) {
        LightBox lightBox = lightBoxRepository.findLightBox(client, lightBoxName);
        if (lightBox == null) {
            lightBox = new LightBox(client, lightBoxName);
            lightBoxRepository.put(lightBox);
        }
        return lightBox;
    }

    private void ensureLightBoxFound(String lightBoxName, LightBox lightBox) {
        if (lightBox == null)
            throw new IllegalArgumentException(String.format("No LightBox with the given name %s", lightBoxName));
    }


}
