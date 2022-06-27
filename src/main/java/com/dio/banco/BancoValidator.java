package com.dio.banco;

import com.dio.validation.ValidationHandler;
import com.dio.validation.Validator;
import com.dio.validation.Error;

public class BancoValidator extends Validator {
    public static final int NUMERO_MAX_LENGTH = 20;
    public static final int NUMERO_MIN_LENGTH = 8;
    public static final int NOME_MAX_LENGTH = 255;
    public static final int NOME_MIN_LENGTH = 3;
    private final Banco banco;

    public BancoValidator(final Banco banco, final ValidationHandler handler) {
        super(handler);
        this.banco = banco;
    }

    @Override
    public void validate() {
        checkNumeroConstraints();
        checkNomeConstraints();
    }

    private void checkNomeConstraints() {
        var nome = this.banco.getNome();
        if (nome == null) {
            this.validationHandler().append(new Error("'nome' should not be null"));
            return;
        }
        if (nome.isBlank()) {
            this.validationHandler().append(new Error("'nome' should not be empty"));
            return;
            
        }
        final int length = nome.trim().length();
        if (length > NOME_MAX_LENGTH || length < NOME_MIN_LENGTH) {
            this.validationHandler().append(new Error("'nome' deve ser entre 3 and 255 characters"));
        }
    }
    private void checkNumeroConstraints() {
        var numero = this.banco.getNumero();
        if (numero == null) {
            this.validationHandler().append(new Error("'numero' should not be null"));
            return;
        }
        if (numero.isBlank()) {
            this.validationHandler().append(new Error("'numero' should not be empty"));
            return;
        }
        final int length = numero.trim().length();
        if (length > NUMERO_MAX_LENGTH || length < NUMERO_MIN_LENGTH) {
            this.validationHandler().append(new Error("'numero' deve ser entre 8 and 20 characters"));
        }
    }
}
