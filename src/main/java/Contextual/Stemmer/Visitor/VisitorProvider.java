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

import Contextual.Stemmer.Morphology.Disambiguator.DisambiguatorPrefixRules;
import Contextual.Stemmer.Morphology.Disambiguator.EnumDisambiguateRules;

import java.util.List;

/**
 * Created by Sami on 17/11/14.
 */
public class VisitorProvider {
    protected List<DontStemShortWord> visitors;
    protected List<VisitorInterface> suffixVisitors;


    protected List<VisitorInterface> prefixVisitors;

    public VisitorProvider() {
        this.initVisitors();
    }

    protected void initVisitors() {
        visitors.add(new DontStemShortWord());

        this.suffixVisitors.add(new RemoveAffixes(EnumRemovalRules.REMOVE_INFLECTIONAL_PARTICLE)); // {lah|kah|tah|pun}
        this.suffixVisitors.add(new RemoveAffixes(EnumRemovalRules.REMOVE_INFLECTIONAL_POSSESSIVE_PRONOUN)); // {ku|mu|nya}
        this.suffixVisitors.add(new RemoveAffixes(EnumRemovalRules.REMOVE_DERIVATIONAL_SUFFIX)); // {i|kan|an}

        this.prefixVisitors.add(new RemoveAffixes(EnumRemovalRules.REMOVE_PLAIN_PREFIX));
        this.prefixVisitors.add(new PrefixDisambiguator(new DisambiguatorPrefixRules(EnumDisambiguateRules.PREFIX_RULE_1A)));


    }

    public List<DontStemShortWord> getVisitors() {
        return this.visitors;
    }

    public List<VisitorInterface> getPrefixVisitors() {
        return this.prefixVisitors;
    }

    public List<VisitorInterface> getSuffixVisitors() {
        return this.suffixVisitors;
    }

    public void setSuffixVisitors(RemoveAffixes suffixVisitor) {
        //setter for the list of suffixes
        this.suffixVisitors.add(suffixVisitor);
    }


    //will be implemented after finishing up removeinflectional classes


}
