package dxp.strategy;

public class SwapStrategy extends BaseStrategy {

    public SwapStrategy(String configName) {
        super(configName);
    }

    @Override
    public void stop() throws Exception {
        // 初始化
        init();

        // 关闭程序
        execCmd(false);
    }

    @Override
    public void start(boolean isReplace) throws Exception {
        // 初始化
        init();

        // 上传服务器
        if (isReplace) {
            transferFiles();
        }

        // 执行程序
        execCmd(true);
    }

    @Override
    protected void init() throws Exception {
        super.init();
        localFiles.add(config.getLocalDirectory() + "XX.jar");
        // 添加需上传list
        config.setLocalFiles(localFiles);
    }

    @Override
    protected String getCmd(boolean isStart) {
        return isStart ? "cd "
                + config.getRemoteDirectory() + " && nohup ./start.sh > /dev/null 2>&1 &"
                : config.getRemoteDirectory() + "stop.sh";
    }
}
