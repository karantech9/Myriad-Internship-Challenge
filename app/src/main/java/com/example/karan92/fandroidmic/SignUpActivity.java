package com.example.karan92.fandroidmic;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class SignUpActivity extends ActionBarActivity {

    private EditText mUserName;
    private EditText mUserEmail;
    private Button mSubmitButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mUserName = (EditText) findViewById(R.id.userName);
        mUserEmail = (EditText) findViewById(R.id.userEmail);
        mSubmitButton = (Button) findViewById(R.id.submit_button);

        mSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String userName = mUserName.getText().toString();
                String userEmail = mUserEmail.getText().toString();
                Boolean bothFilled = false;

                if(TextUtils.isEmpty(userName))
                {
                    mUserName.setError("Name is required");
                    mUserName.requestFocus();
                    bothFilled = true;
                }
                else if (!(userName.matches("^[a-zA-Z][a-zA-Z '&-]*[A-Za-z0-9]$"))){
                    mUserName.setError("Invalid Name");
                    bothFilled = true;
                }

                if (TextUtils.isEmpty(userEmail))
                {
                    mUserEmail.setError("Email Id is required");
                    mUserName.requestFocus();
                    bothFilled = true;
                }
                else if(!(userEmail.matches("[-0-9a-zA-Z.+_]+@[-0-9a-zA-Z.+_]+\\.[a-zA-Z]{2,4}"))){
                    mUserEmail.setError("Invalid Email Id");
                    bothFilled = true;
                }

                if (bothFilled) {

                    mUserName.requestFocus();
                    mUserEmail.requestFocus();
                } else {

                    SignUpBackgroudTask a = new SignUpBackgroudTask(SignUpActivity.this);
                    a.execute(userEmail,userName);
                    System.out.println("filled");
                }
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sign_up, menu);
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
