package org.example;

import com.alibaba.fastjson.JSON;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.example.dto.Student;
import org.example.utils.MongodbHelper;

import java.text.SimpleDateFormat;
import java.util.*;

/** Hello world! */
public class Domain {

  public static void main(String[] args) {
    Student student = new Student();
    student.setGmtCreate(new java.sql.Date(System.currentTimeMillis()));
    student.setName("eason");
    student.setOld(24);
    student.setGmtModify(getISOTime(new Date()));
    MongoDatabase database = MongodbHelper.getDB("admin");
    MongoCollection<Document> collection = database.getCollection("table_01");
    Map<String, Object> map = JSON.parseObject(JSON.toJSONString(student), Map.class);
    Document document = new Document();
    document.put(
        Long.toString(System.currentTimeMillis()), new java.sql.Date(System.currentTimeMillis()));
    //    document.putAll(map);
    collection.insertOne(document);
    FindIterable findIterable = collection.find();
    MongoCursor mongoCursor = findIterable.iterator();
    while (mongoCursor.hasNext()) {
      System.out.println(mongoCursor.next());
    }
    //    collection.drop();
    //    System.out.println("student = " + JSON.toJSONString(student));
  }

  public static String getISOTime(Date date) {
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
    df.setCalendar(new GregorianCalendar(new SimpleTimeZone(0, "GMT")));
    return df.format(date);
  }
}
