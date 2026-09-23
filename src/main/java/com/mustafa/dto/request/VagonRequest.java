package com.mustafa.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

public record VagonRequest(
        @JsonProperty("Ad")
        @NotBlank
        String ad,

        @JsonProperty("Kapasite")
        @NotNull
        @Positive
        Integer kapasite,

        @JsonProperty("DoluKoltukAdet")
        @NotNull
        @PositiveOrZero
        Integer doluKoltukAdet
) {
}