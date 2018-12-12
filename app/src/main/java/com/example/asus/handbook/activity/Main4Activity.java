package com.example.asus.handbook.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import com.example.asus.handbook.R;

public class Main4Activity extends AppCompatActivity {

    private RecyclerView view_schedule;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.schedule);

        BottomNavigationView mNavigationView = findViewById(R.id.navigation);

        mNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                        switch (item.getItemId()) {
                            case R.id.main:
                                Intent intent = new Intent(Main4Activity.this, Main3Activity.class);
                                startActivity(intent);
                                Main4Activity.this.finish();
                                break;
                            case R.id.community:
                                intent=new Intent(Main4Activity.this, CommunityActivity.class);
                                startActivity(intent);
                                break;
                            case R.id.find:
                                intent=new Intent(Main4Activity.this, SearchingActivity.class);
                                startActivity(intent);
                                break;
                            case R.id.me:
                                intent=new Intent(Main4Activity.this, MeActivity.class);
                                startActivity(intent);
                                break;
                        }
                        return true;
                    }
                });

        view_schedule=findViewById(R.id.scrollView);

        //本地的视频  需要在手机SD卡根目录添加一个 fl1234.mp4 视频
        String videoUrl2 ="/mnt/sdcard/Download/video2.mp4" ;
        String videoUrl3 ="/mnt/sdcard/Download/video3.mp4" ;
        String videoUrl4="/mnt/sdcard/Download/video4.mp4" ;
        String videoUrl5 ="/mnt/sdcard/Download/video5.mp4" ;


        Uri uri2 = Uri.parse( videoUrl2);
        Uri uri3 = Uri.parse( videoUrl3);
        Uri uri4 = Uri.parse( videoUrl4);
        Uri uri5 = Uri.parse( videoUrl5);

        /*
        videoView2 = this.findViewById(R.id.videoView2);
        videoView3 = this.findViewById(R.id.videoView3);
        videoView4 = this.findViewById(R.id.videoView4);
        videoView5 = this.findViewById(R.id.videoView5);


        //设置视频控制器
        videoView2.setMediaController(new MediaController(this));
        videoView3.setMediaController(new MediaController(this));
        videoView4.setMediaController(new MediaController(this));
        videoView5.setMediaController(new MediaController(this));

        //设置视频路径
        videoView2.setVideoURI(uri2);
        videoView3.setVideoURI(uri3);
        videoView4.setVideoURI(uri4);
        videoView5.setVideoURI(uri5);


        //播放完成回调
        videoView2.setOnCompletionListener( new MyPlayerOnCompletionListener());
        videoView3.setOnCompletionListener( new MyPlayerOnCompletionListener());
        videoView4.setOnCompletionListener( new MyPlayerOnCompletionListener());
        videoView5.setOnCompletionListener( new MyPlayerOnCompletionListener());
*/

    }
    //开始播放视频
    /*
    ImageButton.OnClickListener listener2 = new ImageButton.OnClickListener() {
        public void onClick(View v) {
            videoView2.start();
        }
    };

    ImageButton.OnClickListener listener3 = new ImageButton.OnClickListener() {
        public void onClick(View v) {
            videoView3.start();
        }
    };
    ImageButton.OnClickListener listener4 = new ImageButton.OnClickListener() {
        public void onClick(View v) {
            videoView4.start();
        }
    };
    ImageButton.OnClickListener listener5 = new ImageButton.OnClickListener() {
        public void onClick(View v) {
            videoView5.start();
        }
    };
    */


}