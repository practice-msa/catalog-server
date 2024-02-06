package msa.catalogserver.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "product")
@NoArgsConstructor
public class Product implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="product_id")
    private Long id;

    @Column(nullable = false)
    private String productId;

    @Column(nullable = false)
    private String productName;

    @Column(nullable = false)
    private Integer stock;

    @Column(nullable = false)
    private Integer unitPrice;

    @Column(nullable = false)
    private Integer salesRate;

    // 카테고리도 추가해야함. enum으ㅡ

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name="inventory_id")
    private Inventory inventory;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name="brand_id")
    private Brand brand;

    @OneToMany(mappedBy = "product")
    private List<Scrap> scraps;

    @Column(nullable = false,updatable = false,insertable = false)
    @CreationTimestamp
    private Date createdAt;
}
