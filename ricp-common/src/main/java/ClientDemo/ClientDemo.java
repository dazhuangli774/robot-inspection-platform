package ClientDemo;

/**
 * 海康威视摄像头 SDK 示例类（ClientDemo）的编译占位实现。
 *
 * 原项目从厂商 SDK 示例中直接引用该类，但仓库未包含它，导致工程无法编译。
 * 此占位类保留原有方法签名，供本地演示使用：不打开任何网络连接，
 * CameraInit / SingleCapture 等一律返回成功，不会真正抓图。
 *
 * 真实部署时请删除本包，改用厂商 SDK。
 */
public class ClientDemo {

    public ClientDemo() {
    }

    public boolean CameraInit(String ip, String port, String user, String password) {
        return true;
    }

    public boolean SingleCapture(String filePath) {
        return true;
    }

    public boolean StopRealPlay() {
        return true;
    }

    public boolean LogoutV30() {
        return true;
    }

    public boolean CleanUp() {
        return true;
    }
}
