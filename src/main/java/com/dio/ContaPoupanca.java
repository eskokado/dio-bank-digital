package com.dio;

import com.dio.banco.Banco;

public class ContaPoupanca extends Conta{
    public ContaPoupanca(Banco banco, Cliente cliente) {
        super(banco, cliente);
    }

    @Override
    public void imprimirExtrato() {
        System.out.println("*** Extrato conta poupanca ***");
        super.ImprimirInfoComuns();
    }

}
