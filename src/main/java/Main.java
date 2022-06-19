import com.dio.Conta;
import com.dio.ContaCorrente;
import com.dio.ContaPoupanca;

public class Main {
    public static void main(String[] args) {
        Conta cc = new ContaCorrente();
        Conta cp = new ContaPoupanca();

        cc.depositar(100);
        cc.transferir(cp, 50);
        cp.sacar(25);

        cc.imprimirExtrato();

        cp.imprimirExtrato();
    }
}
