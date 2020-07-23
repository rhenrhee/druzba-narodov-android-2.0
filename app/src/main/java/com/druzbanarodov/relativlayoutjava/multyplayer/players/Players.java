package com.druzbanarodov.relativlayoutjava.multyplayer.players;

public class Players {

    public String nickname;
    public String email;
    public Integer rating;
    public String status; //"ready" or "not ready"
    public String role; //host/guest

    public Players() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public Players(String nickname, String email, Integer rating, String status, String role) {
        this.nickname = nickname;
        this.email = email;
        this.rating = rating;
        this.status = status;
        this.role = role;
    }

}
