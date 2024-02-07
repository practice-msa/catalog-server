package msa.catalogserver.controller;

import lombok.RequiredArgsConstructor;
import msa.catalogserver.service.BrandService;
import msa.catalogserver.vo.brand.RequestCreateBrand;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Brand")
@RequiredArgsConstructor
public class BrandController {
    private final BrandService brandService;

    @PostMapping("/")
    public ResponseEntity<String> createNewBrand(@RequestBody RequestCreateBrand requestCreateBrand){

        brandService.registerBrand(requestCreateBrand);
        return ResponseEntity.status(HttpStatus.CREATED).body("성공");

    }
}
