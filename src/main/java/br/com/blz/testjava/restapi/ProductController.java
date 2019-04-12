package br.com.blz.testjava.restapi;

import br.com.blz.testjava.domain.Product;
import br.com.blz.testjava.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController(value = "/products")
public class ProductController {


    public ProductRepository repository;

    @Autowired
    public ProductController(final ProductRepository productService) {
        this.repository = productService;
    }

    @PostMapping
    public ResponseEntity<Product> create(@RequestBody final Product product){
        return new ResponseEntity<>(repository.save(product), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Product> update(@RequestBody final Product product){
        return new ResponseEntity( repository.update(product),HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Product> get(@RequestParam(value = "sku") final Integer sku){

        return new ResponseEntity(repository.getBySku(sku), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity delete(@RequestParam("sku") final Integer sku){
        repository.deleteBySku(sku);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
