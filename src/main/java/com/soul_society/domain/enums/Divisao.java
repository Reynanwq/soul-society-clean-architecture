package com.soul_society.domain.enums;

public enum Divisao {
    PRIMEIRA(1, "1ª Divisão"),
    SEGUNDA(2, "2ª Divisão"),
    TERCEIRA(3, "3ª Divisão"),
    QUARTA(4, "4ª Divisão"),
    QUINTA(5, "5ª Divisão"),
    SEXTA(6, "6ª Divisão"),
    SETIMA(7, "7ª Divisão"),
    OITAVA(8, "8ª Divisão"),
    NONA(9, "9ª Divisão"),
    DECIMA(10, "10ª Divisão"),
    DECIMA_PRIMEIRA(11, "11ª Divisão"),
    DECIMA_SEGUNDA(12, "12ª Divisão"),
    DECIMA_TERCEIRA(13, "13ª Divisão");

    private final int numero;
    private final String descricao;

    Divisao(int numero, String descricao) {
        this.numero = numero;
        this.descricao = descricao;
    }

    public static Divisao fromNumero(int numero) {
        for (Divisao divisao : values()) {
            if (divisao.numero == numero) {
                return divisao;
            }
        }
        throw new IllegalArgumentException("Divisão inválida: " + numero);
    }

    public int getNumero() {
        return numero;
    }

    public String getDescricao() {
        return descricao;
    }
}
