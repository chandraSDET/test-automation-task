package com.task.config;

public class ConfigManager {

    private static ConfigManager configManager = new ConfigManager();
    private static ConfigFileReader configFileReader;

    private ConfigManager() {
    }

    public static ConfigManager getInstance( ) {
        return configManager;
    }

    public ConfigFileReader getConfigReader() {
        return (configFileReader == null) ? new ConfigFileReader() : configFileReader;
    }
}
