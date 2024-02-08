package msa.catalogserver.repository;

import msa.catalogserver.domain.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends CrudRepository<Product,Long> {
    //Product findByProductId(String productId);

    Optional<Product> findByProductName(String productName);

    // 상위 상품 10개 가져오기
    @Query("select p from Product p join p.inventory order by p.salesRate desc")
    List<Product> findProductTop10();
}
