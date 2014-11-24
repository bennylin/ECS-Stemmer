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

import Contextual.Stemmer.Cache.ArrayCache;
import Contextual.Stemmer.Dictionary.ArrayDictionary;
import com.google.common.io.Files;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Paths;

/**
 * Created by Sami on 17/11/14.
 */
public class StemmerFactory {
    protected boolean debug = true;
    protected String DICT_FILENAME = "../kata-dasar.txt";

    public void createStemmer() {
        String words[] = this.getWords();
        ArrayDictionary dictionary = new ArrayDictionary(words);
        Stemmer stemmer = new Stemmer(dictionary);

        ArrayCache resultCache = new ArrayCache();
        CachedStemmer cachedStemmer = new CachedStemmer(resultCache, stemmer);
    }

    protected String[] getWords() {
        return this.getWordsFromFile();
    }

    protected String[] getWordsFromFile() {
        String pwd = Paths.get(".").toAbsolutePath().normalize().toString();
        String dictionaryFile = pwd + "/../../" + DICT_FILENAME;
        String contents = new String();
        String[] split;
        File dictFile;

        try {    //Try to read dictionary files to be exploded in string split
            dictFile = new File(dictionaryFile);
            if (!dictFile.canRead()) {
                System.out.println("Missing or incorrect permission for dictionary file");
                System.exit(ErrorDescriptorsEnum.DICTIONARY_FILE_ERROR.getErrCode());
            }
            try {
                contents = Files.toString(dictFile, Charset.defaultCharset());
            } catch (IOException e) {
                System.out.println(e.getStackTrace());
            }
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
        }

        if (contents.isEmpty()) {
            System.out.println("Dictionary file is empty, exiting...\n");
            System.exit(ErrorDescriptorsEnum.DICTIONARY_CONTENTS_ERROR.getErrCode());
        }
        split = StringUtils.explode("\n", contents);
        return split;
    }
}
