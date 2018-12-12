package com.example.asus.handbook.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.asus.handbook.R;
import com.example.asus.handbook.fragment.CourseFragment;

import java.util.ArrayList;

public class SearchingActivity extends AppCompatActivity implements CourseFragment.OnFragmentInteractionListener{

    private BottomNavigationView navigationView;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ArrayList<Fragment> listFragment;
    private MeAdapter myAdapter;
    private ImageButton searchButton;
    private Spinner spinner;
    private EditText editText;
    private static String currentusername;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searching);

        //隐藏软键盘
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        currentusername = getIntent().getStringExtra("username");

        /* 设置底部菜单栏 */
        navigationView = findViewById(R.id.navigation);
        navigationView.getMenu().getItem(0).setChecked(false);
        navigationView.getMenu().getItem(2).setChecked(true);

        navigationView = findViewById(R.id.navigation);
        navigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        Intent intent;
                        switch(item.getItemId()) {
                            case R.id.main:
                                intent=new Intent(SearchingActivity.this, Main3Activity.class);
                                intent.putExtra("username",currentusername);
                                startActivity(intent);
                                break;
                            case R.id.community:
                                intent=new Intent(SearchingActivity.this, CommunityActivity.class);
                                intent.putExtra("username",currentusername);
                                startActivity(intent);
                                break;
                            case R.id.find:
                                intent=new Intent(SearchingActivity.this, SearchingActivity.class);
                                intent.putExtra("username",currentusername);
                                startActivity(intent);
                                SearchingActivity.this.finish();
                                break;
                            case R.id.me:
                                intent=new Intent(SearchingActivity.this, MeActivity.class);
                                intent.putExtra("username",currentusername);
                                startActivity(intent);
                                break;
                        }
                        return true;
                    }
                }
        );

        //设置子菜单（课程类型）
        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);

        listFragment= new ArrayList<>();

        CourseFragment fragment1 = new CourseFragment();
        Bundle bundle1 = new Bundle();
        bundle1.putString("type","瑜伽");

        /* 加一个传入参数 */
        bundle1.putString("username",currentusername);
        fragment1.setArguments(bundle1);
        listFragment.add(fragment1);

        CourseFragment fragment2 = new CourseFragment();
        Bundle bundle2 = new Bundle();
        bundle2.putString("type","操类");

        /* 加一个传入参数 */
        bundle2.putString("username",currentusername);
        fragment2.setArguments(bundle2);
        listFragment.add(fragment2);

        CourseFragment fragment3 = new CourseFragment();
        Bundle bundle3 = new Bundle();
        bundle3.putString("type","器械");

        /* 加一个传入参数 */
        bundle3.putString("username",currentusername);
        fragment3.setArguments(bundle3);
        listFragment.add(fragment3);

        CourseFragment fragment4 = new CourseFragment();
        Bundle bundle4 = new Bundle();
        bundle4.putString("type","户外");
        bundle4.putString("username",currentusername);
        fragment4.setArguments(bundle4);
        listFragment.add(fragment4);

        CourseFragment fragment5 = new CourseFragment();
        Bundle bundle5 = new Bundle();
        bundle5.putString("type","营养");
        bundle5.putString("username",currentusername);
        fragment5.setArguments(bundle5);
        listFragment.add(fragment5);

        CourseFragment fragment6 = new CourseFragment();
        Bundle bundle6 = new Bundle();
        bundle6.putString("type","教练");
        bundle6.putString("username",currentusername);
        fragment6.setArguments(bundle6);
        listFragment.add(fragment6);

        FragmentManager fm=getSupportFragmentManager();
        myAdapter = new MeAdapter(fm);
        viewPager.setAdapter(myAdapter);
        //ViewPager的预加载解决办法
        viewPager.setOffscreenPageLimit(listFragment.size());
        //TabLayout和ViewPager进行联动
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.removeAllTabs();
        tabLayout.addTab(tabLayout.newTab().setText(getResources().getString(R.string.stringYoga)));
        tabLayout.addTab(tabLayout.newTab().setText(getResources().getString(R.string.stringExercise)));
        tabLayout.addTab(tabLayout.newTab().setText(getResources().getString(R.string.stringApparatus)));
        tabLayout.addTab(tabLayout.newTab().setText(getResources().getString(R.string.stringOutdoor)));
        tabLayout.addTab(tabLayout.newTab().setText(getResources().getString(R.string.stringNutrition)));
        tabLayout.addTab(tabLayout.newTab().setText(getResources().getString(R.string.stringCoach)));

        searchButton = findViewById(R.id.imageButton);
        spinner = findViewById(R.id.spinner);
        editText = findViewById(R.id.editText);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchType = spinner.getSelectedItem().toString();
                String searchInput = editText.getText().toString();
                if(searchInput.length() == 0){
                    Toast ts = Toast.makeText(SearchingActivity.this,getString(getResources().getIdentifier("stringSearchNull", "string", getPackageName())), Toast.LENGTH_LONG);
                    ts.show();
                    return;
                }
                Intent intent = new Intent(SearchingActivity.this, SearchResultActivity.class);
                intent.putExtra("searchType", searchType);
                intent.putExtra("searchInput", searchInput);
                intent.putExtra("username",currentusername);
                startActivity(intent);
            }

        });

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    class MeAdapter extends FragmentPagerAdapter {

        private MeAdapter(FragmentManager fm) {
            super(fm);
        }
        @Override
        public Fragment getItem(int position) {
            return listFragment.get(position);
        }

        @Override
        public int getCount() {
            return listFragment.size();
        }

    }


}
