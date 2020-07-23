package com.druzbanarodov.relativlayoutjava.multyplayer;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.druzbanarodov.relativlayoutjava.R;
import com.druzbanarodov.relativlayoutjava.adapter.InviteAdapter;
import com.druzbanarodov.relativlayoutjava.adapter.InvitesUser;
import com.druzbanarodov.relativlayoutjava.multyplayer.gameprocess.Invites;

import java.util.ArrayList;
import java.util.List;

public class InviteFrom_Activity extends AppCompatActivity {

    //global variables
    List<InvitesUser> invitesList;

    //recyclerView
    RecyclerView.LayoutManager layoutManager;
    RecyclerView recyclerView;
    InviteAdapter inviteAdapter;


    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference invitesToRef;
    DatabaseReference inviteFromRef;
    String inv;
    String inviteTo = "";
    String role = "guest";
    String inviteFrom = "";
    String inviteAcceptFrom = "";
    String Default = "N/A";
    private String inviteAcceptStatus = "";
    int i = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Set layout
        setContentView(R.layout.invitefrom_activity);

        final SharedPreferences sharedPreferences = getSharedPreferences("Content_main", Context.MODE_PRIVATE);
        inviteTo = sharedPreferences.getString("name", Default);

        //recyclerView
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        MultiplyerQuestions.setRole("client");
        //set variables and initialization
        invitesToRef = database.getReference("Invites");
        inviteFromRef = FirebaseDatabase.getInstance().getReference();
        //inviteFromRef = database.getReference("Invites/InviteTo" + inviteTo);
        invitesList = new ArrayList<>();

        //invitesToRef.child("Invites").child("InviteTo"+inviteTo); //.child("InviteFrom"); // invateFrom убрать


        final int[] iconIDs = {R.drawable.av_kavkaz_act, R.drawable.avator_f_1_act_1,
                R.drawable.avator_f_1_act, R.drawable.avator_f_1_act_3,
                R.drawable.avator_f_2_act, R.drawable.avator_m_2};


        //Getting lists players for invite to the game
        invitesToRef.child("InviteTo" + inviteTo).addValueEventListener(new ValueEventListener() {
            //Read database and get players
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                System.out.println("inviteTo" + inviteTo);
                System.out.println(inviteFrom + " inviteFrom");
                System.out.println(dataSnapshot.getKey() + "key");
                System.out.println(dataSnapshot.child("gameInvite").equals("invite") + " ASD");
               // if(dataSnapshot.child("gameInvite").getValue().equals("invite")){
              //      System.out.println(" ОТРАБОТАЛ");
              //      invite();
               // }
                invitesList.clear();
                Iterable<DataSnapshot> invites = dataSnapshot.getChildren();

                if (dataSnapshot.child("inviteTo").exists()) {
                    if (dataSnapshot.child("inviteTo").getValue().equals(inviteTo)) {
                        inv = dataSnapshot.child("inviteFrom").getValue().toString();
                        invitesList.add(new InvitesUser(dataSnapshot.child("inviteFrom").getValue().toString(), iconIDs[i]));
                        i++;
                        if (i == 5) {
                            i = 0;
                        }
                    }
                }
                //check username to equals in list
                //if nickname sharedpreferences = nickname list then remove nickname from list
                 /*   for (int i = 0; i < invitesList.size(); i++) {
                        inviteFrom = invitesList.get(i);
                        if (!inviteFrom.equals("InviteTo" + inviteTo)) {
                            //Integer index = invitesList.indexOf();
                            invitesList.remove(inviteFrom);
                            Toast.makeText(InviteFrom_Activity.this, "Приглашения для  " + inviteTo, Toast.LENGTH_SHORT).show();
                            updateUI();
                        }
                    } */


                updateUI();
            }

            // If errors
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

       /* listInvites.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                inviteAcceptStatus = "Accepted";
                //invitesToRef.child("Invites").child("InviteTo" + inviteTo).child("inviteAcceptStatus").setValue(inviteAcceptStatus);
                invitesToRef.child("InviteTo" + inviteTo).child("inviteAcceptStatus").setValue(inviteAcceptStatus);
                getInviteFrom();
                //TODO remove accepted invite from list
            }

        }); */
    }

    //Updating UI after connection to database
    private void updateUI() {
        InviteAdapter.setHost(false);
        System.out.println(invitesList);
        inviteAdapter = new InviteAdapter(this);
        recyclerView.setAdapter(inviteAdapter);
        inviteAdapter.setPlayers(invitesList);
    }
    public void invite(){
        Toast.makeText(this,"У вас новое приглашения в игру от:" + inv,Toast.LENGTH_LONG).show();
        inviteFromRef.child("Invites").child("InviteTo" + inviteTo).child("gameInvite").setValue("0");
    }

    public void getInviteFrom() {
        inviteFromRef.child("Invites").child("InviteTo" + inviteTo).addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        inviteAcceptStatus = "Accepted";
                        //invitesToRef.child("Invites").child("InviteTo" + inviteTo).child("inviteAcceptStatus").setValue(inviteAcceptStatus);
                        invitesToRef.child("InviteTo" + inviteTo).child("inviteAcceptStatus").setValue(inviteAcceptStatus);
                        Invites invite = dataSnapshot.getValue(Invites.class);
                        String invFrom = invite.inviteFrom;
                        inviteAcceptFrom = invFrom;

                        Intent gameIntent = new Intent(InviteFrom_Activity.this, Multyplayer_Activity.class);
                        gameIntent.putExtra("inviteTo", inviteTo);
                        gameIntent.putExtra("role", "guest");
                        gameIntent.putExtra("inviteAcceptFrom", inviteAcceptFrom);
                        //Log.d(inviteAcceptFrom, "Nothing");
                        startActivity(gameIntent);

                        MultiplyerQuestions.setNames(invFrom, inviteTo);
                        MultiplyerQuestions.setName(inviteTo);

                        SharedPreferences sPref;
                        sPref = getPreferences(MODE_PRIVATE);
                        SharedPreferences.Editor ed = sPref.edit();
                        ed.putString("SAVED_TEXT", inviteTo);
                        ed.commit();
                        InviteFrom_Activity.this.finish();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

    }

    public void deleteInviteFrom() {
        inviteFromRef.child("Invites").child("InviteTo" + inviteTo).removeValue();
    }
}
