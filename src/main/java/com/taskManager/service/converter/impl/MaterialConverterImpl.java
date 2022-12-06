package com.taskManager.service.converter.impl;

import com.taskManager.model.entity.Material;
import com.taskManager.model.entity.TempMaterial;
import com.taskManager.model.repository.DepartmentRepository;
import com.taskManager.model.repository.MaterialRepository;
import com.taskManager.service.MaterialService;
import com.taskManager.service.converter.MaterialConverter;
import com.taskManager.service.dto.MaterialDto;
import com.taskManager.service.dto.TempMaterialDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class MaterialConverterImpl implements MaterialConverter {
    private final DepartmentRepository departmentRepository;
    private final MaterialRepository materialRepository;

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
        if (materialDto.getId() != null) {
            material.setId(materialDto.getId());
        }
        material.setName(materialDto.getName());
        material.setProperty(materialDto.getProperty());
        material.setValue(materialDto.getValue());
        material.setDepartmentMaterial(departmentRepository.getReferenceById(materialDto.getDepartmentId()));
        return material;
    }

    @Override
    public TempMaterial convertToTempMaterial(Material material) {
        var tempMaterial = new TempMaterial();
        tempMaterial.setMaterialId(material.getId());
        tempMaterial.setName(material.getName());
        tempMaterial.setProperty(material.getProperty());
        return tempMaterial;
    }

    @Override
    public List<TempMaterial> convertToTempMaterial(String tempMaterial) {
        var materials = new ArrayList<TempMaterial>();
        var strArray = tempMaterial.split(",");
        for (int i = 0; i < strArray.length; i++) {
            if (i % 2 == 0) {
                var material = materialRepository.getReferenceById(Long.parseLong(strArray[i]));
                materials.add(convertToTempMaterial(material));
            } else {
                materials.get(materials.size() - 1).setValue(Integer.parseInt(strArray[i]));
            }
        }
        return materials.stream()
                .filter(temp -> temp.getValue() != 0)
                .collect(Collectors.toList());
    }
}
