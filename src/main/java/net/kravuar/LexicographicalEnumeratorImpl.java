package net.kravuar;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.Lists;
import lombok.NonNull;

import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class LexicographicalEnumeratorImpl implements LexicographicalEnumerator {
    @Override
    public int enumerate(List<@NonNull Character> alphabet, @NonNull String word) {
        if (alphabet.isEmpty())
            return -1;

        final int n = alphabet.size();
        final int k = word.length();
        final var mapping = getMappings(alphabet);


        var N = 0;
        var sb = new StringBuilder(word + "= ");
        for (int i = 0; i < k; ++i) {
            sb.append(String.format("%d^%d * %d + ", n, k - 1 - i, mapping.get(word.charAt(i))));
            N += Math.pow(n, k - 1 - i) * mapping.get(word.charAt(i));
        }
        if (sb.length() > 0)
            sb.delete(sb.length() - 2, sb.length());
        sb.append(String.format(" = %d", N));
        System.out.println(sb);
        return N;
//      Without output
//        return IntStream.range(0, k)
//                .mapToObj(i -> Pair.of(word.charAt(i), i))
//                .reduce(0,
//                        (acc, pair) -> (acc + (int) Math.pow(n, k - 1 - pair.second()) * mapping.get(pair.first())),
//                        Integer::sum);
    }

    @Override
    public String denumerate(List<@NonNull Character> alphabet, int N) {
        if (N <= 0)
            return "";

        final int n = alphabet.size();
        final Queue<Integer> r = new LinkedList<>();
        final var k = new Stack<Integer>();
        final var format = " * " + n + " + %d)";

        k.push(N);
        int ki;
        System.out.print(N);
        while ((ki = k.peek()) > n) {
            System.out.println(" = ");
            var mod = ki % n;
            if (mod != 0) {
                r.add(mod);
                k.push(ki / n);
            } else {
                r.add(n);
                k.push(ki / n - 1);
            }
            System.out.format("%s%d","(".repeat(r.size()), k.peek());
            for (var ri: Lists.reverse(r.stream().toList()))
                System.out.format(format, ri);
        }
        var mappings = getMappings(alphabet).inverse();
        String s = Stream.concat(r.stream(), Stream.of(k.peek()))
                .reduce(new StringBuilder(),
                        (word, ch) -> word.append(mappings.get(ch)),
                        StringBuilder::append)
                .reverse().toString();
        System.out.printf("\n%d = %s", N, s);
        return s;
    }
    private static BiMap<Character, Integer> getMappings(List<@NonNull Character> alphabet) {
        return IntStream.range(0, alphabet.size())
                .mapToObj(i -> Pair.of(alphabet.get(i), i + 1))
                .collect(
                        HashBiMap::create,
                        (bm, p) -> bm.put(p.first(), p.second()),
                        BiMap::putAll
                );
    }
}