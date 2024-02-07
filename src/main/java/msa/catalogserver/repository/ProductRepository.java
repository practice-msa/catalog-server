package msa.catalogserver.repository;

import msa.catalogserver.domain.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends CrudRepository<Product,Long> {
    //Product findByProductId(String productId);

    Optional<Product> findByProductName(String productName);

    // 상품 저장

}
