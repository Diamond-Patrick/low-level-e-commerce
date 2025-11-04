package com.e_commerce_low_level.low_level_e_commerce.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
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
    private String noRumah;

    @Column(name = "nama_jalan")
    private String namaJalan;

    private String kelurahan;

    private String kota;

    private String provinsi;

}
