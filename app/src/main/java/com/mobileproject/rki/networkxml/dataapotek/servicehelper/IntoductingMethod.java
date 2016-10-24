package com.mobileproject.rki.networkxml.dataapotek.servicehelper;

/**
 * Created by RKI on 10/20/2016.
 */

public class IntoductingMethod {

    // Methode name
    private final String adding = "InsertDataApotek";

    // kSOAP ConnectingToServer
    private ConnectingToServer transport;

    /**
     * Contructor
     */
    public IntoductingMethod() {
        this.transport = new ConnectingToServer();
    }

    /**
     * Get Result from Adding
     *
     * @param id
     * @param user
     * @return
     */

    public String adding(String id, String user) {
        transport.addProperty("id", id);
        transport.addProperty("user", user);

        transport.setMethodeName(adding);
        Object result = transport.connect();
        return String.valueOf(result.toString());
    }

}