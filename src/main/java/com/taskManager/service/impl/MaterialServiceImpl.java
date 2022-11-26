package com.taskManager.service.impl;

import com.taskManager.model.entity.Material;
import com.taskManager.model.repository.DepartmentRepository;
import com.taskManager.model.repository.MaterialRepository;
import com.taskManager.service.DepartmentService;
import com.taskManager.service.MaterialService;
import com.taskManager.service.dto.MaterialDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MaterialServiceImpl implements MaterialService {
    private final MaterialRepository materialRepository;
    private final DepartmentRepository departmentRepository;

    @Override
    public boolean save(Material material) {
        if(departmentRepository.findById(material.getDepartmentMaterial().getId()).getMaterials().stream()
                .anyMatch(materialFromDept -> materialFromDept.getName().equals(material.getName())
                        &&materialFromDept.getProperty().equals(material.getProperty()))){
           return false;
        }
        materialRepository.save(material);
        return true;
    }

    @Override
    public Material convertToMaterial(MaterialDto materialDto) {
        var material = new Material();
        if(materialDto.getId()!=0){
            material = findById(materialDto.getId());
        }
        material.setName(materialDto.getName());
        material.setProperty(materialDto.getProperty());
        material.setValue(materialDto.getValue());
        material.setDepartmentMaterial(departmentRepository.findById(materialDto.getDepartmentId()));
        return material;
    }

    @Override
    public MaterialDto convertToMaterialDto(Material material) {
        var materialDto = new MaterialDto();
        materialDto.setId(material.getId());
        materialDto.setName(material.getName());
        materialDto.setDepartmentId(material.getDepartmentMaterial().getId());
        materialDto.setProperty(material.getProperty());
        materialDto.setValue(material.getValue());
        return materialDto;
    }

    @Override
    public boolean update(MaterialDto materialdto) {
        var dbMaterial = materialRepository.findById(materialdto.getId());
        if (materialdto.equals(dbMaterial)){
            return false;
        }

        materialRepository.saveAndFlush(convertToMaterial(materialdto));
        return true;
    }

    @Override
    public Material findById(Long id) {
        return materialRepository.findById(id);
    }
}
