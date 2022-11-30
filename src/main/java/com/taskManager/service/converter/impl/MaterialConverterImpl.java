package com.taskManager.service.converter.impl;

import com.taskManager.model.entity.Material;
import com.taskManager.model.repository.DepartmentRepository;
import com.taskManager.service.converter.MaterialConverter;
import com.taskManager.service.dto.MaterialDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MaterialConverterImpl implements MaterialConverter {
    private final DepartmentRepository departmentRepository;
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
    public Material convertToMaterial(MaterialDto materialDto) {
        var material = new Material();
        if(materialDto.getId()!=null){
            material.setId(materialDto.getId());
        }
        material.setName(materialDto.getName());
        material.setProperty(materialDto.getProperty());
        material.setValue(materialDto.getValue());
        material.setDepartmentMaterial(departmentRepository.getReferenceById(materialDto.getDepartmentId()));
        return material;
    }
}
