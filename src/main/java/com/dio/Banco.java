package com.dio;

import java.util.List;

public class Banco {
    private int numero;
    private String nome;

    public Banco(int numero, String nome) {
        this.numero = numero;
        this.nome = nome;
    }
    public int getNumero() {
        return numero;
    }

    public String getNome() {
        return nome;
    }
}
