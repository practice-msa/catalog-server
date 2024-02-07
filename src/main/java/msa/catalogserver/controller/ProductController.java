package msa.catalogserver.controller;

import lombok.RequiredArgsConstructor;
import msa.catalogserver.domain.Product;
import msa.catalogserver.service.ProductService;
import msa.catalogserver.vo.product.ResponseProduct;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/catalog-service")
@RequiredArgsConstructor
public class CatalogController {
    private final Environment env;
    private final ProductService catalogService;

    @RequestMapping("/health_check")
    public String status(){

        return String.format("catalog service Port %s",env.getProperty("local.server.port"));
    }

    @GetMapping("/catalogs")
    public ResponseEntity<List<ResponseProduct>> getUsers(){
        Iterable<Product> catalogList = catalogService.getAllCatalogs();
        List<ResponseProduct> result = new ArrayList<>();

        for (Product catalogEntity : catalogList) {
            result.add(ResponseProduct.from(catalogEntity));
        }
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
