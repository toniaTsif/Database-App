package gr.hua.android.dbapp;

import java.io.Serializable;

public class ContractRecord implements Serializable {
    private String userId;
    private float longitude;
    private float latitude;
    private String timestamp;

    //CONSTRUCTORS
    public ContractRecord() {
    }

    public ContractRecord(String userId, String timestamp) {
        this.userId = userId;
        this.timestamp = timestamp;
    }

    public ContractRecord(String userId, float longitude, float latitude, String timestamp) {
        this.userId = userId;
        this.longitude = longitude;
        this.latitude = latitude;
        this.timestamp = timestamp;
    }


    //GETTERS - SETTERS
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
