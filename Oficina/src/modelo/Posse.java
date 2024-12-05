package modelo;

import java.util.Date;

public class Posse {
    private int idPosse;
    private String placaVeiculo;
    private int idCliente;
    private Date dataAquisicao;
    private Date dataVenda;

    public int getIdPosse() {
        return idPosse;
    }

    public void setIdPosse(int idPosse) {
        this.idPosse = idPosse;
    }

    public String getPlacaVeiculo() {
        return placaVeiculo;
    }

    public void setPlacaVeiculo(String placaVeiculo) {
        this.placaVeiculo = placaVeiculo;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public Date getDataAquisicao() {
        return dataAquisicao;
    }

    public void setDataAquisicao(Date dataAquisicao) {
        this.dataAquisicao = dataAquisicao;
    }

    public Date getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(Date dataVenda) {
        this.dataVenda = dataVenda;
    }

    
}
