package msa.catalogserver.service;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import msa.catalogserver.domain.Product;
import msa.catalogserver.repository.CatalogRepository;
import org.springframework.stereotype.Service;

@Data
@Slf4j
@Service
@RequiredArgsConstructor
public class CatalogServiceImpl implements CatalogService{
    private final CatalogRepository catalogRepository;
    @Override
    public Iterable<Product> getAllCatalogs() {
        return catalogRepository.findAll();
    }
}
