package com.l124320.cloudprojectfinal;

/**
 * Created by 840 G1 on 4/28/2018.
 */

public class hotelroom {

    String roomId;
    String roomName;
    String price;
    String Availibility;
    String roomPic;
    String detail;

    public hotelroom(String roomidcons, String namecons, String pricecons, String Availcons, String piccons, String detailcons){
        roomId=roomidcons;
        roomName=namecons;
        price=pricecons;
        Availibility=Availcons;
        roomPic=piccons;
        detail=detailcons;
    }

    //Getters
    public String getRoomId() {
        return roomId;
    }

    public String getRoomName() {
        return roomName;
    }

    public String getPrice() {
        return price;
    }

    public String getRoomPic() {
        return roomPic;
    }

    public String getAvailibility() {
        return Availibility;
    }

    public String getDetail() {
        return detail;
    }


    //Setters


    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public void setRoomName(String roomName) {
        roomName = roomName;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setRoomPic(String roomPic) {
        this.roomPic = roomPic;
    }

    public void setAvailibility(String availibility) {
        Availibility = availibility;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
