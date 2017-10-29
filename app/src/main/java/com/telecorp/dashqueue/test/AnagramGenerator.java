package com.telecorp.dashqueue.test;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Created by Saran on 15/10/2560.
 */

public class AnagramGenerator {
    private Map<String, Collection<String>> indexedDictionary;

    public AnagramGenerator(List<String> dictionary) {
        this.indexedDictionary = index(dictionary);
    }

    public Collection<String> getAnagrams(String word) {
        return indexedDictionary.get(sort(word));
    }


    private Map<String, Collection<String>> index(List<String> dictionary) {
        Multimap<String, String> indexedDictionary = HashMultimap.create();

        for (String word : dictionary) {
            indexedDictionary.put(sort(word), word);
        }

        return indexedDictionary.asMap();
    }

    private String sort(String word) {
        List<Character> sortedCharacters = new ArrayList<>();
        for (char c : word.toCharArray()) {
            sortedCharacters.add(c);
        }
        Collections.sort(sortedCharacters);

        StringBuilder builder = new StringBuilder();
        for (Character character : sortedCharacters) {
            builder.append(character);
        }

        return builder.toString();
    }
}
