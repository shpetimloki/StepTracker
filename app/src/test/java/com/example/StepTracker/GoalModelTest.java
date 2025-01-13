package com.example.StepTracker;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import com.example.StepTracker.DatabaseHelper;
import org.junit.Before;
import org.junit.Test;

public class GoalModelTest {

    private DatabaseHelper mockDatabase;

    @Before
    public void setup() {
        mockDatabase = mock(DatabaseHelper.class);
    }

    @Test
    public void testSaveGoal() {
        when(mockDatabase.saveDailyGoal(15000)).thenReturn(true);
        boolean isSaved = mockDatabase.saveDailyGoal(15000);
        assertTrue(isSaved);
    }

    @Test
    public void testLoadGoal() {
        when(mockDatabase.getDailyGoal()).thenReturn(15000);
        int goal = mockDatabase.getDailyGoal();
        assertEquals(15000, goal);
    }
}
