package com.e_commerce_low_level.low_level_e_commerce.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Address {

    @Column(name = "no_rumah")
    @NotBlank(message = "no rumah must not be empty")
    private String noRumah;

    @Column(name = "nama_jalan")
    @NotBlank(message = "nama jalan must not be empty")
    private String namaJalan;

    @NotBlank(message = "kelurahan must not be empty")
    private String kelurahan;

    @NotBlank(message = "kota must not be empty")
    private String kota;

    @NotBlank(message = "provinsi must not be empty")
    private String provinsi;

}
