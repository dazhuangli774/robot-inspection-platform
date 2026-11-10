package com.wust30405.ricp.detect.factory;


import com.wust30405.ricp.detect.factory.utils.IocUtil;

public class DetectorFactory {
    public DetectorFactory() {

    }
    public static ADetector getClassByName(String instanceName)
    {
        Class cls= IocUtil.getClassByName(instanceName);

        return (ADetector)IocUtil.getClassInstanceByClass(cls);
    }
}
