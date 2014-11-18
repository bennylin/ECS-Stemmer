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

import Contextual.Stemmer.Dictionary.DictionaryInterface;
import Contextual.Stemmer.Visitor.VisitableInterface;
import Contextual.Stemmer.Visitor.VisitorInterface;
import Contextual.Stemmer.Visitor.VisitorProvider;

import java.util.List;

/**
 * Created by Sami on 17/11/14.
 */
public class Context implements ContextInterface, VisitableInterface {

    protected boolean processIsStopped = false;
    protected String originalWord, currentWord;
    protected List<RemovalInterface> removals;
    protected DictionaryInterface dictionary;
    protected VisitorProvider visitorProvider;
    protected VisitorInterface[] visitors, suffixVisitors, prefixVisitors;
    protected String result;

    public Context(String originalWord,
                   DictionaryInterface dictionary,
                   VisitorProvider visitorProvider) {
        this.originalWord = originalWord;
        this.currentWord = this.originalWord;
        this.dictionary = dictionary;
        this.visitorProvider = visitorProvider;

        this.initVisitors();
    }

    public void execute() {

    }

    protected void initVisitors() {
        this.visitors = this.visitorProvider.getVisitors();
        this.suffixVisitors = this.visitorProvider.getSuffixVisitors();
        this.prefixVisitors = this.visitorProvider.getPrefixVisitors();
        //to be implemented after finishing up VisitorProvider
    }

    public String getOriginalWord() {
        return null;
    }

    public String getCurrentWord() {
        return this.currentWord;
    }

    public void setCurrentWord(String word) {
        this.currentWord = word;
    }

    public DictionaryInterface getDictionary() {
        return this.dictionary;
    }

    public void stopProcess() {
        this.processIsStopped = true;
    }

    public boolean processIsStopped() {
        return this.processIsStopped;
    }

    public void addRemoval(RemovalInterface removal) {
        this.removals = removal;
    }

    public RemovalInterface[] getRemovals() {
        return this.removals.toArray();
    }

    public void accept(VisitorInterface visitor) {

    }

    /**
     * Restore prefix to proceed with ECS loop pengembalian akhiran
     *
     * @return void
     */
    public void restorePrefix() {
        for (RemovalInterface removal : this.removals) {
            if (removal.getAffixType().equals("DP")) {
                // return the word before precoding (the subject of first prefix removal)
                this.setCurrentWord(removal.getSubject());
                break;
            }
        }

        for ($this -> removals as $i =>$removal){
            if ($removal -> getAffixType() == 'DP') {
                unset($this -> removals[$i]);
            }
        }
    }
