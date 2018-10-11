# Validate Helioviewer servers responses to getDataSources requests

Connects to GSFC, IAS, and ROB Helioviewer servers and requests the list of available datasets. It validates the responses against a JSON schema.

Run it as: `java -Djava.awt.headless=true -jar DataSourcesChecker.jar`.
