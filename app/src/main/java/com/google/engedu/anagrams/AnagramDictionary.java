/* Copyright 2016 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.engedu.anagrams;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

public class AnagramDictionary {

    private static final int MIN_NUM_ANAGRAMS = 5;
    private static final int DEFAULT_WORD_LENGTH = 3;
    private static final int MAX_WORD_LENGTH = 7;
    private Random random = new Random();
    private ArrayList<String> wordList = new ArrayList<String>();
    private HashMap<String,ArrayList> lettersToWord = new HashMap<String, ArrayList>();
    private HashSet<String> wordSet = new HashSet<String>();

    public AnagramDictionary(Reader reader) throws IOException {
        BufferedReader in = new BufferedReader(reader);
        String line;
        while((line = in.readLine()) != null) {
            String word = line.trim();
            wordList.add(word);
            wordSet.add(word);
            String sortedWord = sortLetters(word);
            if (lettersToWord.containsKey(sortedWord)){
                lettersToWord.get(sortedWord).add(word);
            }else{
                lettersToWord.put(sortedWord, new ArrayList());
            }


        }
    }

    public boolean isGoodWord(String word, String base) {
        if (wordSet.contains(word) && !word.contains(base)){
            System.out.println("Word doesn't contains base: " + !word.contains(base));
            System.out.println("Wordset contains base: " + wordSet.contains(word));
            System.out.println(lettersToWord.get(sortLetters(word)).toString());
            return true;
        }else{
            System.out.println("Word is false");
            return false;
        }
    }

    public List<String> getAnagrams(String targetWord) {
        String sortedLetters = sortLetters(targetWord);
        System.out.println(sortedLetters);
        ArrayList<String> result = new ArrayList<String>();
        result = lettersToWord.get(sortedLetters);
        System.out.println(result.toString());
//        for (String word:lettersToWord.keySet()) {
//            System.out.println(word);
//            if (sortedLetters == word) {
//
//                return lettersToWord.get(sortedLetters);
//            }
//        }
//        System.out.println(result);
        return result;
    }

    public List<String> getAnagramsWithOneMoreLetter(String word) {
        String alphabets = "abcdefghijklmnopqrstuvwxyz";
        ArrayList<String> result = new ArrayList<String>();
        char[] chars = alphabets.toCharArray();
        for (char x:chars) {
            String sortedLetters = sortLetters(word + Character.toString(x));
            if (lettersToWord.containsKey(sortedLetters)){
                result.addAll(lettersToWord.get(sortedLetters));
            }
        }

//        for (String word:lettersToWord.keySet()) {
//            System.out.println(word);
//            if (sortedLetters == word) {
//
//                return lettersToWord.get(sortedLetters);
//            }
//        }
//        System.out.println(result);
        System.out.println(result.toString());
        return result;
    }

    public String pickGoodStarterWord() {
        ArrayList<String> result = new ArrayList<>();
        for (String word:wordList) {
            if (lettersToWord.get(sortLetters(word)).size() >= MIN_NUM_ANAGRAMS){
                result.add(word);
            }

        }

        Random randomint = new Random();
        return result.get(randomint.nextInt(result.size()));
    }

    public String sortLetters(String word){
        char[] chars = word.toCharArray();
        Arrays.sort(chars);
        String sorted = new String(chars);
        return sorted;
    }
}
