/**
 * TestServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.bituos.webservices;

public class TestServiceLocator extends org.apache.axis.client.Service implements com.bituos.webservices.TestService {

    public TestServiceLocator() {
    }


    public TestServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public TestServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for Test
    private java.lang.String Test_address = "http://localhost:8080/struts2HibertaneWebService/services/Test";

    public java.lang.String getTestAddress() {
        return Test_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String TestWSDDServiceName = "Test";

    public java.lang.String getTestWSDDServiceName() {
        return TestWSDDServiceName;
    }

    public void setTestWSDDServiceName(java.lang.String name) {
        TestWSDDServiceName = name;
    }

    public com.bituos.webservices.Test getTest() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(Test_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getTest(endpoint);
    }

    public com.bituos.webservices.Test getTest(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.bituos.webservices.TestSoapBindingStub _stub = new com.bituos.webservices.TestSoapBindingStub(portAddress, this);
            _stub.setPortName(getTestWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setTestEndpointAddress(java.lang.String address) {
        Test_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.bituos.webservices.Test.class.isAssignableFrom(serviceEndpointInterface)) {
                com.bituos.webservices.TestSoapBindingStub _stub = new com.bituos.webservices.TestSoapBindingStub(new java.net.URL(Test_address), this);
                _stub.setPortName(getTestWSDDServiceName());
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
        if ("Test".equals(inputPortName)) {
            return getTest();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://webservices.bituos.com", "TestService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://webservices.bituos.com", "Test"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("Test".equals(portName)) {
            setTestEndpointAddress(address);
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
