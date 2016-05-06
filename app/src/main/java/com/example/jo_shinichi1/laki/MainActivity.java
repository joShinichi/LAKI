package com.example.jo_shinichi1.laki;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class MainActivity extends AppCompatActivity {
    EditText etUsername;
    EditText etPassword;
    Button btnLogin, btnRegister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnRegister = (Button) findViewById(R.id.btnRegister);
    }
    public void buttonReg(View view){
        Intent intent = new Intent(this, Register.class);
        startActivity(intent);
    }

    public void buttonLogin(View view){
        String username = etUsername.getText().toString();
        String password = etPassword.getText().toString();
        String type = "login";

        new BackgroundWorkerLogin(this).execute(type,username,password);
    }

    private class BackgroundWorkerLogin extends AsyncTask<String, Void, String> {
        AlertDialog alertDialog;
        Context context;

        BackgroundWorkerLogin(Context ctx) {
            context = ctx;
        }

        @Override
        protected String doInBackground(String... params) {

            String type = params[0];

            String login_url = "http://192.168.88.5/laki_login1.php";
            String register_url = "http://192.168.88.5/laki_register1.php";
            if (type.equals("register")) {
                try {
                    String nama = params[1];
                    String username = params[2];
                    String password = params[3];
                    String email = params[4];
                    URL url = new URL(register_url);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("POST");
                    connection.setDoOutput(true);
                    connection.setDoInput(true);
                    OutputStream outputStream = connection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));

                    String post_data = URLEncoder.encode("nama", "UTF-8") + "=" + URLEncoder.encode(nama, "UTF-8") + "&"
                            + URLEncoder.encode("Username", "UTF-8") + "=" + URLEncoder.encode(username, "UTF-8") + "&"
                            + URLEncoder.encode("Password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8") + "&"
                            + URLEncoder.encode("Email", "UTF-8") + "=" + URLEncoder.encode(email, "UTF-8");
                    bufferedWriter.write(post_data);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    outputStream.close();

                    InputStream inputStream = connection.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                    String result = "";
                    String line = "";
                    while ((line = bufferedReader.readLine()) != null) {
                        result += line;
                    }
                    bufferedReader.close();
                    inputStream.close();
                    connection.disconnect();

                    return result;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (type.equals("login")) {
                try {
                    String userName = params[1];
                    String Password = params[2];
                    URL url = new URL(login_url);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("POST");
                    connection.setDoOutput(true);
                    connection.setDoInput(true);
                    OutputStream outputStream = connection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));

                    String post_data = URLEncoder.encode("username", "UTF-8") + "=" + URLEncoder.encode(userName, "UTF-8") + "&"
                            + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(Password, "UTF-8");
                    bufferedWriter.write(post_data);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    outputStream.close();

                    InputStream inputStream = connection.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                    String result1 = "";
                    String line = "";
                    StringBuffer buffer = new StringBuffer();
                    while ((line = bufferedReader.readLine()) != null) {
                        buffer.append(result1 += line);
                    }
                    bufferedReader.close();
                    inputStream.close();
                    connection.disconnect();

                    return result1;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result1) {
            if(result1.equals(result1)){
                Toast.makeText(context, "berhasil login", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context,Home.class);
                startActivity(intent);
            }
        }
    }
}