package com.yk47.Studentmanage;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class StdLoginActivity extends Activity {
    private EditText Name, password;

    private Button Login, Register;
    FirebaseAuth mAuth;
    FirebaseAuth.AuthStateListener authlistener;
    ProgressDialog pd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stdlogin);
        mAuth = FirebaseAuth.getInstance();
        pd = new ProgressDialog(this);
        Name = (EditText) findViewById(R.id.ed1);
        password = (EditText) findViewById(R.id.ed2);
        Login = (Button) findViewById(R.id.btnLogin);
        Register = (Button) findViewById(R.id.btRegister);
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validate((Name.getText().toString()), (password.getText().toString()));
                singin();
            }
        });

        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent2 = new Intent(StdLoginActivity.this, RegisterActivity.class);
                startActivity(intent2);
            }
        });
        authlistener=new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if(mAuth.getCurrentUser()!=null)
                {
                    Intent intent9 = new Intent(StdLoginActivity.this, StudentActivity.class);
                    startActivity(intent9);
                }
            }
        };
    }
    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(authlistener);
    }
    public void validate(String userName, String userPassword) {

        if ((userName.equals("Admin")) && (userPassword.equals("1234"))) {
            Intent intent = new Intent(StdLoginActivity.this, ResultTActivity.class);
            startActivity(intent);
        } else
            showMessage("Invalid Login", "Enter a Valid UserName or Password");
    }


    public void showMessage(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

    public void singin() {
        final String email, pass;
        email = Name.getText().toString();
        pass = password.getText().toString();
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(StdLoginActivity.this, "Enter Email", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(pass)) {
            Toast.makeText(StdLoginActivity.this, "Enter Password", Toast.LENGTH_SHORT).show();
            return;
        }
        pd.setMessage("Trying to Login");
        pd.show();
        mAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(StdLoginActivity.this, "" + email, Toast.LENGTH_SHORT).show();
                    return;
                } else
                    Toast.makeText(StdLoginActivity.this, "Invalid Login", Toast.LENGTH_SHORT).show();
                return;
            }
        });

    }
}