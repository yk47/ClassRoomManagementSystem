package com.yk47.Studentmanage;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.ImageView;
public class AppinfoActivity extends Activity {
    private TextView stdmage,tilak,devel;
    private ImageView logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appinfo);
        stdmage=(TextView)findViewById(R.id.tv1);
        tilak=(TextView)findViewById(R.id.tv2);
        devel=(TextView)findViewById(R.id.tv3);
        logo=(ImageView)findViewById(R.id.imageView);
    }
}
