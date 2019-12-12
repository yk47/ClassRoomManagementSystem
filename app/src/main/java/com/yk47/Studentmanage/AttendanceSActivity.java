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

public class AttendanceSActivity extends Activity {
    FirebaseDatabase firebaseDatabase;
    DatabaseReference reff;
    Attendance atte;
    ListView listViewAttendance;
    ArrayList<String> neresult;
    ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance_s);
        listViewAttendance = (ListView) findViewById(R.id.listViewAtteS);
        firebaseDatabase=FirebaseDatabase.getInstance();
        atte=new Attendance();
        neresult=new ArrayList<>();

        adapter=new ArrayAdapter<String>(this,R.layout.activity_listview,R.id.textView5,neresult);

        reff= FirebaseDatabase.getInstance().getReference("Attendance");
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                neresult.clear();
                for (DataSnapshot resultSnapshot : dataSnapshot.getChildren()) {
                    atte  = resultSnapshot.getValue(Attendance.class);
                    atte.getAttend().toString();
                    String mrk=atte.getAttend().toString();


                    neresult.add(atte.getName());
                    neresult.add(mrk);

                }



                listViewAttendance.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
