package msa.catalogserver.vo.category;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import msa.catalogserver.domain.Category;

@Data
@AllArgsConstructor
@Builder
public class ResponseGetCategory {
    private String childrenName;

    public static ResponseGetCategory from(Category category){
        return ResponseGetCategory.builder()
                .childrenName(category.getName())
                .build();
    }
}
