package com.cwgis;

import org.geotools.data.DataStoreFactorySpi;
import org.geotools.jdbc.JDBCDataStore;
import org.geotools.jdbc.JDBCDataStoreFactory;
import org.geotools.jdbc.SQLDialect;

import java.io.IOException;
import java.util.Map;

//com.cwgis.SqlServerDataStoreFactory
//自定义添加geoserver的数据源  for sqlserver
// (定义未实现还不能添加到DataStoreFactorySpi文件中)
public class cwgisSqlServerDataStoreFactory
        extends JDBCDataStoreFactory
        implements DataStoreFactorySpi
{
    @Override
    public String getDescription()
    {
       return "cwgis  SqlServerDataStoreFactory";
    }

    @Override
    protected String getDatabaseID() {
        return null;
    }

    @Override
    protected String getDriverClassName() {
        return null;
    }

    @Override
    protected SQLDialect createSQLDialect(JDBCDataStore jdbcDataStore) {
        //return null;
        return new cwgisSQLDialect(jdbcDataStore);
    }

    @Override
    protected String getValidationQuery() {
        return null;
    }
    public boolean canProcess(Map params)
    {
        return true;
        /*
        if (!super.canProcess(params)) {
            return false; // was not in agreement with getParametersInfo
        }
        return checkDBType(params); */
    }
    @Override
    protected String getJDBCUrl(Map params)
            throws IOException
    {
        String URL="";
         // jdbc url
        String host = (String) HOST.lookUp(params);
        Integer port = (Integer) PORT.lookUp(params);
        String db = (String) DATABASE.lookUp(params);
        String url = URL + host;
        if ( port != null ) {
         url += ":" + port;
         }
         url += ";DatabaseName=" + db;
         return url;
     }
}
