package msa.catalogserver.vo.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import msa.catalogserver.domain.Product;

@Data
@Builder
@AllArgsConstructor
public class ResponseGetProduct {
    private String productName;
    private Integer unitPrice;
    private Integer salesRate;
    private String imgUrl;
    private String brandName;
    private Integer stock;
    private String categoryName;

    public static ResponseGetProduct from(Product product){
        return ResponseGetProduct.builder()
                .productName(product.getProductName())
                .unitPrice(product.getUnitPrice())
                .salesRate(product.getSalesRate())
                .imgUrl(product.getImgUrl())
                .brandName(product.getBrand().getName())
                .stock(product.getInventory().getStock())
                .categoryName(product.getCategoryName())
                .build();
    }

    public boolean isEmpty() {
        // 예를 들어 id가 null이면 비어있는 것으로 판단
        return this.productName == null;
    }
}
