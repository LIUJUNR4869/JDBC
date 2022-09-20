package jdbc.connection;


import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executor;

/**
 * @ClassName ConnectionTest
 * @Description T000
 * @Author admin
 * @Date 2022/9/20 17:01
 * @Version 1.0
 **/
public class ConnectionTest {

    //��ʽһ
    @Test
    public void testConnection() throws SQLException {
        Driver driver = new com.mysql.jdbc.Driver();//����

        //jdbc:mysql:Э��
        //localhost:Э��
        //3306.Ĭ�϶˿ں�
        //test ���ݿ�
        String url = "jdbc:mysql://localhost:3306/test";//ͳһ��Դ��λ��
        Properties info = new Properties();
        info.setProperty("user", "root");
        info.setProperty("password", "123456");

        Connection conn = driver.connect(url, info);

        System.out.println(conn);
    }

    //��ʽ�����Է�ʽһ�ĵ����������³����в����ֵ�������API��ʹ֮���и�ǿ�Ŀ���ֲ��
    @Test
    public void testConnection2() throws Exception {
        //1.��ȡDriverʵ�������ʹ�÷���
        Class clazz = Class.forName("com.mysql.jdbc.Driver");
        Driver driver = (Driver) clazz.newInstance();
        //2.�ṩҪ���ӵ����ݿ�
        String url = "jdbc:mysql://localhost:3306/test";
        //3.�ṩ��������Ҫ���û���������
        Properties info = new Properties();
        info.setProperty("user", "root");
        info.setProperty("password", "123456");
        //4.��ȡ����
        Connection conn = driver.connect(url, info);
        System.out.println(conn);


    }

    //��ʽ����ʹ����������������
    @Test
    public void testConnection3() throws Exception {
        //1.��ȡDriverʵ�������ʹ�÷���
        Class clazz = Class.forName("com.mysql.jdbc.Driver");
        Driver driver = (Driver) clazz.newInstance();
        //2.�ṩҪ���ӵ����ݿ�
        String url = "jdbc:mysql://localhost:3306/test";
        String user = "root";
        String password = "123456";
        //ע������
        DriverManager.registerDriver(driver);

        Connection conn = DriverManager.getConnection(url, user, password);
        System.out.println(conn);

    }

    //��ʽ����ʹ����������������
    @Test
    public void testConnection4() throws Exception {
        String url = "jdbc:mysql://localhost:3306/test";
        String user = "root";
        String password = "123456";
        //��ȡDriverʵ�������ʹ�÷���
        Class.forName("com.mysql.jdbc.Driver");
        //Driver driver = (Driver) clazz.newInstance();
        //3.ע������
        //DriverManager.registerDriver(driver);
    /*
        ����ע�͵����������ԭ������Ϊ��mysql��Driver���������У�
        static {
            try {
                DriverManager.registerDriver(new Driver());
            } catch (SQLException var1) {
                throw new RuntimeException("Can't register driver!");
            }
        }
    */

        Connection conn = DriverManager.getConnection(url, user, password);
        System.out.println(conn);
    }

    @Test
    public void testConnection5() throws IOException, ClassNotFoundException, SQLException {
        //1.���������ļ�
        InputStream is =
                ConnectionTest.class.getClassLoader().getResourceAsStream("jdbc.properties");
        Properties pros = new Properties();
        pros.load(is);
        //2.��ȡ������Ϣ
        String user = pros.getProperty("user");
        String password = pros.getProperty("password");
        String url = pros.getProperty("url");
        String driverClass = pros.getProperty("driverClass");
        //3.��������
        Class.forName(driverClass);
        //4.��ȡ����
        Connection conn = DriverManager.getConnection(url, user, password);
        System.out.println(conn);
    }

}
