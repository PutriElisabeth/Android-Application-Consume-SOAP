package com.mobileproject.rki.networkxml.dataapotek.servicehelper;

/**
 * Created by RKI on 10/20/2016.
 */

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.ArrayList;
import java.util.HashMap;

public class ConnectingToServer {
    public static String ERROR_CONNECTION = "error_connection";
    public static String ERROR_DATA = "error_data";
    public static String RESULT_EMPTY = "empty_data";
    // name for servicenamespace
    private final String URLServer = "http://10.160.1.123/services/WebService1.asmx?op=InsertDataApotek";
    private final String serviceNameSpace = "http://tempuri.org/";
    private final String soapActionPrefix = "http://tempuri.org/"; // soapAction
    // name
    private String soapAction;
    private String methodeName; // method name
    private HttpTransportSE transport; // HTTPTransport using kSOAP2
    private HashMap<String, Object> propertyMap = new HashMap<String, Object>();
    private ArrayList<String> listProperty = new ArrayList<String>();

    //    /**
//     * Constructor
//     *
//     * @param ConnectionListener listener
//     */
    public ConnectingToServer() {
    }

    //    /**
//     * Setter for methodName
//     *
//     * @param methodName
//     */
    public void setMethodeName(String methodeName) {
        this.methodeName = methodeName;
        soapAction = soapActionPrefix + methodeName;
    }

    /**
     * Set property for request with value string type
     *
     * @param propertyName
     * @param propertyValue
     */
    public void addProperty(String propertyName, Object propertyValue) {
        listProperty.add(propertyName);
        propertyMap.put(propertyName, propertyValue);
    }

    /**
     * Clear property
     */
    public void clearProperty() {
        listProperty.clear();
        propertyMap.clear();
    }

    /**
     * Run connection to server, send request and return the result from response
     *
     * @return Object result
     */
    public Object connect() {
        transport = new HttpTransportSE(URLServer);

        SoapObject client = new SoapObject(serviceNameSpace, methodeName);
        if (propertyMap.size() > 0) {
            for (String propertyName : listProperty) {
                client.addProperty(propertyName, propertyMap.get(propertyName));
            }
        }

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);
        envelope.dotNet = true;
        envelope.setOutputSoapObject(client);

        Object result = null;
        try {
            transport.call(soapAction, envelope);
            result = envelope.getResponse();

            return result;
        } catch (Exception e) {
            String message = methodeName + " " + e.getMessage();

            return ERROR_CONNECTION;
        }
    }
}