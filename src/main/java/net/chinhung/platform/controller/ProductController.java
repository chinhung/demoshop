package net.chinhung.platform.controller;

import net.chinhung.application.endpoint.product.CreateProduct;
import net.chinhung.application.endpoint.product.ProductDTO;
import net.chinhung.application.endpoint.product.ProductEndpoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/apple/product")
public class ProductController {

    private final ProductEndpoint productEndpoint;

    @Autowired
    public ProductController(ProductEndpoint productEndpoint) {
        this.productEndpoint = productEndpoint;
    }

    @GetMapping("/products")
    public List<ProductDTO> findProducts() {
        return productEndpoint.findProducts();
    }

    @PostMapping("/product")
    public ProductDTO createProduct(@RequestBody CreateProduct command) {
        return productEndpoint.createProduct(command);
    }
}
