package com.example.karan92.fandroidmic;

import android.app.Activity;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.List;

import retrofit.RestAdapter;

/**
 * Created by Karan92 on 3/16/2015.
 */
public class KingdomBackgroundTask extends AsyncTask<Void,Void,Boolean> {

    List<Kingdoms> kingdoms;
    AdapterForKingdoms mAdapterForKingdoms;
    Activity activity;
    RecyclerView mRecyclerView;

    KingdomBackgroundTask(Activity activity, RecyclerView mRecyclerView){
        this.activity = activity;
        this.mRecyclerView = mRecyclerView;
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
                mAdapterForKingdoms = new AdapterForKingdoms(activity,kingdoms);
                mRecyclerView.setAdapter(mAdapterForKingdoms);
        }
        else
        {
            Toast.makeText(activity,"Error Fetching Information! Try Again!",Toast.LENGTH_SHORT).show();
        }

    }
}
