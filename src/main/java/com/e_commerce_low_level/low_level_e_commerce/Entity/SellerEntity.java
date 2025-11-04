package com.e_commerce_low_level.low_level_e_commerce.Entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "seller")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SellerEntity {

    @Id
    private String id;

    @Column(name = "shop_name")
    private String shopName;

    @Column(name = "owner_name")
    private String ownerName;

    private String email;

    private String password;

    @Embedded
    private Address address;

    @ManyToMany
    @JoinTable(name = "omset", joinColumns = @JoinColumn(name = "id_seller", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "kode_product", referencedColumnName = "kode_product"))
    private List<ProductEntity> product;
}
