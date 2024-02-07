package msa.catalogserver.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name= "brand")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="brand_id")
    private Long id;

    @Column(nullable = false)
    private String name;

    private String logo;

    @Column(nullable = false)
    private String description;

    @OneToMany(mappedBy = "brand")
    private List<Product> products;
}
