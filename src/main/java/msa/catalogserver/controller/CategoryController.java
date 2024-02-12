package msa.catalogserver.controller;


import lombok.RequiredArgsConstructor;
import msa.catalogserver.domain.Category;
import msa.catalogserver.service.CategoryService;
import msa.catalogserver.vo.category.RequestCreateCategory;
import msa.catalogserver.vo.category.ResponseGetCategory;
import msa.catalogserver.vo.category.ResponseProductByCategory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping("/category")
    public ResponseEntity<String> createCategory(@RequestBody RequestCreateCategory requestCreateCategory){
        categoryService.createCategory(requestCreateCategory);
        return ResponseEntity.status(HttpStatus.CREATED).body("성공");
    }

    @GetMapping("/category/{name}")
    public ResponseEntity<List<ResponseGetCategory>> getCategoryByName(@PathVariable String name){
        List<ResponseGetCategory> categories = categoryService.getCategoryByName(name);
        return ResponseEntity.status(HttpStatus.FOUND).body(categories);
    }

    @GetMapping("/category/{categoryName}/products")
    public ResponseEntity<List<ResponseProductByCategory>> getProductsByCategoryName(@PathVariable String categoryName){
        List<ResponseProductByCategory> products = categoryService.getProductsByCategoryName(categoryName);
        return ResponseEntity.status(HttpStatus.FOUND).body(products);
    }
}
