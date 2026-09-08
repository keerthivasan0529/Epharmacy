package com.epharmacy.medicine_service.controller;

import com.epharmacy.medicine_service.dto.MedicineDto;
import com.epharmacy.medicine_service.dto.UpdateStockDto;
import com.epharmacy.medicine_service.service.MedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medicine-api/medicines")
public class MedicineController {

    @Autowired
    private MedicineService medicineService;

    @GetMapping("/pageNumber/{pageNumber}/pageSize/{pageSize}")
    public ResponseEntity<Page<MedicineDto>> getAllMedicines(
            @PathVariable int pageNumber,
            @PathVariable int pageSize) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(medicineService.getAllMedicines(pageNumber, pageSize));
    }

    @GetMapping("/{medicineId}")
    public ResponseEntity<MedicineDto> getMedicineById(@PathVariable Integer medicineId) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(medicineService.getMedicineById(medicineId));
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<Page<MedicineDto>> getMedicinesByCategory(@PathVariable String category, @RequestParam int pageNumber, @RequestParam int pageSize) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(medicineService.getMedicinesByCategory(category, pageNumber, pageSize));
    }

    @GetMapping("/search/{medicineName}")
    public ResponseEntity<List<MedicineDto>> searchMedicines(@PathVariable String medicineName) {

        return ResponseEntity.status(HttpStatus.OK)
                .body(medicineService.searchMedicines(medicineName));
    }

    @PutMapping("/update-stock/medicine/{medicineId}")
    public ResponseEntity<String> updateStock(@PathVariable Integer medicineId, @RequestBody UpdateStockDto updateStockDto) {

        return ResponseEntity.status(HttpStatus.OK).body(medicineService.updateStock(medicineId, updateStockDto.getOrderedQuantity()));
    }

}
