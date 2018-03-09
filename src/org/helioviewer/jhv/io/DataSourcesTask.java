package org.helioviewer.jhv.io;

import java.io.InputStream;

import org.helioviewer.jhv.log.Log;
import org.helioviewer.jhv.time.TimeUtils;

import org.everit.json.schema.Schema;
import org.everit.json.schema.Validator;
import org.everit.json.schema.ValidationException;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;

public class DataSourcesTask implements Runnable {

    private final Validator validator;
    private final String url;
    private final String schemaName;

    public DataSourcesTask(String server, Validator _validator) {
        validator = _validator;
        url = DataSources.getServerSetting(server, "API.getDataSources");
        schemaName = DataSources.getServerSetting(server, "schema");
        Thread t = new Thread(this, server);
        t.start();
    }

    @Override
    public void run() {
        try (InputStream is = FileUtils.getResource(schemaName)) {
            JSONObject rawSchema = JSONUtils.get(is);
            SchemaLoader schemaLoader = SchemaLoader.builder().schemaJson(rawSchema).addFormatValidator(new TimeUtils.SQLDateTimeFormatValidator()).build();
            Schema schema = schemaLoader.load().build();

            JSONObject jo = JSONUtils.get(url);
            validator.performValidation(schema, jo);
        } catch (ValidationException e) {
            Log.error("Server " + url + " " + e);
            e.getCausingExceptions().stream().map(ValidationException::getMessage).forEach(Log::error);
        } catch (Exception e) {
            Log.error(url + " : " + e);
        }
    }

}
