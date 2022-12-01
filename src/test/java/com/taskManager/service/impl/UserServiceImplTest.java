package com.taskManager.service.impl;

import com.taskManager.model.entity.User;
import com.taskManager.model.repository.RoleRepository;
import com.taskManager.model.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@RequiredArgsConstructor
class UserServiceImplTest {
    private final static String USERNAME="some_username";
    private final static String EMAIL="example@mail.com";
    @Mock
    private UserRepository userRepository;
    @Mock
    private RoleRepository roleRepository;
    @Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void saveUser() {
        final User user = mock(User.class);
        final User newUser = mock(User.class);
        when(user.getUsername()).thenReturn(USERNAME);
        when(userRepository.findByUsername(USERNAME)).thenReturn(user);
        final boolean actual = userService.saveUser(user);

        assertFalse(actual);

        final boolean newActual = userService.saveUser(newUser);

        assertTrue(newActual);

        verify(userRepository).save(newUser);
    }

    @Test
    void findByUsername() {
        final User user = mock(User.class);
        when(userRepository.findByUsername(USERNAME)).thenReturn(user);

        final User actual = userService.findByUsername(USERNAME);

        assertNotNull(actual);
        assertEquals(user, actual);
        verify(userRepository).findByUsername(USERNAME);
    }

    @Test
    void getAuthUser() {
        final User user = mock(User.class);
        final UserDetails principal = mock(UserDetails.class);
        final Authentication authentication = mock(Authentication.class);
        final SecurityContext securityContext = mock(SecurityContext.class);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);
        when(SecurityContextHolder.getContext().getAuthentication().getPrincipal()).thenReturn(principal);
        when(principal.getUsername()).thenReturn(USERNAME);
        when(userRepository.findByUsername(USERNAME)).thenReturn(user);

        final User actual = userService.getAuthUser();

        assertNotNull(actual);
        assertEquals(user, actual);
        verify(userRepository).findByUsername(USERNAME);
    }

    @Test
    void checkAvailabilityEmail() {
        final User user = mock(User.class);
        when(userRepository.findByEmail(EMAIL)).thenReturn(user);
        final boolean actualSame = userService.checkAvailabilityEmail(EMAIL);

        assertFalse(actualSame);

        when(userRepository.findByEmail(EMAIL)).thenReturn(null);
        final boolean actualOther = userService.checkAvailabilityEmail(EMAIL);

        assertTrue(actualOther);
        verifyNoMoreInteractions(userRepository);
    }

    @Test()
    void findByEmail() {
        final User user = mock(User.class);
        when(userRepository.findByEmail(EMAIL)).thenReturn(user);
        final User actual= userService.findByEmail(EMAIL);

        assertNotNull(actual);
        assertEquals(user, actual);
        verify(userRepository).findByEmail(EMAIL);
    }

    @Test
    void loadUserByUsername() {
        final User user = mock(User.class);
        when(userRepository.findByUsername(USERNAME)).thenReturn(user);
        final User actual= userService.findByUsername(USERNAME);

        assertNotNull(actual);
        assertEquals(user, actual);
        verify(userRepository).findByUsername(USERNAME);
    }
}