/**
 * SOAPServiceServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package etfbl.soap;

public class SOAPServiceServiceLocator extends org.apache.axis.client.Service implements etfbl.soap.SOAPServiceService {

    public SOAPServiceServiceLocator() {
    }


    public SOAPServiceServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public SOAPServiceServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for SOAPService
    private java.lang.String SOAPService_address = "http://localhost:8080/CentralApplication/services/SOAPService";

    public java.lang.String getSOAPServiceAddress() {
        return SOAPService_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String SOAPServiceWSDDServiceName = "SOAPService";

    public java.lang.String getSOAPServiceWSDDServiceName() {
        return SOAPServiceWSDDServiceName;
    }

    public void setSOAPServiceWSDDServiceName(java.lang.String name) {
        SOAPServiceWSDDServiceName = name;
    }

    public etfbl.soap.SOAPService getSOAPService() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(SOAPService_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getSOAPService(endpoint);
    }

    public etfbl.soap.SOAPService getSOAPService(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            etfbl.soap.SOAPServiceSoapBindingStub _stub = new etfbl.soap.SOAPServiceSoapBindingStub(portAddress, this);
            _stub.setPortName(getSOAPServiceWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setSOAPServiceEndpointAddress(java.lang.String address) {
        SOAPService_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (etfbl.soap.SOAPService.class.isAssignableFrom(serviceEndpointInterface)) {
                etfbl.soap.SOAPServiceSoapBindingStub _stub = new etfbl.soap.SOAPServiceSoapBindingStub(new java.net.URL(SOAPService_address), this);
                _stub.setPortName(getSOAPServiceWSDDServiceName());
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
        if ("SOAPService".equals(inputPortName)) {
            return getSOAPService();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://soap.etfbl", "SOAPServiceService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://soap.etfbl", "SOAPService"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("SOAPService".equals(portName)) {
            setSOAPServiceEndpointAddress(address);
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
