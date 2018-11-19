package com.example.administrator.myapplication.oauth;

import okhttp3.*;
import org.json.JSONObject;

import java.io.IOException;

public class OAuthClient {

    private OkHttpClient okHttpClient;
    private OAuthConfig oAuthConfig = null;
    private String token = null;

    public OAuthClient(OAuthConfig oAuthConfig) {
        this.okHttpClient = new OkHttpClient();
        this.oAuthConfig = oAuthConfig;
    }

    public OAuthClient(OAuthConfig oAuthConfig, OkHttpClient okHttpClient) {
        this.okHttpClient = okHttpClient;

    }

    public Boolean refreshToken() {        //TODO: Implement token refresh
        throw new java.lang.UnsupportedOperationException("Not implemented yet.");
    }

    private Call buildGetRequest(String url) {

        Request.Builder builder = new Request.Builder();
        builder = builder.url(url);
        builder.addHeader("Authorization", "Bearer " + token);
        builder.get();
        Request request = builder.build();
        return okHttpClient.newCall(request);
    }

    public void getResponseWithAuthentication(String url, Callback cb) {
        this.getResponseWithAuthentication(url, cb, this.oAuthConfig.getAuthorization_Code());
    }

    public void getResponseWithAuthentication(String aurl, Callback acb, String authorizationCode) {
        final Callback cb = acb;
        final String url = aurl;
        if (this.token == null) //TODO: Implement token refresh
            this.authenticate(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    cb.onFailure(call, e);
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    buildGetRequest(url).enqueue(cb);
                }
            }, authorizationCode);
        else buildGetRequest(url).enqueue(cb);
    }

    public void authenticate(Callback cb) {
        this.authenticate(cb, this.oAuthConfig.getAuthorization_Code());
    }

    public void authenticate(Callback acb, String authorizationCode) {

        FormBody.Builder formBodyBuilder = new FormBody.Builder();

        formBodyBuilder.add("client_id", this.oAuthConfig.getClient_ID());
        formBodyBuilder.add("client_secret", this.oAuthConfig.getClient_Secret());
        formBodyBuilder.add("code", authorizationCode);
        formBodyBuilder.add("redirect_uri", this.oAuthConfig.getRedirect_uri());
        formBodyBuilder.add("grant_type", this.oAuthConfig.getGrant_type());

        FormBody formBody = formBodyBuilder.build();

        Request.Builder builder = new Request.Builder();
        builder = builder.url(this.oAuthConfig.getTokenServerUrl());
        builder = builder.post(formBody);
        Request request = builder.build();
        final Callback cb = acb;
        this.okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                cb.onFailure(call, e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    try {
                        JSONObject obj = new JSONObject(response.body().string());
                        token = obj.getString("access_token");
                        cb.onResponse(call, response);
                    } catch (Exception ex) {
                        cb.onFailure(call, new IOException(ex.toString()));
                    }

                }
            }
        });
    }
}
