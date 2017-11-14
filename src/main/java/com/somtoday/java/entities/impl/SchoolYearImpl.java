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

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.somtoday.java.entities.Link;
import com.somtoday.java.entities.Permission;
import com.somtoday.java.entities.SchoolYear;
import com.somtoday.java.util.WebMethod;

import java.util.ArrayList;
import java.util.List;

public class SchoolYearImpl extends WebMethod implements SchoolYear {

    private String type;
    private List<Link> links;
    private List<Permission> permissions;
    private List<Object> additionalObjects;
    private String name;
    private String startDate;
    private String endDate;
    private boolean current;

    @Override
    public String getType() {
        return type;
    }

    @Override
    public List<Link> getLinks() {
        return links;
    }

    @Override
    public List<Permission> getPermissions() {
        return permissions;
    }

    @Override
    public List<Object> getAdditionalObjects() {
        return additionalObjects;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getStartDate() {
        return startDate;
    }

    @Override
    public String getEndDate() {
        return endDate;
    }

    @Override
    public boolean isCurrent() {
        return current;
    }

    @Override
    public String toString() {
        return "SchoolYearImpl{" +
                "type='" + type + '\'' +
                ", links=" + links +
                ", permissions=" + permissions +
                ", additionalObjects=" + additionalObjects +
                ", name='" + name + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", current=" + current +
                '}';
    }

    SchoolYearImpl(String apiEndpoint) {
        super(apiEndpoint + "/rest/v1/schooljaren/huidig");
    }

    SchoolYearImpl getSchoolYear(String accessToken) {
        clearHeaders();

        addHeader("Accept", "application/vnd.topicus.platinum+json");
        addHeader("Authorization", "Bearer " + accessToken);

        doRequest(RequestType.GET);

        return this;
    }

    @Override
    public void handle(String body) {
        JsonObject jsonObject = new JsonParser().parse(body).getAsJsonObject();
        this.type = jsonObject.get("$type").getAsString();
        this.links = new ArrayList<>();
        jsonObject.get("links").getAsJsonArray().forEach(linkElement -> {
            JsonObject linkObject = linkElement.getAsJsonObject();
            this.links.add(new LinkImpl(linkObject.get("id").getAsString(),
                    linkObject.get("rel").getAsString(),
                    linkObject.get("type").getAsString(),
                    linkObject.get("href").getAsString()));
        });
        this.permissions = new ArrayList<>();
        jsonObject.get("permissions").getAsJsonArray().forEach(permissionElement-> {
            JsonObject permissionObject = permissionElement.getAsJsonObject();
            String[] operations = new String[permissionObject.get("operations").getAsJsonArray().size()];
            for(int i = 0; i < permissionObject.get("operations").getAsJsonArray().size(); i++) {
                operations[i] = permissionObject.get("operations").getAsJsonArray().get(i).getAsString();
            }
            String[] instances = new String[permissionObject.get("instances").getAsJsonArray().size()];
            for(int i = 0; i < permissionObject.get("instances").getAsJsonArray().size(); i++) {
                instances[i] = permissionObject.get("instances").getAsJsonArray().get(i).getAsString();
            }
            this.permissions.add(new PermissionImpl(permissionObject.get("full").getAsString(),
                    permissionObject.get("type").getAsString(),
                    operations,
                    instances));
        });
        this.additionalObjects = new ArrayList<>();
        this.name = jsonObject.get("naam").getAsString();
        this.startDate = jsonObject.get("vanafDatum").getAsString();
        this.endDate = jsonObject.get("totDatum").getAsString();
        this.current = jsonObject.get("isHuidig").getAsBoolean();
    }
}
