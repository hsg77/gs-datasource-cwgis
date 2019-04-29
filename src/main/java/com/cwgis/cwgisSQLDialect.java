package com.cwgis;

import org.geotools.factory.Hints;
import org.geotools.jdbc.BasicSQLDialect;
import org.geotools.jdbc.JDBCDataStore;
import org.locationtech.jts.geom.Envelope;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryFactory;
import org.opengis.feature.type.GeometryDescriptor;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class cwgisSQLDialect
        extends BasicSQLDialect
{
    protected cwgisSQLDialect(JDBCDataStore dataStore)
    {
        super(dataStore);
    }
    @Override
    public void encodeGeometryValue(Geometry geometry, int i, int i1, StringBuffer stringBuffer) throws IOException {

    }

    @Override
    public void encodeGeometryEnvelope(String s, String s1, StringBuffer stringBuffer) {

    }

    @Override
    public Envelope decodeGeometryEnvelope(ResultSet resultSet, int i, Connection connection) throws SQLException, IOException {
        return null;
    }

    @Override
    public Geometry decodeGeometryValue(GeometryDescriptor geometryDescriptor, ResultSet resultSet, String s, GeometryFactory geometryFactory, Connection connection, Hints hints) throws IOException, SQLException {
        return null;
    }
}
