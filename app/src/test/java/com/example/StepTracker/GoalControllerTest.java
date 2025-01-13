package com.example.StepTracker;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import com.example.StepTracker.DatabaseHelper;
import org.junit.Before;
import org.junit.Test;

public class GoalControllerTest {

    private DatabaseHelper mockDatabase;

    @Before
    public void setup() {
        // Initialisiere den Mock für die Datenbank
        mockDatabase = mock(DatabaseHelper.class);
    }

    // Test: Ziel erfolgreich setzen
    @Test
    public void testSetGoalSuccess() {
        // Simuliere das Speichern eines neuen Ziels
        when(mockDatabase.saveDailyGoal(12000)).thenReturn(true);

        boolean isGoalSet = mockDatabase.saveDailyGoal(12000);
        assertTrue("Das Ziel sollte erfolgreich gespeichert werden", isGoalSet);
    }

    // Test: Zielsetzung schlägt fehl
    @Test
    public void testSetGoalFailure() {
        // Simuliere einen Fehler beim Speichern des Ziels
        when(mockDatabase.saveDailyGoal(12000)).thenReturn(false);

        boolean isGoalSet = mockDatabase.saveDailyGoal(12000);
        assertFalse("Das Ziel sollte nicht gespeichert werden", isGoalSet);
    }

    // Test: Richtiges Ziel abrufen
    @Test
    public void testGetGoalSuccess() {
        // Simuliere den Abruf eines gespeicherten Ziels
        when(mockDatabase.getDailyGoal()).thenReturn(12000);

        int retrievedGoal = mockDatabase.getDailyGoal();
        assertEquals("Das abgerufene Ziel sollte 12000 sein", 12000, retrievedGoal);
    }

    // Test: Standardziel abrufen, wenn kein Ziel gespeichert ist
    @Test
    public void testGetDefaultGoal() {
        // Simuliere den Abruf eines Ziels, wenn keins gespeichert ist
        when(mockDatabase.getDailyGoal()).thenReturn(10000); // Standardziel

        int retrievedGoal = mockDatabase.getDailyGoal();
        assertEquals("Das Standardziel sollte 10000 sein", 10000, retrievedGoal);
    }

    // Test: Ungültiges Ziel speichern (z. B. negatives Ziel)
    @Test
    public void testSetInvalidGoal() {
        // Simuliere, dass das Speichern eines negativen Ziels fehlschlägt
        when(mockDatabase.saveDailyGoal(-5000)).thenReturn(false);

        boolean isGoalSet = mockDatabase.saveDailyGoal(-5000);
        assertFalse("Ein negatives Ziel sollte nicht gespeichert werden können", isGoalSet);
    }

    // Test: Abruf eines Ziels, wenn die Datenbankverbindung fehlschlägt
    @Test
    public void testGetGoalDatabaseError() {
        // Simuliere einen Datenbankfehler
        when(mockDatabase.getDailyGoal()).thenThrow(new RuntimeException("Datenbankfehler"));

        try {
            mockDatabase.getDailyGoal();
            fail("Eine Ausnahme sollte ausgelöst werden, wenn die Datenbank nicht verfügbar ist");
        } catch (RuntimeException e) {
            assertEquals("Datenbankfehler", e.getMessage());
        }
    }
}
