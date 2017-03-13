package no.mnemonic.commons.utilities.lambda;

import java.util.stream.Collector;

/**
 * Simplified stream interface accepting functions and consumers with checked exception signature.
 */
public interface TryStream<T, E extends Exception> {

  <R> TryStream<R, E> map(ExceptionalFunction<? super T, ? extends R, E> mapper);

  TryStream<T, E> filter(ExceptionalPredicate<? super T, E> predicate);

  <R, A> R collect(Collector<? super T, A, R> collector) throws E;

  void forEach(ExceptionalConsumer<? super T, E> consumer) throws E;

}
