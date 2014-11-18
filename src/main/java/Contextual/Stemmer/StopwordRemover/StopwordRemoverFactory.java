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

/**
 * Created by Sami on 17/11/14.
 */
class StopwordRemoverFactory {

    public String[] getStopWords() {
        String[] stopWords =
                {
                        "yang", "untuk", "pada", "ke", "para", "namun", "menurut", "antara", "dia", "dua",
                        "ia", "seperti", "jika", "jika", "sehingga", "kembali", "dan", "tidak", "ini", "karena",
                        "kepada", "oleh", "saat", "harus", "sementara", "setelah", "belum", "kami", "sekitar",
                        "bagi", "serta", "di", "dari", "telah", "sebagai", "masih", "hal", "ketika", "adalah",
                        "itu", "dalam", "bisa", "bahwa", "atau", "hanya", "kita", "dengan", "akan", "juga",
                        "ada", "mereka", "sudah", "saya", "terhadap", "secara", "agar", "lain", "anda",
                        "begitu", "mengapa", "kenapa", "yaitu", "yakni", "daripada", "itulah", "lagi", "maka",
                        "tentang", "demi", "dimana", "kemana", "pula", "sambil", "sebelum", "sesudah", "supaya",
                        "guna", "kah", "pun", "sampai", "sedangkan", "selagi", "sementara", "tetapi", "apakah",
                        "kecuali", "sebab", "selain", "seolah", "seraya", "seterusnya", "tanpa", "agak", "boleh",
                        "dapat", "dsb", "dst", "dll", "dahulu", "dulunya", "anu", "demikian", "tapi", "ingin",
                        "juga", "nggak", "mari", "nanti", "melainkan", "oh", "ok", "seharusnya", "sebetulnya",
                        "setiap", "setidaknya", "sesuatu", "pasti", "saja", "toh", "ya", "walau", "tolong",
                        "tentu", "amat", "apalagi", "bagaimanapun",
                };
        return stopWords;
    }
}
