package com.l124320.cloudprojectfinal;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by 840 G1 on 4/28/2018.
 */
@IgnoreExtraProperties
public class hotelroom {

    public String roomId;
    public String roomName;
    public String price;
    public String availibility;
    public String detail;

    public hotelroom() {

    }

    public hotelroom(String roomId, String roomName, String price, String availibility, String detail) {
        this.roomId = roomId;
        this.roomName = roomName;
        this.price = price;
        this.availibility = availibility;
        this.detail = detail;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getAvailibility() {
        return availibility;
    }

    public void setAvailibility(String availibility) {
        this.availibility = availibility;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
