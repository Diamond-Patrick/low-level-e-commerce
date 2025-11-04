package com.e_commerce_low_level.low_level_e_commerce.Entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "customer")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerEntity {

    @Id
    @Column(name = "id_customer")
    private String idCustomer;

    private String name;

    private String email;

    private String password;

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "idCustomer")
    private List<OrderEntity> orders;

}
