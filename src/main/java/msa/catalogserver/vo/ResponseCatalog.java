package msa.catalogserver.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import msa.catalogserver.domain.Product;

import java.util.Date;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseCatalog {
    private String productId;
    private String productName;
    private Integer unitPrice;
    private Integer stock;
    private Date createdAt;

    public ResponseCatalog(String productId, String productName, Integer unitPrice, Integer stock, Date createdAt) {
        this.productId = productId;
        this.productName = productName;
        this.unitPrice = unitPrice;
        this.stock = stock;
        this.createdAt = createdAt;
    }

    public static ResponseCatalog from(Product catalogEntity) {
        return new ResponseCatalog(catalogEntity.getProductId(),catalogEntity.getProductName(),catalogEntity.getUnitPrice(), catalogEntity.getStock(), catalogEntity.getCreatedAt());
    }
}
