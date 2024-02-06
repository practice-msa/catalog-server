package msa.catalogserver.service;

import msa.catalogserver.domain.Product;

public interface CatalogService {
    Iterable<Product> getAllCatalogs();
}
