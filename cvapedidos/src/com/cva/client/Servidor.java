/**
 * Servidor.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.cva.client;

public interface Servidor extends javax.xml.rpc.Service {
    public java.lang.String getServidorPortAddress();

    public com.cva.client.ServidorPortType getServidorPort() throws javax.xml.rpc.ServiceException;

    public com.cva.client.ServidorPortType getServidorPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
