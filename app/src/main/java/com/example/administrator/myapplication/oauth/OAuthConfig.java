package com.example.administrator.myapplication.oauth;

public class OAuthConfig {

    private String client_ID;
    private String client_Secret;
    private String redirect_uri;
    private String grant_type;
    private String authorization_Code;
    private String tokenServerUrl;

    public OAuthConfig(String client_ID, String client_Secret, String redirect_uri, String tokenServerUrl, String grant_type, String autorizationCode) {
        this.client_ID = client_ID;
        this.client_Secret = client_Secret;
        this.redirect_uri = redirect_uri;
        this.grant_type = grant_type;
        this.authorization_Code = autorizationCode;
        this.tokenServerUrl = tokenServerUrl;
    }

    public OAuthConfig(String client_ID, String client_Secret, String redirect_uri, String tokenServerUrl, String autorizationCode) {
        this(client_ID, client_Secret, redirect_uri, tokenServerUrl, "authorization_code", autorizationCode);
    }

    public OAuthConfig(String client_ID, String client_Secret, String redirect_uri, String tokenServerUrl) {
        this(client_ID, client_Secret, redirect_uri, tokenServerUrl, "authorization_code", null);
    }

    public String getTokenServerUrl() {
        return tokenServerUrl;
    }

    public void setTokenServerUrl(String tokenServerUrl) {
        this.tokenServerUrl = tokenServerUrl;
    }

    public String getAuthorization_Code() {
        return authorization_Code;
    }

    public void setAuthorization_Code(String authorization_Code) {
        this.authorization_Code = authorization_Code;
    }

    public String getClient_ID() {
        return client_ID;
    }

    public void setClient_ID(String client_ID) {
        this.client_ID = client_ID;
    }

    public String getClient_Secret() {
        return client_Secret;
    }

    public void setClient_Secret(String client_Secret) {
        this.client_Secret = client_Secret;
    }

    public String getRedirect_uri() {
        return redirect_uri;
    }

    public void setRedirect_uri(String redirect_uri) {
        this.redirect_uri = redirect_uri;
    }

    public String getGrant_type() {
        return grant_type;
    }

    public void setGrant_type(String grant_type) {
        this.grant_type = grant_type;
    }
}
