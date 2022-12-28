package org.example.DBConnection;

public class ConfigPostgreSQL {
    /**
     *     Можно создать класс с конструктором, где подсовывать данные...
     *     Данная реализация не подходит для DataSource
     */
    public static String driver = "org.postgresql.Driver";
    public static String databaseSQL = "postgresql";
    public static String host = "localhost";
    public static String port = "5432";
    public static String database = "House_building";

    public static String user = "postgres";
    public static String pswd = "0706";

}
