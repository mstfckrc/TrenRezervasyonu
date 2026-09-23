package com.mustafa.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public record TrenRequest(
        @JsonProperty("Ad")
        @NotBlank
        String ad,

        @JsonProperty("Vagonlar")
        @NotEmpty
        List<@Valid VagonRequest> vagonlar
) {
}