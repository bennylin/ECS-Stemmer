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

import Contextual.Stemmer.ConfixStripping.PrecedenceAdjustmentSpecification;
import Contextual.Stemmer.Dictionary.DictionaryInterface;
import Contextual.Stemmer.Visitor.EnumRemovalRules;
import Contextual.Stemmer.Visitor.VisitableInterface;
import Contextual.Stemmer.Visitor.VisitorInterface;
import Contextual.Stemmer.Visitor.VisitorProvider;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

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
    protected List<VisitorInterface> visitors, suffixVisitors, prefixVisitors;


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

    protected void initVisitors() {
        this.visitors.addAll(visitorProvider.getVisitors());
        this.suffixVisitors.addAll(this.visitorProvider.getSuffixVisitors());
        this.prefixVisitors.addAll(this.visitorProvider.getPrefixVisitors());
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
        this.removals.add(removal);
    }

    public RemovalInterface[] getRemovals() {
        return (RemovalInterface[]) this.removals.toArray();
    }

    public String getResult() {
        return result;
    }

    /**
     * Execute stemming process; the result can be retrieved with getResult()
     *
     * @return void
     */
    public void execute() {
        // step 1 - 5
        this.startStemmingProcess();

        // step 6
        if (this.dictionary.contains(this.getCurrentWord())) {
            this.result = this.getCurrentWord();
        } else {
            this.result = this.originalWord;
        }
    }

    protected void startStemmingProcess() {
        // step 1
        if (this.dictionary.contains(this.getCurrentWord())) {
            return;
        }

        this.acceptVisitors((VisitorInterface[]) this.visitors.toArray());

        if (this.dictionary.contains(this.getCurrentWord())) {
            return;
        }

        PrecedenceAdjustmentSpecification csPrecedenceAdjustmentSpecification = new PrecedenceAdjustmentSpecification();

        /*
         * Confix Stripping
         * Try to remove prefix before suffix if the specification is met
         */
        if (csPrecedenceAdjustmentSpecification.isSatisfiedBy(this.getOriginalWord())) {

            // step 4, 5
            this.removePrefixes();
            if (this.dictionary.contains(this.getCurrentWord())) {
                return;
            }

            // step 2, 3
            this.removeSuffixes();
            if (this.dictionary.contains(this.getCurrentWord()))
                return;
            else {
                // if the trial is failed, restore the original word
                // and continue to normal rule precedence (suffix first, prefix afterwards)
                this.setCurrentWord(this.originalWord);
                this.removals = null;
            }
        }

        // step 2, 3
        this.removeSuffixes();
        if (this.dictionary.contains(this.getCurrentWord()))
            return;

        //step 4, 5
        this.removePrefixes();
        if (this.dictionary.contains(this.getCurrentWord()))
            return;

        // ECS loop pengembalian akhiran
        this.loopPengembalianAkhiran();
    }//=================================end of startStemmingProcess()=================================

    protected String acceptVisitors(VisitorInterface[] visitors) {
        for (VisitorInterface visitor : visitors) {

            this.accept(visitor);

            if (this.getDictionary().contains(this.getCurrentWord()) {
                return this.getCurrentWord();
            }

            if (this.processIsStopped) {
                return this.getCurrentWord();
            }
        }
    }

    protected void removePrefixes() {
        for (int i = 0; i < 3; i++) {
            this.acceptPrefixVisitors((VisitorInterface[]) this.prefixVisitors.toArray());
            if (this.dictionary.contains(this.getCurrentWord()))
                return;
        }
    }

    protected String acceptPrefixVisitors(VisitorInterface[] visitors) {
        int removalCount = this.removals.size();

        for (VisitorInterface visitor : visitors) {
            this.accept(visitor);

            if (this.getDictionary().contains(this.getCurrentWord()))
                return this.getCurrentWord();

            if (this.processIsStopped)
                return this.getCurrentWord();

            if (this.removals.size() > removalCount)
                break;
        }
        return null;
    }

    /**
     * ECS Loop Pengembalian Akhiran
     */
    public void loopPengembalianAkhiran() {
        // restore prefix to form [DP+[DP+[DP]]] + Root word
        this.restorePrefix();

        //if there's a problem, try to check if the original list is also reversed
        final ImmutableList<RemovalInterface> _removals = ImmutableList.copyOf(removals);
        List<RemovalInterface> reversedRemovals = Lists.reverse(_removals);
        final String _currentWord = this.getCurrentWord();

        for (RemovalInterface removal : reversedRemovals) {
            if (!this.isSuffixRemoval(removal))
                continue;

            if (removal.getRemovedPart().equals("kan")) {
                this.setCurrentWord(removal.getResult().concat("k"));

                // step 4, 5
                this.removePrefixes();
                if (this.dictionary.contains((this.getCurrentWord())))
                    return;

                this.setCurrentWord(removal.getResult().concat("kan"));
            } else
                this.setCurrentWord(removal.getSubject());

            // step 4, 5
            this.removePrefixes();
            if (this.dictionary.contains(this.getCurrentWord()))
                return;
            this.removals = _removals;
            this.setCurrentWord(_currentWord);
        }
    }

    private boolean isSuffixRemoval(RemovalInterface removal) {
        String removalAffixType = removal.getAffixType();
        return removalAffixType.equals(EnumRemovalRules.REMOVE_DERIVATIONAL_SUFFIX.getAffixType()) ||
                removalAffixType.equals(EnumRemovalRules.REMOVE_INFLECTIONAL_PARTICLE.getAffixType()) ||
                removalAffixType.equals(EnumRemovalRules.REMOVE_INFLECTIONAL_POSSESSIVE_PRONOUN.getAffixType());
    }

    public void accept(VisitorInterface visitor) {
        visitor.visit(this);
    }


    public void removeSuffixes() {
        System.out.println("Removing suffixes");
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
        for (RemovalInterface removal : removals) {
            if (removal.getAffixType().equals("DP")) {
                this.removals.remove(removal);
            }
        }
    }
}
