package org.example;

import oracle.ucp.jdbc.PoolDataSource;
import oracle.ucp.jdbc.PoolDataSourceFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class UcpExample {
    public static void main(String[] args) throws Exception {
        PoolDataSource pds = PoolDataSourceFactory.getPoolDataSource();
        pds.setConnectionFactoryClassName("oracle.jdbc.pool.OracleDataSource");
        pds.setURL("jdbc:oracle:thin:@localhost:1521/xepdb1");
        pds.setUser("ucptest");
        pds.setPassword("ucptest");
        try (Connection conn = pds.getConnection()) {
            try (Statement stmt = conn.createStatement()) {
                ResultSet rs = stmt.executeQuery("SELECT table_name FROM all_tables");
                while (rs.next()) {
                    System.out.println(rs.getString("table_name"));
                }
            }
        }
    }
}
