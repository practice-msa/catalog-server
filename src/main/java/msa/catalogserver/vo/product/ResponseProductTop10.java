package msa.catalogserver.vo.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import msa.catalogserver.domain.Product;

@Data
@Builder
@AllArgsConstructor
public class ResponseProductTop10 {
    private String productName;
    private Integer unitPrice;
    private Integer salesRate;
    private String imgUrl;
    private String brandName;
    private Integer stock;

    public static ResponseProductTop10 from(Product product){
        return ResponseProductTop10.builder()
                .productName(product.getProductName())
                .unitPrice(product.getUnitPrice())
                .salesRate(product.getSalesRate())
                .imgUrl(product.getImgUrl())
                .brandName(product.getBrand().getName())
                .stock(product.getInventory().getStock())
                .build();
    }
}
