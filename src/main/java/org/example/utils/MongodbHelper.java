package org.example.utils;

import cn.hutool.core.util.StrUtil;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
public class MongodbHelper {
  private static String host = "127.0.0.1";
  private static String port = "27017";

  public static Map<String, MongoCollection<Document>> maps = new HashMap<>();

  public static MongoClient mongoClient;

  public static Map<String, MongoDatabase> dbMaps = new HashMap<>();

  public static MongoCollection<Document> getCollectionByDB(String db, String col) {
    if (StrUtil.isEmpty(col)) {
      System.out.println("获取连接错误");
      return null;
    }
    MongoCollection<Document> collection = maps.get(col);
    if (collection != null) {
      return collection;
    }
    synchronized (MongodbHelper.class) {
      if (collection == null) {

        MongoDatabase md = getDB(db);

        collection = md.getCollection(col);
      }
      return collection;
    }
  }

  public static MongoCollection<Document> getCollection(String col) {
    return getCollectionByDB("daily", col);
  }

  public static MongoDatabase getDB(String db) {
    if (StrUtil.isEmpty(db)) {
      System.out.println("获取连接错误");
      return null;
    }
    if (dbMaps.get(db) != null) {
      return dbMaps.get(db);
    } else {
      MongoDatabase md = getMongoClientInstace().getDatabase(db);
      dbMaps.put(db, md);
      return md;
    }
  }

  private static MongoClient getMongoClientInstace() {
    MongoCredential mongoCredential=MongoCredential.createCredential("admin","admin","123456".toCharArray());
    if (mongoClient == null) {
      synchronized (MongodbHelper.class) {
        if (mongoClient == null) {
          System.out.println("初始化mongoClient" + host + ":" + port);
          mongoClient = new MongoClient(new ServerAddress(host, Integer.parseInt(port)), Arrays.asList(mongoCredential));
        }
      }
    }
    return mongoClient;
  }

  public static DBCollection getDBCollection(String col) {
    return mongoClient.getDB("daily").getCollection(col);
  }

  public static DBCollection getDBCollectionByDB(String db, String col) {
    return mongoClient.getDB(db).getCollection(col);
  }

  public void setHost(String host) {
    MongodbHelper.host = host;
  }

  public void setPort(String port) {
    System.out.println(port);
    MongodbHelper.port = port;
  }
}
