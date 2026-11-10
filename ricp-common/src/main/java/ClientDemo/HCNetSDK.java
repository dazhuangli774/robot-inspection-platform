package ClientDemo;

/**
 * 海康威视 HCNetSDK 的编译占位实现。
 *
 * 原项目依赖厂商 SDK（ClientDemo / HCNetSDK），该 SDK 未包含在仓库中，
 * 缺少时整个工程无法编译。此占位类仅用于本地跑通界面与接口，
 * 不连接任何摄像头：所有调用直接返回成功，错误码恒为 0。
 *
 * 真实部署时请删除本包，改用厂商 SDK。
 */
public class HCNetSDK {

    public static final HCNetSDK INSTANCE = new HCNetSDK();

    private HCNetSDK() {
    }

    public boolean NET_DVR_Init() {
        return true;
    }

    public boolean NET_DVR_Cleanup() {
        return true;
    }

    public int NET_DVR_GetLastError() {
        return 0;
    }
}
