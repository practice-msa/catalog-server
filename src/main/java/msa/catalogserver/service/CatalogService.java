package msa.catalogserver.service;

import msa.catalogserver.domain.CatalogEntity;

public interface CatalogService {
    Iterable<CatalogEntity> getAllCatalogs();
}
