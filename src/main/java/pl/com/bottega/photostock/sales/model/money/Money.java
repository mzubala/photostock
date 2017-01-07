package pl.com.bottega.photostock.sales.model.money;

public interface Money extends Comparable<Money> {

    public enum Currency {CREDIT;}

    Currency DEFAULT_CURRENCY = Currency.CREDIT;

    Money ZERO = valueOf(0, DEFAULT_CURRENCY);

    Money add(Money add);

    Money subtract(Money subtrahend);

    Money multiply(long factor);

    default boolean gte(Money other) {
        return compareTo(other) >= 0;
    }

    default boolean gt(Money other) {
        return compareTo(other) > 0;
    }

    default boolean lte(Money other) {
        return compareTo(other) <= 0;
    }

    default boolean lt(Money other) {
        return compareTo(other) < 0;
    }

    Money opposite();

    RationalMoney convertToRational();

    IntegerMoney convertToInteger();

    static Money valueOf(long value, Currency currency) {
        return new IntegerMoney(value * 100L, currency);
    }

    static Money valueOf(long value) {
        return new IntegerMoney(value * 100l, DEFAULT_CURRENCY);
    }

    static Money valueOf(Rational value, Currency currency) {
        return new RationalMoney(value, currency).convertToInteger();
    }

    static Money valueOf(float value) {
        return new IntegerMoney((long) (value * 100.0), DEFAULT_CURRENCY);
    }

}
