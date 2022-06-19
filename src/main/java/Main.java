import com.dio.*;

public class Main {
    public static void main(String[] args) {
        Banco bccx = new Banco(123, "Caixa");
        Banco bcgn = new Banco(456, "generico");
        Cliente cliente = new Cliente();
        cliente.setNome("Cliente 1");
        Conta cc = new ContaCorrente(bccx, cliente);
        Conta cp = new ContaPoupanca(bcgn, cliente);

        System.out.println("***** Depositando ************");
        cc.depositar(100);
        System.out.println("***** Transferindo ************");
        cc.transferir(cp, 50);
        System.out.println("***** Sacando ************");
        cc.sacar(500);
        System.out.println("***** Sacando ************");
        cp.sacar(25);

        cc.imprimirExtrato();

        cp.imprimirExtrato();
    }
}
