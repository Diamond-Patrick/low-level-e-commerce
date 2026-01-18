package com.e_commerce_low_level.low_level_e_commerce.Entities;

import java.util.List;

import com.e_commerce_low_level.low_level_e_commerce.Interface.Login;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.groups.Default;
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

    @NotBlank(message = "name must not be blank")
    private String name;

    @Email(message = "invalid email", groups = { Login.class, Default.class })
    @NotBlank(message = "email must not be blank", groups = { Login.class, Default.class })
    private String email;

    @NotBlank(message = "password must not be blank", groups = { Login.class, Default.class })
    private String password;

    @Embedded
    @Valid
    private Address address;

    @OneToMany(mappedBy = "idCustomer")
    private List<OrderEntity> orders;

}
