/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  Jorge
 * Created: 21-may-2019
 */

			---------- Negocio ----------
INSERT INTO public.negocio(
	id_negocio, nombre, moneda_principal)
	VALUES (1, '', ''); --RELLENAR AQUI!!!
	
			---------- Usuario Admin ----------			
INSERT INTO public.puesto_trabajo(
	nombre_puesto,nivel_acceso,id_puesto)
	VALUES ('Desarrollador', 5, 1);
INSERT INTO public.personal(
	usuario, contrasenna, puesto_trabajonombre_puesto)
	VALUES ('admin', '7965801', 'Desarrollador');
INSERT INTO public.datos_personales(
	personalusuario, nombre, apellidos)
	VALUES ('admin', 'administrador', 'administrador');
	
			---------- Inicializar Ordenes ----------
INSERT INTO public.configuracion(
	nombre, valor,valor_string)
	VALUES ('O',1,null);			
	
			----------Generales----------
			
INSERT INTO public.configuracion(
	nombre, valor,valor_string)
	VALUES ('GENERAL_CAMBIO_MONEDA',25 ,null);
INSERT INTO public.configuracion(
	nombre, valor,valor_string)
	VALUES ('GENERAL_MULTIPLES_TURNOS',0 ,null);
INSERT INTO public.configuracion(
	nombre, valor,valor_string)
	VALUES ('GENERAL_CAJERO_PERMISOS_ESP',0 ,null);
INSERT INTO public.configuracion(
	nombre, valor,valor_string)
	VALUES ('GENERAL_CONSUMO_CASA_ESTADISTICAS',1 ,null);
			---------- Impresion ----------
INSERT INTO public.configuracion(
	nombre, valor,valor_string)
	VALUES ('PRINTING_TICKET_PAPER_SIZE',32,null );
INSERT INTO public.configuracion(
	nombre, valor,valor_string)
	VALUES ('PRINTING_TICKET_SEPARATOR_CHAR',null,'*');
INSERT INTO public.configuracion(
	nombre, valor,valor_string)
	VALUES ('PRINTING_CENTRAL_KITCHEN',0,null );
INSERT INTO public.configuracion(
	nombre, valor,valor_string)
	VALUES ('PRINTING_EXPENSES_IN_HAUSE_TICKETS',0,null );
INSERT INTO public.configuracion(
	nombre, valor,valor_string)
	VALUES ('PRINTING_PRINT_KITCHEN_TICKET',1,null );
INSERT INTO public.configuracion(
	nombre, valor,valor_string)
	VALUES ('PRINTING_CENTRAL_KITCHEN',0,null );
INSERT INTO public.configuracion(
	nombre, valor,valor_string)
	VALUES ('PRINTING_COPIES',0,null );
INSERT INTO public.configuracion(
	nombre, valor,valor_string)
	VALUES ('PRINTING_ROUNDING',1,null );

