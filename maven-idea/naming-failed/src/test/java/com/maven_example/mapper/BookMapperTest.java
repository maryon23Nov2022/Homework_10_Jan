package com.maven_example.mapper;

import com.maven_example.pojo.Book;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

public class BookMapperTest{
    private static SqlSessionFactory sqlSessionFactory;
    static{
        String resource = "com/maven_example/mybatis-config.xml";
        try{
            InputStream inputStream = Resources.getResourceAsStream(resource);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        } catch(IOException e){
            e.printStackTrace();
        }
    }
    @Test
    public void testInsert(){

        Book book = new Book(null, "西游记", "吴承恩", "This is a description.", 12, "", "");

        SqlSession sqlSession = sqlSessionFactory.openSession();
        BookMapper bookMapper = sqlSession.getMapper(BookMapper.class);
        String bookName = book.getBookName();
        Book bookInDB = bookMapper.selectByBookName(bookName);
        if(bookInDB == null){
            bookMapper.insert(book);
            sqlSession.commit();
            System.out.printf("%d\n", 1);
        } else{
            System.out.printf("%d\n", 0);
        }
        sqlSession.close();
    }

    @Test
    public void testSelectByBookName(){
        String bookName = "阿甘正传";
        SqlSession sqlSession = sqlSessionFactory.openSession();
        BookMapper bookMapper = sqlSession.getMapper(BookMapper.class);
        Book book = bookMapper.selectByBookName(bookName);
        if(book.getBookName() == null) System.out.printf("error\n");
        System.out.printf("%d %s %s %s %d\n", book.getId(), book.getBookName(), book.getAuthor(), book.getDescription(), book.getSurplus());
    }

}
