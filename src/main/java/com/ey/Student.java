package com.ey;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by vvasund on 31.01.2018.
 */
public class Student {

  private String name;
  private List<String> book = new ArrayList<>();
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<String> getBook() {
    return book;
  }

  public void setBook(List<String> book) {
    this.book = book;
  }

  public void addBook(String name){
    this.book.add(name);
  }



  public static void main(String[] args) {

    Student obj1 = new Student();
    obj1.setName("mkyong");
    obj1.addBook("Java 8 in Action");
    obj1.addBook("Spring Boot in Action");
    obj1.addBook("Effective Java (2nd Edition)");

    Student obj2 = new Student();
    obj2.setName("zilap");
    obj2.addBook("Learning Python, 5th Edition");
    obj2.addBook("Effective Java (2nd Edition)");

    List<Student> list = new ArrayList<>();
    list.add(obj1);
    list.add(obj2);

    Map<String,Object> stringObjectMap= new HashMap<String,Object>();
    stringObjectMap.put("key1","value1");
    stringObjectMap.put("key2","value2");
    Map<String,Object> innerStringObjectMap = new HashMap<>();
    innerStringObjectMap.put("i1key3","value3");
    innerStringObjectMap.put("i1key4","value4");
    innerStringObjectMap.put("i1key5","value5");
    stringObjectMap.put("map1",innerStringObjectMap);
    Map<String,Object> innerStringObjectMap2 = new HashMap<>();
    innerStringObjectMap.put("i2key6","value6");
    innerStringObjectMap2.put("i2key7","value7");
    innerStringObjectMap.put("i1map2",innerStringObjectMap2);

/*    Map<String,Object> collect =
            stringObjectMap.entrySet().stream()
                    .map(x -> x.getValue())
                    .flatMap(x -> x)
                    .distinct();
                    //.collect(Collectors.toList());

    collect.forEach(x -> System.out.println(x));*/

    Map<Object, Object> collect = new HashMap<>();
    stringObjectMap.entrySet()
            .stream()
            .flatMap(FlatMap::flatten).forEach(it -> {
              collect.put(it.getKey(), it.getValue());
    });

    System.out.println(collect.toString());


  }
  static Stream<Object> flatten(Stream<Object> stream) {
    return stream.flatMap((o) ->
            (o instanceof Map) ? flatten(((Map<String, Object>)o).values().stream()) : Stream.of(o)
    );
  }


}
