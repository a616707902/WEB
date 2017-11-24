package com.chenpan.after.db;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class JdbcUtil {


    /* 数据源 */
    private static ComboPooledDataSource dataSourceYw = null;

    /**
     * 静态代码块，创建数据库连接池
     */
    static{

        //只被创建一次
        dataSourceYw = new ComboPooledDataSource("rest3p0");

        Properties pro = new Properties();

        InputStream in = null;

        try {

            // 读取江西车驾管属性文件.properties
            in = JdbcUtil.class.getResourceAsStream("/chenpan.properties");
            pro.load(in);     // 加载属性列表

            dataSourceYw.setJdbcUrl(pro.getProperty("jdbc.url"));
            dataSourceYw.setUser(pro.getProperty("jdbc.username"));
            dataSourceYw.setPassword(pro.getProperty("jdbc.password"));
            dataSourceYw.setAcquireIncrement(Integer.parseInt(pro.getProperty("acquireIncrement")));
            dataSourceYw.setInitialPoolSize(Integer.parseInt(pro.getProperty("initialPoolSize")));
            dataSourceYw.setMinPoolSize(Integer.parseInt(pro.getProperty("minPoolSize")));
            dataSourceYw.setMaxPoolSize(Integer.parseInt(pro.getProperty("maxPoolSize")));
            dataSourceYw.setMaxStatements(Integer.parseInt(pro.getProperty("maxStatements")));
            dataSourceYw.setMaxIdleTime(Integer.parseInt(pro.getProperty("maxIdleTime")));
            dataSourceYw.setIdleConnectionTestPeriod(Integer.parseInt(pro.getProperty("idleConnectionTestPeriod")));
            dataSourceYw.setAcquireRetryAttempts(Integer.parseInt(pro.getProperty("acquireRetryAttempts")));
            dataSourceYw.setMaxStatementsPerConnection(Integer.parseInt(pro.getProperty("maxStatementsPerConnection")));


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * 功能描述：获取解密
     * 作者：齐遥遥
     * 时间：2017-04-11
     * @param oldPassword
     * @return
     */
//    public static String getPassword(String oldPassword) {
//
//        String pwd = "";
//
//        try {
//
//            DESUtil desUtil = new DESUtil(PRIVATE_KEY);
//            pwd = desUtil.decrypt(oldPassword);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return pwd;
//    }

    /**
     * 功能描述：得到连接
     * 作者：齐遥遥
     * 时间：2017/2/14 11:21
     */
    public static Connection getConnection() throws Exception{
        return dataSourceYw.getConnection();
    }

    /**
     * 功能描述：释放资源
     * 作者：齐遥遥
     * 时间：2017/2/14 11:22
     */
    public static void releaseConnection(Connection connection, PreparedStatement ps, ResultSet rs) {

        try {

            if (null != rs) {
                rs.close();
            }

            if(null != ps) {
                ps.close();
            }

            if (null != connection) {
                connection.close();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
