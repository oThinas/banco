public abstract class Conta implements IConta {
    private static final int AGENCIA_PADRAO = 1;
    private static int SEQUENCIAL = 1;
    protected int agencia, numero;
    protected double saldo;
    protected Cliente cliente;

    public Conta(Cliente cliente) {
        this.agencia = Conta.AGENCIA_PADRAO;
        this.numero = SEQUENCIAL++;
        this.cliente = cliente;
    }

    @Override
    public void sacar(double valor) throws SaldoInsuficienteException{
        if (valor > this.saldo)
            throw new SaldoInsuficienteException();
        this.saldo -= valor;
    }

    @Override
    public void depositar(double valor) {
        if (valor <= 0) 
            throw new ValorInvalidoException();
        this.saldo += valor;
    }

    @Override
    public void transferir(double valor, IConta contaDestino) throws SaldoInsuficienteException{
        this.sacar(valor);
        contaDestino.depositar(valor);
    }

    public int getAgencia() {
        return agencia;
    }

    public int getNumero() {
        return numero;
    }

    public double getSaldo() {
        return saldo;
    }

    protected void imprimirInfosComuns() {
        System.out.println(String.format("Títular: %s", this.cliente.getNome()));
        System.out.println(String.format("Agència: %d", this.agencia));
        System.out.println(String.format("Número: %d", this.numero));
        System.out.println(String.format("Saldo: %.2f", this.saldo));
    }
}
