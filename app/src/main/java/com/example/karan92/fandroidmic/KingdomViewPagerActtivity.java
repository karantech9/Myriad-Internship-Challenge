package com.example.karan92.fandroidmic;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import retrofit.RestAdapter;


public class KingdomViewPagerActtivity extends ActionBarActivity {

    AdapterForViewPagerFragments mViewPagerAdapter;
    ViewPager mViewPager;
    Kingdoms kingdom;
    String previousKingdomId;
    QuestBackgroundTask mQuestBackgroundTask;
    List<Fragment> requiredFragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kingdom_view_pager_acttivity);
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
        previousKingdomId = getIntent().getStringExtra("kingdomID");
        System.out.println("KINGDOM ID ="+previousKingdomId);
        mQuestBackgroundTask = new QuestBackgroundTask();
        mQuestBackgroundTask.execute();

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

            mViewPager = (ViewPager) findViewById(R.id.pager);
            mViewPagerAdapter = new AdapterForViewPagerFragments(getSupportFragmentManager(),requiredFragments);
            customizeViewPager();
            mViewPager.setAdapter(mViewPagerAdapter);
        }
    }
    public void customizeViewPager(){
        mViewPager.setPageTransformer(false, new ViewPager.PageTransformer() {
            @Override
            public void transformPage(View page, float position) {
                page.setRotationY(position*-50);
                final float norma = Math.abs(Math.abs(position)-1);
                page.setAlpha(norma);
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_kingdom_view_pager_acttivity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
