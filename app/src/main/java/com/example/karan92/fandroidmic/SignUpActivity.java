package com.example.karan92.fandroidmic;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;


public class SignUpActivity extends ActionBarActivity {

    private EditText mUserName;
    private EditText mUserEmail;
    private Button mSubmitButton;
    private ProgressBar mProgressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);

        mUserName = (EditText) findViewById(R.id.userName);
        mUserEmail = (EditText) findViewById(R.id.userEmail);
        mSubmitButton = (Button) findViewById(R.id.submit_button);
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);

        mSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String userName = mUserName.getText().toString();
                String userEmail = mUserEmail.getText().toString();
                Boolean bothFilled = false;

                //name, email field validation
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

                    //calling async task
                    SignUpBackgroudTask a = new SignUpBackgroudTask(SignUpActivity.this);
                    a.execute(userEmail,userName);
                    mProgressBar.setVisibility(View.VISIBLE);
                }
            }
        });
    }


    @Override
    public void onBackPressed() {
        finish();
    }
}
