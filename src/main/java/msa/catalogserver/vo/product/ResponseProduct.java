package msa.catalogserver.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import msa.catalogserver.domain.Product;

import java.util.Date;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseProduct {
    private String productId;
    private String productName;
    private Integer unitPrice;
    private Integer stock;
    private Date createdAt;

    public ResponseProduct(String productId, String productName, Integer unitPrice, Integer stock, Date createdAt) {
        this.productId = productId;
        this.productName = productName;
        this.unitPrice = unitPrice;
        this.stock = stock;
        this.createdAt = createdAt;
    }

    public static ResponseProduct from(Product product) {
        return new ResponseProduct(product.getProductId(),product.getProductName(),product.getUnitPrice(), product.getStock(), product.getCreatedAt());
    }
}
