package pl.com.bottega.photostock.sales.model.money;

import pl.com.bottega.photostock.sales.model.Rational;

class RationalMoney implements Money {

    public Money opposite() {
        return new RationalMoney(value.negative(), currency);
    }

    public Money add(Money addend) {
        RationalMoney rationalMoney = addend.convertToRational();
        if (currency != rationalMoney.currency)
            throw new IllegalArgumentException("The currencies do not match.");

        return new RationalMoney(value.add(rationalMoney.value), currency);
    }

    public Money subtract(Money subtrahend) {
        RationalMoney rationalMoney = subtrahend.convertToRational();
        if (currency != rationalMoney.currency)
            throw new IllegalArgumentException("The currencies do not match.");

        return new RationalMoney(value.subtract(rationalMoney.value), currency);
    }

    public Money multiply(long factor) {
        if (factor < 0)
            throw new IllegalArgumentException("Money cannot be negative");

        return new RationalMoney(value.multiply(factor), currency);
    }

    public boolean gte(Money other) {
        return compareTo(other) >= 0;
    }

    public boolean gt(Money other) {
        return compareTo(other) > 0;
    }

    public boolean lte(Money other) {
        return compareTo(other) <= 0;
    }

    public int compareTo(Money other) {
        RationalMoney rationalMoney = other.convertToRational();
        if (!rationalMoney.currency.equals(currency))
            throw new IllegalArgumentException("Currency missmatch");
        return value.compareTo(rationalMoney.value);
    }

    public boolean lt(Money other) {
        return compareTo(other) < 0;
    }

    private final Rational value;
    private final Currency currency;

    RationalMoney(Rational value, Currency currency) {
        this.value = value;
        this.currency = currency;
    }

    @Override
    public String toString() {
        return value.toDouble() + " " + currency.name();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RationalMoney)) return false;

        RationalMoney money = (RationalMoney) o;

        if (!value.equals(money.value)) return false;
        return currency == money.currency;
    }

    @Override
    public int hashCode() {
        int result = value.hashCode();
        result = 31 * result + currency.hashCode();
        return result;
    }

    @Override
    public RationalMoney convertToRational() {
        return this;
    }
}