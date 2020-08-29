package com.example.demo;

import javax.sql.DataSource;

import org.seasar.doma.SingletonConfig;
import org.seasar.doma.jdbc.Config;
import org.seasar.doma.jdbc.dialect.Dialect;
import org.seasar.doma.jdbc.dialect.PostgresDialect;
import org.seasar.doma.jdbc.tx.LocalTransactionDataSource;
import org.seasar.doma.jdbc.tx.LocalTransactionManager;
import org.seasar.doma.jdbc.tx.TransactionManager;

/**
 * @author Yuta Takayama
 */
@SingletonConfig
public class AppConfig implements Config {

    private static final AppConfig INSTANCE = new AppConfig();

    private final Dialect dialect;
    private final LocalTransactionDataSource dataSource;
    private final TransactionManager transactionManager;

    private AppConfig() {
        dialect = new PostgresDialect();
        dataSource = new LocalTransactionDataSource("jdbc:postgresql://localhost:5432/sample", "postgres", "postgres");
        transactionManager = new LocalTransactionManager(dataSource.getLocalTransaction(getJdbcLogger()));
    }

    @Override
    public Dialect getDialect() {
        return dialect;
    }

    @Override
    public DataSource getDataSource() {
        return dataSource;
    }

    @Override
    public TransactionManager getTransactionManager() {
        return transactionManager;
    }

    public static AppConfig singleton() {
        return INSTANCE;
    }
}