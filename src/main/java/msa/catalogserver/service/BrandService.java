package msa.catalogserver.service;

import msa.catalogserver.vo.brand.RequestCreateBrand;
import msa.catalogserver.vo.brand.ResponseBrandProduct;

import java.util.List;

public interface BrandService {
    void registerBrand(RequestCreateBrand requestCreateBrand);

    List<ResponseBrandProduct> getBrandProduct(String brandName);
}
