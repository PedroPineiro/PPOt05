package com.pedro.ej05_02;

import org.springframework.stereotype.Service;

@Service
public class MainService {
    
    public String contarDiasEntreFechas(DateForm dateForm) {
        long dias = java.time.temporal.ChronoUnit.DAYS.between(dateForm.getDate1(), dateForm.getDate2());
        return "Días entre fechas: " + Math.abs(dias);
    }

    public String listarAnosBisiestos(DateForm dateForm) {
        StringBuilder sb = new StringBuilder("Años bisiestos entre " + dateForm.getDate1().getYear() + " y " + dateForm.getDate2().getYear() + ": ");
        for (int year = Math.min(dateForm.getDate1().getYear(), dateForm.getDate2().getYear());
             year <= Math.max(dateForm.getDate1().getYear(), dateForm.getDate2().getYear());
             year++) {
            if (java.time.Year.isLeap(year)) {
                sb.append(year).append(" ");
            }
        }
        return sb.toString();
    }

    public String contarEneroDomingo(DateForm dateForm) {
        int count = 0;
        for (int year = Math.min(dateForm.getDate1().getYear(), dateForm.getDate2().getYear());
             year <= Math.max(dateForm.getDate1().getYear(), dateForm.getDate2().getYear());
             year++) {
            java.time.LocalDate firstJanuary = java.time.LocalDate.of(year, 1, 1);
            if (firstJanuary.getDayOfWeek() == java.time.DayOfWeek.SUNDAY) {
                count++;
            }
        }
        return "Número de veces que el 1 de enero fue domingo: " + count;
    }
}
