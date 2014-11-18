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
 * Remove Inflectional particle.
 * <p/>
 * Asian J. (2007) “Effective Techniques for Indonesian Text Retrieval”. page 60
 *
 * @link http://researchbank.rmit.edu.au/eserv/rmit:6312/Asian.pdf
 */
public class RemoveInflectionalParticle implements VisitorInterface {
    public void visit(ContextInterface context) {
        String result = this.remove(context.getCurrentWord());
        if (!result.equals(context.getCurrentWord())) {
            String removedPart = context.getCurrentWord().replaceAll("/" + result + "/", "");
            Removal removal = new Removal(this,
                    context.getCurrentWord(),
                    result,
                    removedPart,
                    "P"
            );
            context.addRemoval(removal);
            context.setCurrentWord(result);
            //will return after finishing up Context class
        }
    }

    public String remove(String word) //to be translated
    {
        return word.replaceAll("/(lah|kah|tah|pun)$/", " ");
//        return StringUtils.preg_replace("", " ", word, 1);
    }
}
