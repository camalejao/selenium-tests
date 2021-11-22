package br.jao.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateUtils {
    
    public static LocalDate obterDataNoFuturo(int dias) {
        LocalDate data = LocalDate.now();
        data = data.plusDays(dias);
        return data;
    }

    public static String obterDataFormatada(LocalDate data) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return data.format(formatter);
    }

}
