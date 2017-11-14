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

import com.somtoday.java.entities.Permission;

import java.util.Arrays;
import java.util.List;

public class PermissionImpl implements Permission {

    private String full;
    private String type;
    private List<String> operations;
    private List<String> instances;

    @Override
    public String getFull() {
        return full;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public List<String> getOperations() {
        return operations;
    }

    @Override
    public List<String> getInstances() {
        return instances;
    }

    @Override
    public String toString() {
        return "PermissionImpl{" +
                "full='" + full + '\'' +
                ", type='" + type + '\'' +
                ", operations=" + operations +
                ", instances=" + instances +
                '}';
    }

    PermissionImpl(String full, String type, String[] operations, String[] instances) {
        this.full = full;
        this.type = type;
        this.operations = Arrays.asList(operations);
        this.instances = Arrays.asList(instances);
    }
}
