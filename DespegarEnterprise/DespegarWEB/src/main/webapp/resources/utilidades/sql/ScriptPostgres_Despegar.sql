-- Table: personas
-- DROP TABLE personas;
CREATE TABLE personas
(
  identificacion character varying(255) NOT NULL,
  primer_apellido character varying(255),
  segundo_apellido character varying(255),
  celular character varying(255),
  email character varying(255),
  estado character varying(255),
  fecha_nacimiento timestamp without time zone,
  nombre character varying(255),
  password character varying(255),
  telefono character varying(255),
  tipo_documento character varying(255),
  admin boolean,
  CONSTRAINT personas_pkey PRIMARY KEY (identificacion)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE personas
  OWNER TO postgres;

-- Table: convenios
-- DROP TABLE convenios;
CREATE TABLE convenios
(
  id bigint NOT NULL,
  porcentaje double precision,
  tipo_entidad character varying(255),
  descripcion character(255),
  CONSTRAINT convenios_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE convenios
  OWNER TO postgres;

-- Table: datostranporteaereo
-- DROP TABLE datostranporteaereo;
CREATE TABLE datostranporteaereo
(
  codigo character varying(255) NOT NULL,
  codigo_vuelo character varying(255),
  destino character varying(255),
  fecha_vuelo timestamp without time zone,
  nombre_aerolinea character varying(255),
  numero_escalas integer,
  origen character varying(255),
  telefono_contacto character varying(255),
  CONSTRAINT datostranporteaereo_pkey PRIMARY KEY (codigo)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE datostranporteaereo
  OWNER TO postgres;

-- Table: datostranporteterrestre
-- DROP TABLE datostranporteterrestre;
CREATE TABLE datostranporteterrestre
(
  codigo character varying(255) NOT NULL,
  codigo_viaje character varying(255),
  destino character varying(255),
  empresa_transporte character varying(255),
  fecha_viaje timestamp without time zone,
  origen character varying(255),
  telefono_concato character varying(255),
  CONSTRAINT datostranporteterrestre_pkey PRIMARY KEY (codigo)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE datostranporteterrestre
  OWNER TO postgres;

-- Table: historialhoteles
-- DROP TABLE historialhoteles;
CREATE TABLE historialhoteles
(
  codigo character varying(255) NOT NULL,
  fecha timestamp without time zone,
  id_hotel character varying(255),
  nombre_hotel character varying(255),
  telefono character varying(255),
  valor_total_reserva numeric(12,2),
  history_hotel character varying(255) NOT NULL,
  CONSTRAINT historialhoteles_pkey PRIMARY KEY (codigo),
  CONSTRAINT fk51acfad384462240 FOREIGN KEY (history_hotel)
      REFERENCES personas (identificacion) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE historialhoteles
  OWNER TO postgres;

-- Table: historialpagos
-- DROP TABLE historialpagos;
CREATE TABLE historialpagos
(
  codigo character varying(255) NOT NULL,
  fecha timestamp without time zone,
  id_transaccion_pago character varying(255),
  pago_hotel character varying(255),
  pago_transporte character varying(255),
  history_pago character varying(255) NOT NULL,
  CONSTRAINT historialpagos_pkey PRIMARY KEY (codigo),
  CONSTRAINT fk9c5662ab3967cb5b FOREIGN KEY (history_pago)
      REFERENCES personas (identificacion) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE historialpagos
  OWNER TO postgres;


-- Table: historialtransportes
-- DROP TABLE historialtransportes;
CREATE TABLE historialtransportes
(
  codigo character varying(255) NOT NULL,
  fecha timestamp without time zone,
  tipo_transporte character varying(255),
  valor_total_transporte numeric(12,2),
  history_transp character varying(255) NOT NULL,
  datos_tran_aero character varying(255) NOT NULL,
  datos_tran_terr character varying(255) NOT NULL,
  CONSTRAINT historialtransportes_pkey PRIMARY KEY (codigo),
  CONSTRAINT fk6cb68688aa38c4ca FOREIGN KEY (history_transp)
      REFERENCES personas (identificacion) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk6cb68688ba7c5c96 FOREIGN KEY (datos_tran_aero)
      REFERENCES datostranporteaereo (codigo) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk6cb68688e41bbf96 FOREIGN KEY (datos_tran_terr)
      REFERENCES datostranporteterrestre (codigo) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE historialtransportes
  OWNER TO postgres;


-- Secuencias

-- Sequence: seq_convenios

-- DROP SEQUENCE seq_convenios;

CREATE SEQUENCE seq_convenios
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 999999999999999999
  START 20
  CACHE 1;
ALTER TABLE seq_convenios
  OWNER TO postgres;

