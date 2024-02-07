package msa.catalogserver.service;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import msa.catalogserver.domain.Brand;
import msa.catalogserver.domain.Inventory;
import msa.catalogserver.domain.Product;
import msa.catalogserver.repository.BrandRepository;
import msa.catalogserver.repository.ProductRepository;
import msa.catalogserver.vo.product.RequestCreateProduct;
import msa.catalogserver.vo.product.RequestUpdateProduct;
import msa.catalogserver.vo.product.ResponseGetProduct;
import msa.catalogserver.vo.product.ResponseProduct;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.util.Optional;
import java.util.UUID;

@Data
@Slf4j
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final BrandRepository brandRepository;

    @Override
    @Transactional
    public void createProduct(RequestCreateProduct requestCreateProduct) {

        // 기존 브랜드 확인
        Optional<Brand> existingBrandOptional = brandRepository.findByName(requestCreateProduct.getBrandName());
        Brand brand;

        if (existingBrandOptional.isPresent()) {
            // 기존 브랜드가 존재하는 경우
            brand = existingBrandOptional.get();

            Inventory inventory = new Inventory();
            inventory.setStock(requestCreateProduct.getStock());

            Product product = requestCreateProduct.toEntity(brand,inventory);
            productRepository.save(product);
            // 아마 여기서 나중에 해당 제대로 실행되었다는 Response 값을 줘야함
        } else {
            // 기존 브랜드가 존재하지 않는 경우, 새로운 브랜드 생성
            // 예외 발생 시킬 예정
            // Exeption
        }
    }

    @Override
    public void updateProduct(RequestUpdateProduct requestUpdateProduct) {

    }

    @Override
    public ResponseProduct getByProductId(String productId) {
        return null;
    }

    @Override
    public ResponseGetProduct getByProductName(String productName) {
        Optional<Product> existingProductOptional = productRepository.findByProductName(productName);
        if(existingProductOptional.isPresent()){
            return ResponseGetProduct.from(existingProductOptional.get());
        }
        else{
            return ResponseGetProduct.builder().build();
        }
    }

    @Override
    public void deleteByProductId(String productId) {

    }

    @Override
    public Iterable<Product> getAllProducts() {
        return productRepository.findAll();
    }
}
