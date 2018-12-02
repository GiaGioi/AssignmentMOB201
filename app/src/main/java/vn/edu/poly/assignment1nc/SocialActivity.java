package vn.edu.poly.assignment1nc;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.facebook.login.widget.ProfilePictureView;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class SocialActivity extends AppCompatActivity {

    ProfilePictureView profilePictureView;
    LoginButton loginButton;
    Button logOut;
    TextView naMe,email,first_name;
    CallbackManager callbackManager;
    String Email,Name,FirstName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
        setContentView(R.layout.activity_social);
        profilePictureView = findViewById(R.id.imgProfilePicture);
        loginButton = findViewById(R.id.btnLogin);
        logOut = findViewById(R.id.btnLogout);
        naMe = findViewById(R.id.tvName);
        email = findViewById(R.id.tvName);
        first_name = findViewById(R.id.tvName);
        loginButton.setReadPermissions(Arrays.asList("public_profile","email"));
        setLogin_Button();

//        try {
//            PackageInfo info = getPackageManager().getPackageInfo(
//                    "vn.edu.poly.assignment1nc",
//                    PackageManager.GET_SIGNATURES);
//            for (Signature signature : info.signatures) {
//                MessageDigest md = MessageDigest.getInstance("SHA");
//                md.update(signature.toByteArray());
//                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
//            }
//        } catch (PackageManager.NameNotFoundException e) {
//
//        } catch (NoSuchAlgorithmException e) {
//
//        }
    }

    private void setLogin_Button() {
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                loginButton.setVisibility(View.VISIBLE);
                naMe.setVisibility(View.VISIBLE);
                email.setVisibility(View.VISIBLE);
                first_name.setVisibility(View.VISIBLE);
                result();
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });
    }

    private void result() {
        GraphRequest graphRequest = GraphRequest.newMeRequest(AccessToken.getCurrentAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(JSONObject object, GraphResponse response) {
                 Log.d("JSON",response.getJSONObject().toString());
                try {
//                    Email = object.getString("email");
                    Name = object.getString("name");
                    FirstName = object.getString("first_name");

                    profilePictureView.setProfileId(Profile.getCurrentProfile().getId());
                    email.setText(Email);
                    naMe.setText(Name);
                    first_name.setText(FirstName);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        Bundle prameters = new Bundle();
        prameters.putString("fields", "name,email,first_name");
        graphRequest.setParameters(prameters);
        graphRequest.executeAsync();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onStart() {
        LoginManager.getInstance().logOut();
        super.onStart();
    }
}
