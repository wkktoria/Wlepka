package io.github.wkktoria.wlepka.controller;

import io.github.wkktoria.wlepka.dto.ProductDto;
import io.github.wkktoria.wlepka.entity.Product;
import io.github.wkktoria.wlepka.service.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final IProductService productService;

    @GetMapping
    public List<ProductDto> getProducts() {
        List<ProductDto> productList = productService.getProducts();
        return productList;
    }

}
