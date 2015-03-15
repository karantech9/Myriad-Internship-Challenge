package com.example.karan92.fandroidmic;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class SplashFragment extends Fragment {

    private static String TAG = MainActivity.class.getName();
    private static long SLEEP_TIME = 2;    // Sleep for some time

    public SplashFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_splash, container, false);

        // Start timer and launch another activity
        IntentLauncher launcher = new IntentLauncher();
        launcher.start();
        return rootView;
    }


    private class IntentLauncher extends Thread {
        @Override
        /**
         * Sleep for some time and than start new activity.
         */
        public void run() {
            try {
                // Sleeping
                Thread.sleep(SLEEP_TIME*1000);
            } catch (Exception e) {
                Log.e(TAG, e.getMessage());
            }
            SharedPreferences signUp = getActivity().getSharedPreferences("registrationInfo",1);
            SharedPreferences.Editor editor = signUp.edit();
            String email = signUp.getString("Email","");
            if(!email.isEmpty()){
                Intent showKingdomsIntent = new Intent(getActivity(), KingdomActivity.class);
                startActivity(showKingdomsIntent);
                getActivity().finish();
                return;
            }

            Intent i = new Intent(getActivity(),SignUpActivity.class);
            startActivity(i);
            getActivity().finish();

        }
    }


}
