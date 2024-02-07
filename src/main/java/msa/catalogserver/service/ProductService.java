package msa.catalogserver.service;

import msa.catalogserver.domain.Product;

public interface ProductService {
    Iterable<Product> getAllCatalogs();
}
