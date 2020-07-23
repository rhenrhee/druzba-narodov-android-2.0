package com.druzbanarodov.relativlayoutjava.multyplayer.gameprocess;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Invites {
    public String inviteFrom;
    public String inviteTo;
    public String inviteSentStatus;//delivered or not
    public String inviteAcceptStatus;//accepted or not
    public String family;
    public String branch;
    public String gameInvite;
    public int resultPlayerOneFB;
    public int resultPlayerTwoFB;
    public long endOnePlayer;
    public long endTwoPlayer;
  //  public String semiCategory;
  //  public String category;
   // public int[] arrayCategory;

    public Invites() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public Invites(String inviteFrom, String inviteTo, String inviteSentStatus, String inviteAcceptStatus,String family,String branch,int resultPlayerOneFB,int resultPlayerTwoFB,int endOnePlayer,int endTwoPlayer,String gameInvite) {
        this.inviteFrom = inviteFrom;
        this.inviteTo = inviteTo;
        this.inviteSentStatus = inviteSentStatus;
        this.inviteAcceptStatus = inviteAcceptStatus;
        this.family = family;
        this.branch = branch;
        this.resultPlayerOneFB = resultPlayerOneFB;
        this.resultPlayerTwoFB = resultPlayerTwoFB;
        this.endOnePlayer = endOnePlayer;
        this.endTwoPlayer = endTwoPlayer;
        this.gameInvite = gameInvite;

       // this.category = category;
       // this.semiCategory = semiCategory;
       // this.arrayCategory = arrayCategory;
    }
}
