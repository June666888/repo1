package com.june.test;

import com.june.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

public class MyBatisTest {
    /**
     * 快速入门测试方法
     * 查询所有用户
     **/
    @Test
    public void myBatisQuickStart() throws IOException {
        //1.加载核心配置文件
        InputStream resourceAsStream = Resources.getResourceAsStream("SqlMapConfig.xml");

        //2.获取sqlSessionFactory工厂对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);

        //3.获取sqlSession会话对象
        SqlSession sqlSession = sqlSessionFactory.openSession();

        //4.执行sql String类型参数statementid：由namespace.id组成
        List<User> users = sqlSession.selectList("userMapper.findAll");

        //5.打印结果
        for (User user : users) {
            System.out.println(user);
        }

        //6.释放资源
        sqlSession.close();
    }

    /**
     *  测试新增用户
     */
    @Test
    public void testSave() throws IOException {
        //1.加载核心配置文件
        InputStream resourceAsStream = Resources.getResourceAsStream("SqlMapConfig.xml");

        //2.获取sqlSessionFactory工厂对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);

        //3.获取sqlSession会话对象
        SqlSession sqlSession = sqlSessionFactory.openSession(true);

        User user=new User();
        user.setUsername("Joy");
        user.setBirthday(new Date());
        user.setSex("女");
        user.setAddress("四川达州");

        //4.执行sql
        sqlSession.insert("userMapper.saveUser", user);//新增用户调用insert()方法

        //5.手动提交事务（插入操作涉及数据库数据变化，所以要使用sqlSession对象显示的提交事务）
        //sqlSession.commit();//sqlSessionFactory.openSession(true)使得可以不写这句手动提交事务的代码了

        //6.释放资源
        sqlSession.close();
    }

    /**
     *  测试更新用户
     */
    @Test
    public void testUpdate() throws IOException {
        //1.加载核心配置文件
        InputStream resourceAsStream = Resources.getResourceAsStream("SqlMapConfig.xml");

        //2.获取sqlSessionFactory工厂对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);

        //3.获取sqlSession会话对象
        SqlSession sqlSession = sqlSessionFactory.openSession();

        User user=new User();
        user.setId(5);
        user.setUsername("Nancy");
        user.setBirthday(new Date());
        user.setSex("女");
        user.setAddress("江苏南京");

        //4.执行sql
        sqlSession.update("userMapper.updateUser", user);//修改用户调用update()方法

        //5.手动提交事务（更新操作涉及数据库数据变化，所以要使用sqlSession对象显示的提交事务）
        sqlSession.commit();

        //6.释放资源
        sqlSession.close();
    }


    /**
     *  测试删除用户
     */
    @Test
    public void testDelete() throws IOException {
        //1.加载核心配置文件
        InputStream resourceAsStream = Resources.getResourceAsStream("SqlMapConfig.xml");

        //2.获取sqlSessionFactory工厂对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);

        //3.获取sqlSession会话对象
        SqlSession sqlSession = sqlSessionFactory.openSession();


        //4.执行sql
        sqlSession.update("userMapper.deleteUser", 5);//删除用户，指定id值

        //5.手动提交事务（更新操作涉及数据库数据变化，所以要使用sqlSession对象显示的提交事务）
        sqlSession.commit();

        //6.释放资源
        sqlSession.close();
    }
}
