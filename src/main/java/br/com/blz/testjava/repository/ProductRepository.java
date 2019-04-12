package br.com.blz.testjava.repository;

import br.com.blz.testjava.exception.CouldNotSaveDuplicatedException;
import br.com.blz.testjava.exception.ElementNotFoundException;
import br.com.blz.testjava.exception.UnavaliableProductException;
import br.com.blz.testjava.domain.Product;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Repository
public class ProductRepository{

    static Set<Product> products = new HashSet<>();

    public Product save(final Product product) throws CouldNotSaveDuplicatedException {
        if(products.contains(product)){
            throw new CouldNotSaveDuplicatedException("It Was Tried To Save Duplicated Product");
        }else {
            products.add(product);
            return product;
        }
    }

    public Product update(final Product product) {
        products.remove(product);
        products.add(product);
        return product;
    }

    public Product getBySku(final Integer sku) throws UnavaliableProductException {
        Product product = products.stream().filter(p -> p.getSku().equals(sku)).collect(singleElement());
        product.getFromInventory();
        return product;
    }

    public void deleteBySku(final Integer sku) {
        products.removeIf(p -> p.getSku().equals(sku));
    }

    public static <T> Collector<T, ?, T> singleElement() {
        return Collectors.collectingAndThen(
            Collectors.toList(),
            list -> {
                if (list.size() > 1) {
                    throw new IllegalStateException();
                }else if(list.size() == 0 ){
                    throw new ElementNotFoundException("Empty Result");
                }
                return list.get(0);
            }
        );
    }
}
