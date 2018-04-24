package dxp.utils;

import dxp.model.Config;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class YamlUtil {
    public static Config getConfig(String configFile) throws Exception {
        Yaml yaml = new Yaml();
        if (!configFile.endsWith(".yaml")) {
            configFile += ".yaml";
        }
        InputStream in = new FileInputStream(new File(System.getProperty("user.dir") +
                File.separator +
                "config" +
                File.separator +
                configFile));
        return yaml.loadAs(in, Config.class);
    }
}
