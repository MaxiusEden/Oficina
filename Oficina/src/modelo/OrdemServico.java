package modelo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;


public class OrdemServico {
    private int idOs;
    private String placaVeiculo;
    private double valorTotal;
    private LocalDate dataEntrada;
    private LocalTime horaInicio;
    private LocalDateTime previsaoEntrega;
    private String status;
    private double valorPago;
    private double diferenca;

    public int getIdOs() {
        return idOs;
    }

    public void setIdOs(int idOs) {
        this.idOs = idOs;
    }

    public String getPlacaVeiculo() {
        return placaVeiculo;
    }

    public void setPlacaVeiculo(String placaVeiculo) {
        this.placaVeiculo = placaVeiculo;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public LocalDate getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(LocalDate dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(LocalTime horaInicio) {
        this.horaInicio = horaInicio;
    }

    public LocalDateTime getPrevisaoEntrega() {
        return previsaoEntrega;
    }

    public void setPrevisaoEntrega(LocalDateTime previsaoEntrega) {
        this.previsaoEntrega = previsaoEntrega;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getValorPago() {
        return valorPago;
    }

    public void setValorPago(double valorPago) {
        this.valorPago = valorPago;
    }

    public double getDiferenca() {
        return diferenca;
    }

    public void setDiferenca(double diferenca) {
        this.diferenca = diferenca;
    }
   
    
}
