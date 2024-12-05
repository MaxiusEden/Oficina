package modelo;

public class ItensPeca {
    private int idItensPeca;
    private int idPeca;
    private int idOs;
    private double valorUnitario;
    private double valorTotal;
    private int quantidade;

    public int getIdItensPeca() {
        return idItensPeca;
    }

    public void setIdItensPeca(int idItensPeca) {
        this.idItensPeca = idItensPeca;
    }

    public int getIdPeca() {
        return idPeca;
    }

    public void setIdPeca(int idPeca) {
        this.idPeca = idPeca;
    }

    public int getIdOs() {
        return idOs;
    }

    public void setIdOs(int idOs) {
        this.idOs = idOs;
    }

    public double getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(double valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
    
    
}
