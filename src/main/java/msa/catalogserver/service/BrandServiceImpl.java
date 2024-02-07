package msa.catalogserver.service;

import lombok.RequiredArgsConstructor;
import msa.catalogserver.domain.Brand;
import msa.catalogserver.domain.Inventory;
import msa.catalogserver.domain.Product;
import msa.catalogserver.repository.BrandRepository;
import msa.catalogserver.vo.brand.RequestCreateBrand;
import msa.catalogserver.vo.brand.ResponseBrandProduct;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BrandServiceImpl implements BrandService{
    private final BrandRepository brandRepository;
    @Override
    @Transactional
    public void registerBrand(RequestCreateBrand requestCreateBrand) {
        Brand brand = new Brand();
        brand.setDescription(requestCreateBrand.getDescription());
        brand.setLogo(requestCreateBrand.getLogo());
        brand.setName(requestCreateBrand.getName());
        brandRepository.save(brand);
    }

    @Override
    public List<ResponseBrandProduct> getBrandProduct(String brandName) {
        Optional<Brand> existingBrandOptional = brandRepository.findByName(brandName);

        if (existingBrandOptional.isPresent()) {
            List<ResponseBrandProduct> responseBrandProducts = new ArrayList<>();
            Brand brand = existingBrandOptional.get();
            return brand.getProducts().stream()
                    .map(ResponseBrandProduct::from)
                    .collect(Collectors.toList());
        } else {
            return null;
        }
    }
}
