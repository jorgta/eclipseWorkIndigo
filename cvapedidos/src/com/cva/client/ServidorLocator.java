/**
 * ServidorLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.cva.client;

public class ServidorLocator extends org.apache.axis.client.Service implements com.cva.client.Servidor {

    public ServidorLocator() {
    }


    public ServidorLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public ServidorLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for ServidorPort
    private java.lang.String ServidorPort_address = "http://www.grupocva.com/pedidos_web/pedidos_ws_cva.php";

    public java.lang.String getServidorPortAddress() {
        return ServidorPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String ServidorPortWSDDServiceName = "ServidorPort";

    public java.lang.String getServidorPortWSDDServiceName() {
        return ServidorPortWSDDServiceName;
    }

    public void setServidorPortWSDDServiceName(java.lang.String name) {
        ServidorPortWSDDServiceName = name;
    }

    public com.cva.client.ServidorPortType getServidorPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(ServidorPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getServidorPort(endpoint);
    }

    public com.cva.client.ServidorPortType getServidorPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.cva.client.ServidorBindingStub _stub = new com.cva.client.ServidorBindingStub(portAddress, this);
            _stub.setPortName(getServidorPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setServidorPortEndpointAddress(java.lang.String address) {
        ServidorPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.cva.client.ServidorPortType.class.isAssignableFrom(serviceEndpointInterface)) {
                com.cva.client.ServidorBindingStub _stub = new com.cva.client.ServidorBindingStub(new java.net.URL(ServidorPort_address), this);
                _stub.setPortName(getServidorPortWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("ServidorPort".equals(inputPortName)) {
            return getServidorPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("urn:Servidor", "Servidor");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("urn:Servidor", "ServidorPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("ServidorPort".equals(portName)) {
            setServidorPortEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
