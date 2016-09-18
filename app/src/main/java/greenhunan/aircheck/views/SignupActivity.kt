package greenhunan.aircheck.views

import android.app.ProgressDialog
import android.content.DialogInterface
import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

import org.w3c.dom.Text

import butterknife.BindView
import butterknife.ButterKnife
import greenhunan.aircheck.R

/**
 * Register
 */
class SignupActivity : AppCompatActivity() {

    private var mAuthTask: UserSignupTask? = null

    private var mProgressDialog: ProgressDialog? = null

    @BindView(R.id.input_name) internal var mUsername: EditText? = null
    @BindView(R.id.input_email) internal var mEmail: EditText? = null
    @BindView(R.id.input_password) internal var mPassword: EditText? = null
    @BindView(R.id.btn_signup) internal var mSignup: Button? = null
    @BindView(R.id.link_login) internal var mLinkLogin: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        ButterKnife.bind(this)

        Log.i(TAG, "views initializing")
        mLinkLogin!!.setOnClickListener { finish() }

        mSignup!!.setOnClickListener { attempSignup() }
    }

    private fun attempSignup() {

        mUsername!!.error = null
        mEmail!!.error = null
        mPassword!!.error = null

        val username = mUsername!!.text.toString()
        val password = mPassword!!.text.toString()
        val email = mEmail!!.text.toString()

        var cancel = false
        var focusView: View? = null

        if (TextUtils.isEmpty(username)) {
            mUsername!!.error = getString(R.string.error_field_required)
            focusView = mUsername
            cancel = true
        }

        if (TextUtils.isEmpty(password)) {
            mPassword!!.error = getString(R.string.error_field_required)
            focusView = mPassword
            cancel = true
        }

        if (TextUtils.isEmpty(email)) {
            mEmail!!.error = getString(R.string.error_field_required)
            focusView = mEmail
            cancel = true
        }

        if (!cancel) {
            showProgress(true)
            //            mAuthTask = new UserSignupTask(username, password, email);
            //            mAuthTask.execute((Void) null);
        } else {
            focusView!!.requestFocus()
        }
    }

    private fun showProgress(show: Boolean) {

        if (show) {
            setSignupButtonEnabled(false)
            mProgressDialog = ProgressDialog(applicationContext, R.style.AppTheme_Dark_Dialog)
            mProgressDialog!!.isIndeterminate = true
            mProgressDialog!!.setMessage(getString(R.string.dialog_signup))
            mProgressDialog!!.setOnCancelListener { setSignupButtonEnabled(true) }
            mProgressDialog!!.show()
        } else {
            mProgressDialog!!.dismiss()
        }
    }

    private fun setSignupButtonEnabled(flag: Boolean) {
        mSignup!!.isEnabled = flag
    }

    inner class UserSignupTask internal constructor(private val username: String, private val password: String, private val email: String) : AsyncTask<Void, Void, Boolean>() {

        override fun doInBackground(vararg params: Void): Boolean? {
            return null
        }

        override fun onPostExecute(success: Boolean?) {
            mAuthTask = null
            showProgress(false)

            if (success!!) {
                finish()
            } else {
                onLoginFailed()
            }
        }

        override fun onCancelled() {
            mAuthTask = null
            showProgress(false)
        }
    }

    private fun onLoginFailed() {
        setSignupButtonEnabled(true)

        // TODO: 8/19/16 Snackbar
    }

    companion object {

        private val TAG = "SignupActivity"
    }


}
