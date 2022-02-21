/**
 * User.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package etfbl.mdp.model;

public class User  implements java.io.Serializable {
    private boolean isOnline;

    private boolean online;

    private java.lang.String password;

    private etfbl.mdp.model.RailwayStation railwayStation;

    private java.lang.String username;

    public User() {
    }

    public User(
           boolean isOnline,
           boolean online,
           java.lang.String password,
           etfbl.mdp.model.RailwayStation railwayStation,
           java.lang.String username) {
           this.isOnline = isOnline;
           this.online = online;
           this.password = password;
           this.railwayStation = railwayStation;
           this.username = username;
    }


    /**
     * Gets the isOnline value for this User.
     * 
     * @return isOnline
     */
    public boolean isIsOnline() {
        return isOnline;
    }


    /**
     * Sets the isOnline value for this User.
     * 
     * @param isOnline
     */
    public void setIsOnline(boolean isOnline) {
        this.isOnline = isOnline;
    }


    /**
     * Gets the online value for this User.
     * 
     * @return online
     */
    public boolean isOnline() {
        return online;
    }


    /**
     * Sets the online value for this User.
     * 
     * @param online
     */
    public void setOnline(boolean online) {
        this.online = online;
    }


    /**
     * Gets the password value for this User.
     * 
     * @return password
     */
    public java.lang.String getPassword() {
        return password;
    }


    /**
     * Sets the password value for this User.
     * 
     * @param password
     */
    public void setPassword(java.lang.String password) {
        this.password = password;
    }


    /**
     * Gets the railwayStation value for this User.
     * 
     * @return railwayStation
     */
    public etfbl.mdp.model.RailwayStation getRailwayStation() {
        return railwayStation;
    }


    /**
     * Sets the railwayStation value for this User.
     * 
     * @param railwayStation
     */
    public void setRailwayStation(etfbl.mdp.model.RailwayStation railwayStation) {
        this.railwayStation = railwayStation;
    }


    /**
     * Gets the username value for this User.
     * 
     * @return username
     */
    public java.lang.String getUsername() {
        return username;
    }


    /**
     * Sets the username value for this User.
     * 
     * @param username
     */
    public void setUsername(java.lang.String username) {
        this.username = username;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof User)) return false;
        User other = (User) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.isOnline == other.isIsOnline() &&
            this.online == other.isOnline() &&
            ((this.password==null && other.getPassword()==null) || 
             (this.password!=null &&
              this.password.equals(other.getPassword()))) &&
            ((this.railwayStation==null && other.getRailwayStation()==null) || 
             (this.railwayStation!=null &&
              this.railwayStation.equals(other.getRailwayStation()))) &&
            ((this.username==null && other.getUsername()==null) || 
             (this.username!=null &&
              this.username.equals(other.getUsername())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        _hashCode += (isIsOnline() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        _hashCode += (isOnline() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getPassword() != null) {
            _hashCode += getPassword().hashCode();
        }
        if (getRailwayStation() != null) {
            _hashCode += getRailwayStation().hashCode();
        }
        if (getUsername() != null) {
            _hashCode += getUsername().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(User.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://model.mdp.etfbl", "User"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("isOnline");
        elemField.setXmlName(new javax.xml.namespace.QName("http://model.mdp.etfbl", "isOnline"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("online");
        elemField.setXmlName(new javax.xml.namespace.QName("http://model.mdp.etfbl", "online"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("password");
        elemField.setXmlName(new javax.xml.namespace.QName("http://model.mdp.etfbl", "password"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("railwayStation");
        elemField.setXmlName(new javax.xml.namespace.QName("http://model.mdp.etfbl", "railwayStation"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://model.mdp.etfbl", "RailwayStation"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("username");
        elemField.setXmlName(new javax.xml.namespace.QName("http://model.mdp.etfbl", "username"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
