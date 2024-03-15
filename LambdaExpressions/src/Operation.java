@FunctionalInterface
public interface Operation<T> {

    T operate(T... value1);
}
