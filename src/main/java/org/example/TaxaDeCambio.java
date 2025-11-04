package org.example;


public class TaxaDeCambio {

    private String base_code;
    private String target_code;
    private double conversion_rate; //


    public String getBaseCode() {
        return base_code;
    }

    public String getTargetCode() {
        return target_code;
    }

    public double getConversionRate() {
        return conversion_rate;
    }

    @Override
    public String toString() {
        return "Taxa de Câmbio [De: " + base_code + ", Para: " + target_code +
                ", Taxa: " + conversion_rate + "]";
    }
}