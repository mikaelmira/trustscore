package com.trustscore.trustscoreapi.domain.valueobjects;

public record Cpf(String value) {

    public Cpf(String value) {
        String normalized = value.replaceAll("\\D", "");
        this.value = normalized;
    }

    public boolean isValid() {
        String cpf = this.value;

        if (cpf.length() != 11) return false;
        if (cpf.matches("(\\d)\\1{10}")) return false;

        int soma = 0;
        for (int i = 0; i < 9; i++) {
            soma += (cpf.charAt(i) - '0') * (10 - i);
        }
        int primeiroDigito = 11 - (soma % 11);
        if (primeiroDigito >= 10) primeiroDigito = 0;

        soma = 0;
        for (int i = 0; i < 10; i++) {
            soma += (cpf.charAt(i) - '0') * (11 - i);
        }
        int segundoDigito = 11 - (soma % 11);
        if (segundoDigito >= 10) segundoDigito = 0;

        return primeiroDigito == (cpf.charAt(9) - '0') &&
                segundoDigito == (cpf.charAt(10) - '0');
    }
}