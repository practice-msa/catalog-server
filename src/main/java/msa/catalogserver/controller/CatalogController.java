package msa.catalogserver.controller;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import msa.catalogserver.domain.CatalogEntity;
import msa.catalogserver.service.CatalogService;
import msa.catalogserver.vo.ResponseCatalog;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
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
    private final CatalogService catalogService;

    @RequestMapping("/health_check")
    public String status(){

        return String.format("catalog service Port %s",env.getProperty("local.server.port"));
    }

    @GetMapping("/catalogs")
    public ResponseEntity<List<ResponseCatalog>> getUsers(){
        Iterable<CatalogEntity> catalogList = catalogService.getAllCatalogs();
        List<ResponseCatalog> result = new ArrayList<>();

        for (CatalogEntity catalogEntity : catalogList) {
            result.add(ResponseCatalog.from(catalogEntity));
        }
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
