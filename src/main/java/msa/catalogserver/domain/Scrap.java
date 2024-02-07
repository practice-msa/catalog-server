package msa.catalogserver.domain;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "scrap")
public class Scrap {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="scrap_id")
    private Long id;

    @Column(nullable = false)
    private String user_id;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id")
    private Product product;

}
