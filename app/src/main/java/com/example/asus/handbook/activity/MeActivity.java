package com.example.asus.handbook.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asus.handbook.userdefined.CircleTransform;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;


import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import com.example.asus.handbook.R;
import com.example.asus.handbook.dataobject.MyUser;

import java.util.List;


public class MeActivity extends AppCompatActivity {

    private BottomNavigationView navigationView;
    private static String currentusername;
    private ImageView figure;
    private TextView username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_me);

        currentusername = getIntent().getStringExtra("username");

        navigationView = findViewById(R.id.navigation);
        navigationView.getMenu().getItem(0).setChecked(false);
        navigationView.getMenu().getItem(3).setChecked(true);

        navigationView = findViewById(R.id.navigation);
        navigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        Intent intent;
                        switch(item.getItemId()) {
                            case R.id.main:
                                intent=new Intent(MeActivity.this, Main3Activity.class);
                                intent.putExtra("username",currentusername);
                                startActivity(intent);
                                break;
                            case R.id.community:
                                intent=new Intent(MeActivity.this, CommunityActivity.class);
                                intent.putExtra("username",currentusername);
                                startActivity(intent);
                                break;
                            case R.id.find:
                                intent=new Intent(MeActivity.this, SearchingActivity.class);
                                intent.putExtra("username",currentusername);
                                startActivity(intent);
                                break;
                            case R.id.me:
                                intent=new Intent(MeActivity.this, MeActivity.class);
                                intent.putExtra("username",currentusername);
                                startActivity(intent);
                                MeActivity.this.finish();
                                break;
                        }
                        return true;
                    }
                }
        );

        figure = findViewById(R.id.image);
        BmobQuery<MyUser> query = new BmobQuery<>();
        query.addWhereEqualTo("username",currentusername);
        query.findObjects(new FindListener<MyUser>() {
            @Override
            public void done(List<MyUser> list, BmobException e) {
                if(e == null){
                    if(list.size()!= 0){
                        Picasso.with(MeActivity.this).load(list.get(0).getFigureimage()).memoryPolicy(MemoryPolicy.NO_CACHE)
                                .transform(new CircleTransform()).into(figure);
                    }
                }
                else{
                    System.out.println("error");
                    Toast ts = Toast.makeText(MeActivity.this,getResources().getString(getResources().getIdentifier("showFigureFail", "string", getPackageName())), Toast.LENGTH_LONG);
                    ts.show() ;
                }
            }
        });
        username = findViewById(R.id.textView2);
        username.setText(currentusername);

    }

    public void logOut(View view) {
        Intent intent;
        intent = new Intent(getApplicationContext(),Main2Activity.class);
        startActivity(intent);
        MeActivity.this.finish();
    }
}
