package com.example.mylibrary77.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mylibrary77.Activity.Util.Checkaccount;
import com.example.mylibrary77.Activity.Util.HttpclientUtil;
import com.example.mylibrary77.R;


public class MainActivity extends AppCompatActivity {
    Button btn_come_register,btn_login_getcode,btn_loginByName;
    ImageButton ib_loginByPhone;
    EditText et_login_phone,et_login_code;
    TimeCount timeCount = new TimeCount(60000, 1000);



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et_login_phone=findViewById(R.id.et_login_phone);
        et_login_code=findViewById(R.id.et_login_code);
        btn_come_register=findViewById(R.id.btn_come_register);
        btn_loginByName=findViewById(R.id.btn_loginByName);
        btn_login_getcode=findViewById(R.id.btn_login_getCode);
        ib_loginByPhone=findViewById(R.id.ib_loginByPhone);

        //回传注册手机号码
        Intent intent=getIntent();
        String msg=intent.getStringExtra("phone");
        Log.d("1111",""+msg);
        et_login_phone.setText(msg);

        //注册
        btn_come_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        //账号密码登录
        btn_loginByName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this, LoginByNameActivity.class);
                startActivity(intent);
                finish();
            }
        });
        //获取验证码
        btn_login_getcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phone=et_login_phone.getText().toString().trim();
                if(phone.isEmpty()){
                    Toast.makeText(MainActivity.this,"手机号不能为空",Toast.LENGTH_SHORT).show();
                }
                else {
                    if (Checkaccount.isPhone(phone)) {
                        timeCount.start();
                        HttpclientUtil.loginCodeByPhone(phone);
                    } else {
                        Toast.makeText(MainActivity.this, "手机号错误", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        //登录
        ib_loginByPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String login_phone=et_login_phone.getText().toString().trim();
                String login_code=et_login_code.getText().toString().trim();
                if(login_phone.isEmpty()){
                    Toast.makeText(MainActivity.this,"手机号不能为空",Toast.LENGTH_SHORT).show();
                }
                else if(login_code.isEmpty()){
                    Toast.makeText(MainActivity.this,"验证码不能为空",Toast.LENGTH_SHORT).show();
                }
                else {
                    //登录
                        HttpclientUtil.loginByPhone(login_phone, login_code);

                        //登录成功跳转界面

                }
            }
        });
    }

    //倒计时
    class TimeCount extends CountDownTimer {
        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }
        @Override
        public void onTick(long l) {
            btn_login_getcode.setClickable(false);
            btn_login_getcode.setText(l/1000 + "S");
        }
        @Override
        public void onFinish() {
            btn_login_getcode.setClickable(true);
            btn_login_getcode.setText("重新发送");
        }
    }

}
