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

package Contextual.Stemmer.Morphology.Disambiguator;

/**
 * Created by samiunn on 19/11/14.
 */
public enum EnumDisambiguateRules {
    PREFIX_RULE_1A("/^ber([aiueo].*)$/", "", ""),      //prefix rule 1a
    PREFIX_RULE_1B("/^ber([aiueo].*)$/", "", "r"),
    PREFIX_RULE_2("/^ber([bcdfghjklmnpqrstvwxyz])([a-z])(.*)$/", "/^er(.*)$/", ""),
    PREFIX_RULE_3("/^ber([bcdfghjklmnpqrstvwxyz])([a-z])er([aiueo])(.*)$/", "", ""),
    PREFIX_RULE_4("belajar", "", "ajar"),
    PREFIX_RULE_5("/^be([bcdfghjklmnpqstvwxyz])(er[bcdfghjklmnpqrstvwxyz])(.*)$/", "", ""),
    PREFIX_RULE_6A("/^ter([aiueo].*)$/", "", ""),
    PREFIX_RULE_6B("/^ter([aiueo].*)$/", "", "r"),
    PREFIX_RULE_7("/^ter([bcdfghjklmnpqrstvwxyz])er([aiueo].*)$/", "r", "er"),
    PREFIX_RULE_8("/^ter([bcdfghjklmnpqrstvwxyz])(.*)$/", "", ""),
    PREFIX_RULE_9("/^te([bcdfghjklmnpqrstvwxyz])er([bcdfghjklmnpqrstvwxyz])(.*)$/", "", ""),
    PREFIX_RULE_10("/^me([lrwy])([aiueo])(.*)$/", "", ""),

    PREFIX_RULE_11("/^mem([bfv])(.*)$/", "", ""),
    PREFIX_RULE_12("/^mempe(.*)$/", "", "pe"),
    PREFIX_RULE_13A("/^mem([aiueo])(.*)$/", "", "m"),
    PREFIX_RULE_13B("/^mem([aiueo])(.*)$/", "", "p"),
    PREFIX_RULE_14("/^men([cdjstz])(.*)$/", "", ""),
    PREFIX_RULE_15A("/^men([aiueo])(.*)$/", "", "n"),
    PREFIX_RULE_15B("/^men([aiueo])(.*)$/", "", "t"),
    PREFIX_RULE_16("/^meng([g|h|q|k])(.*)$/", "", ""),
    PREFIX_RULE_17A("/^meng([aiueo])(.*)$/", "", ""),
    PREFIX_RULE_17B("/^meng([aiueo])(.*)$/", "", ""),
    PREFIX_RULE_17C("/^menge(.*)$/", "", ""),
    PREFIX_RULE_18A("/^meny([aiueo])(.*)$/", "", ""),
    PREFIX_RULE_18B("/^meny([aiueo])(.*)$/", "", "s"),
    PREFIX_RULE_19("/^memp([abcdfghijklmopqrstuvwxyz])(.*)$/", "", "p"),
    PREFIX_RULE_20("/^pe([wy])([aiueo])(.*)$/", "", ""),

    PREFIX_RULE_21A("/^per([aiueo])(.*)$/", "", ""),
    PREFIX_RULE_21B("/^pe(r[aiueo])(.*)$/", "", ""),
    PREFIX_RULE_23("/^per([bcdfghjklmnpqrstvwxyz])([a-z])(.*)$/", "/^er(.*)$/", ""),
    PREFIX_RULE_24("/^per([bcdfghjklmnpqrstvwxyz])([a-z])er([aiueo])(.*)$/", "r", "er"),
    PREFIX_RULE_25("/^pem([bfv])(.*)$/", "", ""),
    PREFIX_RULE_26A("/^pem([aiueo])(.*)$/", "", "m"),
    PREFIX_RULE_26B("/^pem([aiueo])(.*)$/", "", "p"),
    PREFIX_RULE_27("/^pen([cdjz])(.*)$/", "", ""),
    PREFIX_RULE_28A("/^pen([aiueo])(.*)$/", "", "n"),
    //    PREFIX_RULE_28B(""),
//    PREFIX_RULE_29(""),
//    PREFIX_RULE_30A(""),
//    PREFIX_RULE_30B(""),
//    PREFIX_RULE_30C(""),
//
//    PREFIX_RULE_31A(""),
//    PREFIX_RULE_31B(""),
//    PREFIX_RULE_31B(""),
//    PREFIX_RULE_32(""),
//    PREFIX_RULE_33(""),
//    PREFIX_RULE_34(""),
//    PREFIX_RULE_35(""),
//    PREFIX_RULE_36(""),
//    PREFIX_RULE_37A(""),
//    PREFIX_RULE_37B(""),
//    PREFIX_RULE_38A(""),
//    PREFIX_RULE_38B(""),
//    PREFIX_RULE_39A(""),
//    PREFIX_RULE_39B(""),
//    PREFIX_RULE_40A(""),
//    PREFIX_RULE_40B(""),
    private static final int length = EnumDisambiguateRules.values().length;
    //    private final String affixType;
    private final String regexRule1;
    private final String regexRule2;
    private final String returnMatch;

    EnumDisambiguateRules(String regex1, String regex2, String returnMatch) {
//        this.affixType = affixType;
        this.regexRule1 = regex1;
        this.regexRule2 = regex2;
        this.returnMatch = returnMatch;
    }


    //    public String getAffixType(){
//        return affixType;
//    }
    public String getRegexRule1() {
        return this.regexRule1;
    }

    public String getRegexRule2() {
        return this.regexRule2;
    }

    public String getReturnMatch() {
        return this.returnMatch;
    }

    public boolean hasSecondRegex() {
        return !this.regexRule2.isEmpty();
    }

//    public String preg_match() {
//        return returnMatch
//    }

    }
