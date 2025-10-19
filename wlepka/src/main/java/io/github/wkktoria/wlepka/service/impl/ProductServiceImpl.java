package io.github.wkktoria.wlepka.service.impl;

import io.github.wkktoria.wlepka.dto.ProductDto;
import io.github.wkktoria.wlepka.entity.Product;
import io.github.wkktoria.wlepka.repository.ProductRepository;
import io.github.wkktoria.wlepka.service.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements IProductService {

    private final ProductRepository productRepository;

    @Override
    public List<ProductDto> getProducts() {
        return productRepository.findAll().stream()
                .map(this::transformToDto)
                .toList();
    }

    private ProductDto transformToDto(final Product product) {
        ProductDto productDto = new ProductDto();
        BeanUtils.copyProperties(product, productDto);
        return productDto;
    }

}
