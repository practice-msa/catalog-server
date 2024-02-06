package msa.catalogserver.domain;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "inventory")
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "inventory_id")
    private Long id;

    @OneToOne(mappedBy = "inventory")
    private CatalogEntity catalog;

    private int stock;

}
