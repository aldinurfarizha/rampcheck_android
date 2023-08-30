package com.riyan.rampcheck.Util;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;

public class FormatRupiah {

    Locale localeID = new Locale("in", "ID");
    NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);

    public static String convertRupiah(Double angka){
        DecimalFormat fmt = (DecimalFormat) NumberFormat.getInstance();
        Locale locale = Locale.getDefault();
        String symbol = Currency.getInstance(locale).getSymbol(locale);
        fmt.setGroupingUsed(true);
        fmt.setPositivePrefix(symbol + " ");
        fmt.setNegativePrefix("-" + symbol + " ");
        fmt.setMinimumFractionDigits(0);
        fmt.setMaximumFractionDigits(0);
        return fmt.format(angka);
    }
    public static String convertAngka(Double angka){
        DecimalFormat fmt = (DecimalFormat) NumberFormat.getInstance();
        String symbol = "";
        fmt.setGroupingUsed(true);
        fmt.setPositivePrefix(symbol + "");
        fmt.setNegativePrefix("-" + symbol + " ");
        fmt.setMinimumFractionDigits(0);
        fmt.setMaximumFractionDigits(0);
        return fmt.format(angka);
    }
}

