package com.dio;

public abstract class Conta implements IConta {
    private static int AGENCIA_PADRAO = 1;
    private static int SEQUENCIAL = 1;
    protected Banco banco;
    protected int agencia;
    protected int numero;
    protected double saldo;
    protected double limite;
    protected Cliente cliente;
    public Conta(Banco banco, Cliente cliente) {
        this.agencia = AGENCIA_PADRAO;
        this.numero = SEQUENCIAL++;
        this.banco = banco;
        this.cliente = cliente;
        this.limite = 25;
    }
    @Override
    public void sacar(double valor) {
        if (!validadoLimite(valor)) return;
        if (!validadoSaldo(valor)) return;
        saldo -= valor;
    }

    public boolean validadoLimite(double valor) {
        String notification = "";
        if (valor > limite) {
            notification = String.format("O limite da conta é %.2f", limite);
            System.out.println(notification);
            return false;
        }
        return true;
    }

    public boolean validadoSaldo(double valor) {
        String notification = "";
        if ((saldo - valor) < 0) {
            notification = "Saldo insuficiente";
            System.out.println(notification);
            return false;
        }
        return true;
    }

    @Override
    public void depositar(double valor) {
        saldo += valor;
    }

    @Override
    public void transferir(IConta contaDestino, double valor) {
        this.sacar(valor);
        contaDestino.depositar(valor);
    }



    protected void ImprimirInfoComuns() {
        System.out.println(String.format("Cliente: %s", this.cliente.getNome()));
        System.out.println(String.format("Banco: %s - %s", this.banco.getNumero(), this.banco.getNome()));
        System.out.println(String.format("Agencia: %d", this.agencia));
        System.out.println(String.format("Numero: %d", this.numero));
        System.out.println(String.format("Saldo: %.2f", this.saldo));
    }
}
