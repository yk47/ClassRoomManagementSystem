package com.yk47.Studentmanage;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class NoticSActivity extends Activity {
    ListView listViewNoticS;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference reff;
    Notice notii;
    ArrayList<String> neresult2;
    ArrayAdapter<String> adapter2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notic_s);
        listViewNoticS = (ListView) findViewById(R.id.listViewNotiS);
        firebaseDatabase = FirebaseDatabase.getInstance();
        notii = new Notice();
        neresult2 = new ArrayList<>();
        adapter2 = new ArrayAdapter<String>(this, R.layout.activity_listview, R.id.textView5, neresult2);
        reff = FirebaseDatabase.getInstance().getReference("Notice");
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                neresult2.clear();
                for (DataSnapshot resultSnapshot : dataSnapshot.getChildren()) {
                    notii = resultSnapshot.getValue(Notice.class);


                    neresult2.add(notii.getNotgo());


                }


                listViewNoticS.setAdapter(adapter2);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
