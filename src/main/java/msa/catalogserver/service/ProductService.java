package msa.catalogserver.service;

import msa.catalogserver.domain.Product;
import msa.catalogserver.vo.product.*;

import java.util.List;

public interface ProductService {
    Iterable<Product> getAllProducts();

    void createProduct(RequestCreateProduct requestCreateProduct);

    void updateProduct(RequestUpdateProduct requestUpdateProduct);

    // 해당 브랜드 상품들 반환

    ResponseProduct getByProductId(String productId);

    ResponseGetProduct getByProductName(String productName);

    void deleteByProductId(String productId);

    List<ResponseProductTop10> getProductTop10();

}
