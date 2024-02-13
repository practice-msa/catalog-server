package msa.catalogserver.service;

import lombok.RequiredArgsConstructor;
import msa.catalogserver.domain.Product;
import msa.catalogserver.domain.Scrap;
import msa.catalogserver.repository.ProductRepository;
import msa.catalogserver.repository.ScrapRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ScrapServiceImpl implements ScrapService{
    private final ScrapRepository scrapRepository;
    private final ProductRepository productRepository;
    @Override
    @Transactional
    public boolean createScrap(String productName, String userId) {
        Optional<Product> existingProductOptional = productRepository.findByProductName(productName);

        if(existingProductOptional.isPresent()){
            Scrap scrap = new Scrap();
            scrap.setUser_id(userId);
            scrap.setProduct(existingProductOptional.get());
            scrapRepository.save(scrap);
            return true;
        }
        else{
            throw new IllegalArgumentException("존재하지 않는 상품 입니다.");
        }
    }
}
