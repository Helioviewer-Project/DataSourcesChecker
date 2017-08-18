package org.helioviewer.jhv.io;

import java.io.InputStream;

import org.everit.json.schema.Schema;
import org.everit.json.schema.ValidationException;
import org.everit.json.schema.loader.SchemaLoader;
import org.helioviewer.jhv.base.DownloadStream;
import org.helioviewer.jhv.base.FileUtils;
import org.helioviewer.jhv.base.JSONUtils;
import org.helioviewer.jhv.base.logging.Log;
import org.helioviewer.jhv.base.time.TimeUtils;
import org.json.JSONObject;
import org.json.JSONTokener;

public class DataSourcesTask implements Runnable {

    private final String url;
    private final String schemaName;

    public DataSourcesTask(String server) {
        url = DataSources.getServerSetting(server, "API.getDataSources");
        schemaName = DataSources.getServerSetting(server, "schema");
        Thread t = new Thread(this, server);
        t.start();
    }

    @Override
    public void run() {
        try (InputStream is = FileUtils.getResourceInputStream(schemaName)) {
            JSONObject rawSchema = new JSONObject(new JSONTokener(is));
            SchemaLoader schemaLoader = SchemaLoader.builder().schemaJson(rawSchema).addFormatValidator(new TimeUtils.SQLDateTimeFormatValidator()).build();
            Schema schema = schemaLoader.load().build();
            JSONObject json = JSONUtils.getJSONStream(new DownloadStream(url).getInput());
            schema.validate(json);
        } catch (ValidationException e) {
            Log.error(e);
            e.getCausingExceptions().stream().map(ValidationException::getMessage).forEach(Log::error);
        } catch (Exception e) {
            Log.error(url + " : " + e);
        }
    }

}
