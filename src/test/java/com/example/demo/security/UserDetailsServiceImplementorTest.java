package com.example.demo.security;

import com.example.demo.controllers.OrderController;
import com.example.demo.model.persistence.User;
import com.example.demo.model.persistence.repositories.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserDetailsServiceImplementorTest {
    private final UserRepository userRepo = mock(UserRepository.class);
    private UserDetailsServiceImplementor userDetailsServiceImplementor;

    @Before
    public void setUp() {
        userDetailsServiceImplementor = new UserDetailsServiceImplementor(userRepo);
        com.example.demo.TestUtils.injectObjects(userDetailsServiceImplementor, "applicationUserRepository", userRepo);
    }

    @Test
    public void testLoadUserByUsername() {
        String username = "test";
        User user = new User();
        user.setUsername(username);
        String password = "password";
        user.setPassword(password);
        user.setId(0L);
        when(userRepo.findByUsername(username)).thenReturn(user);

        UserDetails userDetails = userDetailsServiceImplementor.loadUserByUsername(username);
        assertNotNull(userDetails);
        Collection<? extends GrantedAuthority> authorityCollection = userDetails.getAuthorities();
        assertNotNull(authorityCollection);
        assertEquals(0, authorityCollection.size());
        assertEquals(password, userDetails.getPassword());
        assertEquals(username, userDetails.getUsername());
    }
}