package com.yk47.Studentmanage;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;


public class MyRecycleViewActivity extends Activity {
    RecyclerView recyclerView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_recycle_view);
        DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference("Upurl");
        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                String fileName=dataSnapshot.getKey();
                String url=dataSnapshot.getValue(String.class);
                ((myAdapter)recyclerView.getAdapter()).update(fileName,url);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        recyclerView=findViewById(R.id.recyckerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(MyRecycleViewActivity.this));
        myAdapter myAdapter=new myAdapter(recyclerView,MyRecycleViewActivity.this,new ArrayList<String>(),new ArrayList<String>());
        recyclerView.setAdapter(myAdapter);
    }
}
