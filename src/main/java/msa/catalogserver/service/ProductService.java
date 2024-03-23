package msa.catalogserver.service;

import msa.catalogserver.domain.Product;
import msa.catalogserver.vo.product.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ProductService {
    Iterable<Product> getAllProducts();

    void createProduct(RequestCreateProduct requestCreateProduct, MultipartFile multipartFile) throws IOException;

    void updateProduct(RequestUpdateProduct requestUpdateProduct);

    // 해당 브랜드 상품들 반환

    ResponseProduct getByProductId(String productId);

    ResponseGetProduct getByProductName(String productName);

    boolean deleteByProductName(String productName);

    List<ResponseProductTop10> getProductTop10();

}
