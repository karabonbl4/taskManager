package com.taskManager.service.impl;

import com.taskManager.model.entity.Material;
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
//    private final DepartmentService departmentService;

    @Override
    public Material convertToMaterial(MaterialDto materialDto) {
        var material = new Material();
        material.setId(materialDto.getId());
        material.setName(materialDto.getName());
//        material.setDepartmentMaterial(departmentService.convertToDepartment(departmentService.findById(materialDto.getDepartmentId())));
        material.setProperty(materialDto.getProperty());
        material.setValue(materialDto.getValue());
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
}
