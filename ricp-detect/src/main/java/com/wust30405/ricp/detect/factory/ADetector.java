package com.wust30405.ricp.detect.factory;


import com.google.gson.JsonObject;
import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Platform;
import com.wust30405.ricp.detect.utils.Box;


public abstract class ADetector {
    public interface Clibrary extends Library {

        //com/sun/jna/win32-x86-64/Dll2
        Clibrary INSTANTCE = (Clibrary)Native.load((Platform.isWindows()?"WMsteveDLL":"c"), Clibrary.class);

        int init(String configurationFilename, String weightsFilename, int gpuId);
        JsonObject detect_image(String filename, Box[] container);

    }


    public int initDetector(String configurationFilename, String weightsFilename, int gpuId) {
        return Clibrary.INSTANTCE.init(configurationFilename, weightsFilename, gpuId);
    }

    public JsonObject detectImage(String imgPath, Box[] container) {
        return Clibrary.INSTANTCE.detect_image(imgPath, container);
    }
}
