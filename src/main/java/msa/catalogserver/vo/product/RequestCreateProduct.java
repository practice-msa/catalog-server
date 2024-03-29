package msa.catalogserver.vo.product;

import lombok.Getter;
import lombok.NonNull;
import msa.catalogserver.customannotation.NonNegativeSize;
import msa.catalogserver.domain.Brand;
import msa.catalogserver.domain.Inventory;
import msa.catalogserver.domain.Product;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
public class RequestCreateProduct {
    @NotNull
    private String productName;
    @NonNegativeSize
    private Integer unitPrice;
    @NotNull
    private String imgUrl;
    @NonNegativeSize
    private Integer stock;
    @NotNull
    private String brandName;
    @NotNull
    private String categoryName;

    public Product toEntity(Brand brand, Inventory inventory){
        return Product.builder()
                .brand(brand)
                .imgUrl(imgUrl)
                .salesRate(0)
                .unitPrice(unitPrice)
                .inventory(inventory)
                .productName(productName)
                .categoryName(categoryName)
                .createdAt(new Date())
                .build();
    }
}
