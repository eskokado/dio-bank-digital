package com.dio;

import com.dio.banco.Banco;

public class ContaCorrente extends Conta {

    public ContaCorrente(Banco banco, Cliente cliente) {
        super(banco, cliente);
    }

    @Override
    public void imprimirExtrato() {
        System.out.println("*** Extrato conta corrente ***");
        super.ImprimirInfoComuns();
    }
}
