package com.mustafa.controller;

import com.mustafa.dto.request.RezervasyonRequest;
import com.mustafa.dto.response.RezervasyonResponse;
import com.mustafa.service.RezervasyonService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/rezervasyon")
public class RezervasyonController {

    @Autowired
    private RezervasyonService rezervasyonService;

    @PostMapping
    public RezervasyonResponse rezervasyonKontrolEt(
            @Valid @RequestBody RezervasyonRequest request
    ) {
        return rezervasyonService.rezervasyonKontrolEt(request);
    }
}