package com.wust30405.ricp.common.config;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("yxw-ricp")

public class Global {
    /**
     * 项目名称
     */
    private static String name;

    /**
     * 版本
     */
    private static String version;

    /**
     * 版权年份
     */
    private static String copyrightYear;

    /**
     * 实例演示开关
     */
    private static boolean demoEnabled;

    /**
     * 获取地址开关
     */
    private static boolean addressEnabled;

    public static String getName() {
        return name;
    }

    public static void setName(String name) {
        Global.name = name;
    }

    public static String getVersion() {
        return version;
    }

    public static void setVersion(String version) {
        Global.version = version;
    }

    public static String getCopyrightYear() {
        return copyrightYear;
    }

    public static void setCopyrightYear(String copyrightYear) {
        Global.copyrightYear = copyrightYear;
    }

    public static boolean isDemoEnabled() {
        return demoEnabled;
    }

    public static void setDemoEnabled(boolean demoEnabled) {
        Global.demoEnabled = demoEnabled;
    }

    public static boolean isAddressEnabled() {
        return addressEnabled;
    }

    public static void setAddressEnabled(boolean addressEnabled) {
        Global.addressEnabled = addressEnabled;
    }
}
