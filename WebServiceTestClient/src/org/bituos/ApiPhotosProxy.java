package org.bituos;

public class ApiPhotosProxy implements org.bituos.ApiPhotos {
  private String _endpoint = null;
  private org.bituos.ApiPhotos apiPhotos = null;
  
  public ApiPhotosProxy() {
    _initApiPhotosProxy();
  }
  
  public ApiPhotosProxy(String endpoint) {
    _endpoint = endpoint;
    _initApiPhotosProxy();
  }
  
  private void _initApiPhotosProxy() {
    try {
      apiPhotos = (new org.bituos.ApiPhotosServiceLocator()).getApiPhotos();
      if (apiPhotos != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)apiPhotos)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)apiPhotos)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (apiPhotos != null)
      ((javax.xml.rpc.Stub)apiPhotos)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public org.bituos.ApiPhotos getApiPhotos() {
    if (apiPhotos == null)
      _initApiPhotosProxy();
    return apiPhotos;
  }
  
  public java.lang.String saveFile(java.lang.String ruta, java.lang.String filename, byte[] infoBytes) throws java.rmi.RemoteException{
    if (apiPhotos == null)
      _initApiPhotosProxy();
    return apiPhotos.saveFile(ruta, filename, infoBytes);
  }
  
  
}