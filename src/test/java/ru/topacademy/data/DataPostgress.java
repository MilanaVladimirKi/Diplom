package ru.topacademy.data;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataPostgress {
    private static final QueryRunner runner = new QueryRunner();

    public DataPostgress() {}

    private static Connection getConn() throws SQLException {
        return DriverManager.getConnection("jdbc:postgresql://localhost:5432/app", "app", "pass");
    }
    public static DataHelper.TransactionStatus getLastPaymentStatus() {
        var codeSQL = "SELECT status FROM payment_entity ORDER BY created DESC LIMIT 1";
        try (var conn = getConn()) {
            return runner.query(conn, codeSQL, new BeanHandler<>(DataHelper.TransactionStatus.class));
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    public static DataHelper.TransactionStatus getLastCreditStatus() {
        var codeSQL = "SELECT status FROM credit_request_entity ORDER BY created DESC LIMIT 1";
        try (var conn = getConn()) {
            return runner.query(conn, codeSQL, new BeanHandler<>(DataHelper.TransactionStatus.class));
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    public static void clearDataBase() {
        try (var conn = getConn()) {
            runner.update(conn, "DELETE FROM credit_request_entity");
            runner.update(conn, "DELETE FROM payment_entity");
            runner.update(conn, "DELETE FROM credit_request_entity");
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }
}
