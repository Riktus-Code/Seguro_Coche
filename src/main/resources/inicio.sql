
create database seguro_coche;
create table cotizacion_seguro  (
id BIGINT auto_increment primary key ,
    nombre varchar(255),
    edad INT ,
    anios_carnet INT,
    marca varchar(255),
    modelo varchar(255),
    anio_mat varchar(255),
    uso varchar(255),
    tipo_cobertura varchar(255),
    asistencia boolean,
    veh_sustitucion boolean,
    precio_total double
                                      );