public class ExchangeRateValue {
    private double rate;

    public double getRate() {
        return rate;
    }

    public ExchangeRateValue(ExchangeRate exchangeRate) {
        this.rate = exchangeRate.conversion_rate();
    }
}
