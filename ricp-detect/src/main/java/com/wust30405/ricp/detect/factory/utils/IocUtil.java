package com.wust30405.ricp.detect.factory.utils;


public class IocUtil {

    public IocUtil() {

    }


    public static Class getClassByName(String ClassName)
    {
        Class cls=null;
        try
        {
            cls = Class.forName(ClassName);

        }
        catch(Exception e)
        {
            System.err.println(e);
        }
        return cls;
    }
    public static Object getClassInstanceByClass(Class cls)
    {
        Object obj=null;
        try
        {
            //cls = Class.forName(mName);
            //System.out.println("load the target class");
            obj=cls.newInstance();

        }
        catch(Exception e)
        {
            System.err.println(e);
        }
        return obj;
    }
}
