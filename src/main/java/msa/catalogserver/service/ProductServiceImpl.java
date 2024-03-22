package msa.catalogserver.service;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import msa.catalogserver.aws.S3Service;
import msa.catalogserver.domain.Brand;
import msa.catalogserver.domain.Inventory;
import msa.catalogserver.domain.Product;
import msa.catalogserver.repository.BrandRepository;
import msa.catalogserver.repository.ProductRepository;
import msa.catalogserver.vo.product.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.swing.text.html.Option;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
@Slf4j
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final BrandRepository brandRepository;
    private final S3Service  S3Service;

    @Override
    public List<ResponseProductTop10> getProductTop10() {
        return productRepository.findProductTop10().stream()
                .map(ResponseProductTop10::from)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void createProduct(RequestCreateProduct requestCreateProduct , MultipartFile multipartFile)
        throws IOException {

        // 기존 브랜드 확인
        Optional<Brand> existingBrandOptional = brandRepository.findByName(requestCreateProduct.getBrandName());
        Brand brand;

        if (existingBrandOptional.isPresent()) {
            // 기존 브랜드가 존재하는 경우
            brand = existingBrandOptional.get();

            Inventory inventory = new Inventory();
            inventory.setStock(requestCreateProduct.getStock());

            Product product = requestCreateProduct.toEntity(brand,inventory);
            String url = S3Service.upload(multipartFile);
            product.setImgUrl(url);
            productRepository.save(product);
            // 아마 여기서 나중에 해당 제대로 실행되었다는 Response 값을 줘야함
        } else {
            throw new IllegalArgumentException("존재하지 않는 브랜드 입니다.");
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
