package com.rnaudiotranscoder;

/**
 * React native doesn't seem to play nicely with the JDK 8 Optional class.
 * This class represents the mvp of the Optional interface that we need.
 * @param <T>
 */
public final class Optional<T> {
    /**
     * Whether or not this Optional contains a value
     */
    public final boolean exists;
    /**
     * The value represented by this optional
     * INVARIANT: If this#exists is true, this parameter will never be null
     */
    public final T value;

    /**
     * Creates an empty optional
     */
    private Optional() {
        exists = false;
        value = null;
    }

    /**
     * Creates an existant optional
     * @param value The value this optional represents
     */
    private Optional(T value) {
        exists = true;
        this.value = value;
    }

    /**
     * Create a new optional value with the given existant value.
     * Will throw a null pointer exception if the value is null.
     * @param existant A value that exists
     * @param <T> The type that this optional represents
     * @return An optional of the type passed in as the existant parameter
     */
    public static <T>Optional<T> of(T existant) {
        if (existant == null) throw new NullPointerException("Cannot construct existant optional of null");
        return new Optional<>(existant);
    }

    /**
     * Create an optional representing a non existant value
     * @return An empty optional
     */
    public static <T>Optional<T> empty() {
        return new Optional<>();
    }
}
