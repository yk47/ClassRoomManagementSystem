package com.yk47.Studentmanage;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.app.Activity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends Activity {
    private EditText Email,Pass;
    private TextView Click;
    private Button Register1;
     FirebaseAuth mAuth;
     ProgressDialog progressDialog;
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ragister);
        mAuth=FirebaseAuth.getInstance();
        progressDialog=new ProgressDialog(this);
        Email= (EditText)findViewById(R.id.edEmail);
        Pass=(EditText)findViewById(R.id.edPass);
        Click=(TextView)findViewById(R.id.arc);
        Register1=(Button)findViewById(R.id.btRagister1);
        Register1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               UserRegister(Email.getText().toString(),Pass.getText().toString());

            }


        });
         Click.setOnClickListener(new View.OnClickListener() {
             @Override
            public void onClick(View v) {
            Intent intent = new Intent(RegisterActivity.this, FirstActivity.class);
           startActivity(intent);
       }
      });
    }
    private void UserRegister(String email,String pass) {
        if(TextUtils.isEmpty(email)||TextUtils.isEmpty(pass)){
            Toast.makeText(RegisterActivity.this, "Some Fields are Empty", Toast.LENGTH_SHORT).show();
            return;
        }
        progressDialog.setMessage("Registration......");
        progressDialog.show();

       mAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {

                    Toast.makeText(RegisterActivity.this, "Registration Successful", Toast.LENGTH_SHORT).show();

                }
            else
                {
                    Toast.makeText(RegisterActivity.this, "Registration Fail", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}
