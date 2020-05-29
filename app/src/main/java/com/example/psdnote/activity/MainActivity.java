package com.example.psdnote.activity;

import androidx.appcompat.app.AppCompatActivity;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.psdnote.R;
import com.example.psdnote.api.UserDataApiManger;
import com.example.psdnote.model.LoginData;
import com.example.psdnote.model.LoginUserUp;
import com.example.psdnote.util.MD5Utils;

public class MainActivity extends AppCompatActivity {

    private final String TAG = MainActivity.class.getSimpleName();
    private Toast toast = null;
    private LoginUserUp loginUserUp = new LoginUserUp();
    private EditText username;
    private EditText password;
    private Button loginButton;
    private ProgressBar progressBar;
    private Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView(){
        username = (EditText) findViewById(R.id.login_edit_account);
        password = (EditText) findViewById(R.id.login_edit_password);
        loginButton = (Button) findViewById(R.id.login_btn_login);
        progressBar = (ProgressBar) findViewById(R.id.login_progressBar);
        progressBar.setVisibility(View.GONE);
        username.setOnFocusChangeListener(onFocusChangeListener);
        password.setOnFocusChangeListener(onFocusChangeListener);
    }

    public void login(View view){
        if (ifUsernamePasswordValue()){
            progressBar.setVisibility(View.VISIBLE);
            loginUserUp.setEmail(username.getText().toString().trim());
            loginUserUp.setPassword(MD5Utils.encode(password.getText().toString().trim()));
            UserDataApiManger.getUserData(loginUserUp)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<LoginData>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(LoginData loginData) {
                            if (loginData.getMsg().equals("登录成功")){
                                intent =new Intent(MainActivity.this,NoteActivity.class);
                                startActivity(intent);
                                finish();
                            }else{
                                //账号密码错误
                                showToast(getString(R.string.login_failed));
                            }
                        }

                        @Override
                        public void onError(Throwable e) {
                            e.printStackTrace();
                            progressBar.setVisibility(View.GONE);
                            intent = new Intent(MainActivity.this, ErrorActivity.class);
                            startActivity(intent);
                        }

                        @Override
                        public void onComplete() {
                            progressBar.setVisibility(View.GONE);
                        }
                    });
        }
    }

    public boolean ifUsernamePasswordValue(){
        if (username.getText().toString().trim().equals("")){
            showToast(getString(R.string.username_empty));
            return false;
        }
        else if (password.getText().toString().trim().equals("")){
            showToast(getString(R.string.password_empty));
            return false;
        }
        return true;
    }

    private void showToast(String s) {
        if (toast == null) {
            toast = Toast.makeText(this, s, Toast.LENGTH_SHORT);
            toast.show();
        } else {
            toast.setText(s);
            toast.show();
        }
    }

    private EditText.OnFocusChangeListener onFocusChangeListener = new View.OnFocusChangeListener() {
        @Override
        public void onFocusChange(View v, boolean hasFocus) {
            EditText editText = (EditText) v;
            String hint;
            if(hasFocus){
                hint = editText.getHint().toString();
                editText.setTag(hint);
                editText.setHint("");
            }else {
                hint = editText.getTag().toString();
                editText.setHint(hint);
            }
        }
    };
}
