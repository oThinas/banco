public interface IConta {
    public void sacar(double valor) throws SaldoInsuficienteException;
    public void depositar(double valor);
    public void transferir(double valor, IConta contaDestino) throws SaldoInsuficienteException;
    public void imprimirExtrato();
}
