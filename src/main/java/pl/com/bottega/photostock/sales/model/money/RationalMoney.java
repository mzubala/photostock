package pl.com.bottega.photostock.sales.model.money;

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

    public int compareTo(Money other) {
        RationalMoney rationalMoney = other.convertToRational();
        if (!rationalMoney.currency.equals(currency))
            throw new IllegalArgumentException("Currency missmatch");
        return value.compareTo(rationalMoney.value);
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
    public RationalMoney convertToRational() {
        return this;
    }

    @Override
    public IntegerMoney convertToInteger() {
        long cents = value.getNumerator() * 100 / value.getDenominator();
        return new IntegerMoney(cents, currency);
    }

    public boolean equals(Object other) {
        if (other == null || !(other instanceof Money))
            return false;
        RationalMoney rationalMoney;
        if (other instanceof IntegerMoney)
            rationalMoney = ((IntegerMoney) other).convertToRational();
        else
            rationalMoney = (RationalMoney) other;
        return rationalMoney.value.equals(value) && rationalMoney.currency == currency;
    }

    @Override
    public int hashCode() {
        int result = value.hashCode();
        result = 31 * result + currency.hashCode();
        return result;
    }

}