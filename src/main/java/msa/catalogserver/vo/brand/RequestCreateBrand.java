package msa.catalogserver.vo.brand;

import lombok.Data;

import javax.persistence.Column;

@Data
public class RequestCreateBrand {
    private String name;

    private String logo;

    private String description;
}
