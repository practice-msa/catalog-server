package msa.catalogserver.vo.brand;

import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class RequestCreateBrand {
    @NotNull
    private String name;
    @NotNull
    private String logo;
    @NotNull
    @Size(max = 20, message = "description length must be between 0 and 20 characters")
    private String description;
}
