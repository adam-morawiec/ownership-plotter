package com.glovoapp.ownership.shared;

import static java.util.stream.Collectors.toMap;
import static lombok.AccessLevel.PRIVATE;

import java.util.Map;
import java.util.Map.Entry;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Stream;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = PRIVATE)
public final class Maps {

    public static <Key, Value> Map<Key, Value> union(final Map<Key, Value> first,
                                                     final Map<Key, Value> second,
                                                     final BinaryOperator<Value> mergeFunction) {
        return Stream.concat(
            first.entrySet()
                 .stream(),
            second.entrySet()
                  .stream()
        )
                     .collect(toMap(Entry::getKey, Entry::getValue, mergeFunction));
    }

    public static <Key, OldValue, NewValue> Map<Key, NewValue> transformValues(
        final Map<Key, OldValue> oldMap,
        final Function<OldValue, NewValue> transformer
    ) {
        return oldMap.entrySet()
                     .stream()
                     .collect(toMap(Entry::getKey, entry -> transformer.apply(entry.getValue())));
    }

}
