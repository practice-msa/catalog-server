package msa.catalogserver.controller;

import lombok.RequiredArgsConstructor;
import msa.catalogserver.dto.response.ApiResponse;
import msa.catalogserver.service.BrandService;
import msa.catalogserver.vo.brand.RequestCreateBrand;
import msa.catalogserver.vo.brand.ResponseBrandProduct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class BrandController {
    private final BrandService brandService;

    @PostMapping("/brand")
    public ApiResponse<String> createNewBrand(@RequestBody @Valid RequestCreateBrand requestCreateBrand){

        brandService.registerBrand(requestCreateBrand);
        return new ApiResponse<>(true,"브랜드를 등록하였습니다.",HttpStatus.CREATED,null);

    }

    @GetMapping("/brand/{brandName}")
    public ApiResponse<List<ResponseBrandProduct>> getBrandProductByName(@PathVariable String brandName){
        List<ResponseBrandProduct> responseBrandProducts = brandService.getBrandProduct(brandName);
        return new ApiResponse<>(true,responseBrandProducts,HttpStatus.FOUND,null);

    }
}
