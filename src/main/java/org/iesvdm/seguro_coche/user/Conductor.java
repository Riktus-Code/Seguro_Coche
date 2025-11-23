package org.iesvdm.seguro_coche.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Conductor {
    private String nombre;
    private int anios;
    private int aniosCarnet;
}
