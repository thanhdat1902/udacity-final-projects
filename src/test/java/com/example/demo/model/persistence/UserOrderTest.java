package com.example.demo.model.persistence;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserOrderTest {

    @Test
    public void testGetId() {
        UserOrder userOrder = new UserOrder();
        userOrder.setId(23L);
        assertEquals(23L, userOrder.getId());
    }
}