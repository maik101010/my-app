package com.mycompany.app;

import java.util.function.Consumer;
import java.util.stream.Stream;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Stream.of(1).peek(
                ((Consumer<Integer>)(i1)->{i1=i1+1;}).andThen((i2)->{i2=i2+2;})).forEach(System.out::print);
//        System.out.println( "Hello World!" );
    }
}
