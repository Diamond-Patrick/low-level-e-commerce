package com.e_commerce_low_level.low_level_e_commerce.Entities;

import java.util.List;

import com.e_commerce_low_level.low_level_e_commerce.Interface.Login;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
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
    // @NotBlank(message = "id ust not be blank")
    private String id;

    @Column(name = "shop_name")
    @NotBlank(message = "shop name must not be blank")
    private String shopName;

    @Column(name = "owner_name")
    @NotBlank(message = "shop name must not be blank")
    private String ownerName;

    @NotBlank(message = "email must not be blank", groups = Login.class)
    @Email(message = "email invalid", groups = Login.class)
    private String email;

    @NotBlank(message = "password must not be blank", groups = Login.class)
    private String password;

    @Embedded
    @Valid
    private Address address;

    @ManyToMany
    @JoinTable(name = "omset", joinColumns = @JoinColumn(name = "id_seller", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "kode_product", referencedColumnName = "kode_product"))
    private List<ProductEntity> product;
}
