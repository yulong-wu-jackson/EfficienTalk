package use_case.connect;

import entity.CommonSendToServerData;

public class ConnectInputData implements CommonSendToServerData {
    final private String ipAddress;
    final private String port;
    final private String flag;

    public ConnectInputData(String ipAddress, String port, String flag) {
        this.ipAddress = ipAddress;
        this.port = port;
        this.flag = flag;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public String getPort() {
        return port;
    }

    public String getFlag() {
        return flag;
    }
}
