package com.dio;

public abstract class Conta implements IConta {
    private int agencia;
    private int numero;
    private double saldo;

    @Override
    public void sacar(double valor) {

    }

    @Override
    public void depositar(double valor) {

    }

    @Override
    public void transferir(Conta contaDestino, double valor) {

    }
}
