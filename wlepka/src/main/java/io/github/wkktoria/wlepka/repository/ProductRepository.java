package io.github.wkktoria.wlepka.repository;

import io.github.wkktoria.wlepka.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
