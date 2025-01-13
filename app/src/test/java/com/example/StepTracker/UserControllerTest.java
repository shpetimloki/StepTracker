package com.example.StepTracker;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import com.example.StepTracker.DatabaseHelper;
import org.junit.Before;
import org.junit.Test;

public class UserControllerTest {

    private DatabaseHelper mockDatabase;

    @Before
    public void setup() {
        mockDatabase = mock(DatabaseHelper.class);
    }

    @Test
    public void testRegisterUser() {
        when(mockDatabase.registerUser("testUser", "password123", "email@example.com")).thenReturn(true);
        boolean isRegistered = mockDatabase.registerUser("testUser", "password123", "email@example.com");
        assertTrue(isRegistered);
    }

    @Test
    public void testLoginUser() {
        when(mockDatabase.checkUser("testUser", "password123")).thenReturn(true);
        boolean isLoggedIn = mockDatabase.checkUser("testUser", "password123");
        assertTrue(isLoggedIn);
    }
}
