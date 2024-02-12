package msa.catalogserver.vo.category;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import msa.catalogserver.domain.Product;

@Data
@Builder
@AllArgsConstructor
public class ResponseProductByCategory {
    private String productName;
    private Integer unitPrice;
    private Integer salesRate;
    private String imgUrl;
    private String brandName;
    private Integer stock;
    private String categoryName;

    public static ResponseProductByCategory from(Product product){
        return ResponseProductByCategory.builder()
                .productName(product.getProductName())
                .unitPrice(product.getUnitPrice())
                .salesRate(product.getSalesRate())
                .imgUrl(product.getImgUrl())
                .brandName(product.getBrand().getName())
                .stock(product.getInventory().getStock())
                .categoryName(product.getCategoryName())
                .build();
    }
}
