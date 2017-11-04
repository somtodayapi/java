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

package com.somtoday.java.util;

import java.util.HashMap;
import java.util.Map;

public abstract class WebMethod extends WebRequest {

    private String url;
    private Map<String, String> headers;
    private Map<String, String> parameters;

    public WebMethod() {
        this(null);
    }

    public WebMethod(String url) {
        this(url, new HashMap<>());
    }

    public WebMethod(String url, Map<String, String> headers) {
        this(url, headers, new HashMap<>());
    }

    public WebMethod(String url, Map<String, String> headers, Map<String, String> parameters) {
        this.url = url;
        this.headers = headers;
        this.parameters = parameters;
    }

    public void addParameter(String key, String value) {
        parameters.put(key, value);
    }

    public void addHeader(String key, String value) {
        headers.put(key, value);
    }

    public void clearParameters() {
        parameters.clear();
    }

    public void clearHeaders() {
        headers.clear();
    }

    public void doRequest(RequestType type) {
        switch (type) {
            case GET:
                handle(doGet(url, headers, parameters));
                break;
            case POST:
                handle(doPost(url, headers, parameters));
                break;
        }
    }

    public abstract void handle(String body);

    public enum RequestType {
        POST, GET;
    }
}
