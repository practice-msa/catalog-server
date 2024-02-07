package msa.catalogserver.controller;

import lombok.RequiredArgsConstructor;
import msa.catalogserver.service.BrandService;
import msa.catalogserver.vo.brand.RequestCreateBrand;
import msa.catalogserver.vo.brand.ResponseBrandProduct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/brand")
@RequiredArgsConstructor
public class BrandController {
    private final BrandService brandService;

    @PostMapping("/")
    public ResponseEntity<String> createNewBrand(@RequestBody RequestCreateBrand requestCreateBrand){

        brandService.registerBrand(requestCreateBrand);
        return ResponseEntity.status(HttpStatus.CREATED).body("성공");

    }

    @GetMapping("/{brandName}")
    public ResponseEntity<List<ResponseBrandProduct>> getBrandProductByName(@PathVariable String brandName){
        List<ResponseBrandProduct> responseBrandProducts = brandService.getBrandProduct(brandName);
        return ResponseEntity.status(HttpStatus.FOUND).body(responseBrandProducts);

    }
}
