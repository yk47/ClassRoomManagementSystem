package com.yk47.Studentmanage;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;


public class FirstActivity extends Activity implements View.OnClickListener{
private ImageView stdimg,techimg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        stdimg=(ImageView) findViewById(R.id.imageView3);
        techimg=(ImageView) findViewById(R.id.imageView4);
        stdimg.setOnClickListener(this);
         techimg.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.imageView3:
                Toast.makeText(this,"Student Login", Toast.LENGTH_SHORT).show();
                Intent intent2 = new Intent(FirstActivity.this, StdLoginActivity.class);
                startActivity(intent2);
                break;
            case R.id.imageView4:
                Toast.makeText(this,"Teacher Login", Toast.LENGTH_SHORT).show();
                Intent intent3 = new Intent(FirstActivity.this, TeachLoginActivity.class);
                startActivity(intent3);
                break;
        }
    }
}
