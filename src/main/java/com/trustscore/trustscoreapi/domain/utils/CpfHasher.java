package com.trustscore.trustscoreapi.domain.utils;

import com.trustscore.trustscoreapi.domain.valueobjects.Cpf;

public interface CpfHasher {
    Cpf hash(String rawCpf);
    boolean verify(String cpf, String storedCpf);
}
