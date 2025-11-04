package org.example;

import java.time.LocalDateTime;

public class HistoricoRegistro {
    private String dePara;
    private double valorOriginal;
    private double valorConvertido;
    private LocalDateTime dataHora;

    public HistoricoRegistro(String dePara, double valorOriginal, double valorConvertido) {
        this.dePara = dePara;
        this.valorOriginal = valorOriginal;
        this.valorConvertido = valorConvertido;
        this.dataHora = LocalDateTime.now();
    }

    @Override
    public String toString() {

        return String.format("[%s] %s: %.2f -> %.2f",
                dataHora.toLocalTime().toString().substring(0, 8),
                dePara,
                valorOriginal,
                valorConvertido);
    }
}