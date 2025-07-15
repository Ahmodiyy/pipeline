package com.example.pipeline;

import org.springframework.stereotype.Service;

@Service
public class Calculator {
    public int sum(int a, int b) {
        return a + b;
    }
    public int minus(int a, int b) {
        return a - b;
    }
}
