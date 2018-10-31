USE activos;


#UPDATE Dependencia set administrador='001' WHERE codigo=1;
#UPDATE Dependencia set administrador='002' WHERE codigo=2;

insert into Dependencia(nombre,codigo) values('Info','Eif-2');
insert into Dependencia(nombre,codigo) values('m','002');
insert into Funcionario(nombre,id) values('Ivania','001');
insert into Funcionario(nombre,id) values('Ana','002');
insert into Puesto (nombre) values ('Administrador');
insert into Puesto (nombre) values ('Registrador');

insert into Labor(dependencia, funcionario,puesto) values ('Eif-2','001',1);
insert into Labor(dependencia, funcionario,puesto) values ('Eif-2','002',1);

insert into Categoria(tipo,consecutivo) values('Silla Lab', 1);
insert into Categoria(tipo,consecutivo) values('Laptop', 1);

insert into Solicitud (fecha, cantidad, tipoAdquisicion, monto, dependencia, 
	funcionario, estado,comprobante) values(CURDATE(), 2,'Compra', 200000.0, 
	'EIF200', '001', 'recibido', '256');

insert into Usuario(funcionario,username,password) values ('001', '001', '001');
#insert into Activo(codigo,bien,labor) values ('LA1', , 1);