package com.cepheid.training;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DiagnosticWorkflowServiceTest {
    @Test
    void statusIsReady() {
        DiagnosticWorkflowService service = new DiagnosticWorkflowService();
        assertEquals("READY", service.getStatus());
    }
}
