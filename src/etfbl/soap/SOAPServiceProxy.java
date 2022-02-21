package etfbl.soap;

public class SOAPServiceProxy implements etfbl.soap.SOAPService {
  private String _endpoint = null;
  private etfbl.soap.SOAPService sOAPService = null;
  
  public SOAPServiceProxy() {
    _initSOAPServiceProxy();
  }
  
  public SOAPServiceProxy(String endpoint) {
    _endpoint = endpoint;
    _initSOAPServiceProxy();
  }
  
  private void _initSOAPServiceProxy() {
    try {
      sOAPService = (new etfbl.soap.SOAPServiceServiceLocator()).getSOAPService();
      if (sOAPService != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)sOAPService)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)sOAPService)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (sOAPService != null)
      ((javax.xml.rpc.Stub)sOAPService)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public etfbl.soap.SOAPService getSOAPService() {
    if (sOAPService == null)
      _initSOAPServiceProxy();
    return sOAPService;
  }
  
  public java.lang.String[] loggedUsers() throws java.rmi.RemoteException{
    if (sOAPService == null)
      _initSOAPServiceProxy();
    return sOAPService.loggedUsers();
  }
  
  public etfbl.mdp.model.User loginUser(java.lang.String username, java.lang.String password) throws java.rmi.RemoteException{
    if (sOAPService == null)
      _initSOAPServiceProxy();
    return sOAPService.loginUser(username, password);
  }
  
  public boolean logout(java.lang.String username) throws java.rmi.RemoteException{
    if (sOAPService == null)
      _initSOAPServiceProxy();
    return sOAPService.logout(username);
  }
  
  
}