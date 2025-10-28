package com.example.nthmin.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class NthMinRequest {
    @Schema(description = "Путь к файлу XLSX", example = "C:/data/numbers.xlsx")
    private String filePath;

    @Schema(description = "Порядковый номер минимального числа (N)", example = "3")
    private int num;
}
