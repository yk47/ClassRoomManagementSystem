package com.yk47.Studentmanage;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class TeachLoginActivity extends Activity {
    private EditText Name,password;

    private Button Login;

    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teach_login);

        pd=new ProgressDialog(this);
        Name= (EditText)findViewById(R.id.ed1);
        password=(EditText)findViewById(R.id.ed2);
        Login =(Button)findViewById(R.id.btnLogin);

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validate((Name.getText().toString()), (password.getText().toString()));

            }
        });



    }



    public void validate (String userName, String userPassword){

        if ((userName.equals("Admin")) && (userPassword.equals("1234"))) {
            Intent intent = new Intent(TeachLoginActivity.this, TeachersActivity.class);
            startActivity(intent);
        } else
            showMessage("Invalid Login", "Enter a Valid UserName or Password");
    }


    public void showMessage (String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }


}

