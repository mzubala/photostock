package pl.com.bottega.photostock.sales.model;

public interface ProductRepository {

    void put(Product product);

    Product get(String number);

}
