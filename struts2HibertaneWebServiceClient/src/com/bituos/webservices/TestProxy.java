package com.bituos.webservices;

public class TestProxy implements com.bituos.webservices.Test {
  private String _endpoint = null;
  private com.bituos.webservices.Test test = null;
  
  public TestProxy() {
    _initTestProxy();
  }
  
  public TestProxy(String endpoint) {
    _endpoint = endpoint;
    _initTestProxy();
  }
  
  private void _initTestProxy() {
    try {
      test = (new com.bituos.webservices.TestServiceLocator()).getTest();
      if (test != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)test)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)test)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (test != null)
      ((javax.xml.rpc.Stub)test)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.bituos.webservices.Test getTest() {
    if (test == null)
      _initTestProxy();
    return test;
  }
  
  public java.lang.String getName() throws java.rmi.RemoteException{
    if (test == null)
      _initTestProxy();
    return test.getName();
  }
  
  
}