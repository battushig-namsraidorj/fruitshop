package com.profnaya.fruitshop.api.v1.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class CustomerDTO {
    private String firstname;
    private String lastname;

    @JsonProperty("customer_url")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String customUrl;
}
