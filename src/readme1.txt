扩展GeoServer数据源
    今天我们来讲讲怎么扩展GeoServer（简称GS）的数据源。大家都知道，GS支持多种数据源，而且都提供了友好的界面供操作。下面我们就来简单介绍一下，如何把自定义的数据源增加到GS中，让我们可以在统一风格的界面上愉快的操作。

要完成这个任务，需要如下四个步骤（以矢量数据为例）：

1 创建一个类实现接口org.geotools.data.DataStoreFactorySpi（栅格数据实现org.geotools.coverage.grid.io.GridFormatFactorySpi）；

2 在目录META-INF/services/下增加文件org.geotools.data.DataStoreFactorySpi，内容为刚创建的类的完全类名；

3 将编译好的classes复制到<GeoServer install path>\webapps\geoserver\WEB-INF\classes下，或者复制打包的jar文件到<GeoServer install path>\webapps\geoserver\WEB-INF\lib目录下；

4 重启GS服务。

这里提供一个例子：

package wj.demo.geocsv;

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

public class GeoCSVFileDataStoreFactory extends AbstractDataStoreFactory
        implements DataStoreFactorySpi {

    static final Logger LOGGER = Logging.getLogger("wj.demo.geosvc");

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
        return "CSV file";
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
        return null;
    }

    public DataStore createNewDataStore(Map<String, Serializable> arg0)
            throws IOException {
        // TODO Auto-generated method stub
        return null;
    }

}
检验成果：
用数据源创建界面的前后对比照来说明：