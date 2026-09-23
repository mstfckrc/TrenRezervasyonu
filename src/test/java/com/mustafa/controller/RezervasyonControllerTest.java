package com.mustafa.controller;

import com.mustafa.starter.TrenRezervasyonuApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = TrenRezervasyonuApplication.class)
@AutoConfigureMockMvc
class RezervasyonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void sifirKisilikIstekBadRequestDoner() throws Exception {
        String json = """
                {
                  "Tren": {
                    "Ad": "Başkent Ekspres",
                    "Vagonlar": [
                      {"Ad": "Vagon 1", "Kapasite": 100, "DoluKoltukAdet": 68}
                    ]
                  },
                  "RezervasyonYapilacakKisiSayisi": 0,
                  "KisilerFarkliVagonlaraYerlestirilebilir": true
                }
                """;

        mockMvc.perform(post("/api/rezervasyon")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest());
    }
}