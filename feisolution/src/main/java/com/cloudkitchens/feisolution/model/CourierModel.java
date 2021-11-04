package com.cloudkitchens.feisolution.model;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

@Data
@Component
@Validated
public class CourierModel {

    @Value("Courier ID")
    @NotNull
    private String id;

}
