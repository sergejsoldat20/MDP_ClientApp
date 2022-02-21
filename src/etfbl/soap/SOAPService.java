/**
 * SOAPService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package etfbl.soap;

public interface SOAPService extends java.rmi.Remote {
    public java.lang.String[] loggedUsers() throws java.rmi.RemoteException;
    public etfbl.mdp.model.User loginUser(java.lang.String username, java.lang.String password) throws java.rmi.RemoteException;
    public boolean logout(java.lang.String username) throws java.rmi.RemoteException;
}
