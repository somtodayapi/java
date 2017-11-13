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

import com.somtoday.java.entities.Restriction;

import java.util.Arrays;
import java.util.List;

public class RestrictionImpl implements Restriction {

    private String type;
    //TODO Find out which types there are
    private List<Object> items;

    @Override
    public String getType() {
        return type;
    }

    @Override
    public List<Object> getItems() {
        return items;
    }

    @Override
    public String toString() {
        return "RestrictionImpl{" +
                "type='" + type + '\'' +
                ", items=" + items +
                '}';
    }

    public RestrictionImpl(String type, Object[] items) {
        this.type = type;
        this.items = Arrays.asList(items);
    }
}
