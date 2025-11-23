package org.iesvdm.seguro_coche.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Cobertura {
    private String tipoCobertura;
    private boolean asistencia;
    private boolean vehSustitucion;
}
