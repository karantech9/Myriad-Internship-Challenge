package com.example.karan92.fandroidmic;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.widget.Toast;

import retrofit.RestAdapter;

/**
 * Created by Karan92 on 3/15/2015.
 */
public class SignUpBackgroudTask extends AsyncTask<String, Void, Boolean> {

    Activity mActivity;

    SignUpBackgroudTask (Activity activity) {
        super();
        mActivity = activity;
    }

    @Override
    protected Boolean doInBackground(String... params) {
        String email = params[0];
        String name = params[1];
        String response = null;

        Registration newRegistration = null;
        try {
            RestAdapter restAdapter = new RestAdapter.Builder()
                    .setEndpoint("https://challenge2015.myriadapps.com")
                    .setLogLevel(RestAdapter.LogLevel.FULL)
                    .build();

            RetrofitService service = restAdapter.create(RetrofitService.class);
            newRegistration = service.signUp(email);

            response = newRegistration.getMessage();
            if(!response.contains("thank you"))
            {
                throw new Exception();
            }
       } catch (Exception e) {
            e.printStackTrace();
       }

        newRegistration.setUserName(name);
        newRegistration.setUserEmail(email);

        SharedPreferences signUp = mActivity.getSharedPreferences("registrationInfo",1);
        SharedPreferences.Editor editor = signUp.edit();
        editor.putString("Email", newRegistration.getUserEmail());
        editor.commit();

        return true;
    }

    @Override
    protected void onPostExecute(final Boolean success) {

        if (success) {
            Toast.makeText(mActivity, "Bravo! Signed Up!", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(mActivity, KingdomActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            mActivity.startActivity(i);
            this.mActivity.finish();
        } else {
            System.out.println("UnSuccess");
        }
    }

}
