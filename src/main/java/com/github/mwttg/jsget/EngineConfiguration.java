package com.github.mwttg.jsget;

import io.github.mwttg.sjge.configuration.Configuration;
import io.github.mwttg.sjge.configuration.GameWindowConfiguration;
import io.github.mwttg.sjge.configuration.OpenGlConfiguration;
import io.github.mwttg.sjge.configuration.ViewPortConfiguration;

public final class EngineConfiguration {

    private EngineConfiguration() {
    }

    public static Configuration create() {
        final var gameWindowConfig = new GameWindowConfiguration("Test-Window", 800, 600);
        final var openGlConfig = new OpenGlConfiguration(4, 1, true, false, true);
        final var viewPortConfig = new ViewPortConfiguration(60.0f, 0.01f, 300.0f);

        return new Configuration(gameWindowConfig, openGlConfig, viewPortConfig);
    }
}
