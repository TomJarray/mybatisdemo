package com.zhongsou.mybatis;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        
        myMethod(new Object[]{1,2});
        
        System.out.println( "Hello World!" );
    }
    
    private static void myMethod(Object... args) {
    	System.out.println(args.toString());
    }
}
