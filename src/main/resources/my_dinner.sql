
CREATE DATABASE my_dinner CHARACTER SET utf8 COLLATE utf8_general_ci;

USE my_dinner;

CREATE TABLE cliente (
  id bigint NOT NULL AUTO_INCREMENT,
  apellido_materno varchar(255) DEFAULT NULL,
  apellido_paterno varchar(255) DEFAULT NULL,
  create_at date DEFAULT NULL,
  domicilio_principal varchar(255) DEFAULT NULL,
  email varchar(255) NOT NULL,
  nombre varchar(255) NOT NULL,
  telefono varchar(255) DEFAULT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY uc_email (email)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;


CREATE TABLE platillo (
  id bigint NOT NULL AUTO_INCREMENT,
  cantidad int DEFAULT NULL,
  create_at date NOT NULL,
  descripcion varchar(255) NOT NULL,
  estatus bit(1) NOT NULL,
  nombre varchar(255) NOT NULL,
  precio double NOT NULL,
  tipo_cocina int NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

CREATE TABLE orden (
  id bigint NOT NULL AUTO_INCREMENT,
  domicilio_cliente varchar(255) NOT NULL,
  fecha_pedido date NOT NULL,
  hora_pedido time NOT NULL,
  monto_total double NOT NULL,
  sub_total double NOT NULL,
  id_cliente bigint NOT NULL,
  PRIMARY KEY (id),
  KEY FK_ID_CLIENTE (id_cliente),
  CONSTRAINT FK_ID_CLIENTE FOREIGN KEY (id_cliente) REFERENCES cliente (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE orden_platillo(
    id_orden bigint NOT NULL,
    id_platillo bigint NOT NULL,
    FOREIGN KEY (id_orden) REFERENCES orden(id), 
    FOREIGN KEY (id_platillo) REFERENCES platillo(id),
    UNIQUE (id_orden, id_platillo)
);
