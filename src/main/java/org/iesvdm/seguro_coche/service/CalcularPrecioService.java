package org.iesvdm.seguro_coche.service;

import org.iesvdm.seguro_coche.dto.CotizacionDTO;
import org.springframework.stereotype.Service;

@Service
public class CalcularPrecioService {
    private static final double precioBase = 200.0;
    private static final double recargoJoven = 150.0;
    private static final double recargoNovato = 100.0;
    private static final double regargoPro = 200.0;
    private static final double precioTerceros = 20.0;
    private static final double precioTercerosLunas = 100.0;
    private static final double precioTodoRiesgo = 400.0;
    private static final double precioAsistencia = 50.0;
    private static final double precioVehSus = 80.0;
    public double calcularPrecioTotal(CotizacionDTO cotizacionDTO) {
        double precio = precioBase;

        if(cotizacionDTO.getConductor().getAnios()<25){
            precio += recargoJoven;
        }
        if(cotizacionDTO.getConductor().getAniosCarnet()<2){
            precio += recargoNovato;
        }

        if("profesional".equalsIgnoreCase(cotizacionDTO.getCoche().getUso())){
            precio += regargoPro;
        }

        if("terceros".equalsIgnoreCase(cotizacionDTO.getCobertura().getTipoCobertura())){
            precio += precioTerceros;
        } else if ("terceros_lunas".equalsIgnoreCase(cotizacionDTO.getCobertura().getTipoCobertura())) {
            precio += precioTercerosLunas;

        }else{
            precio += precioTodoRiesgo;
        }

        if(cotizacionDTO.getCobertura().isAsistencia()){
            precio += precioAsistencia;
        }
        if(cotizacionDTO.getCobertura().isVehSustitucion()){
            precio += precioVehSus;
        }

        return precio;

    }
}
