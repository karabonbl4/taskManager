package com.taskManager.service.impl;

import com.taskManager.model.entity.Material;
import com.taskManager.model.repository.MaterialRepository;
import com.taskManager.service.MaterialService;
import com.taskManager.service.converter.MaterialConverter;
import com.taskManager.service.dto.MaterialDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MaterialServiceImpl implements MaterialService {
    private final MaterialRepository materialRepository;
    private final MaterialConverter materialConverter;

    @Override
    public boolean save(Material material) {
        if(material.getDepartmentMaterial().getMaterials().stream()
                .anyMatch(materialFromDept -> materialFromDept.getName().equals(material.getName())
                        &&materialFromDept.getProperty().equals(material.getProperty()))){
           return false;
        }
        materialRepository.save(material);
        return true;
    }

    @Override
    public boolean update(MaterialDto materialDto) {
        var dbMaterial = materialRepository.getReferenceById(materialDto.getId());
        var material = materialConverter.convertToMaterial(materialDto);
        if (material.equals(dbMaterial)){
            return false;
        }
        materialRepository.saveAndFlush(material);
        return true;
    }

    @Override
    public void delete(MaterialDto materialDto) {
        var dbMaterial = materialRepository.getReferenceById(materialDto.getId());
        dbMaterial.setName("deleted");
        materialRepository.saveAndFlush(dbMaterial);
    }

    @Override
    public Material findById(Long id) {
        return materialRepository.getReferenceById(id);
    }
}
