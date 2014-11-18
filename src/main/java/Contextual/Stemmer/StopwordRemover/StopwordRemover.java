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

package Contextual.Stemmer.StopwordRemover;

import Contextual.Stemmer.Dictionary.DictionaryInterface;
import Contextual.Stemmer.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Sami on 17/11/14.
 */
public class StopwordRemover {
    protected DictionaryInterface dictionary;

    public StopwordRemover(DictionaryInterface dictionary) {
        this.dictionary = dictionary;
    }

    public DictionaryInterface getDictionary() {
        return this.dictionary;
    }

    /**
     * Remove stop words.
     *
     * @param string $text The text which stop words to be removed
     * @return string The text after removal
     */
    public String remove(String text) {

        List<String> words = new ArrayList<String>(Arrays.asList(StringUtils.explode(" ", text)));
        ;
        String[] joined = new String[words.size()];
        int i = 0;
        for (String word : words) {
            if (this.dictionary.contains(word))
                words.remove(i);
            i++;
        }

        joined = words.toArray(joined);
        return StringUtils.implode(" ", joined); //todo: implement an implode with space ' ' filler on text
    }
}
