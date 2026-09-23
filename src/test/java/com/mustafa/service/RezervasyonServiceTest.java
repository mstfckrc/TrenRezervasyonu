package com.mustafa.service;

import com.mustafa.dto.request.RezervasyonRequest;
import com.mustafa.dto.request.TrenRequest;
import com.mustafa.dto.request.VagonRequest;
import com.mustafa.dto.response.RezervasyonResponse;
import com.mustafa.dto.response.YerlesimAyrintiResponse;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RezervasyonServiceTest {

    private final RezervasyonService service = new RezervasyonService();

    @Test
    void farkliVagonlaraYerlesebilenUcKisiyiDagitir() {
        RezervasyonRequest request = new RezervasyonRequest(
                new TrenRequest("Başkent Ekspres", List.of(
                        new VagonRequest("Vagon 1", 100, 68),
                        new VagonRequest("Vagon 2", 90, 50),
                        new VagonRequest("Vagon 3", 80, 80)
                )),
                3,
                true
        );

        RezervasyonResponse beklenen = new RezervasyonResponse(
                true,
                List.of(
                        new YerlesimAyrintiResponse("Vagon 1", 2),
                        new YerlesimAyrintiResponse("Vagon 2", 1)
                )
        );

        RezervasyonResponse gercek = service.rezervasyonKontrolEt(request);

        assertEquals(beklenen, gercek);
    }

    @Test
    void farkliVagonlaraYerlesemeyenUcKisiyiTekVagonaYerlestirir() {
        RezervasyonRequest request = new RezervasyonRequest(
                new TrenRequest("Başkent Ekspres", List.of(
                        new VagonRequest("Vagon 1", 100, 68),
                        new VagonRequest("Vagon 2", 90, 50),
                        new VagonRequest("Vagon 3", 80, 80)
                )),
                3,
                false
        );

        RezervasyonResponse beklenen = new RezervasyonResponse(
                true,
                List.of(new YerlesimAyrintiResponse("Vagon 2", 3))
        );

        assertEquals(beklenen, service.rezervasyonKontrolEt(request));
    }

    @Test
    void toplamYerOnAltiKisiyeYetmedigindeRezervasyonuReddeder() {
        RezervasyonRequest request = new RezervasyonRequest(
                new TrenRequest("Başkent Ekspres", List.of(
                        new VagonRequest("Vagon 1", 100, 68),
                        new VagonRequest("Vagon 2", 90, 50),
                        new VagonRequest("Vagon 3", 80, 80)
                )),
                16,
                true
        );

        RezervasyonResponse beklenen =
                new RezervasyonResponse(false, List.of());

        assertEquals(beklenen, service.rezervasyonKontrolEt(request));
    }

    @Test
    void onDortKisiTekVagonaSigmadigindaRezervasyonuReddeder() {
        RezervasyonRequest request = new RezervasyonRequest(
                new TrenRequest("Başkent Ekspres", List.of(
                        new VagonRequest("Vagon 1", 100, 68),
                        new VagonRequest("Vagon 2", 90, 50),
                        new VagonRequest("Vagon 3", 80, 80)
                )),
                14,
                false
        );

        RezervasyonResponse beklenen =
                new RezervasyonResponse(false, List.of());

        assertEquals(beklenen, service.rezervasyonKontrolEt(request));
    }

    @Test
    void yuzdeYetmisDoluVagonaYeniKisiYerlestirmez() {
        RezervasyonRequest request = new RezervasyonRequest(
                new TrenRequest("Başkent Ekspres", List.of(
                        new VagonRequest("Vagon 1", 100, 70)
                )),
                1,
                true
        );

        assertEquals(
                new RezervasyonResponse(false, List.of()),
                service.rezervasyonKontrolEt(request)
        );
    }

    @Test
    void doluluguTamYuzdeYetmiseUlastirabilir() {
        RezervasyonRequest request = new RezervasyonRequest(
                new TrenRequest("Başkent Ekspres", List.of(
                        new VagonRequest("Vagon 1", 100, 68)
                )),
                2,
                true
        );

        assertEquals(
                new RezervasyonResponse(
                        true,
                        List.of(new YerlesimAyrintiResponse("Vagon 1", 2))
                ),
                service.rezervasyonKontrolEt(request)
        );
    }
}