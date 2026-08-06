package com.uwc_cam_champion.backend.services.User;

import com.uwc_cam_champion.backend.models.User;
import com.uwc_cam_champion.backend.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class UserServiceDeleteTest {

    @Test
    void shouldDeleteUserById() {
        UserRepository userRepository = Mockito.mock(UserRepository.class);
        UserService userService = new UserService(userRepository, null);

        User user = new User();
        user.setId(1L);
        user.setName("Test");
        user.setSurname("User");
        user.setUsername("testuser");
        user.setEmail("test@example.com");
        user.setPassword("secret");

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        assertDoesNotThrow(() -> userService.deleteUser(1L));

        verify(userRepository).delete(user);
    }
}
