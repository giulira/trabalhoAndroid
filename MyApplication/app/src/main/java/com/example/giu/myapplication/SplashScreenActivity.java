package com.example.giu.myapplication;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.giu.myapplication.dao.UsuarioDAO;
import com.example.giu.myapplication.model.Usuario;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class SplashScreenActivity extends AppCompatActivity {

    private final int SPLASH_DISPLAY_LENGTH = 3500;
    private String urlStr = "http://www.mocky.io/v2/58b9b1740f0000b614f09d2f";
    InputStream is = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        carregar();
    }

    private void carregar() {
        Animation anim = AnimationUtils.loadAnimation(this,
                R.anim.animacao_splash);
        anim.reset();

        ImageView iv = (ImageView) findViewById(R.id.idImagem);
        if (iv != null) {
            iv.clearAnimation();
            iv.startAnimation(anim);
        }

        new UsuarioUtils().execute(urlStr);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent intent = new Intent(SplashScreenActivity.this, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
                SplashScreenActivity.this.finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }

 private class UsuarioUtils extends AsyncTask<String, Integer, String>{

      @Override
      protected String doInBackground(String... params) {

          try {
              URL url = new URL(params[0]);
              HttpURLConnection connection = (HttpURLConnection) url.openConnection();
              connection.setReadTimeout(15000);
              connection.setConnectTimeout(10000);
              connection.setRequestMethod("GET");
              connection.setDoOutput(true);

              if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                  is = connection.getInputStream();
                  String resultado = converteStream(is);
                  connection.disconnect();
                  return resultado;
              }

          } catch (MalformedURLException e) {
              e.printStackTrace();
          } catch (IOException e) {
              e.printStackTrace();
          }
          return null;
      }

      public String converteStream(InputStream is){
          BufferedReader buffer = new BufferedReader((new InputStreamReader(is)));
          StringBuilder result = new StringBuilder();
          String ln;
          try {
              while ((ln = buffer.readLine()) != null) {
                  result.append(ln);
              }

          } catch (IOException e) {
              e.printStackTrace();
          }
          return result.toString();
      }

      @Override
      protected void onPostExecute(String s) {
          Usuario usuario = new Usuario();
          JSONObject json = null;
          Usuario u = new Usuario();
          UsuarioDAO dao = new UsuarioDAO(SplashScreenActivity.this);
          try {
              json = new JSONObject(s);
              usuario.setLogin(json.getString("usuario"));
              usuario.setSenha(json.getString("senha"));
              usuario.setId(0);

             u = dao.getBy(usuario.getLogin());
              if(u == null) {
                  dao.add(usuario);
              }

          } catch (JSONException e) {
              e.printStackTrace();
          }

      }
  }

}