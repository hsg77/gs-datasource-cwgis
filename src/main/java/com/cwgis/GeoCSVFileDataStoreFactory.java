package com.cwgis;

import java.awt.RenderingHints.Key;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.util.Collections;
import java.util.Map;
import java.util.logging.Logger;

import org.geotools.data.AbstractDataStoreFactory;
import org.geotools.data.DataAccessFactory;
import org.geotools.data.DataStore;
import org.geotools.data.DataStoreFactorySpi;
import org.geotools.data.DataUtilities;
import org.geotools.util.KVP;
import org.geotools.util.logging.Logging;

//com.cwgis.GeoCSVFileDataStoreFactory
public class GeoCSVFileDataStoreFactory
        extends AbstractDataStoreFactory
        implements DataStoreFactorySpi
{
    static final Logger loger = Logging.getLogger("com.cwgis.GeoCSVFileDataStoreFactory");

    public static final DataAccessFactory.Param URLP = new DataAccessFactory.Param(
            "url", URL.class, "url to a .csv file", true, null, new KVP(
            new Object[] { "ext", "csv" }));

    public static final DataAccessFactory.Param GEO_FIELINDEX = new DataAccessFactory.Param(
            "geofield", Integer.class, "Index of geometry field.", true, "0",
            new KVP(new Object[] { "level", "advanced" }));

    public boolean canProcess(Map params) {
        if (!super.canProcess(params))
            return false;
        try {
            URL url = (URL) URLP.lookUp(params);
            if (canProcess(url)) {
                return true;
            }

            Object geofld = GEO_FIELINDEX.lookUp(params);
            File dir = DataUtilities.urlToFile(url);

            return (dir.isDirectory())
                    && ((geofld == null) || (geofld instanceof Integer));
        } catch (IOException e) {
        }
        return false;
    }

    public boolean canProcess(URL f) {
        return (f != null) && (f.getFile().toUpperCase().endsWith("CSV"));
    }

    public String getDescription() {
        return "Comma Separated Values(CSV) file (*.csv)";
    }

    public String getDisplayName() {
        return "cwgis csv file";
    }

    public Param[] getParametersInfo() {
        return new DataAccessFactory.Param[] { URLP, GEO_FIELINDEX };
    }

    public boolean isAvailable() {
        return true;
    }

    public Map<Key, ?> getImplementationHints() {
        return Collections.EMPTY_MAP;
    }

    public DataStore createDataStore(Map<String, Serializable> arg0)
            throws IOException {
        // TODO Auto-generated method stub
        //return null; //super.createDataStore(arg0);
        return new cwgisDataStore();
    }

    public DataStore createNewDataStore(Map<String, Serializable> arg0)
            throws IOException {
        // TODO Auto-generated method stub
        //return null;
        return new cwgisDataStore();
    }
}
