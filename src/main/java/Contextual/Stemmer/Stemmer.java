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
import Contextual.Stemmer.Filter.TextNormalizer;
import Contextual.Stemmer.Visitor.VisitorProvider;

public class Stemmer implements StemmerInterface {
    protected DictionaryInterface dictionary;
    protected VisitorProvider visitorProvider;

    public Stemmer(DictionaryInterface dictionary) {
        this.dictionary = dictionary;
        this.visitorProvider = new VisitorProvider();
    }

    public DictionaryInterface getDictionary() {
        return this.dictionary;
    }

    /**
     * Stem a text string to its common stem form.
     *
     * @param string $text the text string to stem, e.g : memberdayakan pembangunan
     * @return string common stem form, e.g : daya bangun
     */
    public String stem(String text) {
        String normalizedText = TextNormalizer.normalizeText(text);
        String[] words = StringUtils.explode(" ", normalizedText);
        String[] stems = new String[words.length];


        return text;
    }

    /**
     * Stem a singular word to its common stem form.
     *
     * @param string $word the word to stem, e.g : mengalahkan
     * @return string common stem form, e.g : kalah
     */
    protected String stemSingularWord(String word) {
        Context context = new Context(word, this.dictionary, this.visitorProvider);
        context.execute();

        return context.getResult();
    }
}
