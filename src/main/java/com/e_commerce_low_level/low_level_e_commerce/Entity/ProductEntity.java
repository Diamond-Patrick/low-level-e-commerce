package com.e_commerce_low_level.low_level_e_commerce.Entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "product")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductEntity {

    @Id
    @Column(name = "kode_product")
    private String kodeProduct;

    private String name;

    private Double harga;

    private Integer stock;

    @Lob
    private byte[] gambar;

    private String description;

    @OneToMany
    private List<OrderEntity> oders;

    @ManyToMany(mappedBy = "product")
    private List<SellerEntity> seller;
}
