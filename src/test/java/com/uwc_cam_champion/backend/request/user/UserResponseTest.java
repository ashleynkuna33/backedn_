package com.uwc_cam_champion.backend.request.user;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class UserResponseTest {

    @Test
    void shouldExposeOnlySafeUserFields() {
        UserResponse response = new UserResponse();
        response.setId(1L);
        response.setName("Ada");
        response.setSurname("Lovelace");
        response.setEmail("ada@example.com");
        response.setPassword("secret");

        assertEquals(1L, response.getId());
        assertEquals("Ada", response.getName());
        assertEquals("Lovelace", response.getSurname());
        assertEquals("ada@example.com", response.getEmail());
        assertNull(response.getPhone());
    }
}
