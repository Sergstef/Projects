package sample;

import java.sql.*;
import java.util.ArrayList;

public class DatabaseHandler {
    Connection connection;
    String login = "root";
    String pass = "666enilab";

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public Connection getConnection() throws ClassNotFoundException, SQLException {
        String connectionStr = "jdbc:mysql://localhost:3306/mydatabase";
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection(connectionStr, login, pass);
        return connection;
    }

    public Connection getConnection(String db) throws ClassNotFoundException, SQLException {
        String connectionStr = "jdbc:mysql://localhost:3306/" + db;
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection(connectionStr, login, pass);
        return connection;
    }

    public Connection getConnectionWithoutDatabase() throws ClassNotFoundException, SQLException {
        String connectionStr = "jdbc:mysql://localhost:3306/";
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection(connectionStr, login, pass);
        return connection;
    }

    public void createUser(User user) {
        CallableStatement callableStatement = null;
        if(user.getIdentity().equals("Администратор")) {
            String createSql = "{call createAdmin(?, ?)}";
            try {
                callableStatement = getConnection().prepareCall(createSql);
                callableStatement.setString(1, user.getLogin());
                callableStatement.setString(2, user.getPassword());
                callableStatement.execute();
            } catch (SQLException | ClassNotFoundException throwables) {
                throwables.printStackTrace();
            }
        } else {
            String createSql = "{call createUser(?, ?)}";
            try {
                callableStatement = getConnection().prepareCall(createSql);
                callableStatement.setString(1, user.getLogin());
                callableStatement.setString(2, user.getPassword());
                callableStatement.execute();
                System.out.println("qwerty");
            } catch (SQLException | ClassNotFoundException throwables) {
                throwables.printStackTrace();
            }
        }
        try {
            callableStatement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void signInUser(String login, String pass) {
        try {
            getConnection();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void createBase(String name) {
        CallableStatement callableStatement = null;
        String createSql = "{call createDatabase(?)}";
        try {
            callableStatement = getConnection().prepareCall(createSql);
            callableStatement.setString(1, name);
            callableStatement.execute();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        try {
            callableStatement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            PreparedStatement preparedStatement = getConnection(name).prepareStatement("CREATE PROCEDURE `createTables`()\n" +
                    "BEGIN\n" +
                    "\tCREATE TABLE автомобиль (\n" +
                    "    идентификатор int primary key auto_increment,\n" +
                    "    марка varchar(100) not null,\n" +
                    "    `атп_владелец` varchar(20) not null,\n" +
                    "    `скидка_%` int not null);\n" +
                    "    CREATE TABLE гараж (\n" +
                    "    идентификатор int primary key auto_increment,\n" +
                    "    номер varchar(100) not null,\n" +
                    "    расположение varchar(50) not null,\n" +
                    "    `комиссионные_%` int not null);\n" +
                    "    CREATE TABLE детали (\n" +
                    "    идентификатор int primary key auto_increment,\n" +
                    "    деталь varchar(50) not null,\n" +
                    "    продавец varchar(50) not null,\n" +
                    "    `стоимость_руб` int not null,\n" +
                    "    `макс_кол_во` int not null);\n" +
                    "    CREATE TABLE ремонт (\n" +
                    "    `номер_заказа` int primary key auto_increment,\n" +
                    "    автомобиль varchar(10) not null,\n" +
                    "    гараж int not null,\n" +
                    "    детали int not null,\n" +
                    "    `кол_во` int not null,\n" +
                    "    `общая_стоимость_руб` int not null);\n" +
                    "END");
            preparedStatement.executeUpdate();
            preparedStatement = getConnection(name).prepareStatement("CREATE PROCEDURE `addDataToFix` (car text, garage int, details int, number int, totalCost int)\n" +
                    "BEGIN\n" +
                    "\tinsert into ремонт (автомобиль, дата, гараж, детали, `кол_во`, `общая_стоимость_руб`) values (car, garage, details, number, totalCost);\n" +
                    "END\n");
            preparedStatement.executeUpdate();
            preparedStatement = getConnection(name).prepareStatement("CREATE PROCEDURE `addToCars` (mark text, owner text, sale int)\n" +
                    "BEGIN\n" +
                    "\tinsert into автомобиль (марка, `атп_владелец`, `скидка_%`) values (mark, owner, sale);\n" +
                    "END");
            preparedStatement.executeUpdate();
            preparedStatement = getConnection(name).prepareStatement("CREATE PROCEDURE `addToDetails` (detail text, seller text, cost int, maxDetails int)\n" +
                    "BEGIN\n" +
                    "\tinsert into детали (деталь, продавец, `стоимость_руб`, `макс_кол_во`) values (detail, seller, cost, maxDetails);\n" +
                    "END\n");
            preparedStatement.executeUpdate();
            preparedStatement = getConnection(name).prepareStatement("CREATE PROCEDURE `addToGarages` (number text, place text, money int)\n" +
                    "BEGIN\n" +
                    "\tinsert into гараж (номер, расположение, `комиссионные_%`) values (number, place, money);\n" +
                    "END\n");
            preparedStatement.executeUpdate();
            preparedStatement = getConnection(name).prepareStatement("CREATE PROCEDURE `clearTable`(name text)\n" +
                    "BEGIN\n" +
                    "\tSET @s = CONCAT('DELETE FROM ', name);\n" +
                    "    PREPARE stmt_create FROM @s;\n" +
                    "    EXECUTE stmt_create;\n" +
                    "    DEALLOCATE PREPARE stmt_create;\n" +
                    "END");
            preparedStatement.executeUpdate();
            preparedStatement = getConnection(name).prepareStatement("CREATE PROCEDURE `createAdmin`(IN `login` TEXT, IN `pass` TEXT)\n" +
                    "BEGIN\n" +
                    "\tDECLARE `_HOST` CHAR(14) DEFAULT '@\\'localhost\\'';\n" +
                    "    SET `login` := CONCAT('\\'', REPLACE(TRIM(`login`), CHAR(39), CONCAT(CHAR(92), CHAR(39))), '\\''),\n" +
                    "    `pass` := CONCAT('\\'', REPLACE(`pass`, CHAR(39), CONCAT(CHAR(92), CHAR(39))), '\\'');\n" +
                    "    SET @`sql` := CONCAT('CREATE USER ', `login`, `_HOST`, ' IDENTIFIED BY ', `pass`);\n" +
                    "    PREPARE `stmt` FROM @`sql`;\n" +
                    "    EXECUTE `stmt`;\n" +
                    "    SET @`sql` := CONCAT('GRANT All PRIVIlEGES ON *.* TO ', `login`, `_HOST`);\n" +
                    "    PREPARE `stmt` FROM @`sql`;\n" +
                    "    EXECUTE `stmt`;\n" +
                    "    DEALLOCATE PREPARE `stmt`;\n" +
                    "    FLUSH PRIVILEGES;\n" +
                    "END");
            preparedStatement.executeUpdate();
            preparedStatement = getConnection(name).prepareStatement("CREATE PROCEDURE `createDatabase`(name TEXT)\n" +
                    "BEGIN\n" +
                    "\tSET @s = CONCAT('CREATE DATABASE ', name);\n" +
                    "    PREPARE stmt_create FROM @s;\n" +
                    "    EXECUTE stmt_create;\n" +
                    "    DEALLOCATE PREPARE stmt_create;\n" +
                    "END");
            preparedStatement.executeUpdate();
            preparedStatement = getConnection(name).prepareStatement("CREATE PROCEDURE `createUser`(IN `login` TEXT, IN `pass` TEXT)\n" +
                    "BEGIN\n" +
                    "\tDECLARE `_HOST` CHAR(14) DEFAULT '@\\'localhost\\'';\n" +
                    "    SET `login` := CONCAT('\\'', REPLACE(TRIM(`login`), CHAR(39), CONCAT(CHAR(92), CHAR(39))), '\\''),\n" +
                    "    `pass` := CONCAT('\\'', REPLACE(`pass`, CHAR(39), CONCAT(CHAR(92), CHAR(39))), '\\'');\n" +
                    "    SET @`sql` := CONCAT('CREATE USER ', `login`, `_HOST`, ' IDENTIFIED BY ', `pass`);\n" +
                    "    PREPARE `stmt` FROM @`sql`;\n" +
                    "    EXECUTE `stmt`;\n" +
                    "    SET @`sql` := CONCAT('GRANT SELECT ON *.* TO ', `login`, `_HOST`);\n" +
                    "    PREPARE `stmt` FROM @`sql`;\n" +
                    "    EXECUTE `stmt`;\n" +
                    "    DEALLOCATE PREPARE `stmt`;\n" +
                    "    FLUSH PRIVILEGES;\n" +
                    "END");
            preparedStatement.executeUpdate();
            preparedStatement = getConnection(name).prepareStatement("CREATE PROCEDURE `deleteData`(tableItem text, field text, dataItem text)\n" +
                    "BEGIN\n" +
                    "\tSET @s = CONCAT('DELETE FROM ', tableItem, ' WHERE ', field, '=', dataItem);\n" +
                    "    PREPARE stmt_create FROM @s;\n" +
                    "    EXECUTE stmt_create;\n" +
                    "    DEALLOCATE PREPARE stmt_create;\n" +
                    "END");
            preparedStatement.executeUpdate();
            preparedStatement = getConnection(name).prepareStatement("CREATE PROCEDURE `deleteDatabase`(name TEXT)\n" +
                    "BEGIN\n" +
                    "\tSET @s = CONCAT('DROP DATABASE ', name);\n" +
                    "    PREPARE stmt_create FROM @s;\n" +
                    "    EXECUTE stmt_create;\n" +
                    "    DEALLOCATE PREPARE stmt_create;\n" +
                    "END");
            preparedStatement.executeUpdate();
            preparedStatement = getConnection(name).prepareStatement("CREATE PROCEDURE `insertData`()\n" +
                    "BEGIN\n" +
                    "\tinsert into автомобиль (марка, `атп_владелец`, `скидка_%`) values ('Газ-24', 'АТП1', 4);\n" +
                    "\tinsert into автомобиль (марка, `атп_владелец`, `скидка_%`) values ('Газ-52', 'АТП1', 0);\n" +
                    "\tinsert into автомобиль (марка, `атп_владелец`, `скидка_%`) values ('Зил-130', 'АТП3', 3);\n" +
                    "\tinsert into автомобиль (марка, `атп_владелец`, `скидка_%`) values ('Зил-133', 'АТП4', 5);\n" +
                    "\tinsert into автомобиль (марка, `атп_владелец`, `скидка_%`) values ('Газ-1222', 'АТП5', 4);\n" +
                    "    insert into ремонт (автомобиль, гараж, детали, `кол_во`, `общая_стоимость_руб`) values (4, 3, 7, 7, 77000);\n" +
                    "\tinsert into ремонт (автомобиль, гараж, детали, `кол_во`, `общая_стоимость_руб`) values (3, 3, 2, 4, 20000);\n" +
                    "\tinsert into ремонт (автомобиль, гараж, детали, `кол_во`, `общая_стоимость_руб`) values (3, 5, 4, 1, 7000);\n" +
                    "\tinsert into ремонт (автомобиль, гараж, детали, `кол_во`, `общая_стоимость_руб`) values (3, 6, 5, 6, 30000);\n" +
                    "\tinsert into ремонт (автомобиль, гараж, детали, `кол_во`, `общая_стоимость_руб`) values (2, 6, 7, 9, 99000);\n" +
                    "\tinsert into ремонт (автомобиль, гараж, детали, `кол_во`, `общая_стоимость_руб`) values (4, 6, 6, 8, 40000);\n" +
                    "\tinsert into ремонт (автомобиль, гараж, детали, `кол_во`, `общая_стоимость_руб`) values (1, 7, 3, 33000);\n" +
                    "\tinsert into ремонт (автомобиль, гараж, детали, `кол_во`, `общая_стоимость_руб`) values (1, 3, 3, 2, 80000);\n" +
                    "\tinsert into ремонт (автомобиль, гараж, детали, `кол_во`, `общая_стоимость_руб`) values (3, 6, 1, 16, 160000);\n" +
                    "\tinsert into ремонт (автомобиль, гараж, детали, `кол_во`, `общая_стоимость_руб`) values (3, 5, 5, 21, 105000);\n" +
                    "\tinsert into ремонт (автомобиль, гараж, детали, `кол_во`, `общая_стоимость_руб`) values (2, 1, 1, 5, 50000);\n" +
                    "\tinsert into ремонт (автомобиль, гараж, детали, `кол_во`, `общая_стоимость_руб`) values (5, 6, 2, 3, 15000);\n" +
                    "\tinsert into ремонт (автомобиль, гараж, детали, `кол_во`, `общая_стоимость_руб`) values (3, 2, 6, 6, 30000);\n" +
                    "\tinsert into ремонт (автомобиль, гараж, детали, `кол_во`, `общая_стоимость_руб`) values (4, 5, 1, 4, 40000);\n" +
                    "\tinsert into ремонт (автомобиль, гараж, детали, `кол_во`, `общая_стоимость_руб`) values (4, 1, 7, 7, 77000);\n" +
                    "\tinsert into ремонт (автомобиль, гараж, детали, `кол_во`, `общая_стоимость_руб`) values (5, 1, 6, 1, 5000);\n" +
                    "\tinsert into ремонт (автомобиль, гараж, детали, `кол_во`, `общая_стоимость_руб`) values (2, 4, 2, 1, 5000);\n" +
                    "    insert into гараж (номер, расположение, `комиссионные_%`) values ('N1', 'АТП1', 3);\n" +
                    "\tinsert into гараж (номер, расположение, `комиссионные_%`) values ('N2', 'АТП1', 3);\n" +
                    "\tinsert into гараж (номер, расположение, `комиссионные_%`) values ('N1', 'АТП2', 4);\n" +
                    "\tinsert into гараж (номер, расположение, `комиссионные_%`) values ('N3', 'АТП2', 4);\n" +
                    "\tinsert into гараж (номер, расположение, `комиссионные_%`) values ('N4', 'АТП4', 4);\n" +
                    "\tinsert into гараж (номер, расположение, `комиссионные_%`) values ('N5', 'АТП5', 3);\n" +
                    "    insert into детали (деталь, продавец, `стоимость_руб`, `макс_кол_во`) values ('Трубка', 'АТП1', 10000, 100);\n" +
                    "\tinsert into детали (деталь, продавец, `стоимость_руб`, `макс_кол_во`) values ('Скоба', 'АТП1', 5000, 230);\n" +
                    "\tinsert into детали (деталь, продавец, `стоимость_руб`, `макс_кол_во`) values ('Картер', 'АТП3', 40000, 70);\n" +
                    "\tinsert into детали (деталь, продавец, `стоимость_руб`, `макс_кол_во`) values ('Штуцер', 'АТП2', 7000, 200);\n" +
                    "\tinsert into детали (деталь, продавец, `стоимость_руб`, `макс_кол_во`) values ('Прокладка', 'АТП2', 5000, 1200);\n" +
                    "\tinsert into детали (деталь, продавец, `стоимость_руб`, `макс_кол_во`) values ('Пробка', 'АТП1', 5000, 300);\n" +
                    "\tinsert into детали (деталь, продавец, `стоимость_руб`, `макс_кол_во`) values ('Толкатель', 'АТП1', 11000, 120);\n" +
                    "END");
            preparedStatement.executeUpdate();
            preparedStatement = getConnection(name).prepareStatement("CREATE PROCEDURE `selectSomething`(tableItem text, field text)\n" +
                    "BEGIN\n" +
                    "\tSET @s = CONCAT('SELECT ', field, ' FROM ', tableItem);\n" +
                    "    PREPARE stmt_create FROM @s;\n" +
                    "    EXECUTE stmt_create;\n" +
                    "    DEALLOCATE PREPARE stmt_create;\n" +
                    "END");
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void deleteBase(String name) {
        CallableStatement callableStatement = null;
        String createSql = "{call deleteDatabase(?)}";
        try {
            callableStatement = getConnection().prepareCall(createSql);
            callableStatement.setString(1, name);
            callableStatement.execute();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        try {
            callableStatement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void createTables(String name) {
        CallableStatement callableStatement = null;
        String createSql = "{call createTables()}";
        try {
            callableStatement = getConnection(name).prepareCall(createSql);
            callableStatement.execute();
            createSql = "{call insertData()}";
            callableStatement = getConnection(name).prepareCall(createSql);
            callableStatement.execute();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        try {
            callableStatement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void findData(String name, String table, String field) {
        CallableStatement callableStatement = null;
        String createSql = "{call selectSomething(?,?)}";
        try {
            callableStatement = getConnection(name).prepareCall(createSql);
            callableStatement.setString(1, table);
            callableStatement.setString(2, field);
            ResultSet a = callableStatement.executeQuery();
            while(a.next()) {
                System.out.println(a.getString(1));
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        try {
            callableStatement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void clearTable(String name, String table) {
        CallableStatement callableStatement = null;
        String createSql = "{call clearTable(?)}";
        try {
            callableStatement = getConnection(name).prepareCall(createSql);
            callableStatement.setString(1, table);
            callableStatement.execute();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        try {
            callableStatement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void addDataToCars(String name, String mark, String owner, int sale) {
        CallableStatement callableStatement = null;
        String createSql = "{call addToCars(?,?,?)}";
        try {
            callableStatement = getConnection(name).prepareCall(createSql);
            callableStatement.setString(1, mark);
            callableStatement.setString(2, owner);
            callableStatement.setInt(3, sale);
            callableStatement.execute();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        try {
            callableStatement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void addDataToGarages(String name, String number, String place, int money) {
        CallableStatement callableStatement = null;
        String createSql = "{call addToGarages(?,?,?)}";
        try {
            callableStatement = getConnection(name).prepareCall(createSql);
            callableStatement.setString(1, number);
            callableStatement.setString(2, place);
            callableStatement.setInt(3, money);
            callableStatement.execute();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        try {
            callableStatement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void addDataToDetails(String name, String detail, String seller, int cost, int maxDetails) {
        CallableStatement callableStatement = null;
        String createSql = "{call addToDetails(?,?,?,?)}";
        try {
            callableStatement = getConnection(name).prepareCall(createSql);
            callableStatement.setString(1, detail);
            callableStatement.setString(2, seller);
            callableStatement.setInt(3, cost);
            callableStatement.setInt(4, maxDetails);
            callableStatement.execute();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        try {
            callableStatement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void addDataToFix(String name, String car, int garage, int details, int number, int totalCost) {
        CallableStatement callableStatement = null;
        String createSql = "{call addToFix(?,?,?,?,?)}";
        try {
            callableStatement = getConnection(name).prepareCall(createSql);
            callableStatement.setString(1, car);
            callableStatement.setInt(2, garage);
            callableStatement.setInt(3, details);
            callableStatement.setInt(4, number);
            callableStatement.setInt(5, totalCost);
            callableStatement.execute();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        try {
            callableStatement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void deleteData(String name, String table, String field, String data) {
        CallableStatement callableStatement = null;
        String createSql = "{call deleteData(?,?,?)}";
        try {
            callableStatement = getConnection(name).prepareCall(createSql);
            callableStatement.setString(1, table);
            callableStatement.setString(2, field);
            callableStatement.setString(3, data);
            callableStatement.execute();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        try {
            callableStatement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void updateData(String name, String table, String column, String data) {
        CallableStatement callableStatement = null;
        String createSql = "{call deleteData(?,?,?)}";
        try {
            callableStatement = getConnection(name).prepareCall(createSql);
            callableStatement.setString(1, table);
            callableStatement.setString(2, column);
            callableStatement.setString(3, data);
            callableStatement.execute();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        try {
            callableStatement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
