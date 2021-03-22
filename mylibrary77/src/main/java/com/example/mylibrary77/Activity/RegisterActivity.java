package com.example.mylibrary77.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mylibrary77.Activity.Util.Checkaccount;
import com.example.mylibrary77.Activity.Util.HttpclientUtil;
import com.example.mylibrary77.R;


public class RegisterActivity extends AppCompatActivity {

    EditText et_register_account,et_register_code,et_register_password;
    Button btn_register_getCode,btn_register;
    final TimeCount timeCount = new TimeCount(60000, 1000);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        et_register_account=findViewById(R.id.et_register_account);
        et_register_code=findViewById(R.id.et_register_code);
        et_register_password=findViewById(R.id.et_register_password);
        btn_register=findViewById(R.id.btn_register);
        btn_register_getCode=findViewById(R.id.btn_register_getCode);
        btn_register_getCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String account=et_register_account.getText().toString().trim();
                if(account.isEmpty()){
                    Toast.makeText(RegisterActivity.this,"账号不能为空",Toast.LENGTH_SHORT).show();
                }else {
                    if (Checkaccount.isEmail(account)) {
                        timeCount.start();
                        HttpclientUtil.registerCodeByEmail(account);
                    } else if (Checkaccount.isPhone(account)) {
                        timeCount.start();
                        HttpclientUtil.registerCodeByPhone(account);
                    } else {
                        Toast.makeText(RegisterActivity.this, "手机号或者邮箱号错误", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String register_account=et_register_account.getText().toString().trim();
                String register_code=et_register_code.getText().toString().trim();
                String register_password=et_register_password.getText().toString().trim();
                if(register_account.isEmpty()){
                    Toast.makeText(RegisterActivity.this,"账号不能为空",Toast.LENGTH_SHORT).show();
                }
                else if(register_code.isEmpty()){
                    Toast.makeText(RegisterActivity.this,"验证码不能为空",Toast.LENGTH_SHORT).show();
                }
                else if(register_password.isEmpty()){
                    Toast.makeText(RegisterActivity.this,"密码不能为空",Toast.LENGTH_SHORT).show();
                }
                else {
                    if(Checkaccount.isEmail(register_account)){
                        //邮箱号注册
                        HttpclientUtil.registerByEmail(register_account, register_code, register_password);
                    }
                    else if(Checkaccount.isPhone(register_account)){
                        //手机号注册
                        HttpclientUtil.registerByPhone(register_account, register_code, register_password);

                        //回传注册手机号码
                        Intent intent = new Intent(RegisterActivity.this,MainActivity.class);
                        intent.putExtra("phone",register_account);
                        startActivity(intent);
                        finish();

                    }

                }
            }
        });
    }
    class TimeCount extends CountDownTimer {
        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }
        @Override
        public void onTick(long l) {
            btn_register_getCode.setClickable(false);
            btn_register_getCode.setText(l/1000 + "S");
        }
        @Override
        public void onFinish() {
            btn_register_getCode.setClickable(true);
            btn_register_getCode.setText("重新发送");
        }
    }

    }


