package com.example.nthmin.services;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Comparator;
import java.util.PriorityQueue;

@Service
public class NthMinService {

    public int findNthMin(String filePath, int n) throws IOException {
        if (n <= 0) {
            throw new IllegalArgumentException("N должно быть положительным числом");
        }

        try (Workbook workbook = new XSSFWorkbook(filePath)) {
            Sheet sheet = workbook.getSheetAt(0);

            return findNthMinFromSheet(sheet, n);
        }
    }

    private int findNthMinFromSheet(Sheet sheet, int n) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());

        for (Row row : sheet) {
            for (Cell cell : row) {
                if (cell.getCellType() == CellType.NUMERIC) {
                    int value = (int) cell.getNumericCellValue();
                    processValue(value, maxHeap, n);
                }
            }
        }

        if (maxHeap.size() < n) {
            throw new IllegalArgumentException("В файле меньше " + n + " чисел");
        }

        return maxHeap.peek();
    }

    private void processValue(int value, PriorityQueue<Integer> maxHeap, int n) {
        if (maxHeap.size() < n) {
            maxHeap.offer(value);
        } else if (value < maxHeap.peek()) {
            maxHeap.poll();
            maxHeap.offer(value);
        }
    }
}
