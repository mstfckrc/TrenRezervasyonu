package com.mustafa.service;

import com.mustafa.dto.request.RezervasyonRequest;
import com.mustafa.dto.request.VagonRequest;
import com.mustafa.dto.response.RezervasyonResponse;
import com.mustafa.dto.response.YerlesimAyrintiResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RezervasyonService {

    public RezervasyonResponse rezervasyonKontrolEt(RezervasyonRequest request) {
        int kisiSayisi = request.rezervasyonYapilacakKisiSayisi();

        if (!request.kisilerFarkliVagonlaraYerlestirilebilir()) {
            for (VagonRequest vagon : request.tren().vagonlar()) {
                if (musaitKoltukSayisi(vagon) >= kisiSayisi) {
                    return new RezervasyonResponse(
                            true,
                            List.of(new YerlesimAyrintiResponse(vagon.ad(), kisiSayisi))
                    );
                }
            }

            return new RezervasyonResponse(false, List.of());
        }

        List<YerlesimAyrintiResponse> yerlesim = new ArrayList<>();
        int kalanKisiSayisi = kisiSayisi;

        for (VagonRequest vagon : request.tren().vagonlar()) {
            int yerlestirilecekKisiSayisi =
                    Math.min(kalanKisiSayisi, musaitKoltukSayisi(vagon));

            if (yerlestirilecekKisiSayisi > 0) {
                yerlesim.add(new YerlesimAyrintiResponse(
                        vagon.ad(), yerlestirilecekKisiSayisi
                ));
                kalanKisiSayisi -= yerlestirilecekKisiSayisi;
            }

            if (kalanKisiSayisi == 0) {
                return new RezervasyonResponse(true, yerlesim);
            }
        }

        return new RezervasyonResponse(false, List.of());
    }

    private int musaitKoltukSayisi(VagonRequest vagon) {
        int enFazlaDoluKoltuk =
                (int) ((long) vagon.kapasite() * 70 / 100);

        return Math.max(0, enFazlaDoluKoltuk - vagon.doluKoltukAdet());
    }
}