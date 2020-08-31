CREATE TABLE PERIODO_PAGO (
  id bigint primary key,
  fecha_inicio date NOT NULL,
  fecha_fin date NOT NULL,
  estado_pago VARCHAR(20),
  orden smallint NOT null
);

CREATE TABLE EMPLEADO (
  id bigint primary key,
  nombres varchar(30) NOT NULL,
  apellidos varchar(30) NOT NULL,
  salario numeric(8) NOT NULL,
  riesgo_laboral varchar(30) not null
) ;

CREATE TABLE RECARGO (
  id bigserial primary key,
  id_periodo bigint NOT NULL,
  id_empleado bigint NOT NULL,
  horas DOUBLE PRECISION NOT NULL,
  concepto varchar(25) NOT NULL,
  orden smallint NOT null,
  UNIQUE(concepto,id_periodo,id_empleado),
  CONSTRAINT RECARGO_idPeriodo_PERIODO_PAGO_id FOREIGN KEY (id_periodo) REFERENCES PERIODO_PAGO(id),
  CONSTRAINT RECARGO_idEmpleado_EMPLEADO_id FOREIGN KEY (id_empleado) REFERENCES EMPLEADO(id)
) ;

CREATE TABLE DEVENGADO (
  id bigserial primary key,
  id_periodo bigint NOT NULL,
  id_empleado bigint NOT NULL,
  concepto varchar(25) NOT NULL,
  monto numeric(10,2) NOT NULL,
  orden smallint NOT null,
  UNIQUE(concepto,id_periodo,id_empleado),
  CONSTRAINT DEVENGADO_idPeriodo_PERIODO_PAGO_id FOREIGN KEY (id_periodo) REFERENCES PERIODO_PAGO(id),
  CONSTRAINT DEVENGADO_idEmpleado_EMPLEADO_id FOREIGN KEY (id_empleado) REFERENCES EMPLEADO(id)
) ;

CREATE TABLE DESCUENTO (
  id bigserial primary key,
  id_periodo bigint NOT NULL,
  id_empleado bigint NOT NULL,
  grupo varchar(25) NOT NULL,
  concepto varchar(25) NOT NULL,
  porcentaje numeric(7,5) NOT NULL,
  base_gravable numeric(10,2),
  monto numeric(10,2) NOT NULL,
  orden smallint NOT null,
  UNIQUE(concepto,id_periodo,id_empleado),
  CONSTRAINT DESCUENTO_idPeriodo_PERIODO_PAGO_id FOREIGN KEY (id_periodo) REFERENCES PERIODO_PAGO(id),
  CONSTRAINT DESCUENTO_idEmpleado_EMPLEADO_id FOREIGN KEY (id_empleado) REFERENCES EMPLEADO(id)
) ;