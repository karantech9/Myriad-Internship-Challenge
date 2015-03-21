package com.example.karan92.fandroidmic;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit.RestAdapter;


public class KingdomViewPagerActtivity extends ActionBarActivity {

    private static AdapterForViewPagerFragments mViewPagerAdapter;
    private static ViewPager mViewPager;
    private static Kingdoms kingdom;
    private static String previousKingdomId;
    private static String previousKingdomName;
    private static QuestBackgroundTask mQuestBackgroundTask;
    private static List<Fragment> requiredFragments;
    private Toolbar mToolbar;
    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kingdom_view_pager_acttivity);
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
        previousKingdomId = getIntent().getStringExtra("kingdomID");
        mToolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationIcon(R.drawable.back);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        previousKingdomName = getIntent().getStringExtra("kingdomName");
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(previousKingdomName);

        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
        mProgressBar.setVisibility(View.VISIBLE);

        //calling async task
        mQuestBackgroundTask = new QuestBackgroundTask();
        mQuestBackgroundTask.execute();

        mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                int id = menuItem.getItemId();
                switch (id){
                    case R.id.logOut:

                        SharedPreferences signUp = getSharedPreferences("registrationInfo",1);
                        SharedPreferences.Editor editor = signUp.edit();
                        editor.putString("Email","");
                        editor.commit();
                        Toast.makeText(getApplication(), "Logout Successful", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(getApplication(), SignUpActivity.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(i);
                        finish();
                        return false;
                    default:
                        break;
                }
                return false;

            }
        });


    }


    public class QuestBackgroundTask extends AsyncTask<Void,Void,Boolean> {

        QuestBackgroundTask(){
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            try {
                //Get the subscription from Myriad web api
                RestAdapter restAdapter = new RestAdapter.Builder()
                        .setEndpoint("https://challenge2015.myriadapps.com")
                        .build();

                RetrofitService service = restAdapter.create(RetrofitService.class);
                kingdom = service.getQuests(previousKingdomId);
                if(kingdom==null){
                    throw new InterruptedException() ;
                }
            } catch (InterruptedException e) {
                return false;
            }

            List<Fragment> fList = new ArrayList<Fragment>();
            //Load Kingdom Fragment first
            fList.add(KingdomViewPagerFragment.newInstance(kingdom));

            for(Kingdoms.Quest quest: kingdom.getQuests()){
                //Load quest fragment thereafter
                fList.add(QuestViewPagerFragment.newInstance(quest));

            }

            requiredFragments = fList;

            return true;
        }

        public void onPostExecute(Boolean success)
        {
            mProgressBar.setVisibility(View.INVISIBLE);

            //setting adapter for ViewPager
            mViewPager = (ViewPager) findViewById(R.id.pager);
            mViewPagerAdapter = new AdapterForViewPagerFragments(getSupportFragmentManager(),requiredFragments);
            customizeViewPager();
            mViewPager.setAdapter(mViewPagerAdapter);
        }
    }

    //added animation while sliding the screen
    public static void customizeViewPager(){
        mViewPager.setPageTransformer(false, new ViewPager.PageTransformer() {
            @Override
            public void transformPage(View page, float position) {
                page.setRotationY(position*-50);
                final float norma = Math.abs(Math.abs(position)-1);
                page.setAlpha(norma);
            }
        });
    }

}
