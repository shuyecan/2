package com.xxdainiyou.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.xxdainiyou.MainActivity;
import com.xxdainiyou.R;
import com.xxdainiyou.been.UserBeen;

import org.litepal.LitePal;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.text_zhuce)
    TextView textZhuce;
    @BindView(R.id.login_phone)
    EditText loginPhone;
    @BindView(R.id.login_pass)
    EditText loginPass;
    @BindView(R.id.submit)
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        List<UserBeen> userBeenList = LitePal.findAll(UserBeen.class);
        inittooler();
    }

    private void inittooler() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("登录");
        setSupportActionBar(toolbar);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }

    @OnClick({R.id.text_zhuce,R.id.submit})
    public void onViewClicked(View view) {
        switch (view.getId()){
            case R.id.text_zhuce:
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
                break;
            case R.id.submit:
                String phone = loginPhone.getText().toString();
                String pwd = loginPass.getText().toString();
                if(!"".equals(phone)&&!"".equals(pwd)){
                    List<UserBeen> userBeenList = LitePal.where("phone = ? and password = ?",phone,pwd).find(UserBeen.class);
                    if(userBeenList.size()>0){
                        UserBeen userBeen = userBeenList.get(0);
                        userBeen.setIslogin("true");
                        userBeen.save();
                        Intent it = new Intent(LoginActivity.this,MainActivity.class);
                        startActivity(it);
                        finish();
                    }else {
                        Toast.makeText(this, "用户名或密码错误！", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(this, "用户名或密码不能为空！", Toast.LENGTH_SHORT).show();
                }

                break;
        }

    }

}
