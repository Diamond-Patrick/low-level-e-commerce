package com.e_commerce_low_level.low_level_e_commerce.Entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
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
    @NotBlank(message = "id customer msut not be blank")
    private String idCustomer;

    @NotBlank(message = "name must not be blank")
    private String name;

    @Email(message = "invalid email")
    private String email;

    @NotBlank(message = "password must not be blank")
    private String password;

    @Embedded
    @Valid
    private Address address;

    @OneToMany(mappedBy = "idCustomer")
    private List<OrderEntity> orders;

}
