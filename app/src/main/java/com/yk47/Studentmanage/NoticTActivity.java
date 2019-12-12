package com.yk47.Studentmanage;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class NoticTActivity extends Activity {
    private EditText Notice;
    private Button Send;
    ListView listViewNotic;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference reff;
    Notice notii;
    ArrayList<String> neresult2;
    ArrayAdapter<String> adapter2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notic_t);
        Notice = (EditText) findViewById(R.id.editText3);
        Send = (Button) findViewById(R.id.btnSendgo);
        listViewNotic = (ListView) findViewById(R.id.listViewnoti);
        firebaseDatabase = FirebaseDatabase.getInstance();
        notii = new Notice();
        neresult2 = new ArrayList<>();
        adapter2 = new ArrayAdapter<String>(this, R.layout.activity_listview, R.id.textView5, neresult2);
        reff = FirebaseDatabase.getInstance().getReference("Notice");
        Send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String string=Notice.getText().toString().trim();
                String id = reff.push().getKey();
                reff.child(id).setValue(notii);
                notii.setNotgo(string);
                reff.child(id).setValue(notii);
                Toast.makeText(NoticTActivity.this, "Data insert Sucessfully", Toast.LENGTH_LONG).show();
            }
        });

        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                neresult2.clear();
                for (DataSnapshot resultSnapshot : dataSnapshot.getChildren()) {
                    notii = resultSnapshot.getValue(Notice.class);


                    neresult2.add(notii.getNotgo());


                }


                listViewNotic.setAdapter(adapter2);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}