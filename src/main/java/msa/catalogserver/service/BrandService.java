package msa.catalogserver.service;

import msa.catalogserver.vo.brand.RequestCreateBrand;

public interface BrandService {
    void registerBrand(RequestCreateBrand requestCreateBrand);
}
