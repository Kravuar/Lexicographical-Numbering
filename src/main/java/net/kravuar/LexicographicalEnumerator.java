package net.kravuar;

import lombok.NonNull;

import java.util.List;

public interface LexicographicalEnumerator {
    int enumerate(List<@NonNull Character> alphabet, String word);
    String denumerate(List<@NonNull Character> alphabet, int N);
}
