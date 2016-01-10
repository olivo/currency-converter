package com.oswaldo.currencyconverter;

/**
 * Created by Oswaldo on 1/9/2016.
 */
public class ConversionPair {

    private String sourceCurrency;
    private String targetCurrency;

    public ConversionPair(String sourceCurrency, String targetCurrency) {

        this.sourceCurrency = sourceCurrency;
        this.targetCurrency = targetCurrency;
    }

    public String getSourceCurrency() {

        return sourceCurrency;
    }

    public String getTargetCurrency() {

        return targetCurrency;
    }

    @Override
    public boolean equals(Object o) {

        if (!(o instanceof ConversionPair)) {

            return false;
        }

        ConversionPair cp = (ConversionPair)o;
        return cp.getSourceCurrency().equals(getSourceCurrency()) &&
                cp.getTargetCurrency().equals(getTargetCurrency());
    }

    @Override
    public int hashCode() {

        return getSourceCurrency().hashCode() + 31 * getTargetCurrency().hashCode();
    }
}
