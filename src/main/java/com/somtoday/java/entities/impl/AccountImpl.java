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
import com.somtoday.java.entities.Account;
import com.somtoday.java.entities.Link;
import com.somtoday.java.entities.Permission;
import com.somtoday.java.entities.Person;
import com.somtoday.java.manager.AdditionalObject;
import com.somtoday.java.util.WebMethod;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AccountImpl extends WebMethod implements Account {

    private List<Link> links;
    private List<Permission> permissions;
    private List<Object> additionalObjects;
    private String username;
    private List<Object> accountPermissions;
    private Person person;

    private AdditionalObject[] additionalObjectArray;

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
    public String getUsername() {
        return username;
    }

    @Override
    public List<Object> getAccountPermissions() {
        return accountPermissions;
    }

    @Override
    public Person getPerson() {
        return person;
    }

    @Override
    public String toString() {
        return "AccountImpl{" +
                "links=" + links +
                ", permissions=" + permissions +
                ", additionalObjects=" + additionalObjects +
                ", username='" + username + '\'' +
                ", accountPermissions=" + accountPermissions +
                ", person=" + person +
                '}';
    }

    public AccountImpl(String apiEndpoint) {
        super(apiEndpoint + "/rest/v1/account/me");
        this.additionalObjectArray = new AdditionalObject[0];
    }

    public AccountImpl(String apiEndpoint, String pupilId) {
        super(apiEndpoint + "/rest/v1/account/" + pupilId);
        this.additionalObjectArray = new AdditionalObject[0];
    }

    public AccountImpl(String apiEndpoint, AdditionalObject... additionalObjects) {
        super(apiEndpoint + "/rest/v1/account/me?" + AdditionalObject.buildUrl(additionalObjects));
        this.additionalObjectArray = additionalObjects;
    }

    public AccountImpl(String apiEndpoint, String pupilId, AdditionalObject... additionalObjects) {
        super(apiEndpoint + "/rest/v1/account/" + pupilId + "?" + AdditionalObject.buildUrl(additionalObjects));
        this.additionalObjectArray = additionalObjects;
    }

    public Account getAccount(String accessToken) {
        clearParameters();
        clearHeaders();

        addHeader("Accept", "application/vnd.topicus.platinum+json");
        addHeader("Authorization", "Bearer " + accessToken);

        doRequest(RequestType.GET);

        return this;
    }

    @Override
    public void handle(String body) {
        JsonObject jsonObject = new JsonParser().parse(body).getAsJsonObject();
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
        if(this.additionalObjectArray.length > 0) {
            List<AdditionalObject> additionalObjects = Arrays.asList(this.additionalObjectArray);
            JsonObject additionalJsonObject = jsonObject.get("additionalObjects").getAsJsonObject();
            if(additionalObjects.contains(AdditionalObject.RESTRICTIONS)) {
                JsonObject restrictionsJsonObject = additionalJsonObject.get(AdditionalObject.RESTRICTIONS.getUrlName()).getAsJsonObject();
                Object[] objects = new Object[restrictionsJsonObject.get("items").getAsJsonArray().size()];
                for(int i = 0; i < restrictionsJsonObject.get("items").getAsJsonArray().size(); i++) {
                    objects[i] = restrictionsJsonObject.get("items").getAsJsonArray().get(i).getAsJsonObject();
                }
                this.additionalObjects.add(new RestrictionImpl(restrictionsJsonObject.get("$type").getAsString(), objects));
            }
        }
        this.username = jsonObject.get("gebruikersnaam").getAsString();
        this.accountPermissions = new ArrayList<>();
        jsonObject.get("accountPermissions").getAsJsonArray().forEach(jsonElement -> this.accountPermissions.add(jsonElement));
        JsonObject personObject = jsonObject.get("persoon").getAsJsonObject();
        List<Link> personLinks = new ArrayList<>();
        personObject.get("links").getAsJsonArray().forEach(linkElement -> {
            JsonObject linkObject = linkElement.getAsJsonObject();
            personLinks.add(new LinkImpl(linkObject.get("id").getAsString(),
                    linkObject.get("rel").getAsString(),
                    linkObject.get("type").getAsString(),
                    linkObject.get("href").getAsString()));
        });
        List<Permission> personPermissions = new ArrayList<>();
        personObject.get("permissions").getAsJsonArray().forEach(permissionElement-> {
            JsonObject permissionObject = permissionElement.getAsJsonObject();
            String[] operations = new String[permissionObject.get("operations").getAsJsonArray().size()];
            for(int i = 0; i < permissionObject.get("operations").getAsJsonArray().size(); i++) {
                operations[i] = permissionObject.get("operations").getAsJsonArray().get(i).getAsString();
            }
            String[] instances = new String[permissionObject.get("instances").getAsJsonArray().size()];
            for(int i = 0; i < permissionObject.get("instances").getAsJsonArray().size(); i++) {
                instances[i] = permissionObject.get("instances").getAsJsonArray().get(i).getAsString();
            }
            personPermissions.add(new PermissionImpl(permissionObject.get("full").getAsString(),
                    permissionObject.get("type").getAsString(),
                    operations,
                    instances));
        });
        Link[] links = new Link[personLinks.size()];
        links = personLinks.toArray(links);

        Permission[] permissions = new Permission[personPermissions.size()];
        permissions = personPermissions.toArray(permissions);
        this.person = new PersonImpl(personObject.get("$type").getAsString(),
                links,
                permissions,
                personObject.get("leerlingnummer").getAsString(),
                personObject.get("roepnaam").getAsString(),
                personObject.get("achternaam").getAsString());
    }
}
