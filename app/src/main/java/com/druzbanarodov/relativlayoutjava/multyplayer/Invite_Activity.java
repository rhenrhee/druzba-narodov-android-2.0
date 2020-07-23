package com.druzbanarodov.relativlayoutjava.multyplayer;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
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

public class Invite_Activity extends AppCompatActivity {
    private RecyclerView.LayoutManager layoutManager;
    private InviteAdapter inviteAdapter;;
    private RecyclerView recyclerView;
    public final static String PLAYER2NAME = "";
    public final static String PLAYER2INVITESTATUS = "";
    String nickName = "";
    private final String Default = "N/A";

    //global variables
    List<InvitesUser> playersList;

   static int [] arrayCategoryFB;
   static String categoryFB;
   static String semiCategoryFB;


   public static void setCategory(int[] arrayCategory,String category, String semiCategory){
       arrayCategoryFB = arrayCategory;
       categoryFB = category;
       semiCategoryFB = semiCategory;
   }
    // Database reference
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference playersRef;
    DatabaseReference invitesRef;

    private String inviteFrom = "";
    private String inviteTo = "";
    private String inviteSentStatus = "";
    private String inviteAcceptStatus = "";
    int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Set layout
        setContentView(R.layout.invite_activity);

        //set variables and initialization
        recyclerView = findViewById(R.id.recyclerview);
        playersRef = database.getReference("Players/Players_one");
        playersList = new ArrayList<>();

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);

        final SharedPreferences sharedPreferences = getSharedPreferences("Content_main", Context.MODE_PRIVATE);
        nickName = sharedPreferences.getString("name", Default);
        inviteSocial();
        //Getting lists players for invite to the game
        final int[] iconIDs= {R.drawable.av_kavkaz_act,R.drawable.avator_f_1_act_1,
                R.drawable.avator_f_1_act,R.drawable.avator_f_1_act_3,
                R.drawable.avator_f_2_act,R.drawable.avator_m_2};
        playersRef.addValueEventListener(new ValueEventListener() {
            //Read database and get players
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                playersList.clear();
                Iterable<DataSnapshot> players = dataSnapshot.getChildren();
                for (DataSnapshot snapshot : players){
                    System.out.println(snapshot.getKey() + " ключзнаач " + nickName);
                    if(!snapshot.getKey().equals(nickName)) {
                        playersList.add(new InvitesUser(snapshot.getKey(), iconIDs[i]));
                        i++;
                        if (i == 5) {
                            i = 0;
                        }
                    }
                    //check username to equals in list
                    //if nickname sharedpreferences = nickname list then remove nickname from list
                    if (playersList.contains(nickName)){
                        //Integer index = playersList.indexOf(nickName);
                        playersList.remove(nickName);
                    }


                }
                updateUI();
            }
            // If errors
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    //Updating UI after connection to database
    private void updateUI(){
        InviteAdapter.setHost(true);
        inviteAdapter = new InviteAdapter(this);
        System.out.println(playersList + "playerList");
        inviteAdapter.setPlayers(playersList);
        recyclerView.setAdapter(inviteAdapter);
    }

    private void inviteSocial(){

        ImageView vkLogo;
        vkLogo = findViewById(R.id.vk_icon);
        vkLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
             //   Uri imageUri = Uri.parse("file://" + getPackageName()
                 //       + "/drawable/" + "computer.png");
              //  System.out.println(imageUri.getPath());
             //   shareIntent.setType("image/*");
                shareIntent.setType("message/rfc822");
                shareIntent.putExtra(Intent.EXTRA_TEXT, "Друг, давай проверим кто лучше знает нашу необъятную родину? Поиграем https://play.google.com/store/apps/details?id=com.druzbanarodov.relativlayoutjava");
             //  shareIntent.putExtra(Intent.EXTRA_STREAM, imageUri);
              //  shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                startActivity(Intent.createChooser(shareIntent, "Отправить приглашение"));


            }
        });

    }

    public void sendInvite(int position) {

        invitesRef = database.getReference("Invites");
        inviteFrom = nickName;
        inviteTo = playersList.get(position).getName();
        inviteSentStatus = "Sent";
        inviteAcceptStatus = "Not accepted";

        Multyplayer_Activity.setSelectedPlayer(true);
        String family = (getIntent().getStringExtra("Family"));
        String branch = (getIntent().getStringExtra("Branch"));
        Invites invite = new Invites(inviteFrom, inviteTo, inviteSentStatus, inviteAcceptStatus,family,branch,0,0,0,0,"invite");


       // invitesRef.child("InviteTo" + inviteTo).child("Family").setValue(getIntent().getStringExtra("Family"));
      //  invitesRef.child("InviteTo" + inviteTo).child("Branch").setValue(getIntent().getStringExtra("Branch"));
        invitesRef.child("InviteTo" + inviteTo).setValue(invite);
        System.out.println(getIntent().getStringExtra("Family") + "Метод А");
        //invitesRef.child("InviteTo"+ inviteTo).child("Question").setValue(categoryFB);
        Toast.makeText(Invite_Activity.this, "Приглашение отправлено игроку " + inviteTo, Toast.LENGTH_SHORT).show();

        MultiplyerQuestions.setName(inviteTo);
        int [] arrayNumbersQuestion = getIntent().getIntArrayExtra("Array");
        for(int i =0;i<arrayNumbersQuestion.length;i++){
            invitesRef.child("InviteTo" + inviteTo).child("Question "+i).setValue(arrayNumbersQuestion[i]);
        }


        MultiplyerQuestions.setNames(inviteFrom,inviteTo);


        Log.d("Первый метод","Должен сработать вперед");
        Intent returnInviteTo = new Intent();
        returnInviteTo.putExtra(PLAYER2NAME, inviteTo);
        setResult(RESULT_OK, returnInviteTo);
        SharedPreferences sPref;
        sPref = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor ed = sPref.edit();
        ed.putString("SAVED_TEXT", inviteTo);
        ed.commit();
        this.finish();
        //Log.d(inviteFrom, inviteTo);

    }
}
