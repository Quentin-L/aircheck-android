package greenhunan.aircheck.views;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;
import greenhunan.aircheck.R;

/**
 * Register
 */
public class SignupActivity extends AppCompatActivity {

    private static final String TAG = "SignupActivity";

    private UserSignupTask mAuthTask = null;

    private ProgressDialog mProgressDialog;

    @BindView(R.id.input_name) EditText mUsername;
    @BindView(R.id.input_email) EditText mEmail;
    @BindView(R.id.input_password) EditText mPassword;
    @BindView(R.id.btn_signup) Button mSignup;
    @BindView(R.id.link_login) TextView mLinkLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        ButterKnife.bind(this);

        Log.i(TAG, "views initializing");
        mLinkLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attempSignup();
            }
        });
    }

    private void attempSignup() {

        mUsername.setError(null);
        mEmail.setError(null);
        mPassword.setError(null);

        String username = mUsername.getText().toString();
        String password = mPassword.getText().toString();
        String email = mEmail.getText().toString();

        boolean cancel = false;
        View focusView = null;

        if (TextUtils.isEmpty(username)) {
            mUsername.setError(getString(R.string.error_field_required));
            focusView = mUsername;
            cancel = true;
        }

        if (TextUtils.isEmpty(password)) {
            mPassword.setError(getString(R.string.error_field_required));
            focusView = mPassword;
            cancel = true;
        }

        if (TextUtils.isEmpty(email)) {
            mEmail.setError(getString(R.string.error_field_required));
            focusView = mEmail;
            cancel = true;
        }

        if (!cancel) {
            showProgress(true);
//            mAuthTask = new UserSignupTask(username, password, email);
//            mAuthTask.execute((Void) null);
        } else {
            focusView.requestFocus();
        }
    }

    private void showProgress(boolean show) {

        if (show) {
            setSignupButtonEnabled(false);
            mProgressDialog = new ProgressDialog(SignupActivity.this, R.style.AppTheme_Dark_Dialog);
            mProgressDialog.setIndeterminate(true);
            mProgressDialog.setMessage(getString(R.string.dialog_signup));
            mProgressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialog) {
                    setSignupButtonEnabled(true);
                }
            });
            mProgressDialog.show();
        } else {
            mProgressDialog.dismiss();
        }
    }

    private void setSignupButtonEnabled(boolean enabled) {
        mSignup.setEnabled(enabled);
    }

    public class UserSignupTask extends AsyncTask<Void, Void, Boolean> {

        private String username;
        private String password;
        private String email;

        UserSignupTask(String username, String password, String email) {
            this.username = username;
            this.password = password;
            this.email = email;
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            return null;
        }

        @Override
        protected void onPostExecute(Boolean success) {
            mAuthTask = null;
            showProgress(false);

            if (success) {
                finish();
            } else {
                onLoginFailed();
            }
        }

        @Override
        protected void onCancelled() {
            mAuthTask = null;
            showProgress(false);
        }
    }

    private void onLoginFailed() {
        setSignupButtonEnabled(true);

        // TODO: 8/19/16 Snackbar
    }


}
