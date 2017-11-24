package com.chenpan.after.dao.impl;

import com.chenpan.after.dao.LoginDao;
import com.chenpan.after.db.JdbcUtil;
import com.chenpan.after.network.Response;
import com.chenpan.after.utils.StringUtil;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.HashMap;
import java.util.Map;

/**
 * @作者:陈攀
 * @公司：四川星盾科技股份有限公司
 * @创建时间: 2017/11/21 15:59
 * @描述：
 */
@Repository
public class LoginDaoImpl implements LoginDao {


    //查询数据库
    public Map<String, String> login(String username, String password) throws Exception {
        Map<String, String> map=null;
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SELECT * FROM USER  A WHERE A.ACCOUNT=? AND A.PWD=? \n");
        try {
            connection = JdbcUtil.getConnection();
            ps = connection.prepareStatement(stringBuilder.toString());
            ps.setString(1, username);
            ps.setString(2, password);
            rs = ps.executeQuery();
            ResultSetMetaData rmd = rs.getMetaData();
            if (rs.next()) {
                map = new HashMap<String, String>(rmd.getColumnCount());
                for (int i = 1; i <= rmd.getColumnCount(); i++) {
                    map.put(rmd.getColumnName(i).toLowerCase(), rs.getString(i));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            JdbcUtil.releaseConnection(connection, ps, rs);
        }
        return map;
    }
}
