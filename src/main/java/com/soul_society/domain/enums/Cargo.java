package com.soul_society.domain.enums;

public enum Cargo {
    CAPITAO_COMANDANTE("Capitão-Comandante", 1),
    CAPITAO("Capitão", 2),
    TENENTE("Tenente", 3),
    TERCEIRO_ASSENTO("3º Assento", 4),
    QUARTO_ASSENTO("4º Assento", 5),
    QUINTO_ASSENTO("5º Assento", 6),
    SEXTO_ASSENTO("6º Assento", 7),
    SETIMO_ASSENTO("7º Assento", 8),
    OITAVO_ASSENTO("8º Assento", 9),
    NONO_ASSENTO("9º Assento", 10),
    DECIMO_ASSENTO("10º Assento", 11),
    DECIMO_PRIMEIRO_ASSENTO("11º Assento", 12),
    DECIMO_SEGUNDO_ASSENTO("12º Assento", 13),
    DECIMO_TERCEIRO_ASSENTO("13º Assento", 14),
    DECIMO_QUARTO_ASSENTO("14º Assento", 15),
    DECIMO_QUINTO_ASSENTO("15º Assento", 16),
    DECIMO_SEXTO_ASSENTO("16º Assento", 17),
    DECIMO_SETIMO_ASSENTO("17º Assento", 18),
    DECIMO_OITAVO_ASSENTO("18º Assento", 19),
    DECIMO_NONO_ASSENTO("19º Assento", 20),
    VIGESIMO_ASSENTO("20º Assento", 21),
    SHINIGAMI_COMUM("Shinigami Comum", 22);

    private final String descricao;
    private final int hierarquia;

    Cargo(String descricao, int hierarquia) {
        this.descricao = descricao;
        this.hierarquia = hierarquia;
    }

    public boolean isMaisAltoQue(Cargo outroCargo) {
        return this.hierarquia < outroCargo.hierarquia;
    }

    /**
     * Verifica se o cargo é único (só pode ter uma pessoa ocupando)
     * SHINIGAMI_COMUM é o único cargo que pode ter múltiplas pessoas
     */
    public boolean isCargoUnico() {
        return this != SHINIGAMI_COMUM;
    }

    /**
     * Verifica se o cargo é único globalmente (em toda Soul Society)
     * Apenas o Capitão-Comandante tem essa restrição
     */
    public boolean isCargoUnicoGlobal() {
        return this == CAPITAO_COMANDANTE;
    }

    public String getDescricao() {
        return descricao;
    }

    public int getHierarquia() {
        return hierarquia;
    }
}
