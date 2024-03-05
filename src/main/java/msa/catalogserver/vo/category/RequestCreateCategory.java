package msa.catalogserver.vo.category;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import msa.catalogserver.domain.Category;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class RequestCreateCategory {

    private String parentCategoryName;
    @NotNull
    private String name;

    public Category toEntity(){
        return Category.builder()
                .name(name)
                .children(new ArrayList<>())
                .build();
    }

}
