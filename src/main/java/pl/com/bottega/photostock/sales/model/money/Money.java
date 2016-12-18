package pl.com.bottega.photostock.sales.model.money;

import pl.com.bottega.photostock.sales.model.Rational;

public interface Money extends Comparable<Money> {

    public enum Currency {CREDIT;}

    Currency DEFAULT_CURRENCY = Currency.CREDIT;

    Money ZERO = valueOf(0, DEFAULT_CURRENCY);

    Money add(Money add);

    Money subtract(Money subtrahend);

    Money multiply(long factor);

    boolean gte(Money other);

    boolean lte(Money other);

    boolean gt(Money other);

    boolean lt(Money other);

    Money opposite();

    RationalMoney convertToRational();

    static Money valueOf(long value, Currency currency) {
        return new RationalMoney(Rational.valueOf(value), currency);
    }

    static Money valueOf(long value) {
        return new RationalMoney(Rational.valueOf(value), DEFAULT_CURRENCY);
    }

    static Money valueOf(Rational value, Currency currency) {
        return new RationalMoney(value, currency);
    }

}
