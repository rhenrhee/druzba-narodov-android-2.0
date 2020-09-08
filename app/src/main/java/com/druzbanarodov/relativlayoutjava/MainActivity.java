package com.druzbanarodov.relativlayoutjava;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.text.InputType;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity
{

    private FirebaseAuth mAuth;
    CallbackManager callbackManager;
    private static final String TAG = "EmailPassword";
    private static final int RC_SIGN_IN = 5;

    //variables
    //LoginButton loginButton;
    SignInButton signInButton;
    Button show, show2, getStarted, Continue, loginButton;
    ImageButton googleSignInButton, facebook_signIn_btn, twitter_signIn_button;
    EditText edit_password, edit_name, edit_email, edit_password2;
    TextView toast, name_display, forget;
    private final String Default = "N/A";
    String[] Gender = {"Муж", "Жен"};
    String gender;
    final static String TARGET_BASE_PATH = "/sdcard/appname/voices/";
    Spinner spinner;
    ImageView icon_user;
    private ProgressDialog progressBar;//Create a circular progressBar Dialog
    //private android.content.Context ActivityContext;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        mAuth = FirebaseAuth.getInstance();

        final SharedPreferences sharedPreferences = getSharedPreferences("Content_main", Context.MODE_PRIVATE);
        String name_file = sharedPreferences.getString("name", Default);
        String pass_file = sharedPreferences.getString("password", Default);
        String email_file = sharedPreferences.getString("email", Default);
        String gender_file = sharedPreferences.getString("gender", Default);

        progressBar = new ProgressDialog(this);
        progressBar.setCancelable(false);
        progressBar.setMessage("Проверка профиля...");
        progressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressBar.setProgress(0);
        progressBar.setMax(100);

        callbackManager = CallbackManager.Factory.create();


        if (name_file.equals(Default) || pass_file.equals(Default) || email_file.equals(Default) || gender_file.equals(Default))
        {
            // Пользователь не существует в телефоне

            setContentView(R.layout.activity_main);

            show = (Button) findViewById(R.id.show);
            edit_password = (EditText) findViewById(R.id.password);
            edit_email = (EditText) findViewById(R.id.email);
            edit_name = (EditText) findViewById(R.id.name);
            show.setOnClickListener(new showOrHidePassword());
            toast = (TextView) findViewById(R.id.toast_help);
            spinner = (Spinner) findViewById(R.id.spinner);
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.spinner, Gender);
            adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
            spinner.setAdapter(adapter);
            spinner.setOnItemSelectedListener(new spinner());
            getStarted = (Button) findViewById(R.id.getStarted);
            googleSignInButton = (ImageButton) findViewById(R.id.google_sign_in_button);
            //facebook_signIn_btn = (ImageButton) findViewById(R.id.facebook_sign_in_button);
            //twitter_signIn_button = (ImageButton) findViewById(R.id.twitter_sign_in_button);


            //Google auth button
            //signInButton = (SignInButton) findViewById(R.id.sign_in_button);
            //Facebook auth
            loginButton = (Button) findViewById(R.id.facebook_sign);
            //loginButton.setReadPermissions("email");

            getStarted.setOnClickListener(v ->
            {
                String save_name = edit_name.getText().toString();
                //String save_name = "D1";
                String save_email = edit_email.getText().toString();
                //String save_email = "d1@rambler.ru";
                String save_password = edit_password.getText().toString();
                //String save_password = "19Mama#";

                if (save_name.equals("") || save_email.equals("") || save_password.equals(""))
                {
                    try
                    {
                        Toast.makeText(MainActivity.this, "Пожалуйста, укажите детали", Toast.LENGTH_SHORT).show();
                    }
                    catch (Exception ex)
                    { }
                }
                else
                {
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("name", save_name);
                    editor.putString("password", save_password);
                    editor.putString("email", save_email);
                    editor.putString("gender", gender);

                    // Приложение было удалено и снова установлено. Попытка регистрации под тем же e-mail
                    progressBar.cancel();
                    asyncSignInOrCreateUser(save_email, save_password, editor);
                }
            });
        }
        else
        {
            progressBar.cancel();
            // Пользователь есть в телефоне
            asyncSignInOrCreateUser(email_file, pass_file, null);
        }



        /*loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Клик по кнопке",
                        Toast.LENGTH_SHORT).show();
                LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        handleFacebookToken(loginResult.getAccessToken());
                    }

                    @Override
                    public void onCancel() {
                        Toast.makeText(MainActivity.this, "Отмена авторизации",
                                Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(FacebookException error) {
                        Toast.makeText(MainActivity.this, "Ошибка авторизации",
                                Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });*/

       /* googleSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();
            }
        });*/
        /*facebook_signIn_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CallbackManager callbackManager = CallbackManager.Factory.create();
                //facebookSign(view);
            }
        });*/
    }

    public void fbLogin(View view)
    {
        // Before Edit:
        // LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("user_photos", "email", "public_profile", "user_posts" , "AccessToken"));

        LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("user_photos", "email"));
       // LoginManager.getInstance().logInWithPublishPermissions(this, Arrays.asList("publish_actions"));
        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>()
                {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        handleFacebookToken(loginResult.getAccessToken());
                    }

                    @Override
                    public void onCancel() {
                        Toast.makeText(MainActivity.this, "Отмена авторизации",
                                Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(FacebookException error) {
                        Toast.makeText(MainActivity.this, "Ошибка авторизации",
                                Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public void onClick(View v) {
        if (v == loginButton) {
            LoginManager.getInstance().logInWithReadPermissions(
                    this,
                    Arrays.asList("user_photos", "email")
            );
        }
    }

    public void googleSignIn(View v) {
                signIn();
    }

    //Facebook auth event listener
    public void facebookSign(View v){
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                handleFacebookToken(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {
                Toast.makeText(MainActivity.this, "Отмена авторизации",
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException error) {
                Toast.makeText(MainActivity.this, "Ошибка авторизации",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void handleFacebookToken(AccessToken accessToken) {
        AuthCredential credential = FacebookAuthProvider.getCredential(accessToken.getToken());
        mAuth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    updateUIwithFacebook(mAuth.getCurrentUser());
                }
                else {
                    Toast.makeText(MainActivity.this, "Невозможно зарегистрироваться",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void signIn() {
        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        // Build a GoogleSignInClient with the options specified by gso.
        GoogleSignInClient mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        System.out.println("Google Клиент: " + gso);
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    private void asyncSignInOrCreateUser(final String email, final String password, final SharedPreferences.Editor editor)
    {
        //progressBar.show();
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(MainActivity.this, taskResult1 ->
                {
                    try
                    {
                        taskResult1.getResult();

                        if (taskResult1.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            // Аккаунт уже существует на сервере
                            updateUI(mAuth.getCurrentUser());
                            Intent intent = new Intent(MainActivity.this, Navigation_Nome_Menu.class);
                            startActivity(intent);
                            MainActivity.this.finish();
                            progressBar.cancel();
                            Toast.makeText(MainActivity.this, "Успешная авторизация", Toast.LENGTH_SHORT).show();
                            if (editor != null) editor.commit();
                        } else {
                            // If sign in fails, display a message to the user.
                            progressBar.cancel();
                            Log.w(TAG, "createUserWithEmail:failure", taskResult1.getException());
                            Toast.makeText(MainActivity.this, "Ошибка авторизации, неверный логин или пароль",
                                    Toast.LENGTH_SHORT).show();
                            //updateUI(null);
                        }

                    }
                    catch (Exception ex1)
                    {
                        switch (ex1.getMessage())
                        {
                            case "com.google.firebase.auth.FirebaseAuthInvalidUserException: There is no user record corresponding to this identifier. The user may have been deleted.":
                                // Регистрация на сервере
                                mAuth.createUserWithEmailAndPassword(email, password)
                                        .addOnCompleteListener(MainActivity.this, taskResult2 ->
                                        {
                                            try
                                            {
                                                updateUI(mAuth.getCurrentUser());
                                                Intent intent = new Intent(MainActivity.this, Navigation_Nome_Menu.class);
                                                startActivity(intent);
                                                MainActivity.this.finish();
                                                progressBar.cancel();
                                                Toast.makeText(MainActivity.this, "Успешная регистрация", Toast.LENGTH_SHORT).show();
                                                if (editor != null) editor.commit();
                                            }
                                            catch (Exception ex2)
                                            {
                                                progressBar.cancel();
                                                Toast.makeText(MainActivity.this, "Проблемы с сетью!", Toast.LENGTH_SHORT).show();
                                            }
                                        });
                                break;
                            case "com.google.firebase.FirebaseNetworkException: A network error (such as timeout, interrupted connection or unreachable host) has occurred.":
                                Toast.makeText(MainActivity.this, "Проблемы с сетью!", Toast.LENGTH_SHORT).show();
                                progressBar.cancel();
                                break;
                        }
                    }
                });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            System.out.println("Google account " + account);
            // Signed in successfully, show authenticated UI.
            updateUIwithGoogle(account);
            //if (editor != null) editor.commit();
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Toast.makeText(MainActivity.this, "Ошибка авторизации", Toast.LENGTH_SHORT).show();
            Log.w(TAG, "signInResult:failed code=" + e.getStatusCode());
            //updateUIwithGoogle(null);
        }
    }

    //Used to show the help by triggering a toast
    public void showHelp(View view) {

        Toast toast_help = new Toast(getApplicationContext());
        toast_help.setGravity(Gravity.CENTER, 0, 0);
        toast_help.setDuration(Toast.LENGTH_LONG);
        LayoutInflater inflater = getLayoutInflater();
        View appear = inflater.inflate(R.layout.toast_help, (ViewGroup) findViewById(R.id.linear));
        toast_help.setView(appear);
        toast_help.show();

    }


    //Used to add some time so that user cannot directly press and exity out of the activity
    boolean doubleBackToExitPressedOnce = false;

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 4000);

    }


    //class to show or hide password on button click in main activity
    class showOrHidePassword implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            if (show.getText().toString() == "Показать") {
                edit_password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                show.setText("Скрыть");

            } else {

                edit_password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                show.setText("Показать");
            }
        }
    }

    //class to show or hide password on button click in main activity second
    class showOrHidePassword2 implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            if (show2.getText().toString() == "Показать") {
                edit_password2.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                show2.setText("Скрыть");

            } else {

                edit_password2.setTransformationMethod(PasswordTransformationMethod.getInstance());
                show2.setText("Показать");
            }
        }
    }


    //Spinner class to select spinner and also add gender
    class spinner implements AdapterView.OnItemSelectedListener {


        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            gender = parent.getItemAtPosition(position).toString();
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {
            //When nothing is selected
            Toast.makeText(getApplicationContext(), "Please Enter the gender", Toast.LENGTH_SHORT).show();
        }
    }

    public void showDialog(View view) {
        int[][] states = new int[][]{
                new int[]{android.R.attr.state_pressed}, // pressed
                new int[]{android.R.attr.state_enabled}
        };

        int[] colors = new int[]{
                Color.parseColor("#9B1D20"), // red
                Color.parseColor("#AAFAC8") //light green

        };

        ColorStateList list = new ColorStateList(states, colors);
        forget.setTextColor(list);

        AlertDialog.Builder alertDialog;//Create a dialog object
        alertDialog = new AlertDialog.Builder(MainActivity.this);
        //EditText to show up in the AlertDialog so that the user can enter the email address
        final EditText editTextDialog = new EditText(MainActivity.this);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        editTextDialog.setLayoutParams(layoutParams);
        editTextDialog.setHint("Email");
        //Adding EditText to Dialog Box
        alertDialog.setView(editTextDialog);
        alertDialog.setTitle("Введите Email");
        final SharedPreferences sharedPreferences = getSharedPreferences("Content_main", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPreferences.edit();
        alertDialog.setPositiveButton("Отправить", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String email_dialog = editTextDialog.getText().toString();
                if (sharedPreferences.getString("email", Default).equals(email_dialog)) {
                    //We are setting the values of Prefrences in sharedPrefrences to Default
                    editor.putString("name", Default);
                    editor.putString("email", Default);
                    editor.putString("password", Default);
                    editor.putString("gender", Default);
                    editor.commit();

                    //This intent will call the package manager and restart the current activity
                    Intent i = getBaseContext().getPackageManager().getLaunchIntentForPackage(getBaseContext().getPackageName());
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(i);
                } else {
                    Toast.makeText(MainActivity.this, "Введите действительный адрес электронной почты", Toast.LENGTH_SHORT).show();
                }
            }

        });
        alertDialog.setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //When the Disagree button is pressed
            }
        });
        //Showing up the alert dialog box
        alertDialog.show();
    }


    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    private void updateUI(FirebaseUser currentUser) {


    }

    private void updateUIwithFacebook(FirebaseUser currentUser) {
        final SharedPreferences sharedPreferences = getSharedPreferences("Content_main", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPreferences.edit();
        System.out.println("Email пользователя: " + currentUser.getEmail());
        editor.putString("name", currentUser.getDisplayName());
        editor.putString("email", currentUser.getEmail());
        //editor.putString("email", account.getEmail());
        editor.commit();
        Intent intent = new Intent(MainActivity.this, Navigation_Nome_Menu.class);
        startActivity(intent);
        MainActivity.this.finish();
        progressBar.cancel();
        Toast.makeText(MainActivity.this, "Успешная регистрация", Toast.LENGTH_SHORT).show();
    }

    private void updateUIwithGoogle(GoogleSignInAccount account) {
        final SharedPreferences sharedPreferences = getSharedPreferences("Content_main", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPreferences.edit();
        System.out.println("Email пользователя: " + account.getEmail());
        editor.putString("name", account.getDisplayName());
        editor.putString("email", account.getEmail());
        //editor.putString("email", account.getEmail());
        editor.commit();
        Intent intent = new Intent(MainActivity.this, Navigation_Nome_Menu.class);
        startActivity(intent);
        MainActivity.this.finish();
        progressBar.cancel();
        Toast.makeText(MainActivity.this, "Успешная регистрация", Toast.LENGTH_SHORT).show();
    }


}
