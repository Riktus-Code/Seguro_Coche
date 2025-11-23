package org.iesvdm.seguro_coche.dto;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;

@Slf4j
@Repository
public class CotizacionRepository {
    private JdbcTemplate jdbcTemplate;
    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public Long guardarCotizacion(CotizacionDTO cotizacion) {
        String sql = """
            INSERT INTO cotizacion_seguro 
            (nombre, edad, anios_carnet, marca, modelo, anio_mat, uso, 
             tipo_cobertura, asistencia, veh_sustitucion, precio_total)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
        """;

        KeyHolder keyHolder = new GeneratedKeyHolder();

        String ids [] = {"id"};
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, ids);
            ps.setString(1, cotizacion.getConductor().getNombre());
            ps.setInt(2, cotizacion.getConductor().getAnios());
            ps.setInt(3, cotizacion.getConductor().getAniosCarnet());
            ps.setString(4, cotizacion.getCoche().getMarca());
            ps.setString(5, cotizacion.getCoche().getModelo());
            ps.setInt(6, cotizacion.getCoche().getAnio());
            ps.setString(7, cotizacion.getCoche().getUso());
            ps.setString(8, cotizacion.getCobertura().getTipoCobertura());
            ps.setBoolean(9, cotizacion.getCobertura().isAsistencia());
            ps.setBoolean(10, cotizacion.getCobertura().isVehSustitucion());
            ps.setDouble(11, cotizacion.getPrecioTotal());
            return ps;
        }, keyHolder);

        return keyHolder.getKey().longValue();
    }

}
