package msa.catalogserver.service;

import lombok.RequiredArgsConstructor;
import msa.catalogserver.domain.Category;
import msa.catalogserver.repository.CategoryRepository;
import msa.catalogserver.vo.category.RequestCreateCategory;
import msa.catalogserver.vo.category.ResponseGetCategory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService{
    private final CategoryRepository categoryRepository;
    @Override
    @Transactional
    public void createCategory(RequestCreateCategory requestCreateCategory) {

        Category category = requestCreateCategory.toEntity();
        if(requestCreateCategory.getParentCategoryName() != null){
            String parentCategoryName = requestCreateCategory.getParentCategoryName();
            Optional<Category> parent = categoryRepository.findByName(parentCategoryName);
            // 만약 없으면 예외처리하는 코드 추가해야함.

            Category existingCategoryOptional = parent.get();
            category.setDepth(existingCategoryOptional.getDepth()+1);
            category.setParent(existingCategoryOptional);
            existingCategoryOptional.getChildren().add(category);

        }
        else{
            if(categoryRepository.existsByName(category.getName())){
                //throw new RuntimeException("동일한 이름의 카테고리 존재");
            }
            category.setDepth(0);
        }
        categoryRepository.save(category);
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
}
