package org.iesvdm.seguro_coche.dto;

import lombok.extern.slf4j.Slf4j;
import org.iesvdm.seguro_coche.user.Cobertura;
import org.iesvdm.seguro_coche.user.Coche;
import org.iesvdm.seguro_coche.user.Conductor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;

@Slf4j
@Repository
public class CotizacionDTOImpl implements CotizacionDTO {



    private Cobertura cobertura;

    private Conductor conductor;

    private Coche coche;

    private double precioTotal;

    // Constructor vacío
    public CotizacionDTOImpl() {
        this.conductor = new Conductor();
        this.coche = new Coche();
        this.cobertura = new Cobertura();
        this.precioTotal = 0.0;
    }



    @Override
    public Conductor getConductor() {
        return conductor;
    }

    @Override
    public void setCondunctor(Conductor conductor) {
        this.conductor = conductor;
    }

    @Override
    public Cobertura getCobertura() {
        return cobertura;
    }

    @Override
    public void setCobertura(Cobertura cobertura) {
        this.cobertura = cobertura;

    }

    @Override
    public Coche getCoche() {
        return coche;
    }

    @Override
    public void setCoche(Coche coche) {
        this.coche = coche;
    }

    @Override
    public double getPrecioTotal() {
        return precioTotal;
    }



    @Override
    public void setPrecioTotal(double precioTotal) {
        this.precioTotal = precioTotal;
    }


}
