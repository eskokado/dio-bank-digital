package com.dio.cliente;

import com.dio.validation.ValidationHandler;
import com.dio.validation.Validator;
import com.dio.validation.Error;

public class ClienteValidator extends Validator {
    public static final int NOME_MAX_LENGTH = 255;
    public static final int NOME_MIN_LENGTH = 5;
    private final Cliente cliente;

    public ClienteValidator(final Cliente cliente, final ValidationHandler handler) {
        super(handler);
        this.cliente = cliente;
    }

    @Override
    public void validate() {
        checkNomeConstraints();
    }

    private void checkNomeConstraints() {
        var nome = this.cliente.getNome();
        if (nome == null) {
            this.validationHandler().append(new Error("'nome' não pode ser nulo"));
            return;
        }
        if (nome.isBlank()) {
            this.validationHandler().append(new Error("'nome' não pode ser vazio"));
            return;
            
        }
        final int length = nome.trim().length();
        if (length > NOME_MAX_LENGTH || length < NOME_MIN_LENGTH) {
            this.validationHandler().append(new Error("'nome' deve ser entre 5 and 255 characters"));
        }
    }
}
