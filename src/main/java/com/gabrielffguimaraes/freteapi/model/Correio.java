package com.gabrielffguimaraes.freteapi.model;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@Data
public class Correio {

    private final String URL_BASE = "http://ws.correios.com.br/calculador/CalcPrecoPrazo.aspx";

    private final String SERVICO_SEDEX = "04014";
    private final String SERVICO_SEDEX_12 = "04782";
    private final String SERVICO_SEDEX_10 = "04790";
    private final String SERVICO_SEDEX_HOJE = "04804";
    private final String SERVICO_PAC = "04510";
    private final int FORMATO_CAIXA_PACOTE = 1;
    private final int FORMATO_ROLO_PRISMA = 2;
    private final int FORMATO_ENVELOPE = 3;
    private String codigoEmpresa = "";
    private String senhaEmpresa = "";
    private String cepOrigem = "";
    private String cepDestino = "";
    private String codigoServico = SERVICO_PAC;

    private String destinatario;
    private float peso = 1f;
    private int formato = FORMATO_CAIXA_PACOTE;
    private float comprimento = 15f;
    private float altura = 15f;
    private float largura = 15;
    private float diametro = 0;
    private boolean maoPropria = false;
    private float valorDeclarado = 0;
    private boolean avisoRecebimento = false;

    public String getServicoDescricao(String servico) {
        String response;
        switch (servico) {
            case SERVICO_SEDEX:
                response = "SEDEX";
            break;
            case SERVICO_SEDEX_12:
                response = "SEDEX_12";
            break;
            case SERVICO_SEDEX_10:
                response = "SEDEX_10";
            break;
            case SERVICO_PAC:
                response = "PAC";
            break;
            default:
                response = "Nenhum servico encontrado .";
        }
        return response;
    }
    @Override
    public String toString() {
        return
        URL_BASE+
        "?nCdEmpresa="+this.codigoEmpresa +
        "&sDsSenha="+this.senhaEmpresa +
        "&nCdServico="+this.codigoServico +
        "&sCepOrigem="+this.cepOrigem +
        "&sCepDestino="+this.cepDestino +
        "&nVlPeso="+this.peso +
        "&nCdFormato="+this.formato +
        "&nVlComprimento="+this.comprimento +
        "&nVlAltura="+ this.altura +
        "&nVlLargura="+ this.largura +
        "&nVlDiametro="+ this.diametro +
        "&sCdMaoPropria="+ ((this.maoPropria) ? 'S' : 'N') +
        "&nVlValorDeclarado="+ this.valorDeclarado +
        "&sCdAvisoRecebimento=" + ((this.avisoRecebimento) ? 'S' : 'N') +
        "&StrRetorno=xml";
    }
}
