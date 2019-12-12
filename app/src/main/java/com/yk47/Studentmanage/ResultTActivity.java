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

public class ResultTActivity extends Activity  {

    private EditText name,marks,rollno;
    private Button Submit;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference reff;
    Resultshow results;

    ListView listViewTeacher;
    ArrayList<String> neresult;

    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultech);
        listViewTeacher=(ListView)findViewById(R.id.listViewTech);
        name=(EditText)findViewById(R.id.ediName) ;
        rollno=(EditText)findViewById(R.id.edRoll);
        marks=(EditText)findViewById(R.id.edMarks);
        firebaseDatabase=FirebaseDatabase.getInstance();
        results=new Resultshow();
        neresult=new ArrayList<>();
        adapter=new ArrayAdapter<String>(this,R.layout.activity_listview,R.id.textView5,neresult);

        reff= FirebaseDatabase.getInstance().getReference("Results");


        Submit=(Button)findViewById(R.id.btSub);
        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               addResult();

            }
        });
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

                listViewTeacher.setAdapter(adapter);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

 }



    public void addResult()
    {
      int Rollno=Integer.parseInt(rollno.getText().toString().trim());
      int Marks=Integer.parseInt(marks.getText().toString().trim());
        String Nam=name.getText().toString().trim();
        String id =reff.push().getKey();
        reff.child(id).setValue(results);
        results.setName(Nam);
        results.setRollno(Rollno);
        results.setMarks(Marks);
        reff.child(id).setValue(results);
        Toast.makeText(ResultTActivity.this,"Data insert Sucessfully",Toast.LENGTH_LONG).show();
    }

}





