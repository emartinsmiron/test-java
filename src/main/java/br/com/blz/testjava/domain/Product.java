package br.com.blz.testjava.domain;

import br.com.blz.testjava.exception.UnavaliableProductException;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
public class Product {

    @Setter(AccessLevel.NONE)
    private Boolean isMarketable;

    private Integer sku;
    private String name;
    private Inventory inventory;

    public Boolean getIsMarketable(){
        return inventory.getQuantity() > 0;

    }

    public void getFromInventory() throws UnavaliableProductException {
        if(this.getIsMarketable()){
            inventory.getProduct();
        }else{
            throw new UnavaliableProductException(String.format("Product %s Is Unavaliable", this.name));
        }
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(sku, product.sku);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sku);
    }
}
