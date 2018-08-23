package com.sample.calculator.parser.util;

/**
 * The Pair class is an immutable object which represents a name-value pair.
 */
public class Pair<K, V> {
    /**
     * The key of this pair.
     */
    private K key;

    /**
     * The value of this pair.
     */
    private V value;

    /**
     * Initialize a new Pair.
     *
     * @param key The key of this pair.
     * @param value The value of this pair.
     */
    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    /**
     * Returns the key of this pair.
     *
     * @return The key of this pair.
     */
    public K getKey() {
        return key;
    }

    /**
     * Returns the value of this pair.
     *
     * @return The value of this pair.
     */
    public V getValue() {
        return value;
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + getKey().hashCode();
        result = 32 * result + getValue().hashCode();

        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Pair)) {
            return false;
        }

        Pair pair = (Pair) o;

        return (getKey().equals(pair.getKey()) &&
                getValue().equals(pair.getValue()));
    }
}
