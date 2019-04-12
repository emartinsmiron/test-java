package br.com.blz.testjava.domain;

import br.com.blz.testjava.exception.UnavaliableProductException;
import lombok.Getter;
import lombok.Setter;

import java.util.Collections;
import java.util.Comparator;
import java.util.Set;
@Getter
@Setter
public class Inventory {

    private Integer quantity;

    private Set<Warehouse> warehouses;

    public Integer getQuantity(){
        return warehouses.stream().mapToInt(w -> w.getQuantity()).sum();
    }


    public void getProduct() throws UnavaliableProductException {
        final Comparator<Warehouse> cmp = Comparator.comparing(Warehouse::getQuantity);
        Collections.max(this.warehouses, cmp).getProduct();

    }
}
