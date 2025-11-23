package org.iesvdm.seguro_coche.dto;

import org.iesvdm.seguro_coche.user.Cobertura;
import org.iesvdm.seguro_coche.user.Coche;
import org.iesvdm.seguro_coche.user.Conductor;

public interface CotizacionDTO {

    Conductor getConductor();
    void setCondunctor(Conductor conductor);
    Cobertura getCobertura();
    void setCobertura(Cobertura cobertura);
    Coche getCoche();
    void setCoche(Coche coche);

    double getPrecioTotal();
    void setPrecioTotal(double precioTotal);


}
