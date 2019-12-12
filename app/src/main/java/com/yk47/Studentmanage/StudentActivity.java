package com.yk47.Studentmanage;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class StudentActivity extends Activity {
    private LinearLayout Notice1,Event1,Result,Assignment1,Attendence1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);
        Notice1=(LinearLayout)findViewById(R.id.Noti1);
        Notice1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent6 = new Intent(StudentActivity.this,NoticSActivity.class);
                startActivity(intent6);
            }
        });
        Event1=(LinearLayout)findViewById(R.id.Evnt1);
        Event1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent12 = new Intent(StudentActivity.this,EventSActivity.class);
                startActivity(intent12);
            }
        });
        Result=(LinearLayout)findViewById(R.id.Rslt1);
        Assignment1=(LinearLayout)findViewById(R.id.Assign1);
        Assignment1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent5 = new Intent(StudentActivity.this,MyRecycleViewActivity.class);
                startActivity(intent5);
            }
        });
        Attendence1=(LinearLayout)findViewById(R.id.Attnd1);
        Attendence1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent4 = new Intent(StudentActivity.this,AttendanceSActivity.class);
                startActivity(intent4);
            }
        });
        Result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(StudentActivity.this, ResultShowActivity.class);
                startActivity(intent2);
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater= getMenuInflater();
        inflater.inflate(R.menu.example_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.action_Contact:
                showMessage("Contact no.", "Yash 8698940084");
                Toast.makeText(getApplicationContext(), "Contact", Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_Appinfo:
                Toast.makeText(getApplicationContext(), "App info", Toast.LENGTH_SHORT).show();
                Intent intent1 = new Intent(StudentActivity.this, AppinfoActivity.class);
                startActivity(intent1);
                break;
            case R.id.action_Logout:
                Toast.makeText(getApplicationContext(), "Logout", Toast.LENGTH_SHORT).show();
                FirebaseAuth.getInstance().signOut();
                finish();
                Intent intent5 = new Intent(StudentActivity.this, FirstActivity.class);
                startActivity(intent5);
                break;
            default:
                showMessage("Error", "Page not found");

        }
        return super.onOptionsItemSelected(item);
    }
    public void showMessage(String title,String message)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}
