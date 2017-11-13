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

package com.somtoday.java.manager;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.somtoday.java.entities.School;
import com.somtoday.java.entities.impl.SchoolImpl;
import com.somtoday.java.util.WebMethod;

import java.util.ArrayList;
import java.util.List;

public class SchoolManager extends WebMethod {

    private List<School> schools = new ArrayList<>();

    public SchoolManager() {
        super("https://servers.somtoday.nl/organisaties.json");

        doRequest(RequestType.GET);
    }

    @Override
    public void handle(String body) {
        JsonObject jsonObject = new JsonParser().parse(body).getAsJsonArray().get(0).getAsJsonObject();
        JsonArray jsonArray = jsonObject.get("instellingen").getAsJsonArray();
        for(int i = 0; i < jsonArray.size(); i++) {
            JsonObject instanceObject = jsonArray.get(i).getAsJsonObject();
            SchoolImpl school = new SchoolImpl(instanceObject.get("naam").getAsString(),
                    instanceObject.get("plaats").getAsString(),
                    instanceObject.get("uuid").getAsString());
            schools.add(school);
        }
    }

    public List<String> getSchoolNames() {
        List<String> names = new ArrayList<>();
        for(School school : schools) {
            names.add(school.getName());
        }
        return names;
    }

    public School getSchoolByName(String name) {
        for(School school : schools) {
            if(school.getName().equalsIgnoreCase(name)) {
                return school;
            }
        }
        throw new NullPointerException("No school found with name " + name);
    }
}
