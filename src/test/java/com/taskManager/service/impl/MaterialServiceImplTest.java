package com.taskManager.service.impl;

import com.taskManager.model.entity.Department;
import com.taskManager.model.entity.Material;
import com.taskManager.model.repository.MaterialRepository;
import com.taskManager.service.converter.MaterialConverter;
import com.taskManager.service.dto.MaterialDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MaterialServiceImplTest {
    private static final String NAME = "paper";
    private static final String PROPERTY = "white";
    private static final Long DEPARTMENT_ID = 11L;
    private static final Long ID = 1L;
    @Mock
    private MaterialRepository materialRepository;
    @Mock
    private MaterialConverter materialConverter;
    @InjectMocks
    private MaterialServiceImpl materialService;

    @Test
    @DisplayName("Save new material")
    void save() {
        final Material material = mock(Material.class);
        final Department department = mock(Department.class);
        final List<Material> materials = new ArrayList<>();
        materials.add(material);
        when(material.getDepartmentMaterial()).thenReturn(department);
        when(department.getMaterials()).thenReturn(materials);
        lenient().when(material.getName()).thenReturn(NAME);
        lenient().when(material.getProperty()).thenReturn(PROPERTY);
        boolean actual = materialService.save(material);

        assertFalse(actual);

        materials.remove(material);
        when(material.getDepartmentMaterial()).thenReturn(department);
        when(department.getMaterials()).thenReturn(materials);
        lenient().when(material.getName()).thenReturn(NAME);
        lenient().when(material.getProperty()).thenReturn(PROPERTY);
        boolean actualTrue = materialService.save(material);

        assertTrue(actualTrue);
    }

    @Test
    @DisplayName("Update material")
    void update() {
        final Material material = mock(Material.class);
        final MaterialDto materialDto = mock(MaterialDto.class);
        when(materialDto.getId()).thenReturn(ID);
        when(materialRepository.getReferenceById(ID)).thenReturn(material);
        lenient().when(materialDto.getDepartmentId()).thenReturn(DEPARTMENT_ID);
        when(materialConverter.convertToMaterial(materialDto)).thenReturn(material);
        boolean actual = materialService.update(materialDto);

        assertFalse(actual);
    }

    @Test
    @DisplayName("Soft delete material")
    void delete() {
        final Material material = mock(Material.class);
        final MaterialDto materialDto = mock(MaterialDto.class);
        when(materialRepository.getReferenceById(ID)).thenReturn(material);
        when(materialDto.getId()).thenReturn(ID);
        materialService.delete(materialDto);

        verify(materialRepository).saveAndFlush(material);

    }
}