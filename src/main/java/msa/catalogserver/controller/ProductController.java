package msa.catalogserver.controller;

import lombok.RequiredArgsConstructor;
import msa.catalogserver.domain.Product;
import msa.catalogserver.service.ProductService;
import msa.catalogserver.vo.product.RequestCreateProduct;
import msa.catalogserver.vo.product.ResponseGetProduct;
import msa.catalogserver.vo.product.ResponseProduct;
import msa.catalogserver.vo.product.ResponseProductTop10;
import org.springframework.core.env.Environment;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductController {
    private final Environment env;
    private final ProductService productService;

    @RequestMapping("/health_check")
    public String status(){
        return String.format("catalog service Port %s",env.getProperty("local.server.port"));
    }

//    @GetMapping("/catalogs")
//    public ResponseEntity<List<ResponseProduct>> getUsers(){
//        Iterable<Product> catalogList = productService.getAllCatalogs();
//        List<ResponseProduct> result = new ArrayList<>();
//
//        for (Product catalogEntity : catalogList) {
//            result.add(ResponseProduct.from(catalogEntity));
//        }
//        return ResponseEntity.status(HttpStatus.OK).body(result);
//    }

    @PostMapping("/product")
    public ResponseEntity<String> createProduct(@RequestBody RequestCreateProduct requestCreateProduct){
        productService.createProduct(requestCreateProduct);

        return ResponseEntity.status(HttpStatus.CREATED).body("성공");

    }

    @GetMapping("/product/{productName}")
    public ResponseEntity<ResponseGetProduct> getProductByName(@PathVariable String productName){
        ResponseGetProduct responseGetProduct = productService.getByProductName(productName);

        if (responseGetProduct.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseGetProduct);
        } else {
            return ResponseEntity.status(HttpStatus.FOUND).body(responseGetProduct);
        }
    }

    @GetMapping("/top/products")
    public ResponseEntity<List<ResponseProductTop10>> getProductTop10(){
        return ResponseEntity.status((HttpStatus.FOUND)).body(productService.getProductTop10());
    }
}
