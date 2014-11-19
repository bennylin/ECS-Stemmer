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

package Contextual.Stemmer.Visitor;

import Contextual.Stemmer.ContextInterface;
import Contextual.Stemmer.Removal;

/**
 * Created by samiunn on 18/11/14.
 */
public abstract class AbstractRemoveAffixes implements VisitorInterface {
    Removal removal;
    ContextInterface context;
    String result, removedPart;
    String regexRule, replacement;

    @Override
    public void visit(ContextInterface context) {
        this.context = context;
        result = this.remove(context.getCurrentWord());
        if (!result.equals(context.getCurrentWord())) {
            removedPart = context.getCurrentWord().replaceAll("/" + result + "/", "");
            setRemoval();
            context.addRemoval(removal);
            context.setCurrentWord(result);
        }
    }

    abstract void setRemoval();

    public String remove(String word) //to be translated
    {
        return word.replaceAll(regexRule, replacement);
    }
}
