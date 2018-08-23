package com.sample.calculator.parser.util;


import java.util.*;
import java.util.function.Predicate;

/**
 * The BracketBalancer maintains a collection of bracket pairs.
 */
public class BracketBalancer {
    /**
     * A mapping of bracket pairs.
     */
    private Map<String, Pair<String, String>> pairs;

    /**
     * A stack containing unbalanced bracket pairs.
     */
    private Deque<Pair<String, String>> stack = new ArrayDeque<>();

    /**
     * Initialize a new BracketBalancer.
     *
     * @param pairs A collection of bracket pairs.
     */
    public BracketBalancer(Collection<Pair<String, String>> pairs) {
        this.pairs = createMapWithPairs(pairs);
    }

    /**
     * Consumes the specified bracket.
     *
     * @param bracket The bracket to consume.
     * @throws IllegalArgumentException If the specified bracket could not be consumed.
     */
    public void consume(String bracket) {
        if (pairs.containsKey(bracket)) {
            push(pairs.get(bracket));
            return;
        }

        if (pop(pair -> pair.getValue().equals(bracket))) {
            return;
        }

        throw new IllegalArgumentException(String.format("Malformed expression: cannot consume bracket ('%s')", bracket));
    }

    /**
     * Remove all bracket pairs from this balancer.
     */
    @SuppressWarnings("unused")
    public void clear() {
        stack.clear();
    }

    /**
     * Returns true if this balancer contains no bracket pairs.
     *
     * @return True if this balancer is empty.
     */
    public boolean isBalanced()
    {
        return stack.isEmpty();
    }

    /**
     * Pushes the specified pair onto the top of the stack.
     *
     * @param pair The pair to push onto the top of the stack.
     */
    private void push(Pair<String, String> pair) {
        stack.push(pair);
    }

    /**
     * Removes the pair at the top of the stack only if the predicate is satisfied.
     *
     * @param predicate The predicate that determines if the pair will be removed.
     * @return True if the pair at the top of the stack was removed.
     */
    private boolean pop(Predicate<Pair<String, String>> predicate) {
        boolean matches = false;

        if (!stack.isEmpty()) {
            matches = predicate.test(stack.peek());

            if (matches) {
                stack.pop();
            }
        }

        return matches;
    }

    /**
     * Populate the underlying map with bracket pairs.
     *
     * @param pairs A collection of bracket pairs.
     */
    private Map<String, Pair<String, String>> createMapWithPairs(Collection<Pair<String, String>> pairs) {
        Map<String, Pair<String, String>> map = new HashMap<>();

        for (Pair<String, String> pair : pairs) {
            map.put(pair.getKey(), pair);
        }

        return map;
    }
}
