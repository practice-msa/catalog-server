package msa.catalogserver.vo.product;

import lombok.Getter;
import msa.catalogserver.domain.Brand;
import msa.catalogserver.domain.Inventory;
import msa.catalogserver.domain.Product;

import java.util.Date;

@Getter
public class RequestCreateProduct {
    private String productName;
    private Integer unitPrice;
    private String imgUrl;
    private Integer stock;
    private String brandName;

    public Product toEntity(Brand brand, Inventory inventory){
        return Product.builder()
                .brand(brand)
                .imgUrl(imgUrl)
                .salesRate(0)
                .unitPrice(unitPrice)
                .inventory(inventory)
                .productName(productName)
                .createdAt(new Date())
                .build();
    }
}
