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

import org.litepal.LitePal;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UserInfoActivity extends AppCompatActivity {

    @BindView(R.id.reg_phone)
    EditText regPhone;
    @BindView(R.id.reg_pwd)
    EditText regPwd;
    @BindView(R.id.reg_name)
    EditText regName;
    @BindView(R.id.reg_year)
    EditText regYear;
    @BindView(R.id.reg_qq)
    EditText regQq;
    @BindView(R.id.submit)
    Button submit;
    UserBeen userBeen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        ButterKnife.bind(this);
        inittooler();
        List<UserBeen> userBeenList = LitePal.where("islogin = ?", "true").find(UserBeen.class);
        userBeen = userBeenList.get(0);
        regName.setText(userBeen.getUsername());
        regPhone.setText(userBeen.getPhone());
        regPwd.setText(userBeen.getPassword());
        regYear.setText(userBeen.getAge());
        regQq.setText(userBeen.getQq());
    }


    private void inittooler() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("信息修改");
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        assert ab != null;
        ab.setDisplayHomeAsUpEnabled(true);
    }

    @OnClick(R.id.submit)
    public void onViewClicked() {
        String phone = regPhone.getText().toString();
        String pwd = regPwd.getText().toString();
        String age = regYear.getText().toString();
        String name = regName.getText().toString();
        String QQ = regQq.getText().toString();
        if(!"".equals(phone)&&!"".equals(pwd)&&!"".equals(name)){
            userBeen.setPhone(phone);
            userBeen.setPassword(pwd);
            userBeen.setUsername(name);
            userBeen.setAge(age);
            userBeen.setQq(QQ);
            userBeen.updateAll("phone = ?",phone);
            Toast.makeText(this, "修改成功！", Toast.LENGTH_SHORT).show();
            setResult(200);
            finish();
        }else {
            Toast.makeText(this, "请完善信息", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                setResult(200);
                finish();
                break;
        }
        return true;
    }
}
