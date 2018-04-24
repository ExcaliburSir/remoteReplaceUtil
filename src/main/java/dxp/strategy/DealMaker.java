package dxp.strategy;

import org.apache.commons.lang.StringUtils;

public enum DealMaker {
    SWAP("swap", new SwapStrategy("swapConfig")),
    GATE("gate", new GateStrategy("gateConfig"));

    private String name;
    private BaseStrategy strategy;

    DealMaker(String name, BaseStrategy strategy) {
        this.name = name;
        this.strategy = strategy;
    }

    public String getName() {
        return name;
    }

    public BaseStrategy getStrategy() {
        return strategy;
    }

    public static BaseStrategy getStrategy(String name) {
        for (DealMaker maker : values()) {
            if (StringUtils.equals(maker.getName(), name)) {
                return maker.getStrategy();
            }
        }
        return null;
    }
}
