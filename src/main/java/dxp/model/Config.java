package dxp.model;

import java.util.List;

public class Config {
    private String host;

    private String userName;

    private String passwd;

    private List<String> localFiles;

    private String remoteDirectory;

    private String localDirectory;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public List<String> getLocalFiles() {
        return localFiles;
    }

    public void setLocalFiles(List<String> localFiles) {
        this.localFiles = localFiles;
    }

    public String getRemoteDirectory() {
        return formatDirectory(remoteDirectory);
    }

    public void setRemoteDirectory(String remoteDirectory) {
        this.remoteDirectory = remoteDirectory;
    }

    public String getLocalDirectory() {
        return formatDirectory(localDirectory);
    }

    public void setLocalDirectory(String localDirectory) {
        this.localDirectory = localDirectory;
    }

    private static String formatDirectory(String directory) {
        if (!directory.endsWith("/")) {
            directory += "/";
        }
        return directory;
    }
}
