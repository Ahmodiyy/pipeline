package com.example.pipeline;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "resources.features", glue = "com.example.pipeline.features")
public class AcceptanceTest { }
