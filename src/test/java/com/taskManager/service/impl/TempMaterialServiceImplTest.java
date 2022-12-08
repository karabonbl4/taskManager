package com.taskManager.service.impl;

import com.taskManager.model.entity.TempMaterial;
import com.taskManager.model.repository.TempMaterialRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TempMaterialServiceImplTest {
    @Mock
    private TempMaterialRepository tempMaterialRepository;
    @InjectMocks
    private TempMaterialServiceImpl tempMaterialService;
    @Test
    void save() {
        final TempMaterial tempMaterial = mock(TempMaterial.class);
        when(tempMaterialRepository.saveAndFlush(tempMaterial)).thenReturn(tempMaterial);

        final TempMaterial actualTempMaterial = tempMaterialService.save(tempMaterial);

        assertNotNull(actualTempMaterial);
        assertEquals(tempMaterial, actualTempMaterial);
    }

    @Test
    void saveAll() {
        final List<TempMaterial> tempMaterials = mock(List.class);
        when(tempMaterialRepository.saveAllAndFlush(tempMaterials)).thenReturn(tempMaterials);

        final List<TempMaterial> actualTempMaterials = tempMaterialService.saveAll(tempMaterials);

        assertNotNull(actualTempMaterials);
        assertEquals(tempMaterials, actualTempMaterials);
    }
}