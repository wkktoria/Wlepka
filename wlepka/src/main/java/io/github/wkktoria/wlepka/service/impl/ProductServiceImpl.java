package io.github.wkktoria.wlepka.service.impl;

import io.github.wkktoria.wlepka.entity.Product;
import io.github.wkktoria.wlepka.repository.ProductRepository;
import io.github.wkktoria.wlepka.service.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements IProductService {

    private final ProductRepository productRepository;

    @Override
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

}
