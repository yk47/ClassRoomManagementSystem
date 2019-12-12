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

public class ResultShowActivity extends Activity {
    FirebaseDatabase firebaseDatabase;
    DatabaseReference reff;
    Resultshow results;

    ListView listViewStudent;
    ArrayList<String> neresult;
    ArrayAdapter<String> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_show);
        listViewStudent = (ListView) findViewById(R.id.listViewStu);
        firebaseDatabase=FirebaseDatabase.getInstance();
        results=new Resultshow();
        neresult=new ArrayList<>();

        adapter=new ArrayAdapter<String>(this,R.layout.activity_listview,R.id.textView5,neresult);

        reff= FirebaseDatabase.getInstance().getReference("Results");




        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                neresult.clear();
                for (DataSnapshot resultSnapshot : dataSnapshot.getChildren()) {
                    results  = resultSnapshot.getValue(Resultshow.class);
                    results.getMarks().toString();
                    String mrk=results.getMarks().toString();


                    neresult.add(results.getName());
                    neresult.add(mrk);

                }



                listViewStudent.setAdapter(adapter);



            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}