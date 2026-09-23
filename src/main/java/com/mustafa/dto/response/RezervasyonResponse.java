package com.mustafa.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record RezervasyonResponse(
        @JsonProperty("RezervasyonYapilabilir") boolean rezervasyonYapilabilir,
        @JsonProperty("YerlesimAyrinti") List<YerlesimAyrintiResponse> yerlesimAyrinti
) {
}