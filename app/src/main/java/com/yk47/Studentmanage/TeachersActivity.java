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

public class TeachersActivity extends Activity {
private LinearLayout Notice,Event,Result,Assignment,Attendenceob;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teachers);
        Notice=(LinearLayout)findViewById(R.id.noti);

        Notice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent8 = new Intent(TeachersActivity.this, NoticTActivity.class);
                startActivity(intent8);
            }
        });
        Event=(LinearLayout)findViewById(R.id.Evnt1);
        Event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent11 = new Intent(TeachersActivity.this, EventTActivity.class);
                startActivity(intent11);
            }
        });
        Result=(LinearLayout)findViewById(R.id.Rslt1);
        Assignment=(LinearLayout)findViewById(R.id.Assign1);
        Attendenceob=(LinearLayout)findViewById(R.id.Attnd1);
        Assignment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent7 = new Intent(TeachersActivity.this, AssignmentTActivity.class);
                startActivity(intent7);
            }
        });
        Attendenceob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent7 = new Intent(TeachersActivity.this, AttendanceTActivity.class);
                startActivity(intent7);
            }
        });
        Result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(TeachersActivity.this, ResultTActivity.class);
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
                showMessage("Contact no.", "Email yckarnik@gmail.com");
                Toast.makeText(getApplicationContext(), "Contact", Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_Appinfo:
                Toast.makeText(getApplicationContext(), "App info", Toast.LENGTH_SHORT).show();
                Intent intent1 = new Intent(TeachersActivity.this, AppinfoActivity.class);
                startActivity(intent1);
                break;
            case R.id.action_Logout:
                Toast.makeText(getApplicationContext(), "Logout", Toast.LENGTH_SHORT).show();
                Intent intent5 = new Intent(TeachersActivity.this, FirstActivity.class);
                startActivity(intent5);
                finish();
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
