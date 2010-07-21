package org.springframework.orm.ibatis3.transaction;

import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.Transaction;

import java.util.Properties;

import java.sql.Connection;

public class SpringManagedTransactionFactory implements TransactionFactory {

    public SpringManagedTransactionFactory() {}

    @Override
    public Transaction newTransaction(Connection conn, boolean autoCommit) {
        return new SpringManagedTransaction(conn);
    }

    @Override
    public void setProperties(Properties props) {}

}
