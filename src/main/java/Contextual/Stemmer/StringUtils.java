//This is free and unencumbered software released into the public domain.
//
//Anyone is free to copy, modify, publish, use, compile, sell, or
//distribute this software, either in source code form or as a compiled
//binary, for any purpose, commercial or non-commercial, and by any
//means.
//
//In jurisdictions that recognize copyright laws, the author or authors
//of this software dedicate any and all copyright interest in the
//software to the public domain. We make this dedication for the benefit
//of the public at large and to the detriment of our heirs and
//successors. We intend this dedication to be an overt act of
//relinquishment in perpetuity of all present and future rights to this
//software under copyright law.
//
//THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
//EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
//MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
//IN NO EVENT SHALL THE AUTHORS BE LIABLE FOR ANY CLAIM, DAMAGES OR
//OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE,
//ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
//OTHER DEALINGS IN THE SOFTWARE.
//
//For more information, please refer to <http://unlicense.org/>

package Contextual.Stemmer;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Sami on 18/11/14.
 */
public class StringUtils {

    public static String[] explode(String tokenizer, String text)
    {
        String[] split = text.split(tokenizer);
        split = text.split(tokenizer);
        return split;
    }

    public static String implode(String tokenizer, String[] split){

//        String[] split = text.split(tokenizer);
        StringBuilder sb = new StringBuilder();

        for(int i=0; i<split.length;i++)
        {
            sb.append(split[i]);
            if (i != split.length - 1)
                sb.append(tokenizer);
        }

        return sb.toString(); //return the imploded string
    }

    //params for preg_replace on php : { preg_replace(pattern, replacement, subject, [limit: int = -1], [&count : int|null = null]) }
    //Examples on usages : (String) preg_replace('/(lah|kah|tah|pun)$/', '', $word, 1);

    public static String preg_replace(String pattern, String replacement, String text, int limit){

        text = text.toLowerCase();
        //initialization of regex pattern to remove symbols & chars other than alphanumeric
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(text);
        text = m.replaceAll(replacement);

        return text;
    }

}
