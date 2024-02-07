package msa.catalogserver.repository;

import msa.catalogserver.domain.Brand;
import msa.catalogserver.domain.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface BrandRepository extends CrudRepository<Brand,Long> {
    Optional<Brand> findByName(String name);
}
