package ru.topacademy.data;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataPostgress {
    private static final QueryRunner runner = new QueryRunner();

    // конструктор класса
    private DataPostgress() {}

    private static Connection getConn() throws SQLException {
        return DriverManager.getConnection("jdbc:postgresql://localhost:5432/app", "app", "pass");
    }

    // метод выполняет sql запрос и возвращает объект с результатом последней оплаты
    public static DataHelper.TransactionStatus getLastPaymentStatus() {
        var codeSQL = "SELECT status FROM payment_entity ORDER BY created DESC LIMIT 1";
        try (var conn = getConn()) {
            return runner.query(conn, codeSQL, new BeanHandler<>(DataHelper.TransactionStatus.class));
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    // метод выплняет sql запрос и возвращает объект с результатом последней оплаты в кредит
    public static DataHelper.TransactionStatus getLastCreditStatus() {
        var codeSQL = "SELECT status FROM credit_request_entity ORDER BY created DESC LIMIT 1";
        try (var conn = getConn()) {
            return runner.query(conn, codeSQL, new BeanHandler<>(DataHelper.TransactionStatus.class));
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    // метод очистки БД
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
