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

public class AttendanceTActivity extends Activity {
    private EditText name1,roll1,attend;
    private Button Submit1;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference reff;
    Attendance atte;
    ListView listViewAttendance;
    ArrayList<String> neresult1;
    ArrayAdapter<String> adapter1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance_t);
        listViewAttendance=(ListView)findViewById(R.id.listViewAttend);
        name1=(EditText)findViewById(R.id.ediName1) ;
        attend=(EditText)findViewById(R.id.edAttend);
        atte=new Attendance();
        roll1=(EditText)findViewById(R.id.edRoll1);
        Submit1=(Button)findViewById(R.id.btSub1);
        firebaseDatabase=FirebaseDatabase.getInstance();
        neresult1=new ArrayList<>();
        adapter1=new ArrayAdapter<String>(this,R.layout.activity_listview,R.id.textView5,neresult1);
        reff= FirebaseDatabase.getInstance().getReference("Attendance");
        Submit1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addAttend();
            }
        });
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                neresult1.clear();
                for (DataSnapshot resultSnapshot : dataSnapshot.getChildren()) {
                    atte  = resultSnapshot.getValue(Attendance.class);
                    atte.getAttend().toString();
                    String mrk=atte.getAttend().toString();


                    neresult1.add(atte.getName());
                    neresult1.add(mrk);

                }



                listViewAttendance.setAdapter(adapter1);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
    public void addAttend()
    {
        int Rollno=Integer.parseInt(roll1.getText().toString().trim());
        int Attend=Integer.parseInt(attend.getText().toString().trim());
        String Nam=name1.getText().toString().trim();
        String id =reff.push().getKey();
        reff.child(id).setValue(atte);
        atte.setName(Nam);
        atte.setRollno(Rollno);
        atte.setAttend(Attend);
        reff.child(id).setValue(atte);
        Toast.makeText(AttendanceTActivity.this,"Data insert Sucessfully",Toast.LENGTH_LONG).show();
    }
}
