package com.example.karan92.fandroidmic;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.List;

import retrofit.RestAdapter;

/**
 * Created by Karan92 on 3/16/2015.
 */
public class KingdomBackgroundTask extends AsyncTask<Void,Integer,Boolean> {

    List<Kingdoms> kingdoms;
    AdapterForKingdoms mAdapterForKingdoms;
    Activity activity;
    RecyclerView mRecyclerView;
    ProgressBar mProgressBar;

    KingdomBackgroundTask(Activity activity, RecyclerView mRecyclerView, ProgressBar mProgressBar){
        this.activity = activity;
        this.mRecyclerView = mRecyclerView;
        this.mProgressBar = mProgressBar;

    }

    @Override
    protected Boolean doInBackground(Void... params) {
        try{
            RestAdapter restAdapter = new RestAdapter.Builder()
                    .setEndpoint("https://challenge2015.myriadapps.com")
                    .build();

            RetrofitService service = restAdapter.create(RetrofitService.class);
            kingdoms = service.getKingdoms();

            if(kingdoms.isEmpty()) throw new Exception();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
       return true;
    }

    @Override
    protected void onPostExecute(final Boolean result){
        if(result){

                //setting up adapter
                mProgressBar.setVisibility(View.INVISIBLE);
                mAdapterForKingdoms = new AdapterForKingdoms(activity,kingdoms);
                mRecyclerView.setAdapter(mAdapterForKingdoms);


                //on click view pager activity loads
                mAdapterForKingdoms.SetOnItemClickListener(new AdapterForKingdoms.OnItemClickListner() {
                    @Override
                    public void onItemClick(View view, int position) {
                        String kingdomID = kingdoms.get(position).getId();
                        Intent i = new Intent(activity, KingdomViewPagerActtivity.class);
                        i.putExtra("kingdomID", kingdomID);
                        i.putExtra("kingdomName", kingdoms.get(position).getName());
                        activity.startActivity(i);

                    }
                });
        }
        else
        {
            Toast.makeText(activity,"Error Fetching Information! Try Again!",Toast.LENGTH_SHORT).show();
        }

    }

}
