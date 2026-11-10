package com.wust30405.ricp.detect.factory;


import com.google.gson.JsonObject;
import com.sun.jna.Library;
import com.sun.jna.Native;
import com.wust30405.ricp.detect.utils.Box;

public class DigitMeterDetect extends ADetector {
    public interface Clibrary extends Library {

        //com/sun/jna/win32-x86-64/Dll2
        ADetector.Clibrary INSTANTCE = (ADetector.Clibrary) Native.load("meterDetect.dll", ADetector.Clibrary.class);

        int init(String configurationFilename, String weightsFilename, int gpuId);
        JsonObject detect_image(String filename, Box[] container);

    }

    @Override
    public int initDetector(String configurationFilename, String weightsFilename, int gpuId) {
        return ADetector.Clibrary.INSTANTCE.init(configurationFilename, weightsFilename, gpuId);
    }

    @Override
    public JsonObject detectImage(String imgPath, Box[] container) {
        return ADetector.Clibrary.INSTANTCE.detect_image(imgPath, container);
    }
}
