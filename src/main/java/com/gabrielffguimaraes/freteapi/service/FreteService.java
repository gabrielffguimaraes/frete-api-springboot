package com.gabrielffguimaraes.freteapi.service;

import com.gabrielffguimaraes.freteapi.entity.CorreioLog;
import com.gabrielffguimaraes.freteapi.model.Correio;
import com.gabrielffguimaraes.freteapi.model.cServico;

import java.util.List;

public interface FreteService {
    cServico calculaFrete(String destinatario, String cepDestino, String cepOrigem, float Peso) throws Exception;

    List<CorreioLog> log();

    void salvarLog(Correio correio, cServico correioApi);
}
