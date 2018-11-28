package com.example.asus.handbook.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asus.handbook.R;
import com.example.asus.handbook.adapter.MyAdapter;
import com.example.asus.handbook.dataobject.Coach;
import com.example.asus.handbook.dataobject.Course;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class CoachActivity extends AppCompatActivity {

    private BottomNavigationView mNavigationView;
    private RecyclerView view_teaching;
    private MyAdapter tAdapter;
    private static String currentusername;
    private List<String> values1,values2;
    private ImageView coachFigure;
    private TextView coachName, coachType;
    private static String coachname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coach);

        currentusername = getIntent().getStringExtra("username");
        coachname = getIntent().getStringExtra("coachname");

        mNavigationView = (BottomNavigationView) findViewById(R.id.navigation);
        mNavigationView.setSelectedItemId(R.id.find);//根据具体情况调用
        mNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.main:
                                Intent intent = new Intent(CoachActivity.this, Main3Activity.class);
                                intent.putExtra("username",currentusername);
                                startActivity(intent);
                                break;
                            case R.id.community:
                                intent=new Intent(CoachActivity.this, CommunityActivity.class);
                                intent.putExtra("username",currentusername);
                                startActivity(intent);
                                break;
                            case R.id.find:
                                intent=new Intent(CoachActivity.this, SearchingActivity.class);
                                intent.putExtra("username",currentusername);
                                startActivity(intent);
                                break;
                            case R.id.me:
                                intent=new Intent(CoachActivity.this, MeActivity.class);
                                intent.putExtra("username",currentusername);
                                startActivity(intent);
                                break;
                        }
                        return true;
                    }
                });

        coachFigure = findViewById(R.id.imageView);
        coachName = findViewById(R.id.textView);
        coachType = findViewById(R.id.textView2);

        BmobQuery<Coach> query=new BmobQuery<Coach>();
        query.addWhereEqualTo("coachname",this.coachname);
        query.findObjects(new FindListener<Coach>() {
            @Override
            public void done(List<Coach> list, BmobException e) {
                if(e == null){
                    if(list.size() != 0){
                        Picasso.with(CoachActivity.this).load(list.get(0).getImage()).into(coachFigure);
                        coachName.setText(list.get(0).getCName());
                        coachType.setText(list.get(0).getCoachtype());
                    }
                    else{
                        Toast ts = Toast.makeText(CoachActivity.this,getResources().getString(getResources().getIdentifier("showCoachFail", "string", getPackageName())), Toast.LENGTH_LONG);
                        ts.show() ;
                    }
                }
                else{
                    System.out.println("error");
                    Toast ts = Toast.makeText(CoachActivity.this,getResources().getString(getResources().getIdentifier("showCoachFail", "string", getPackageName())), Toast.LENGTH_LONG);
                    ts.show() ;
                }

            }
        });

        values1 = new ArrayList<>();
        values2 = new ArrayList<>();
        view_teaching = findViewById(R.id.view_teaching);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);// 将“教授课程”列表排列置为横向。
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        view_teaching.setLayoutManager(linearLayoutManager);
        view_teaching.setItemAnimator(new DefaultItemAnimator());
        BmobQuery<Course> query2 = new BmobQuery<>();
        query2.addWhereEqualTo("coachname",coachname);
        query2.findObjects(new FindListener<Course>() {
            @Override
            public void done(List<Course> list, BmobException e) {
                if(e == null){
                    for(int i = 0;i < list.size();i++){
                        values1.add(list.get(i).getName());
                        values2.add(list.get(i).getImage());
                    }
                    if(values1.size() != 0 && values2.size() != 0){
                        /* 加两个参数 */
                        tAdapter = new MyAdapter("课程",currentusername,values1,values2, R.layout.layout_mycoursecard, CoachActivity.this);
                        view_teaching.setAdapter(tAdapter);
                    }
                }
                else{
                    System.out.println("error");
                    Toast ts = Toast.makeText(CoachActivity.this,getResources().getString(getResources().getIdentifier("stringShowMCFail", "string", getPackageName())), Toast.LENGTH_LONG);
                    ts.show() ;
                }
            }
        });

    }

    // 致电教练
    public void makeCall(View view) {

    }

    // 传电邮给教练
    public void sendMail(View view) {
        View d_view = LayoutInflater.from(CoachActivity.this).inflate(R.layout.layout_mailbox, null);
        AlertDialog.Builder dialog =
                new AlertDialog.Builder(CoachActivity.this);
        dialog.setView(d_view);

        final TextView emailAddress = d_view.findViewById(R.id.textView);
        final EditText mailTitle = d_view.findViewById(R.id.editText);
        final EditText mailContent = d_view.findViewById(R.id.editText2);

        BmobQuery<Coach> query=new BmobQuery<Coach>();
        query.addWhereEqualTo("coachname",coachname);
        query.findObjects(new FindListener<Coach>() {
            @Override
            public void done(List<Coach> list, BmobException e) {
                if(e == null){
                    if(list.size() != 0){
                        emailAddress.setText("发送给："+coachName.getText().toString()+"("+list.get(0).getCoachmail()+")");
                    }
                    else{
                        Toast ts = Toast.makeText(CoachActivity.this,getResources().getString(getResources().getIdentifier("showMailAddressFail", "string", getPackageName())), Toast.LENGTH_LONG);
                        ts.show() ;
                    }
                }
                else{
                    System.out.println("error");
                    Toast ts = Toast.makeText(CoachActivity.this,getResources().getString(getResources().getIdentifier("showMailAddressFail", "string", getPackageName())), Toast.LENGTH_LONG);
                    ts.show() ;
                }

            }
        });

        dialog.setTitle(getResources().getString(getResources().getIdentifier("dialogTitle", "string", getPackageName())));
        dialog.setNegativeButton("取消", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {

            }
        });
        dialog.setPositiveButton("发送邮件",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(mailTitle.getText().toString().equalsIgnoreCase("") ||
                                mailContent.getText().toString().equalsIgnoreCase("") ){
                            Toast ts = Toast.makeText(CoachActivity.this,getResources().getString(getResources().getIdentifier("mailInputNull", "string", getPackageName())), Toast.LENGTH_LONG);
                            ts.show() ;
                        }
                        else{
                            BmobQuery<Coach> query=new BmobQuery<Coach>();
                            query.addWhereEqualTo("coachname",coachname);
                            query.findObjects(new FindListener<Coach>() {
                                @Override
                                public void done(List<Coach> list, BmobException e) {
                                    if(e == null){
                                        if(list.size() != 0){
                                            /* 发送邮件 */
                                            Intent data=new Intent(Intent.ACTION_SENDTO);
                                            data.setData(Uri.parse(list.get(0).getCoachmail()));
                                            data.putExtra(Intent.EXTRA_EMAIL, Uri.parse(list.get(0).getCoachmail()));
                                            data.putExtra(Intent.EXTRA_SUBJECT, mailTitle.getText().toString());
                                            data.putExtra(Intent.EXTRA_TEXT, mailContent.getText().toString());
                                            startActivity(data);
                                        }
                                        else{
                                            Toast ts = Toast.makeText(CoachActivity.this,getResources().getString(getResources().getIdentifier("sendMailFail", "string", getPackageName())), Toast.LENGTH_LONG);
                                            ts.show() ;
                                        }
                                    }
                                    else{
                                        System.out.println("error");
                                        Toast ts = Toast.makeText(CoachActivity.this,getResources().getString(getResources().getIdentifier("sendMailFail", "string", getPackageName())), Toast.LENGTH_LONG);
                                        ts.show() ;
                                    }

                                }
                            });
                        }
                    }
                });

        dialog.show();


    }

    // 传短信给教练
    public void sendMSG(View view) {

    }
}
