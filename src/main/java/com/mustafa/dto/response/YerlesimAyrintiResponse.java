package com.mustafa.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record YerlesimAyrintiResponse(
        @JsonProperty("VagonAdi") String vagonAdi,
        @JsonProperty("KisiSayisi") int kisiSayisi
) {
}