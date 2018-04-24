package dxp;

import dxp.strategy.DealMaker;
import org.apache.commons.lang.StringUtils;

public class Main {

    public static void main(String[] args) throws Exception {
        String mode = args[0];
        String option = args[1];
        String isReplace = args[2];

        if (StringUtils.equals(option, "start")) {
            start(mode, isReplace);
        } else if (StringUtils.equals(option, "restart")) {
            restart(mode, isReplace);
        } else if (StringUtils.equals(option, "stop")) {
            stop(mode);
        }
    }

    /**
     * 启动服务器
     * @param mode  (swap|gate)
     * @param isReplaceStr (yes|no)
     * @throws Exception
     */
    private static void start(String mode, String isReplaceStr) throws Exception {
        boolean isReplace = StringUtils.equals(isReplaceStr, "yes");
        DealMaker.getStrategy(mode).start(isReplace);
    }

    private static void restart(String mode, String isReplaceStr) throws Exception {
        boolean isReplace = StringUtils.equals(isReplaceStr, "yes");
        DealMaker.getStrategy(mode).restart(isReplace);
    }

    private static void stop(String mode) throws Exception {
        DealMaker.getStrategy(mode).stop();
    }
}
