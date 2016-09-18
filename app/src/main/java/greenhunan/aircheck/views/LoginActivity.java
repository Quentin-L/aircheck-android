package greenhunan.aircheck.views;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import android.os.AsyncTask;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.ButterKnife;
import greenhunan.aircheck.R;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {

    // debug 
    private static final String TAG = "LoginActivity";

    private ProgressDialog mProgressDialog;

    // UI references, bound by butterKnife
//    { Log.i(TAG, "butter knife binds views"); }
//    @BindView(R.id.input_username)
    EditText mUsernameInput;
//    @BindView(R.id.input_password)
    EditText mPasswordInput;
//    @BindView(R.id.btn_login)
    Button mLoginButton;
//    @BindView(R.id.link_signup)
    TextView mSignup;
//    @BindView(R.id.parentView)
    View parentView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Log.i(TAG, "butter knife binds");
        ButterKnife.bind(this);

        // Set up the login form.
//        populateAutoComplete();

        Log.i(TAG, "views initializing");
        initViews();
        mLoginButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });

        mSignup.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), SignupActivity.class));
            }
        });
    }

    // TODO: 8/18/16 debug; delete later
    private void initViews() {
        mUsernameInput = (EditText) findViewById(R.id.input_name);
        mPasswordInput = (EditText) findViewById(R.id.input_password);
//        mLoginButton = (Button) findViewById(R.id.btn_login);
//        mSignup = (TextView) findViewById(R.id.link_signup);
    }

    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private void attemptLogin() {
//        if (mAuthTask != null) {
//            return;
//        }

        // Reset errors.
        mUsernameInput.setError(null);
        mPasswordInput.setError(null);

        // Store values at the time of the login attempt.
        String email = mUsernameInput.getText().toString();
        String password = mPasswordInput.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (TextUtils.isEmpty(password)) {
            mPasswordInput.setError(getString(R.string.error_field_required));
            focusView = mPasswordInput;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            mUsernameInput.setError(getString(R.string.error_field_required));
            focusView = mUsernameInput;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            showProgress(true);

//            mAuthTask = new UserLoginTask(email, password);
//            mAuthTask.execute((Void) null);
        }
    }

    private void showProgress(boolean show) {
        setLoginButtonEnabled(false);

        if (show) {
            mProgressDialog = new ProgressDialog(LoginActivity.this, R.style.AppTheme_Dark_Dialog);
            mProgressDialog.setIndeterminate(true);
            mProgressDialog.setMessage("正在验证...");
            mProgressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialog) {
                    setLoginButtonEnabled(true);
                }
            });
            mProgressDialog.show();
        } else {
            mProgressDialog.dismiss();
        }
    }

    private void onLoginFailed() {
//        Snackbar.make(parentView,
//                getString(R.string.error_authentication_fail), Snackbar.LENGTH_INDEFINITE)
//                .setAction("Action", null).show();

        setLoginButtonEnabled(true);
    }

    private void setLoginButtonEnabled(boolean flag) {
        mLoginButton.setEnabled(flag);
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }
}

