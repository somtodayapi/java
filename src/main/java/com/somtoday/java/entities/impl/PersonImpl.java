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

import com.somtoday.java.entities.Link;
import com.somtoday.java.entities.Permission;
import com.somtoday.java.entities.Person;

import java.util.Arrays;
import java.util.List;

public class PersonImpl implements Person {

    private String type;
    private List<Link> links;
    private List<Permission> permissions;
    private String pupilNumber;
    private String name;
    private String lastName;

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
    public String getPupilNumber() {
        return pupilNumber;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getLastName() {
        return lastName;
    }

    @Override
    public String toString() {
        return "PersonImpl{" +
                "type='" + type + '\'' +
                ", links=" + links +
                ", permissions=" + permissions +
                ", pupilNumber='" + pupilNumber + '\'' +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

    PersonImpl(String type, Link[] links, Permission[] permissions, String pupilNumber, String name, String lastName) {
        this.type = type;
        this.links = Arrays.asList(links);
        this.permissions = Arrays.asList(permissions);
        this.pupilNumber = pupilNumber;
        this.name = name;
        this.lastName = lastName;
    }
}
