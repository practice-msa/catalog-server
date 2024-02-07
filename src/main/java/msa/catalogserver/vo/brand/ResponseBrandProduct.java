package msa.catalogserver.vo.brand;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import msa.catalogserver.domain.Product;

@Data
@Builder
@AllArgsConstructor
public class ResponseBrandProduct {
    private String productName;
    private Integer unitPrice;
    private Integer salesRate;
    private String imgUrl;
    private Integer stock;

    public static ResponseBrandProduct from(Product product){
        return ResponseBrandProduct.builder()
                .productName(product.getProductName())
                .unitPrice(product.getUnitPrice())
                .salesRate(product.getSalesRate())
                .imgUrl(product.getImgUrl())
                .stock(product.getInventory().getStock())
                .build();
    }
}
