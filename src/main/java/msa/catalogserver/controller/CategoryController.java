package msa.catalogserver.controller;


import lombok.RequiredArgsConstructor;
import msa.catalogserver.domain.Category;
import msa.catalogserver.dto.response.ApiResponse;
import msa.catalogserver.service.CategoryService;
import msa.catalogserver.vo.category.RequestCreateCategory;
import msa.catalogserver.vo.category.ResponseGetCategory;
import msa.catalogserver.vo.category.ResponseProductByCategory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping("/category")
    public ApiResponse<String> createCategory(@RequestBody @Valid RequestCreateCategory requestCreateCategory){
        categoryService.createCategory(requestCreateCategory);
        return new ApiResponse<>(true,"카테고리를 등록하였습니다.",HttpStatus.CREATED,null);
    }

    @GetMapping("/category/{name}")
    public ApiResponse<List<ResponseGetCategory>> getCategoryByName(@PathVariable String name){
        List<ResponseGetCategory> categories = categoryService.getCategoryByName(name);
        return new ApiResponse<>(true,categories,HttpStatus.FOUND,null);
    }

    @GetMapping("/category/{categoryName}/products")
    public ApiResponse<List<ResponseProductByCategory>> getProductsByCategoryName(@PathVariable String categoryName){
        List<ResponseProductByCategory> products = categoryService.getProductsByCategoryName(categoryName);
        return new ApiResponse<>(true,products,HttpStatus.FOUND,null);
    }
}
