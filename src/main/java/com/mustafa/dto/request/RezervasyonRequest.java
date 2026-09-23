package com.mustafa.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record RezervasyonRequest(
        @JsonProperty("Tren")
        @NotNull
        @Valid
        TrenRequest tren,

        @JsonProperty("RezervasyonYapilacakKisiSayisi")
        @NotNull
        @Positive
        Integer rezervasyonYapilacakKisiSayisi,

        @JsonProperty("KisilerFarkliVagonlaraYerlestirilebilir")
        @NotNull
        Boolean kisilerFarkliVagonlaraYerlestirilebilir
) {
}