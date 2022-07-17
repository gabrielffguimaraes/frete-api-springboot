package com.gabrielffguimaraes.freteapi.service.ipml;

import com.gabrielffguimaraes.freteapi.entity.CorreioLog;
import com.gabrielffguimaraes.freteapi.exceptions.ApiCorreioException;
import com.gabrielffguimaraes.freteapi.model.Correio;
import com.gabrielffguimaraes.freteapi.helper.Helper;
import com.gabrielffguimaraes.freteapi.model.CorreioXml;
import com.gabrielffguimaraes.freteapi.model.cServico;
import com.gabrielffguimaraes.freteapi.repisotory.CorreioLogRepository;
import com.gabrielffguimaraes.freteapi.service.FreteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class FreteServiceIpml implements FreteService {
    @Autowired
    private Correio correio;
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private CorreioLogRepository correioLogRepository;

    @Override
    public cServico calculaFrete(String destinatario,String cepDestino, String cepOrigem, float Peso) throws Exception {
        // INICIO
        correio.setCepDestino(cepDestino);
        correio.setCepOrigem(cepOrigem);
        correio.setPeso(Peso);
        correio.setDestinatario(destinatario);
        // MONTA URL HTTP
        String url = correio.toString();
        // REALIZA A REQUISIÇÃO PARA API
        String xml = restTemplate.getForObject(url, String.class);
        CorreioXml s = (CorreioXml) Helper.convertXMLToObject(CorreioXml.class, xml);

        if (s.cServico.getMsgErro() != "") {
            throw new ApiCorreioException(s.cServico.getMsgErro());
        }
        // LOG
        salvarLog(correio,s.cServico);
        return s.cServico;
    }
    @Override
    public List<CorreioLog> log() {
        return correioLogRepository.findAll();
    }
    @Override
    public void salvarLog(Correio correio, cServico correioApi) {
        String servico = correio.getServicoDescricao(correio.getCodigoServico());

        CorreioLog correioLog = new CorreioLog();
        correioLog.setServico(servico);
        correioLog.setCepDestino(correio.getCepDestino());
        correioLog.setCepOrigem(correio.getCepOrigem());
        correioLog.setDestinatario(correio.getDestinatario());

        correioLog.setValor(correioApi.getValor());
        correioLog.setPrazoEntrega(correioApi.getPrazoEntrega());
        correioLogRepository.save(correioLog);
    }
}
