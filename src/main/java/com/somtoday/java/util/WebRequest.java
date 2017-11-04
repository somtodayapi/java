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

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

public class WebRequest {

    String doPost(String url, Map<String, String> headers, Map<String, String> parameters) {
        StringBuilder response = new StringBuilder();
        try {
            StringBuilder urlParameters = new StringBuilder();
            for(String key : parameters.keySet()) {
                urlParameters.append(key);
                if(parameters.get(key) != null) {
                    urlParameters.append("=");
                    urlParameters.append(parameters.get(key));
                }
                urlParameters.append("&");
            }
            if(urlParameters.length() > 0) urlParameters.deleteCharAt(urlParameters.length() - 1);

            byte[] data = urlParameters.toString().getBytes("UTF-8");
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(url).openConnection();
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            httpURLConnection.setRequestProperty("Charset", "UTF-8");
            httpURLConnection.setRequestProperty("Content-Length", Integer.toString(data.length));
            for(String key : headers.keySet()) {
                httpURLConnection.setRequestProperty(key, headers.get(key));
            }
            httpURLConnection.setUseCaches(false);
            DataOutputStream outputStream = new DataOutputStream(httpURLConnection.getOutputStream());
            outputStream.write(data);

            BufferedReader bufferedReader;
            if(httpURLConnection.getResponseCode() >= 200 && httpURLConnection.getResponseCode() < 300) {
                bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            } else {
                bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getErrorStream()));
            }

            String buffer;
            while((buffer = bufferedReader.readLine()) != null) {
                response.append(buffer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response.toString();
    }

    String doGet(String url, Map<String, String> headers, Map<String, String> parameters) {
        StringBuilder response = new StringBuilder();
        try {
            StringBuilder urlParameters = new StringBuilder();
            for(String key : parameters.keySet()) {
                urlParameters.append(key);
                if(parameters.get(key) != null) {
                    urlParameters.append("=");
                    urlParameters.append(parameters.get(key));
                }
                urlParameters.append("&");
            }
            if(urlParameters.length() > 0) urlParameters.deleteCharAt(urlParameters.length() - 1);
            url += "?" + urlParameters;

            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(url).openConnection();
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setRequestProperty("Charset", "UTF-8");
            for(String key : headers.keySet()) {
                httpURLConnection.setRequestProperty(key, headers.get(key));
            }
            httpURLConnection.setUseCaches(false);

            BufferedReader bufferedReader;
            if(httpURLConnection.getResponseCode() >= 200 && httpURLConnection.getResponseCode() < 300) {
                bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            } else {
                bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getErrorStream()));
            }

            String buffer;
            while((buffer = bufferedReader.readLine()) != null) {
                response.append(buffer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response.toString();
    }
}
