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

    //方式一
    @Test
    public void testConnection() throws SQLException {
        Driver driver = new com.mysql.jdbc.Driver();//驱动

        //jdbc:mysql:协议
        //localhost:协议
        //3306.默认端口号
        //test 数据库
        String url = "jdbc:mysql://localhost:3306/test";//统一资源定位符
        Properties info = new Properties();
        info.setProperty("user", "root");
        info.setProperty("password", "123456");

        Connection conn = driver.connect(url, info);

        System.out.println(conn);
    }

    //方式二：对方式一的迭代，在如下程序中不出现第三方的API，使之具有更强的可移植性
    @Test
    public void testConnection2() throws Exception {
        //1.获取Driver实现类对象，使用反射
        Class clazz = Class.forName("com.mysql.jdbc.Driver");
        Driver driver = (Driver) clazz.newInstance();
        //2.提供要连接的数据库
        String url = "jdbc:mysql://localhost:3306/test";
        //3.提供连接所需要的用户名和密码
        Properties info = new Properties();
        info.setProperty("user", "root");
        info.setProperty("password", "123456");
        //4.获取连接
        Connection conn = driver.connect(url, info);
        System.out.println(conn);


    }

    //方式三：使用驱动管理器管理
    @Test
    public void testConnection3() throws Exception {
        //1.获取Driver实现类对象，使用反射
        Class clazz = Class.forName("com.mysql.jdbc.Driver");
        Driver driver = (Driver) clazz.newInstance();
        //2.提供要连接的数据库
        String url = "jdbc:mysql://localhost:3306/test";
        String user = "root";
        String password = "123456";
        //注册驱动
        DriverManager.registerDriver(driver);

        Connection conn = DriverManager.getConnection(url, user, password);
        System.out.println(conn);

    }

    //方式三：使用驱动管理器管理
    @Test
    public void testConnection4() throws Exception {
        String url = "jdbc:mysql://localhost:3306/test";
        String user = "root";
        String password = "123456";
        //获取Driver实现类对象，使用反射
        Class.forName("com.mysql.jdbc.Driver");
        //Driver driver = (Driver) clazz.newInstance();
        //3.注册驱动
        //DriverManager.registerDriver(driver);
    /*
        可以注释掉上述代码的原因，是因为在mysql的Driver类中声明有：
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
        //1.加载配置文件
        InputStream is =
                ConnectionTest.class.getClassLoader().getResourceAsStream("jdbc.properties");
        Properties pros = new Properties();
        pros.load(is);
        //2.读取配置信息
        String user = pros.getProperty("user");
        String password = pros.getProperty("password");
        String url = pros.getProperty("url");
        String driverClass = pros.getProperty("driverClass");
        //3.加载驱动
        Class.forName(driverClass);
        //4.获取连接
        Connection conn = DriverManager.getConnection(url, user, password);
        System.out.println(conn);
    }

}
