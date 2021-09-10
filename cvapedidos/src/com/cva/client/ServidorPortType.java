/**
 * ServidorPortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.cva.client;

public interface ServidorPortType extends java.rmi.Remote {

    /**
     * El web services recibe un array con 3 parametros en el llamado
     * a PedidoWeb:<br><br>
     * 	usuario, contraseña y un xml estructurado de la siguiente manera:<br><br>
     * 	< ?xml version="1.0" encoding="ISO-8859-1"?><br>
     * < PEDIDO>    <br>
     * < NumOC>Orden 123456789< /NumOC>   // numero de oc en caso de que
     * se requiera aparesca en tu facturacion<br>
     * < Paqueteria>0< /Paqueteria>       // paqueteria de envio (catalogo)<br>
     * < CodigoSucursal>1< /CodigoSucursal>  // codigo sucursal del pedido<br>
     * < PedidoBO>N< /PedidoBO>              // si el pedido genera BO, actualmente
     * solo se levantan pedidos de producto en existencia, no se genera BO<br>
     * < Observaciones>Pedidos de prueba desde web services< /Observaciones>
     * // observaciones para el pedido<br>
     * < productos>   //comienza los productos<br>
     * < producto>         <br>
     * < clave>PR-1531< /clave>          // clave de producto<br>
     * < cantidad>1< /cantidad>          // cantidad<br>
     * < /producto><br>
     * < /productos><br>
     * < TipoFlete>SF< /TipoFlete>  // tipo de flete: SF: SIn flete, FF:
     * Flete cobrado en la factura de CVA, FS: Flete cobrado en la factura
     * de CVA Asegurado <br>
     * < Calle>SAN MATEO PIAS< /Calle>  // calle de envio<br>
     * < Numero>1< /Numero> // numero de envio (max 20 caracteres)<br>
     * < NumeroInt>< /NumeroInt> // numero int de envio (max 20 caracteres)<br>
     * < Colonia>SF< /Colonia> // colonia de envio<br>
     * < CP>12345< /CP> // CP de envio<br>
     * < Estado>1< /Estado> // estado de envio (catalogo)<br>
     * < Ciudad>1< /Ciudad> // ciudad de envio (catalogo)<br>
     * < Atencion>Atencion< /Atencion> // con atencion a quien
     * < TipoEnvio>1</ TipoEnvio> // 1 domicilio, 2 ocurre
     * < /PEDIDO><br>
     */
    public void pedidoWeb(java.lang.String usuario, java.lang.String PWD, java.lang.String XMLOC, javax.xml.rpc.holders.StringHolder error, javax.xml.rpc.holders.StringHolder estado, javax.xml.rpc.holders.StringHolder pedido, javax.xml.rpc.holders.StringHolder total, javax.xml.rpc.holders.StringHolder agentemail, javax.xml.rpc.holders.StringHolder almacenmail) throws java.rmi.RemoteException;

    /**
     * El web services recibe un array con 2 parametros en el llamado
     * a ListaPedidos:<br><br>
     * 	usuario, contraseña<br><br>
     * 	Retorna un arreglo con 2 valores<br>
     * 	error -> en caso de datos de conexion invalidos regresa el error,
     * ok cuando el usuario es correcto<br>
     * 	pedidos -> xml con la informacion de lo pedidos activos estructurado
     * de la siguiente manera:<br>
     * 	<?xml version="1.0" encoding="ISO-8859-1"?><br>
     * < PEDIDOS><br>
     * < pedido><br>
     * < Numero>GB-1111< /Numero> // Numero de pedido <br>
     * < Total>1243.52< /Total>  // total del pedido<br>
     * < Moneda>MN< /Moneda> // moneda del pedido<br>
     * < NumOC>JDU564DSD< /NumOC> // orden de compra del pedido<br>
     * < Almacen>< /Almacen> // almacen de donde se surte el pedido<br>
     * < Asignado>< /Asignado>   // datos del domicilio de envio<br>
     * < FechaAsignado>< /FechaAsignado><br>
     * < /pedido><br>
     * < /PEDIDOS>
     */
    public void listaPedidos(java.lang.String usuario, java.lang.String PWD, javax.xml.rpc.holders.StringHolder error, javax.xml.rpc.holders.StringHolder pedidos) throws java.rmi.RemoteException;

    /**
     * El web services recibe un array con 3 parametros en el llamado
     * a PedidoOrdenCompra:<br><br>
     * 	usuario, contraseña y la orden de compra<br><br>
     * 	Retorna un arreglo con 2 valores<br>
     * 	error -> en caso de no encontrar el pedido, o datos de conexion invalidos
     * regresa el error, ok cuando el pedido se encuentra<br>
     * 	pedido -> numero del pedido asociado a la orden de compra, en caso
     * de tener mas de un pedido, se separan por coma<br>
     * 	solo considera pedidos afectados, cancelados no los toma en cuenta
     */
    public void pedidoOrdenCompra(java.lang.String usuario, java.lang.String PWD, java.lang.String ORDEN, javax.xml.rpc.holders.StringHolder error, javax.xml.rpc.holders.StringHolder pedido) throws java.rmi.RemoteException;

    /**
     * El web services recibe un array con 3 parametros en el llamado
     * a ConsultaPedido:<br><br>
     * 	usuario, contraseña y el numero de pedido ejemplo (GB-1111)<br><br>
     * 	Retorna un arreglo con 2 valores<br>
     * 	error -> en caso de no encontrar el pedido, o datos de conexion invalidos
     * regresa el error, ok cuando el pedido se encuentra<br>
     * 	pedido -> xml con la informacion del pedido estructurado de la siguiente
     * manera:<br>
     * 	<?xml version="1.0" encoding="ISO-8859-1"?><br>
     * < PEDIDO><br>
     * < Estatus>PENDIENTE< /Estatus>  // PENDIENTE= Pendiente de facturar,
     * CANCELADO= PEDIDO CANCELADO, FACTURADO= PEDIDO YA FACTURADO<br> 
     * < Factura>< /Factura>  // Regresa numero de factura cuando ya se facturo<br>
     * < Total>1243.52< /Total>  // total del pedido<br>
     * < Moneda>MN< /Moneda> // moneda del pedido<br>
     * < FechaPedido>01/01/2015< /FechaPedido> // fecha<br>
     * < NumOC>JDU564DSD< /NumOC> // orden de compra del pedido<br>
     * < Almacen>< /Almacen> // almacen de donde se surte el pedido<br>
     * < Observaciones>< /Observaciones> // obervaciones del pedido<br>
     * < CalleEnvio>< /CalleEnvio>   // datos del domicilio de envio<br>
     * < NumeroEnvio>< /NumeroEnvio><br>
     * < NumeroIntEnvio>< /NumeroIntEnvio><br>
     * < ColoniaEnvio>< /ColoniaEnvio><br>
     * < CPEnvio>< /CPEnvio><br>
     * < EstadoEnvio>< /EstadoEnvio><br>
     * < CiudadEnvio>< /CiudadEnvio><br>
     * < Atencion>< /Atencion><br>
     * < Flete>< /Flete> // tipo de flete<br>
     * < TipoEnvio>OCURRE< /TipoEnvio> // tipo de envio en caso de, domicilio
     * u ocurre<br>
     * < Paqueteria>< /Paqueteria> // paqueteria para el envio<br>
     * < Guia>< /Guia> // guia para el envio (cuando esta facturado)<br>
     * < productos> //listado de productos en el pedido<br>
     * < producto><br>
     * < clave>AC-3767< /clave><br>
     * < fabricante>DMA6000< /fabricante><br>
     * < cantidad>1< /cantidad><br>
     * < precioSinIva>1072< /precioSinIva><br>
     * < iva>171.52< /iva><br>
     * < /producto>< /productos><br>
     * < /PEDIDO>
     */
    public void consultaPedido(java.lang.String usuario, java.lang.String PWD, java.lang.String PEDIDO, javax.xml.rpc.holders.StringHolder error, javax.xml.rpc.holders.StringHolder pedido) throws java.rmi.RemoteException;
}
