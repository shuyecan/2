package com.xxdainiyou.Activity;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.xxdainiyou.R;
import com.xxdainiyou.been.UserBeen;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends AppCompatActivity {

    @BindView(R.id.reg_phone)
    EditText regPhone;
    @BindView(R.id.reg_name)
    EditText regName;
    @BindView(R.id.reg_year)
    EditText regYear;
    @BindView(R.id.reg_qq)
    EditText regQq;
    @BindView(R.id.submit)
    Button submit;
    @BindView(R.id.reg_pwd)
    EditText regPwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        inittooler();
    }

    private void inittooler() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("注册");
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        assert ab != null;
        ab.setDisplayHomeAsUpEnabled(true);
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

    @OnClick(R.id.submit)
    public void onViewClicked() {
        String phone = regPhone.getText().toString();
        String pwd = regPwd.getText().toString();
        String age = regYear.getText().toString();
        String name = regName.getText().toString();
        String QQ = regQq.getText().toString();
        if(!"".equals(phone)&&!"".equals(pwd)&&!"".equals(name)){
            UserBeen userBeen = new UserBeen();
            userBeen.setPhone(phone);
            userBeen.setPassword(pwd);
            userBeen.setUsername(name);
            userBeen.setAge(age);
            userBeen.setQq(QQ);
            userBeen.save();
            Toast.makeText(this, "注册成功！", Toast.LENGTH_SHORT).show();
            finish();
        }else {
            Toast.makeText(this, "请完善信息", Toast.LENGTH_SHORT).show();
        }


    }
}
