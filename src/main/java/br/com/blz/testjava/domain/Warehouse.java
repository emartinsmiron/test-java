package br.com.blz.testjava.domain;

import br.com.blz.testjava.exception.UnavaliableProductException;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Warehouse {
    private String locality;
    private Integer quantity;
    private WarehouseType type;

    public Integer getQuantity(){
        return quantity;
    }

    public void getProduct() throws UnavaliableProductException {
        if(this.hasAvaliableQuantity()){
            this.quantity--;
        }else{
            throw new UnavaliableProductException(String.format("The Product Is Unavaliable in the Warehouse %s", locality));
        }
    }

    private boolean hasAvaliableQuantity(){
        return this.quantity > 0;
    }
}
