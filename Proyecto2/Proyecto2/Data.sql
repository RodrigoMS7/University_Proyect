use Activos;

#insert into Usuario (username,password,funcionario) values ('ivania','001',(SELECT id FROM Funcionario WHERE nombre ='Ivania'));

#insert into Dependencia 

-- insert into Dependencia (codigo) values ('EIF200');


-- insert into Puesto (nombre) values ('Administrador');

-- insert into Labor(dependencia, funcionario,puesto) values ('EIF200','001',1); 
-- insert into Labor(dependencia, funcionario,puesto) values (2,'002',1); 

-- insert into Categoria(tipo,consecutivo) values('Silla Lab', 1);
-- insert into Categoria(tipo,consecutivo) values('Laptop', 1);
insert into Solicitud (fecha, cantidad, tipoAdquisicion, monto, dependencia, funcionario, estado,comprobante) values(CURDATE(), 2,'Compra', 200000.0, 'EIF200', '001', 'recibido', '256');

insert into Bien(cantidad,modelo,marca,precio,solicitud,categoria) values (20,'Silla Lab','PPP',20000,1,1);
insert into Bien(cantidad,modelo,marca,precio,solicitud,categoria) values (10,'Monitor','Lenovo',150000,1,2);

insert into Activo(codigo,bien,labor) values ('SI1', 1, 1);
insert into Activo(codigo,bien,labor) values ('LA1', 2 , 1);