package msa.catalogserver.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import msa.catalogserver.domain.Category;
import msa.catalogserver.domain.Product;
import msa.catalogserver.repository.CategoryRepository;
import msa.catalogserver.repository.ProductRepository;
import msa.catalogserver.vo.category.RequestCreateCategory;
import msa.catalogserver.vo.category.ResponseGetCategory;
import msa.catalogserver.vo.category.ResponseProductByCategory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryServiceImpl implements CategoryService{
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;
    @Override
    @Transactional
    public void createCategory(RequestCreateCategory requestCreateCategory) {

        Category category = requestCreateCategory.toEntity();
        if(requestCreateCategory.getParentCategoryName() != null){
            String parentCategoryName = requestCreateCategory.getParentCategoryName();
            Optional<Category> parent = categoryRepository.findByName(parentCategoryName);
            // 만약 없으면 예외처리하는 코드 추가해야함.
            if(parent.isEmpty()) throw new IllegalArgumentException("존재하지 않는 부모 카테고리입니다.");

            Category existingCategoryOptional = parent.get();
            category.setDepth(existingCategoryOptional.getDepth()+1);
            category.setParent(existingCategoryOptional);
            existingCategoryOptional.getChildren().add(category);

        }
        else{
            if(categoryRepository.existsByName(category.getName())){
                throw new IllegalArgumentException("동일한 이름의 카테고리 존재");
            }
            category.setDepth(0);
        }
        categoryRepository.save(category);
    }

    @Override
    public List<ResponseProductByCategory> getProductsByCategoryName(String categoryName) {
        Optional<Category> categoryOptional = categoryRepository.findByName(categoryName);
        if(categoryOptional.isPresent()){
            Category category = categoryOptional.get();
            log.info(category.getName());
            List<Product> products = new ArrayList<>();
            findChildren(category,products);
            log.info(products.get(1).getProductName());
            return products.stream().map(ResponseProductByCategory::from).collect(Collectors.toList());

        }
        else{
            throw new IllegalArgumentException("존재하지 않는 카테고리입니다.");
        }
    }

    @Override
    public List<ResponseGetCategory> getCategoryByName(String name) {
        Optional<Category> category = categoryRepository.findByName(name);
        if(category.isPresent()){
            Category category1 = category.get();
            return category1.getChildren().stream().map(ResponseGetCategory::from).collect(Collectors.toList());
        }
        else {
            return null;
        }
    }

    private void findChildren(Category category, List<Product> products){
        if(category.getChildren().isEmpty()){
            List<Product> products1 = productRepository.findByCategoryName(category.getName());
            products.addAll(products1);
            return;
        }else{
            for (Category child : category.getChildren()) {
                findChildren(child, products);
            }
        }
    }
}
