package com.example.mylibrary77.Activity.Util;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class HttpclientUtil {

    private static Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 10:
                    JSONObject response = (JSONObject) msg.obj;
                    try {
                        int code=(int)response.get("code");
                        Log.d("http", "code: " + code);
                        if (code==20000) {
                            switch ((int) response.get("action")) {
                                case 0:
                                    Log.d("http", "注册成功" );
                                    Toast.makeText(MyApplication.getContext(),
                                            "注册成功",
                                            Toast.LENGTH_LONG).show();
                                    break;
                                case 1:
                                    Log.d("http", "登录成功" );
                                    Toast.makeText(MyApplication.getContext(),
                                            "登录成功",
                                            Toast.LENGTH_LONG).show();
                                    break;
                                case 2:
                                    Log.d("http", "密码重置成功" );
                                    Toast.makeText(MyApplication.getContext(), "密码重置成功", Toast.LENGTH_LONG).show();
                                    break;
                                case 3:
                                    Log.d("http", "验证码发送成功" );
                                    Toast.makeText(MyApplication.getContext(), "验证码获取成功", Toast.LENGTH_LONG).show();
                                    break;
                            }
                        } else {
                            Log.d("http", ""+response.get("message") );
                            Toast.makeText(MyApplication.getContext(), ""+response.get("message"), Toast.LENGTH_LONG).show();
                            break;
                        }
                        break;
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                default:
                    break;
            }
        }
    };


    //手机号码注册
    public static void registerByPhone(final String phone, final String code, final String password) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String uri = "https://everysing.tarblog.cn/v1/user/register";
                JSONObject obj = new JSONObject();
                try {
                    obj.put("phone", phone);
                    obj.put("code", code);
                    if (!password.isEmpty() && !password.equals("")) {
                        obj.put("password", password);
                    }
                    Log.d("http","req: " + obj.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                //json格式
                MediaType type = MediaType.parse("application/json;charset=utf-8");
                //创建表单
                RequestBody requestBody = RequestBody.create(type, "" + obj.toString());
                try {
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            // 指定访问的服务器地址
                            .url(uri)
                            .post(requestBody)
                            .build();
                    Response response = client.newCall(request).execute();
                    if(response.isSuccessful()) {
                        String responseData = response.body().string();
                        JSONObject jsonRecv=new JSONObject(responseData);
                        jsonRecv.put("action", 0);
                        Log.d("http","recv: " + jsonRecv.toString());
                        Message message = new Message();
                        message.what = 10;
                        message.obj = jsonRecv;
                        handler.sendMessage(message);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }



    //邮箱注册
    public static void registerByEmail(final String email, final String code, final String password) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String uri = "https://everysing.tarblog.cn/v1/user/register";
                JSONObject obj = new JSONObject();
                try {
                    obj.put("email", email);
                    obj.put("code", code);
                    if (!password.isEmpty() && !password.equals("")) {
                        obj.put("password", password);
                    }
                    Log.d("http","req: " + obj.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                //json格式
                MediaType type = MediaType.parse("application/json;charset=utf-8");
                //创建表单
                RequestBody requestBody = RequestBody.create(type, "" + obj.toString());
                try {
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            // 指定访问的服务器地址
                            .url(uri)
                            .post(requestBody)
                            .build();
                    Response response = client.newCall(request).execute();
                    if(response.isSuccessful()) {
                        String responseData = response.body().string();
                        JSONObject jsonRecv=new JSONObject(responseData);
                        jsonRecv.put("action", 0);
                        Log.d("http","recv: " + jsonRecv.toString());
                        Message message = new Message();
                        message.what = 10;
                        message.obj = jsonRecv;
                        handler.sendMessage(message);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }

    //手机注册验证码
    public static void registerCodeByPhone(final String phone) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String uri = "https://everysing.tarblog.cn/v1/user/register/phone";
                JSONObject obj = new JSONObject();
                try {
                    obj.put("phone", phone);
                    Log.d("http","req: " + obj.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                //json格式
                MediaType type = MediaType.parse("application/json;charset=utf-8");
                //创建表单
                RequestBody requestBody = RequestBody.create(type, "" + obj.toString());
                try {
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            // 指定访问的服务器地址
                            .url(uri)
                            .post(requestBody)
                            .build();
                    Response response = client.newCall(request).execute();
                    if(response.isSuccessful()) {
                        String responseData = response.body().string();
                        JSONObject jsonRecv=new JSONObject(responseData);
                        jsonRecv.put("action", 3);
                        Log.d("http","recv: " + jsonRecv.toString());
                        Message message = new Message();
                        message.what = 10;
                        message.obj = jsonRecv;
                        handler.sendMessage(message);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    //邮箱注册验证码
    public static void registerCodeByEmail(final String email) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String uri = "https://everysing.tarblog.cn/v1/user/register/email";
                JSONObject obj = new JSONObject();
                try {
                    obj.put("email", email);
                    Log.d("http","req: " + obj.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                //json格式
                MediaType type = MediaType.parse("application/json;charset=utf-8");
                //创建表单
                RequestBody requestBody = RequestBody.create(type, "" + obj.toString());
                try {
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            // 指定访问的服务器地址
                            .url(uri)
                            .post(requestBody)
                            .build();
                    Response response = client.newCall(request).execute();
                    if(response.isSuccessful()) {
                        String responseData = response.body().string();
                        JSONObject jsonRecv=new JSONObject(responseData);
                        jsonRecv.put("action", 3);
                        Log.d("http","recv: " + jsonRecv.toString());
                        Message message = new Message();
                        message.what = 10;
                        message.obj = jsonRecv;
                        handler.sendMessage(message);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    //手机号码登录
    public static void loginByPhone(final String phone, final String code) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String uri = "https://everysing.tarblog.cn/v1/user/login/phone";
                JSONObject obj = new JSONObject();
                try {
                    obj.put("phone", phone);
                    obj.put("code", code);
                    Log.d("http","req: " + obj.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                //json格式
                MediaType type = MediaType.parse("application/json;charset=utf-8");
                //创建表单
                RequestBody requestBody = RequestBody.create(type, "" + obj.toString());
                try {
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            // 指定访问的服务器地址
                            .url(uri)
                            .post(requestBody)
                            .build();
                    Response response = client.newCall(request).execute();
                    if(response.isSuccessful()) {
                        String responseData = response.body().string();
                        JSONObject jsonRecv=new JSONObject(responseData);
                        jsonRecv.put("action", 1);
                        Log.d("http","recv: " + jsonRecv.toString());
                        Message message = new Message();
                        message.what = 10;
                        message.obj = jsonRecv;
                        handler.sendMessage(message);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    //账号密码登录
    public static void loginByName(final String username, final String password) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String uri = "https://everysing.tarblog.cn/v1/user/login";
                JSONObject obj = new JSONObject();
                try {
                    obj.put("username", username);
                    obj.put("password", password);
                    Log.d("http","req: " + obj.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                //json格式
                MediaType type = MediaType.parse("application/json;charset=utf-8");
                //创建表单
                RequestBody requestBody = RequestBody.create(type, "" + obj.toString());
                try {
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            // 指定访问的服务器地址
                            .url(uri)
                            .post(requestBody)
                            .build();
                    Response response = client.newCall(request).execute();
                    if(response.isSuccessful()) {
                        String responseData = response.body().string();
                        JSONObject jsonRecv=new JSONObject(responseData);
                        jsonRecv.put("action", 1);
                        Log.d("http","recv: " + jsonRecv.toString());
                        Message message = new Message();
                        message.what = 10;
                        message.obj = jsonRecv;
                        handler.sendMessage(message);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    //手机登录验证码
    public static void loginCodeByPhone(final String phone) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String uri = "https://everysing.tarblog.cn/v1/user/login/phone/code";
                JSONObject obj = new JSONObject();
                try {
                    obj.put("phone", phone);
                    Log.d("http","req: " + obj.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                //json格式
                MediaType type = MediaType.parse("application/json;charset=utf-8");
                //创建表单
                RequestBody requestBody = RequestBody.create(type, "" + obj.toString());
                try {
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            // 指定访问的服务器地址
                            .url(uri)
                            .post(requestBody)
                            .build();
                    Response response = client.newCall(request).execute();
                    if(response.isSuccessful()) {
                        String responseData = response.body().string();
                        JSONObject jsonRecv=new JSONObject(responseData);
                        jsonRecv.put("action", 3);
                        Log.d("http","recv: " + jsonRecv.toString());
                        Message message = new Message();
                        message.what = 10;
                        message.obj = jsonRecv;
                        handler.sendMessage(message);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    //修改密码
    public static void ChangePassword(final String old_password, final String password,final String password_confirm) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String uri = "https://everysing.tarblog.cn/v1/user/password";
                JSONObject obj = new JSONObject();
                try {
                    obj.put("old_password", old_password);
                    obj.put("password", password);
                    obj.put("password_confirm", password_confirm);
                    Log.d("http","req: " + obj.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                //json格式
                MediaType type = MediaType.parse("application/json;charset=utf-8");
                //创建表单
                RequestBody requestBody = RequestBody.create(type, "" + obj.toString());
                try {
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            // 指定访问的服务器地址
                            .url(uri)
                            .post(requestBody)
                            .build();
                    Response response = client.newCall(request).execute();
                    if(response.isSuccessful()) {
                        String responseData = response.body().string();
                        JSONObject jsonRecv=new JSONObject(responseData);
                        jsonRecv.put("action", 2);
                        Log.d("http","recv: " + jsonRecv.toString());
                        Message message = new Message();
                        message.what = 10;
                        message.obj = jsonRecv;
                        handler.sendMessage(message);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

}
