/*
 * MIT License
 * Copyright (c) 2017 somtodayapi
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.somtoday.java.entities.impl;

import com.somtoday.java.entities.*;
import com.somtoday.java.manager.AdditionalObject;

public class SomAPIImpl implements SomAPI {

    public static final String clientId = "D50E0C06-32D1-4B41-A137-A9A850C892C2";
    public static final String clientSecret = "vDdWdKwPNaPCyhCDhaCnNeydyLxSGNJX";

    @Override
    public Oauth login(String username, String password, School school) {
        OauthImpl oauth = new OauthImpl();
        return oauth.login(username, password, school);
    }

    @Override
    public Oauth refresh(String refreshToken) {
        OauthImpl oauth = new OauthImpl();
        return oauth.refresh(refreshToken);
    }

    @Override
    public Account getAccount(String endpoint, String accessToken) {
        AccountImpl account = new AccountImpl(endpoint);
        return account.getAccount(accessToken);
    }

    @Override
    public Account getAccount(String endpoint, String accessToken, String username) {
        AccountImpl account = new AccountImpl(endpoint, username);
        return account.getAccount(accessToken);
    }

    @Override
    public Account getAccount(String endpoint, String accessToken, AdditionalObject... additionalObjects) {
        AccountImpl account = new AccountImpl(endpoint, additionalObjects);
        return account.getAccount(accessToken);
    }

    @Override
    public Account getAccount(String endpoint, String accessToken, String username, AdditionalObject... additionalObjects) {
        AccountImpl account = new AccountImpl(endpoint, username, additionalObjects);
        return account.getAccount(accessToken);
    }

    @Override
    public SchoolYear getCurrentSchoolYear(String endpoint, String accessToken) {
        SchoolYearImpl schoolYear = new SchoolYearImpl(endpoint);
        return schoolYear.getSchoolYear(accessToken);
    }
}
