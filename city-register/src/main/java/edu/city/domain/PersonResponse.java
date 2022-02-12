package edu.city.domain;

import edu.city.exception.PersonCheckException;

import java.sql.*;

public class PersonResponse {

    private boolean registered;
    private boolean temporal;




    public boolean isRegistered() {
        return registered;
    }

    public void setRegistered(boolean registered) {
        this.registered = registered;
    }

    public boolean isTemporal() {
        return temporal;
    }

    public void setTemporal(boolean temporal) {
        this.temporal = temporal;
    }
}
