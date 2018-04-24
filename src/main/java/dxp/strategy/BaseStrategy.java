package dxp.strategy;

import ch.ethz.ssh2.ChannelCondition;
import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.SCPClient;
import ch.ethz.ssh2.Session;
import com.google.common.collect.Lists;
import dxp.model.Config;
import dxp.utils.YamlUtil;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public abstract class BaseStrategy {
    protected String configName;
    protected Config config;
    protected Connection connection;

    protected List<String> localFiles = Lists.newArrayList();


    public BaseStrategy(String configName) {
        this.configName = configName;
    }

    public void restart(boolean isReplace) throws Exception {
        System.out.println("restart service ...");
        stop();
        start(isReplace);
        System.out.println("restart service complete.");
    }

    abstract public void start(boolean isReplace) throws Exception;

    abstract public void stop() throws Exception;

    protected void init() throws Exception {
        config = YamlUtil.getConfig(configName);
        connection = new Connection(config.getHost());
        connection.connect();
        if (!authenticateWithPassword()) {
            throw new RuntimeException("Authentication failed. Please check hostName, userName and passwd");
        }
    }

    abstract protected String getCmd(boolean isStart);

    private boolean authenticateWithPassword() throws IOException {
        return connection.authenticateWithPassword(config.getUserName(), config.getPasswd());
    }

    // 上传服务器
    public void transferFiles() throws IOException {
        SCPClient scpClient = connection.createSCPClient();
        String remoteDirectory = config.getRemoteDirectory() + "lib";
        for (String localFile : config.getLocalFiles()) {
            scpClient.put(localFile, remoteDirectory);
        }
    }

    // 启动shell执行命令行
    public void execCmd(boolean isStart) throws Exception {
        String cmd = getCmd(isStart);
        Session session = connection.openSession();
        session.requestPTY("bash");
        session.startShell();
        PrintWriter out = new PrintWriter(session.getStdin());
        out.println(cmd);
        out.println("exit");
        out.close();
        //等待，除非1.连接关闭；2.进程状态为退出；3.超时
        session.waitForCondition(ChannelCondition.CLOSED
                | ChannelCondition.EXIT_STATUS , 30000);
    }
}
