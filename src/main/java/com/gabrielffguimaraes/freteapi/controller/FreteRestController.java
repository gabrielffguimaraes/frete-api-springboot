package com.gabrielffguimaraes.freteapi.controller;


import com.gabrielffguimaraes.freteapi.dto.CorreioResponseDto;
import com.gabrielffguimaraes.freteapi.entity.CorreioLog;
import com.gabrielffguimaraes.freteapi.exceptions.ApiCorreioException;
import com.gabrielffguimaraes.freteapi.service.ipml.FreteServiceIpml;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@RestController
public class FreteRestController {
    @Autowired
    private FreteServiceIpml freteService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/log")
    public List<CorreioLog> log() {
        return freteService.log();
    }
    @GetMapping("/calculaFrete")
    public CorreioResponseDto calculaFrete(@RequestParam("destinatario") String destinatario,
                                           @RequestParam("peso") float peso,
                                           @RequestParam("cepOrigem") String cepOrigem,
                                           @RequestParam("cepDestino") String cepDestino)  {

        try {
            CorreioResponseDto correioResponseDto = modelMapper
                    .map(freteService.calculaFrete(destinatario, cepOrigem, cepDestino, peso), CorreioResponseDto.class);
            return correioResponseDto;
        } catch (ApiCorreioException e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Erro ao calcular frete");
        }
    }


}
