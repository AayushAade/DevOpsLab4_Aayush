package com.aayush.devops;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class AppInfoTest {

    @Test
    void verifyStudentInformation() {
        String message = AppInfo.getMessage();

        assertTrue(message.contains("Aayush Aade"));
        assertTrue(message.contains("34101"));
    }
}
