package com.example.a;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private TextView tv_toobar;
    private Toolbar toobar;
    private ViewPager vp;
    private ArrayList<Fragment> list;
    private TabLayout tab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        tv_toobar = (TextView) findViewById(R.id.tv_toobar);
        toobar = (Toolbar) findViewById(R.id.toobar);
        vp = (ViewPager) findViewById(R.id.vp);
        tab = (TabLayout) findViewById(R.id.tab);
        toobar.setTitle("");
        tv_toobar.setText("我的收藏");
        setSupportActionBar(toobar);
        list = new ArrayList<>();
        list.add(new AFragment());
        list.add(new BFragment());

        Fragmentadapter fragmentadapter = new Fragmentadapter(getSupportFragmentManager(), list);
        vp.setAdapter(fragmentadapter);
        tab.setupWithViewPager(vp);
        tab.getTabAt(0).setText("全部");
        tab.getTabAt(1).setText("视频");
       initview();
       //添加竖的分割线
        LinearLayout linearLayout = (LinearLayout) tab.getChildAt(0);
        linearLayout.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
        linearLayout.setDividerDrawable(ContextCompat.getDrawable(this, R.drawable.layout_divider_vertical));
        linearLayout.setDividerPadding(30);
        //隐藏下划线
        tab.setSelectedTabIndicatorHeight(0);

    }

    private void initview() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case  android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_options,menu);
        return super.onCreateOptionsMenu(menu);

    }
}
