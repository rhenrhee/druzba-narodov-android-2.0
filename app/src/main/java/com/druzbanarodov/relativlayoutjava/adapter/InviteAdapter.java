package com.druzbanarodov.relativlayoutjava.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.druzbanarodov.relativlayoutjava.R;
import com.druzbanarodov.relativlayoutjava.multyplayer.InviteFrom_Activity;
import com.druzbanarodov.relativlayoutjava.multyplayer.Invite_Activity;

import java.util.ArrayList;
import java.util.List;

public class InviteAdapter extends RecyclerView.Adapter<InviteAdapter.InviteViewHolder> {
        Context context;
    private List<InvitesUser> players = new ArrayList<>();
    private static boolean host;

    public InviteAdapter(Context context){
        this.context = context;
    }

    class InviteViewHolder extends RecyclerView.ViewHolder{
        private TextView namePlayer;
        private ImageView logoPlayer;
        private Button accepted;
        private Button notAccepted;

        int i = 0;

        public InviteViewHolder(View itemView){
            super(itemView);
            namePlayer = itemView.findViewById(R.id.namePlayer);
            logoPlayer = itemView.findViewById(R.id.logo_player);
            accepted = itemView.findViewById(R.id.btn_ready);
            notAccepted = itemView.findViewById(R.id.btn_ready_no);
        }

        public void bind(InvitesUser invitesUser){
            if(host){
                notAccepted.setVisibility(View.INVISIBLE);
            }
        namePlayer.setText(invitesUser.getName());
        logoPlayer.setImageResource(invitesUser.getIdRes());


        }
    }

    public static void setHost(boolean hos) {
       host = hos;
    }

    @NonNull
    @Override
    public InviteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_invite_view_fragment,parent,false);
        return new InviteViewHolder(view);
    }

    public void setPlayers(List<InvitesUser> player) {
        players.addAll(player);
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull InviteViewHolder holder, final int position) {
        holder.bind(players.get(position));
        holder.accepted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (host) {
                    if(context instanceof Invite_Activity){
                    ((Invite_Activity) context).sendInvite(position);
                    }
                }else {
                    if(context instanceof InviteFrom_Activity){
                        ((InviteFrom_Activity)context).getInviteFrom();
                    }

                    }


            }
        });
        holder.notAccepted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(context instanceof InviteFrom_Activity){
                    ((InviteFrom_Activity)context).deleteInviteFrom();
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        System.out.println(players.size() + " сайз");
        return players.size() ;
    }
}
