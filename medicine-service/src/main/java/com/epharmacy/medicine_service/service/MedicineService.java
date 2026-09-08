package com.epharmacy.medicine_service.service;

import com.epharmacy.medicine_service.dto.MedicineDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface MedicineService {

    // US01: Get all medicines — paginated
    Page<MedicineDto> getAllMedicines(int pageNumber, int pageSize);

    // US01: Get single medicine by ID
    MedicineDto getMedicineById(Integer medicineId);

    Page<MedicineDto> getMedicinesByCategory(String category, int pageNumber, int pageSize);

    List<MedicineDto> searchMedicines(String medicineName);

    String updateStock(Integer medicineId, Integer orderedQuantity);
}
