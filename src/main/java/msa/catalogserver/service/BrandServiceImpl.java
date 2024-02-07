package msa.catalogserver.service;

import lombok.RequiredArgsConstructor;
import msa.catalogserver.domain.Brand;
import msa.catalogserver.repository.BrandRepository;
import msa.catalogserver.vo.brand.RequestCreateBrand;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
