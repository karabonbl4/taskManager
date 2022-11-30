package com.taskManager.service.impl;

import com.taskManager.model.entity.Customer;
import com.taskManager.model.entity.Department;
import com.taskManager.model.entity.Provider;
import com.taskManager.model.repository.ProviderRepository;
import com.taskManager.service.converter.ProviderConverter;
import com.taskManager.service.dto.CustomerDto;
import com.taskManager.service.dto.ProviderDto;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
class ProviderServiceImplTest {
    private static final Integer TAX_NUMBER = 3366985;
    private static final Long ID = 1L;
    @Mock
    private ProviderRepository providerRepository;
    @Mock
    private ProviderConverter providerConverter;
    @InjectMocks
    private ProviderServiceImpl providerService;

    @Test
    @DisplayName("Find provider by tax number with call to repository")
    void findByTaxNumber() {
        final Provider provider = mock(Provider.class);
        when(providerRepository.findByTaxNumber(TAX_NUMBER)).thenReturn(provider);

        final Provider actual = providerService.findByTaxNumber(TAX_NUMBER);

        assertNotNull(actual);
        assertEquals(provider, actual);
    }

    @Test
    @DisplayName("Save new provider with call to repository and false condition")
    void save() {
        final Provider provider = mock(Provider.class);
        final Department department = mock(Department.class);
        final List<Provider> providers = new ArrayList<>();
        providers.add(provider);
        when(provider.getDepartmentProvider()).thenReturn(department);
        when(department.getProviders()).thenReturn(providers);
        lenient().when(provider.getTaxNumber()).thenReturn(TAX_NUMBER);
        boolean actual = providerService.save(provider);

        assertFalse(actual);
    }

    @Test
    @DisplayName("Update provider with call to repository and false condition")
    void update() {
        final Provider provider = mock(Provider.class);
        final ProviderDto providerDto = mock(ProviderDto.class);
        when(providerDto.getId()).thenReturn(ID);
        when(providerRepository.getReferenceById(ID)).thenReturn(provider);
        when(providerConverter.convertToProvider(providerDto)).thenReturn(provider);
        boolean actual = providerService.update(providerDto);

        assertFalse(actual);
    }

    @Test
    @DisplayName("Soft delete provider")
    void delete() {
        final Provider provider = mock(Provider.class);
        final ProviderDto providerDto = mock(ProviderDto.class);
        when(providerRepository.getReferenceById(ID)).thenReturn(provider);
        when(providerDto.getId()).thenReturn(ID);
        providerService.delete(providerDto);

        verify(providerRepository).saveAndFlush(provider);
    }
}