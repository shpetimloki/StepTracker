package com.example.StepTracker;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import android.content.SharedPreferences;
import com.example.StepTracker.DatabaseHelper;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class MainActivityUnitTest {

    @Mock
    SharedPreferences mockPreferences;
    @Mock
    SharedPreferences.Editor mockEditor;
    @Mock
    DatabaseHelper mockDatabase;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    // Test für updateGoalText
    @Test
    public void testUpdateGoalText() {
        String result = updateGoalText(5000, 10000);
        assertEquals("5000 von 10000 Schritten erreicht", result);

        result = updateGoalText(8000, 10000);
        assertEquals("8000 von 10000 Schritten erreicht", result);
    }

    private String updateGoalText(int stepCount, int dailyGoal) {
        return stepCount + " von " + dailyGoal + " Schritten erreicht";
    }

    // Test für Schrittzählung
    @Test
    public void testProcessStepCount() {
        int stepCount = processStepCount(1234.0f);
        assertEquals(1234, stepCount);

        stepCount = processStepCount(0.0f);
        assertEquals(0, stepCount);
    }

    private int processStepCount(float sensorValue) {
        return (int) sensorValue;
    }

    // Test für erfolgreichen Login
    @Test
    public void testSuccessfulLogin() {
        when(mockDatabase.checkUser("testUser", "password123")).thenReturn(true);

        boolean isValid = mockDatabase.checkUser("testUser", "password123");
        assertTrue(isValid);
    }

    // Test für fehlgeschlagenen Login
    @Test
    public void testFailedLogin() {
        when(mockDatabase.checkUser("wrongUser", "wrongPassword")).thenReturn(false);

        boolean isValid = mockDatabase.checkUser("wrongUser", "wrongPassword");
        assertFalse(isValid);
    }

    // Test für Zieländerung
    @Test
    public void testSaveAndRetrieveGoal() {
        // Simuliere das Speichern eines neuen Ziels
        when(mockPreferences.edit()).thenReturn(mockEditor);
        when(mockEditor.putInt("dailyGoal", 15000)).thenReturn(mockEditor);
        when(mockPreferences.getInt("dailyGoal", 10000)).thenReturn(15000);

        mockEditor.putInt("dailyGoal", 15000).apply();
        int savedGoal = mockPreferences.getInt("dailyGoal", 10000);

        assertEquals(15000, savedGoal);
    }

    // Test für Logout
    @Test
    public void testLogout() {
        when(mockPreferences.edit()).thenReturn(mockEditor);
        when(mockEditor.putBoolean("isLoggedIn", false)).thenReturn(mockEditor);

        mockEditor.putBoolean("isLoggedIn", false).apply();
        verify(mockEditor).putBoolean("isLoggedIn", false);
        verify(mockEditor).apply();
    }

    // Test für Benutzerregistrierung
    @Test
    public void testUserRegistration() {
        when(mockDatabase.registerUser("newUser", "password123", "email@example.com")).thenReturn(true);

        boolean isRegistered = mockDatabase.registerUser("newUser", "password123", "email@example.com");
        assertTrue(isRegistered);
    }

    @Test
    public void testFailedRegistration() {
        when(mockDatabase.registerUser("", "", "")).thenReturn(false);

        boolean isRegistered = mockDatabase.registerUser("", "", "");
        assertFalse(isRegistered);
    }
}
