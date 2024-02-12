package msa.catalogserver.service;

import msa.catalogserver.domain.Category;
import msa.catalogserver.vo.category.RequestCreateCategory;
import msa.catalogserver.vo.category.ResponseGetCategory;
import msa.catalogserver.vo.category.ResponseProductByCategory;

import java.util.List;

public interface CategoryService {
    void createCategory(RequestCreateCategory requestCreateCategory);

    List<ResponseGetCategory> getCategoryByName(String name);

    List<ResponseProductByCategory> getProductsByCategoryName(String categoryName);
}
