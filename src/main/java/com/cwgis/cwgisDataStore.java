package com.cwgis;

import org.geotools.data.store.ContentDataStore;
import org.geotools.data.store.ContentEntry;
import org.geotools.data.store.ContentFeatureSource;
import org.opengis.feature.type.Name;

import java.io.IOException;
import java.util.List;

public class cwgisDataStore
        extends ContentDataStore
{

    @Override
    protected List<Name> createTypeNames() throws IOException {
        return null;
    }

    @Override
    protected ContentFeatureSource createFeatureSource(ContentEntry contentEntry) throws IOException {
        return null;
    }
}
