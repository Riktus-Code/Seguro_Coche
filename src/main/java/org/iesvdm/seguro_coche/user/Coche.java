package org.iesvdm.seguro_coche.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Coche {
   private String marca;
   private String modelo;
   private int anio;
   private String uso;
}
