/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package liquibase.database.ext;

import java.util.Arrays;

import liquibase.CatalogAndSchema;
import liquibase.database.AbstractJdbcDatabase;
import liquibase.database.DatabaseConnection;
import liquibase.exception.DatabaseException;
import liquibase.logging.LogFactory;
import liquibase.logging.Logger;
import liquibase.statement.DatabaseFunction;
import liquibase.structure.core.Schema;

/**
 * @author Michal Bocek
 * @since 1.0.0
 */
public class TimestenDatabase extends AbstractJdbcDatabase {

    private static final String PRODUCT_NAME = "TimesTen";
    private static final Logger log = LogFactory.getInstance().getLog();;

    public TimestenDatabase() {
        super.setCurrentDateTimeFunction("TT_SYSDATE");
        // Setting list of Timesten native functions
        dateFunctions.add(new DatabaseFunction("SYSDATE"));
        dateFunctions.add(new DatabaseFunction("GETDATE"));
    }

    /* (non-Javadoc)
     * @see liquibase.database.Database#getDefaultDriver(java.lang.String)
     */
    public String getDefaultDriver(String url) {
        if (url.startsWith("jdbc:timesten")) {
            return "com.timesten.jdbc.TimesTenClientDriver";
        }
        return null;
    }

    /* (non-Javadoc)
     * @see liquibase.database.Database#getDefaultPort()
     */
    public Integer getDefaultPort() {
        return 53389;
    }

    /* (non-Javadoc)
     * @see liquibase.database.Database#getShortName()
     */
    public String getShortName() {
        return PRODUCT_NAME.toLowerCase();
    }

    /* (non-Javadoc)
     * @see liquibase.database.Database#isCorrectDatabaseImplementation(liquibase.database.DatabaseConnection)
     */
    public boolean isCorrectDatabaseImplementation(DatabaseConnection connection) throws DatabaseException {
        return PRODUCT_NAME.equals(connection.getDatabaseProductName());
    }

    /* (non-Javadoc)
     * @see liquibase.database.Database#supportsInitiallyDeferrableColumns()
     */
    public boolean supportsInitiallyDeferrableColumns() {
        return true;
    }

    /* (non-Javadoc)
     * @see liquibase.database.Database#supportsTablespaces()
     */
    public boolean supportsTablespaces() {
        return false;
    }

    /* (non-Javadoc)
     * @see liquibase.servicelocator.PrioritizedService#getPriority()
     */
    public int getPriority() {
        return PRIORITY_DEFAULT;
    }

    /* (non-Javadoc)
     * @see liquibase.database.AbstractJdbcDatabase#getDefaultDatabaseProductName()
     */
    @Override
    protected String getDefaultDatabaseProductName() {
        return PRODUCT_NAME;
    }

    @Override
    public void setConnection(DatabaseConnection conn) {
        setReserverdWords();
        super.setConnection(conn);
    }

    private void setReserverdWords() {
        addReservedWords(Arrays.asList("AGING", "ALL", "ANY", "AS", "BETWEEN", "BINARY_DOUBLE_INFINITY",
                "BINARY_DOUBLE_NAN", "BINARY_FLOAT_INFINITY", "BINARY_FLOAT_NAN", "CASE", "CHAR", "COLUMN",
                "CONNECTION", "CONSTRAINT", "CROSS", "CURRENT_SCHEMA", "CURRENT_USER", "CURSOR", "DATASTORE_OWNER",
                "DATE", "DECIMAL", "DEFAULT", "DESTROY", "DISTINCT", "FIRST", "FLOAT", "FOR", "FOREIGN", "FROM",
                "GROUP", "HAVING", "INNER", "INTEGER", "INTERSECT", "INTERVAL", "INTO", "IS", "JOIN", "LEFT", "LIKE",
                "LONG", "MINUS", "NULL", "ON", "ORA_SYSDATE", "ORDER", "PRIMARY", "PROPAGATE", "PUBLIC", "READONLY",
                "RIGHT", "ROWNUM", "ROWS", "SELECT", "SELF", "SESSION_USER", "SET", "SMALLINT", "SOME", "SYSDATE",
                "SYSTEM_USER", "TO", "TT_SYSDATE", "UID", "UNION", "UNIQUE", "UPDATE", "USER", "USING", "VARCHAR",
                "WHEN", "WHERE", "WITH"));
    }

    @Override
    public boolean supportsSchemas() {
        return false;
    }

    @Override
    public boolean supportsAutoIncrement() {
        return false;
    }

    @Override
    public boolean supportsDDLInTransaction() {
        return false;
    }

    @Override
    public boolean supportsPrimaryKeyNames() {
        return false;
    }

    @Override
    public boolean jdbcCallsCatalogsSchemas() {
        return true;
    }

    @Override
    public String getJdbcCatalogName(CatalogAndSchema schema) {
        return null;
    }

    @Override
    public String getJdbcSchemaName(CatalogAndSchema schema) {
        return correctObjectName(schema.getCatalogName() == null ? schema.getSchemaName() : schema.getCatalogName(),
                Schema.class);
    }

    @Override
    public String getDefaultCatalogName() {// NOPMD
        return super.getDefaultCatalogName() == null ? null : super.getDefaultCatalogName().toUpperCase();
    }

}
