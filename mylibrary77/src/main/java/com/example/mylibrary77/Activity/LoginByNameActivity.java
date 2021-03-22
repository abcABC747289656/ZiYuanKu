package com.example.mylibrary77.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mylibrary77.Activity.Util.HttpclientUtil;
import com.example.mylibrary77.R;


public class LoginByNameActivity extends AppCompatActivity {
    EditText et_loginByName,et_loginByName_password;
    ImageButton ib_loginByName;
    Button btn_loginByCode,btn_forget_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_by_name);
        et_loginByName=findViewById(R.id.et_loginByName);
        et_loginByName_password=findViewById(R.id.et_loginByName_password);
        btn_loginByCode=findViewById(R.id.btn_loginByCode);
        btn_forget_password=findViewById(R.id.btn_forget_password);
        ib_loginByName=findViewById(R.id.ib_loginByName);

        //验证码登录
        btn_loginByCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(LoginByNameActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        //忘记密码
        btn_forget_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(LoginByNameActivity.this, ChangePasswordActivity.class);
                startActivity(intent);
                finish();
            }
        });

        //登录
        ib_loginByName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username=et_loginByName.getText().toString().trim();
                String password=et_loginByName_password.getText().toString().trim();
                if(username.isEmpty()){
                    Toast.makeText(LoginByNameActivity.this,"账号不能为空",Toast.LENGTH_LONG).show();
                }
                else if(password.isEmpty()){
                    Toast.makeText(LoginByNameActivity.this,"密码不能为空",Toast.LENGTH_LONG).show();
                }
                else {
                    HttpclientUtil.loginByName(username,password);

                    //登录成功跳转页面

                }
            }
        });
    }
}
