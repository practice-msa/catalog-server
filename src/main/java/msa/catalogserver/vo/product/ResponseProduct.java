package msa.catalogserver.vo.product;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import msa.catalogserver.domain.Product;

import java.util.Date;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseProduct {
    private String productName;
    private Integer unitPrice;
    private Date createdAt;

    public ResponseProduct(String productName, Integer unitPrice, Date createdAt) {
        this.productName = productName;
        this.unitPrice = unitPrice;
        this.createdAt = createdAt;
    }

    public static ResponseProduct from(Product product) {
        return new ResponseProduct(product.getProductName(),product.getUnitPrice(), product.getCreatedAt());
    }
}
