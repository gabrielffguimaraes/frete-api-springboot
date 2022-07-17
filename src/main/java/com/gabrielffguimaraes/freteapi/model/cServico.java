package com.gabrielffguimaraes.freteapi.model;


import lombok.Data;

@Data
public class cServico {
    public int Codigo;
    public String Valor;
    public int PrazoEntrega;
    public String ValorSemAdicionais;
    public String ValorMaoPropria;
    public String ValorAvisoRecebimento;
    public String ValorValorDeclarado;
    public String EntregaDomiciliar;
    public String EntregaSabado;

    public int Erro;
    public String MsgErro;

}
