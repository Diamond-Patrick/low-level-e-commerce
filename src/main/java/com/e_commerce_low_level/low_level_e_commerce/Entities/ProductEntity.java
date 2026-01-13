package com.e_commerce_low_level.low_level_e_commerce.Entities;

import java.util.List;

import com.e_commerce_low_level.low_level_e_commerce.Interface.Essential;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
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
    @NotBlank(message = "kode product must not be blank", groups = Essential.class)
    private String kodeProduct;

    @NotBlank(message = "name must not be blank", groups = Essential.class)
    private String name;

    @Positive(groups = Essential.class)
    private Double harga;

    @Positive(groups = Essential.class)
    private Integer stock;

    @NotNull
    @Size(max = 5_000_000)
    @Lob
    private byte[] gambar;

    @NotBlank(message = "description must not be blank", groups = Essential.class)
    private String description;

    @OneToMany
    private List<OrderEntity> oders;

    @ManyToMany(mappedBy = "product", fetch = FetchType.EAGER)
    private List<SellerEntity> seller;
}
