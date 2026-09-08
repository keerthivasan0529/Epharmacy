package com.epharmacy.medicine_service.service;

import com.epharmacy.medicine_service.dto.MedicineDto;
import com.epharmacy.medicine_service.entity.Medicine;
import com.epharmacy.medicine_service.exception.MedicineNotFoundException;
import com.epharmacy.medicine_service.repository.MedicineRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class MedicineServiceImpl implements MedicineService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private MedicineRepository medicineRepository;

    @Override
    public Page<MedicineDto> getAllMedicines(int pageNumber, int pageSize) {

        Pageable pageable = PageRequest.of(pageNumber, pageSize);

        Page<Medicine> medicinePage = medicineRepository.findAll(pageable);

        Page<MedicineDto> medicineDtoPage = medicinePage.map(this::convertToDto);

        return medicineDtoPage;
    }

    @Override
    public MedicineDto getMedicineById(Integer medicineId) {

        Medicine medicine = medicineRepository.findById(medicineId).orElseThrow(() -> new MedicineNotFoundException("Medicine not found with id: " + medicineId));;

        return convertToDto(medicine);
    }

    @Override
    public Page<MedicineDto> getMedicinesByCategory(String category, int pageNumber, int pageSize) {

        Pageable pageable = PageRequest.of(pageNumber, pageSize);

        Page<Medicine> medicinePage = medicineRepository.findByCategory(category, pageable);

        if(medicinePage.isEmpty()) {
            throw new MedicineNotFoundException("No medicines found in category: " + category);
        }

        Page<MedicineDto> medicineDtoPage = medicinePage.map(this::convertToDto);

        return medicineDtoPage;
    }

    @Override
    public List<MedicineDto> searchMedicines(String medicineName) {

        List<Medicine> medicine = medicineRepository.findByMedicineNameContainingIgnoreCase(medicineName);

        if(medicine.isEmpty()) {
            throw new MedicineNotFoundException("No Medicines found with name: "+ medicineName);
        }

        List<MedicineDto> medicineDtoList = medicine.stream().map(this::convertToDto).toList();

        return medicineDtoList;
    }

    @Override
    public String updateStock(Integer medicineId, Integer orderedQuantity) {

        Medicine medicine = medicineRepository.findById(medicineId)
                .orElseThrow(() -> new MedicineNotFoundException(
                        "Medicine not found with id: " + medicineId));

        if (medicine.getStockQuantity() >= orderedQuantity) {
            medicine.setStockQuantity(medicine.getStockQuantity() - orderedQuantity);
            medicineRepository.save(medicine);
            return "Stock updated successfully";
        } else {
            throw new RuntimeException(
                    "Insufficient stock for medicine with id: " + medicineId);
        }
    }

    public MedicineDto convertToDto(Medicine medicine) {

        MedicineDto dto = modelMapper.map(medicine, MedicineDto.class);

        LocalDate today = LocalDate.now();
        LocalDate expiryDate = medicine.getExpiryDate();

        long monthsUntilExpiry = java.time.temporal.ChronoUnit.MONTHS
                .between(today, expiryDate);

        if(monthsUntilExpiry <= 3) {
            dto.setDiscountPercent(30);
        }
        else if(monthsUntilExpiry <= 6) {
            dto.setDiscountPercent(20);
        }
        else {
            dto.setDiscountPercent(dto.getDiscountPercent() != null ? dto.getDiscountPercent() : 0);
        }

        if(medicine.getStockQuantity() > 0) {
            dto.setStockStatus("In Stock");
        } else {
            dto.setStockStatus("Out of Stock");
        }

        return dto;
    }
}
