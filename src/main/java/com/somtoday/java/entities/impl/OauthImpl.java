/*
 * MIT License
 *
 * Copyright (c) 2017 somtodayapi
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.somtoday.java.entities.impl;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.somtoday.java.SomAPI;
import com.somtoday.java.entities.Oauth;
import com.somtoday.java.entities.School;
import com.somtoday.java.util.WebMethod;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class OauthImpl extends WebMethod implements Oauth {

    private String accessToken;
    private String refreshToken;
    private String idToken;
    private String apiUrl;
    private String scope;
    private String tenant;
    private String tokenType;
    private Integer expireDate;

    @Override
    public String getAccessToken() {
        return accessToken;
    }

    @Override
    public String getRefreshToken() {
        return refreshToken;
    }

    @Override
    public String getIdToken() {
        return idToken;
    }

    @Override
    public String getApiUrl() {
        return apiUrl;
    }

    @Override
    public String getScope() {
        return scope;
    }

    @Override
    public String getTenant() {
        return tenant;
    }

    @Override
    public String getTokenType() {
        return tokenType;
    }

    @Override
    public Integer getExpireTime() {
        return expireDate;
    }

    @Override
    public String toString() {
        return "OauthImpl{" +
                "accessToken='" + accessToken + '\'' +
                ", refreshToken='" + refreshToken + '\'' +
                ", idToken='" + idToken + '\'' +
                ", apiUrl='" + apiUrl + '\'' +
                ", scope='" + scope + '\'' +
                ", tenant='" + tenant + '\'' +
                ", tokenType='" + tokenType + '\'' +
                ", expireDate='" + expireDate + '\'' +
                '}';
    }

    public OauthImpl() {
        super("https://productie.somtoday.nl/oauth2/token");
    }

    public Oauth login(String username, String password, School school) {
        clearParameters();

        addHeader("Accept", "application/json");

        byte[] authorizationBytes = (SomAPI.clientId + ":" + SomAPI.clientSecret).getBytes(StandardCharsets.UTF_8);
        addHeader("Authorization", "Basic " + Base64.getEncoder().encodeToString(authorizationBytes));

        addParameter("username", school.getUuid() + "\\" + username);
        addParameter("password", password);
        addParameter("scope", "openid");
        addParameter("grant_type", "password");

        doRequest(RequestType.POST);

        return this;
    }

    @Override
    public void handle(String body) {
        JsonObject jsonObject = new JsonParser().parse(body).getAsJsonObject();
        this.accessToken = jsonObject.get("access_token").getAsString();
        this.refreshToken = jsonObject.get("refresh_token").getAsString();
        this.apiUrl = jsonObject.get("somtoday_api_url").getAsString();
        this.scope = jsonObject.get("scope").getAsString();
        this.tenant = jsonObject.get("somtoday_tenant").getAsString();
        this.idToken = jsonObject.get("id_token").getAsString();
        this.tokenType = jsonObject.get("token_type").getAsString();
        this.expireDate = jsonObject.get("expires_in").getAsInt();
    }
}
