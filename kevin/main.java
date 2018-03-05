package com.f.login.loginf;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {



    EditText editEmail, editPassword, editName;
    Button btnSignIn, btnRegister;

    String URL= "https://web.njit.edu/~kl297/web/index.php";

    JSONParser jsonParser=new JSONParser();

    int i=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView regis = (TextView) findViewById(R.id.register);
        editName=(EditText)findViewById(R.id.editName);
        editPassword=(EditText)findViewById(R.id.editPassword);

        btnSignIn=(Button)findViewById(R.id.btnSignIn);

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AttemptLogin attemptLogin= new AttemptLogin();
                attemptLogin.execute(editName.getText().toString(),editPassword.getText().toString());
            }
        });

        TextView register = (TextView) findViewById(R.id.register);
        TextView recoverpass = (TextView) findViewById(R.id.forgot_password);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(MainActivity.this, Register.class);
                startActivity(intent);
            }
        });

        recoverpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(MainActivity.this, Recovery.class);
                startActivity(intent);
            }
        });


    }

    private class AttemptLogin extends AsyncTask<String, String, JSONObject> {

        @Override

        protected void onPreExecute() {

            super.onPreExecute();

        }

        @Override

        protected JSONObject doInBackground(String... args) {


            String password = args[1];
            String name= args[0];
            name = name.replaceAll("[\\s\\-()]", "");

            ArrayList params = new ArrayList();
            params.add(new BasicNameValuePair("username", name));
            params.add(new BasicNameValuePair("password", password));


            JSONObject json = jsonParser.makeHttpRequest(URL, "POST", params);

            return json;

        }

        protected void onPostExecute(JSONObject result) {

            // dismiss the dialog once product deleted
            //Toast.makeText(getApplicationContext(),result,Toast.LENGTH_LONG).show();

            try {
                if (result != null) {
                    Toast.makeText(getApplicationContext(),result.getString("message"),Toast.LENGTH_LONG).show();
                    String outjson = result.getString("message");
                        if(outjson.contains("Successfully")){
                            Intent intent = new Intent(MainActivity.this, Landing.class);
                            startActivity(intent);
                        }
                } else {
                    Toast.makeText(getApplicationContext(), "Incorrect details", Toast.LENGTH_LONG).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }


        }

    }

}
