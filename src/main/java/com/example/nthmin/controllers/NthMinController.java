package com.example.nthmin.controllers;

import com.example.nthmin.dtos.NthMinRequest;
import com.example.nthmin.services.NthMinService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/nth-min")
@Tag(name = "N-th Minimum Controller", description = "Контроллер для поиска N-го минимального числа")
public class NthMinController {
    private final NthMinService nthMinService;

    @PostMapping("/find")
    @Operation(summary = "Найти N-ное минимальное число",
            description = "Принимает путь к файлу XLSX и число N, возвращает N-ное минимальное число")
    public ResponseEntity<?> findNthMin(@RequestBody NthMinRequest request) {
        try {
            int result = nthMinService.findNthMin(request.getFilePath(), request.getNum());
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Ошибка: " + e.getMessage());
        }
    }
}
