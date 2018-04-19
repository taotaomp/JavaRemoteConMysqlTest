package cn.PApudding.JavaRemoteConMySql;

import java.sql.*;

public class DBCtl implements DBStr {
    private Connection connection = null;
    public boolean conMySql() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        connection = DriverManager.getConnection(Url,Usr,Psd);

        return !connection.isClosed();
    }

    public boolean disConMysql() throws SQLException {
        connection.close();

        return connection.isClosed();
    }

    public String selectAll() throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet  = statement.getResultSet();
        StringBuilder result =new StringBuilder("编号\t姓名\t性别\n");
        resultSet = statement.executeQuery("select * from demo;");
        while (resultSet.next()){
            result.append(resultSet.getString(1)+"\t"+
                            resultSet.getString(2)+"\t"+
                            resultSet.getString(3)+"\n");
        }
        return result.toString();
    }

    public static void main(String argv[]){
        try {
            DBCtl dbCtl = new DBCtl();
            if(dbCtl.conMySql()){
                System.out.println("connected");
                System.out.println(dbCtl.selectAll());

            }
            if(dbCtl.disConMysql()){
                System.out.println("closed");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
