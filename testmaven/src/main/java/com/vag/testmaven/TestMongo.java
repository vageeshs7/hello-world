package com.vag.testmaven;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.ServerAddress;

import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;

import org.bson.Document;
import java.util.Arrays;
import com.mongodb.Block;

import com.mongodb.client.MongoCursor;
import static com.mongodb.client.model.Filters.*;
import com.mongodb.client.result.DeleteResult;
import static com.mongodb.client.model.Updates.*;
import com.mongodb.client.result.UpdateResult;
import java.util.ArrayList;
import java.util.List;

public class TestMongo {

	public static void main(String[] args) {
		MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
		 MongoDatabase database = mongoClient.getDatabase("test");
		 MongoCollection<Document> collection = database.getCollection("restaurants");
		 
		 Document myDoc = collection.find(eq("Bar", "Yes")).first();
		 System.out.println(myDoc.toJson());
		 
		 mongoClient.close();
		 
	}

}
