package org.iesvdm.seguro_coche.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CotizacionSeguro {

    private Long id;
    private String nombre;
    private String nif;
    private Integer edad;
    private Integer aniosCarnet;
    private String marca;
    private String modelo;
    private Integer anioMat;
    private String uso;
    private String tipoCobertura;
    private boolean asistencia;
    private boolean vehSustitucion;
    private BigDecimal precioTotal;





}
