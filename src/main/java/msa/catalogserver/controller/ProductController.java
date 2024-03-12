package msa.catalogserver.controller;

import lombok.RequiredArgsConstructor;
import msa.catalogserver.domain.Product;
import msa.catalogserver.dto.response.ApiResponse;
import msa.catalogserver.dto.response.ErrorResponse;
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

import javax.validation.Valid;
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
    public ApiResponse<String> createProduct(@RequestBody @Valid RequestCreateProduct requestCreateProduct){
        productService.createProduct(requestCreateProduct);

        return new ApiResponse<>(true,"상품을 등록하였습니다.",HttpStatus.CREATED,null);

    }

    @GetMapping("/product/{productName}")
    public ApiResponse<ResponseGetProduct> getProductByName(@PathVariable String productName){
        ResponseGetProduct responseGetProduct = productService.getByProductName(productName);

        if (responseGetProduct.isEmpty()) {
            return new ApiResponse<>(false,responseGetProduct,HttpStatus.NOT_FOUND,new ErrorResponse("해당 이름의 제품은 없습니다."));
        } else {
            return new ApiResponse<>(true,responseGetProduct,HttpStatus.FOUND,null);
        }
    }

    @GetMapping("/top/products")
    public ApiResponse<List<ResponseProductTop10>> getProductTop10(){
        List<ResponseProductTop10> responseProductTop10s = productService.getProductTop10();
        return new ApiResponse<>(true,responseProductTop10s,HttpStatus.FOUND,null);
    }
}
