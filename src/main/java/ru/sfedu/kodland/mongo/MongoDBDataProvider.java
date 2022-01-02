package ru.sfedu.kodland.mongo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bson.Document;
import org.joda.time.DateTime;
import ru.sfedu.kodland.model.Result;
import ru.sfedu.kodland.utils.ConfigurationUtil;
import ru.sfedu.kodland.Constants;
import java.io.IOException;
import java.util.*;

public class MongoDBDataProvider {
    private static final Logger log = LogManager.getLogger(MongoDBDataProvider.class);
    private MongoClient client;
    private MongoDatabase database;

    private  <T> Map<String, Object> convertEntityToMap(T entity) {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.convertValue(entity, Map.class);
    }

    public HistoryContent initHistoryContentTrue(Object object,String objectName,String className,String methodName){
        DateTime dateTime = new DateTime();
        Map<String,Object> map = new HashMap<>();
        HistoryContent historyContent = new HistoryContent();
        historyContent.setId(new Random().nextLong());
        historyContent.setClassName(className);
        historyContent.setCreatedDate(dateTime.toString());
        historyContent.setActor("System");
        historyContent.setMethodName(methodName);
        historyContent.setObject(map);
        historyContent.setStatus(Result.SUCCESS.toString());
        return historyContent;
    }
    
    public HistoryContent initHistoryContentFalse(String string,String className,String methodName){
        DateTime dateTime = new DateTime();
        Map<String,Object> map = new HashMap<>();
        HistoryContent historyContent = new HistoryContent();
        historyContent.setId(new Random().nextLong());
        historyContent.setClassName(className);
        historyContent.setCreatedDate(dateTime.toString());
        historyContent.setActor("System");
        historyContent.setMethodName(methodName);
        historyContent.setObject(map);
        historyContent.setStatus(Result.UNSUCCESS.toString());
        return historyContent;
    }
    
    public List<Document> readAll()  {
        initConnection(Constants.MONGO_DB_TEST);
        MongoCollection<Document> mongoCollection = database.getCollection(Constants.MONGO_DB_TEST);
        FindIterable<Document> documents = mongoCollection.find();
        return documents.into(new ArrayList<Document>());
    }
    
    public void initConnection(String string) {
        try {
            this.client = new MongoClient(new MongoClientURI(ConfigurationUtil.getConfigurationEntry(Constants.MONGO_DB)));
            this.database = client.getDatabase(string);
        } catch (IOException e){
            log.error(e);
        }
    }
    
    public void closeConnection(){
        this.client.close();
    }
    
    public void insertRecord(HistoryContent historyContent,String string) {
        initConnection(string);
        MongoCollection<Document> mongoCollection = database.getCollection(string);
        mongoCollection.insertOne(new Document(this.convertEntityToMap(historyContent)));
        closeConnection();
    }
}
