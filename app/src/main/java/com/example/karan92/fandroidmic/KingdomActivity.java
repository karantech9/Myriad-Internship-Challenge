package com.example.karan92.fandroidmic;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;


public class KingdomActivity extends ActionBarActivity  {

    private Toolbar mToolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kingdom);
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
        //setting up toolbar
        mToolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        SharedPreferences signUp = getSharedPreferences("registrationInfo",1);
        String userEmail = signUp.getString("Email","");
        actionBar.setTitle(userEmail);

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
                        Toast.makeText(getApplication(), "Logout Successful",Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(getApplication(), SignUpActivity.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(i);

                        return false;
                    default:
                        break;
                }
                return false;

            }
        });

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_toolbar, menu);
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

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        AdapterForKingdoms mAdapterForKingdoms;
        RecyclerView mRecyclerView;
        RecyclerView.LayoutManager mLayoutManager;
        List<Kingdoms> kingdoms = null;

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_kingdom, container, false);
            mRecyclerView = (RecyclerView) rootView.findViewById(R.id.cardList);
            mRecyclerView.setHasFixedSize(true);
            mLayoutManager = new LinearLayoutManager(getActivity());
            mRecyclerView.setLayoutManager(mLayoutManager);

              //Calling AsyncTask
            KingdomBackgroundTask mKingdomBackgroundTask = new KingdomBackgroundTask(getActivity(),mRecyclerView);
            mKingdomBackgroundTask.execute((Void)null);

            return rootView;
        }
    }
}
