package com.flab.stargram.controller;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.MockitoAnnotations;

public abstract class BaseMockTest {
    @BeforeEach
    void SetUp() {
        MockitoAnnotations.openMocks(this);
    }
}
