/*
 * The MIT License (MIT)
 * Copyright (c) 2014 Andy Librian
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

package Contextual.Stemmer;

import Contextual.Stemmer.Visitor.VisitorInterface;

/**
 * Created by M.Sami on 17/11/14.
 */
public class Removal {


    protected VisitorInterface visitor;
    protected String subject, result, removedPart, affixType;

    public Removal(VisitorInterface visitor,
                   String subject,
                   String result,
                   String removedPart,
                   String affixType) {
        this.visitor = visitor;
        this.subject = subject;
        this.result = result;
        this.removedPart = removedPart;
        this.affixType = affixType;
    }

    public VisitorInterface getVisitor() {
        return this.visitor;
    }

    public String getSubject() {
        return this.subject;
    }

    public String getResult() {
        return this.result;
    }

    public String getRemovedPart() {
        return this.removedPart;
    }

    public String getAffixType() {
        return this.affixType;
    }

}
