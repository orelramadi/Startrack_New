package com.upstore.myapplication;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;


public class Payment_Stars_Dialog extends DialogFragment {
    private FirebaseUser user, business;
    private DatabaseReference db_users, db_businesses;
    private TextView star_balance;


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("אשר רכישה")
                .setMessage("האם אתה בטוח שברצונך לרכוש בכוכבים ?")
                .setPositiveButton("אישור", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                pay_stars();
            }
        });

        return builder.create();
    }

    private void pay_stars() {
        user = FirebaseAuth.getInstance().getCurrentUser();
        db_users.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int user_get_stars_balance = Integer.parseInt(snapshot.child(user.getUid()).child("stars").getValue(String.class));
                int user_get_stars_cost = Integer.parseInt(snapshot.child(user.getUid()).child("cost_stars").getValue(String.class));
                String new_user_stars_balance = Integer.toString(user_get_stars_balance - user_get_stars_cost);



            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}