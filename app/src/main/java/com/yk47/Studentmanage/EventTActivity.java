package com.yk47.Studentmanage;

import android.support.annotation.NonNull;
import android.app.Activity;
import android.os.Bundle;
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

public class EventTActivity extends Activity {
    private EditText Events;
    private Button SendEve;
    ListView listViewEven;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference reff;
    Events event;
    ArrayList<String> neresult2;
    ArrayAdapter<String> adapter2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_t);
        Events = (EditText) findViewById(R.id.editEvent);
        SendEve = (Button) findViewById(R.id.btnSendgo1);
        listViewEven = (ListView) findViewById(R.id.listViewevent);
        firebaseDatabase = FirebaseDatabase.getInstance();
       event=new Events();
        neresult2 = new ArrayList<>();
        adapter2 = new ArrayAdapter<String>(this, R.layout.activity_listview, R.id.textView5, neresult2);
        reff = FirebaseDatabase.getInstance().getReference("Events");
        SendEve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String string=Events.getText().toString().trim();
                String id = reff.push().getKey();
                reff.child(id).setValue(event);
                event.setEvents(string);
                reff.child(id).setValue(event);
                Toast.makeText(EventTActivity.this, "Data insert Sucessfully", Toast.LENGTH_LONG).show();
            }
        });
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                neresult2.clear();
                for (DataSnapshot resultSnapshot : dataSnapshot.getChildren()) {
                    event = resultSnapshot.getValue(Events.class);


                    neresult2.add(event.getEvents());


                }


                listViewEven.setAdapter(adapter2);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
