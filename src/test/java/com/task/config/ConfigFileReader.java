package com.task.config;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

public class ConfigFileReader {

    private String url;
    private String email;
    private String password;
    private String siteName;

    private String blockColour;

    public String getBlockColour() {
        return blockColour;
    }

    public void setBlockColour(String blockColour) {
        this.blockColour = blockColour;
    }

    public String getBlueColour() {
        return blueColour;
    }

    public void setBlueColour(String blueColour) {
        this.blueColour = blueColour;
    }

    private String blueColour;

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }



    public ConfigFileReader() {
        Properties prop = new Properties();
        try {
            FileInputStream ip = new FileInputStream("./src/test/resources/config/config.properties");
            prop.load(new InputStreamReader(ip, StandardCharsets.UTF_8));

        } catch (Exception e) {
            e.printStackTrace();
        }
        setUrl(prop.getProperty("url"));
        setEmail(prop.getProperty("email"));
        setPassword(prop.getProperty("password"));
        setSiteName(prop.getProperty("siteName"));
        setBlockColour(prop.getProperty("blockColourReplacement"));
        setBlueColour(prop.getProperty("blueColourReplacement"));
    }

}
