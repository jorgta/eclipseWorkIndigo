/**
 * ApiPhotosServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.bituos;

public class ApiPhotosServiceLocator extends org.apache.axis.client.Service implements org.bituos.ApiPhotosService {

    public ApiPhotosServiceLocator() {
    }


    public ApiPhotosServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public ApiPhotosServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for ApiPhotos
    private java.lang.String ApiPhotos_address = "http://localhost:8080/WebServiceTest/services/ApiPhotos";

    public java.lang.String getApiPhotosAddress() {
        return ApiPhotos_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String ApiPhotosWSDDServiceName = "ApiPhotos";

    public java.lang.String getApiPhotosWSDDServiceName() {
        return ApiPhotosWSDDServiceName;
    }

    public void setApiPhotosWSDDServiceName(java.lang.String name) {
        ApiPhotosWSDDServiceName = name;
    }

    public org.bituos.ApiPhotos getApiPhotos() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(ApiPhotos_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getApiPhotos(endpoint);
    }

    public org.bituos.ApiPhotos getApiPhotos(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            org.bituos.ApiPhotosSoapBindingStub _stub = new org.bituos.ApiPhotosSoapBindingStub(portAddress, this);
            _stub.setPortName(getApiPhotosWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setApiPhotosEndpointAddress(java.lang.String address) {
        ApiPhotos_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (org.bituos.ApiPhotos.class.isAssignableFrom(serviceEndpointInterface)) {
                org.bituos.ApiPhotosSoapBindingStub _stub = new org.bituos.ApiPhotosSoapBindingStub(new java.net.URL(ApiPhotos_address), this);
                _stub.setPortName(getApiPhotosWSDDServiceName());
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
        if ("ApiPhotos".equals(inputPortName)) {
            return getApiPhotos();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://bituos.org", "ApiPhotosService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://bituos.org", "ApiPhotos"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("ApiPhotos".equals(portName)) {
            setApiPhotosEndpointAddress(address);
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
