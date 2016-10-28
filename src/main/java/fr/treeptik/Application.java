package fr.treeptik;

import com.google.inject.Injector;
import com.googlecode.jmxtrans.JmxTransformer;
import com.googlecode.jmxtrans.cli.JmxTransConfiguration;
import com.googlecode.jmxtrans.guice.JmxTransModule;
import com.googlecode.jmxtrans.model.JmxProcess;
import com.googlecode.jmxtrans.model.output.InfluxDbWriter;
import com.googlecode.jmxtrans.util.JsonUtils;

import java.io.File;

/**
 * Created by nicolas on 13/10/2016.
 */
public class Application {

    public static void main(String[] args) {
        try {
            //System.setProperty("jmxtrans.log.dir", "/Users/nicolas/software/cu-monitor");
            Injector injector = JmxTransModule.createInjector(new JmxTransConfiguration());
            JsonUtils jsonUtils = injector.getInstance(JsonUtils.class);
            JmxProcess process = jsonUtils.parseProcess(new File("src/main/resources/influxDB.json"));
            new JsonPrinter(System.out).print(process);
            JmxTransformer transformer = injector.getInstance(JmxTransformer.class);
            transformer.executeStandalone(process);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
