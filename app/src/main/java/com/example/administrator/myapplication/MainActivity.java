package com.example.administrator.myapplication;

import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Pair;
import android.view.View;
import android.widget.ListView;
import com.example.administrator.myapplication.adapters.ProfileListViewAdapter;
import com.example.administrator.myapplication.common.Widgets;
import com.example.administrator.myapplication.oauth.OAuthClient;
import com.example.administrator.myapplication.oauth.OAuthConfig;
import com.example.administrator.myapplication.tools.Messenger;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class MainActivity extends AppCompatActivity {

    private static final String jsonStrID = "jsonStr";

    private String getCallbackUrl() {
        Resources resources = getResources();
        return resources.getString(R.string.FORGE_CALLBACK_SCHEME) + "://" + resources.getString(R.string.FORGE_CALLBACK_HOST);
    }

    private void getProfile(String authorizationCode) {
        final String code = authorizationCode;
        final Resources resources = getResources();

        final Handler errorHandler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message message) {
                Widgets.ShowToast(getApplication(), "Error: " + message.getData().getString(Messenger.msgId));
            }
        };

        final Handler UIHandler = new Handler(Looper.myLooper()) {
            @Override
            public void handleMessage(Message message) {
                try {
                    JSONObject object = new JSONObject(message.getData().getString(jsonStrID));
                    Iterator<String> it = object.keys();

                    ArrayList<Pair<String, String>> list = new ArrayList<>();

                    String key;
                    while (it.hasNext() && !(key = it.next()).isEmpty())
                        list.add(new Pair(key, object.get(key).toString()));
                    ListView profileListView = findViewById(R.id.profileListView);
                    profileListView.setVisibility(View.VISIBLE);
                    findViewById(R.id.progressBar).setVisibility(View.GONE);
                    profileListView.setAdapter(new ProfileListViewAdapter(MainActivity.this, list));
                } catch (Exception ex) {
                    Messenger.sendString(errorHandler, ex.toString());
                }
            }
        };

        new OAuthClient(new OAuthConfig(resources.getString(R.string.FORGE_CLIENT_ID), resources.getString(R.string.FORGE_CLIENT_SECRET), this.getCallbackUrl(), resources.getString(R.string.FORGE_TOKEN_URL), authorizationCode)).getResponseWithAuthentication(resources.getString(R.string.FORGE_PROFILE_URL), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Messenger.sendString(errorHandler, e.toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Message msg = new Message();
                Bundle bundle = new Bundle();
                bundle.putString(jsonStrID, response.body().string());
                msg.setData(bundle);
                UIHandler.sendMessage(msg);
            }
        });
    }


    @Override
    protected void onStart() {

        super.onStart();
        Intent intent = getIntent();

        Uri data = intent == null ? null : intent.getData();
        String authorizationCode = data == null ? null : data.getQueryParameter("code");

        if (authorizationCode != null && !authorizationCode.isEmpty()) {
            Intent newIntent = new Intent(this, this.getClass());
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                    | Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS
                    | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
            newIntent.putExtra("authorizationCode", authorizationCode);
            this.finish();

            startActivity(newIntent);

        } else {
            authorizationCode = intent.getStringExtra("authorizationCode");
            if (authorizationCode != null) {

                findViewById(R.id.progressBar).setVisibility(View.VISIBLE);
                findViewById(R.id.loginButton).setVisibility(View.GONE);

                getProfile(authorizationCode);

            } else {

                findViewById(R.id.loginButton).setVisibility(View.VISIBLE);
                findViewById(R.id.progressBar).setVisibility(View.GONE);
                findViewById(R.id.profileListView).setVisibility(View.GONE);
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onLoginClick(View v) {
        Intent i = new Intent(Intent.ACTION_VIEW);
        Resources resources = getResources();
        i.setData(Uri.parse(getResources().getString(R.string.FORGE_AUTHORIZATION_URL) + "?response_type=code&redirect_uri=" + this.getCallbackUrl() + "&scope=" + resources.getString(R.string.FORGE_SCOPE) + "&client_id=" + resources.getString(R.string.FORGE_CLIENT_ID)));
        startActivity(i);
    }
}
