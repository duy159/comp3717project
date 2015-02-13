package com.example.andrew.comp3717project;


import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

public class MongoAccess
{
    public String getUser(String loginUser, String loginPass)
    {
        String user,pass,email,fname,lname,error = "error";

        long count;
        MongoClient mongo = new MongoClient("localhost", 27017);
        //  MongoCredential credential = MongoCredential.createMongoCRCredential(userName, database, password);
        //   MongoClient mongoClient = new MongoClient(new ServerAddress(), Arrays.asList(credential));
        MongoDatabase db = mongo.getDatabase("workoutbuddies");
        DBCollection coll = (DBCollection) db.getCollection("registeredUsers");
        count = coll.getCount();

        BasicDBObject query = new BasicDBObject("user", loginUser);
        DBCursor cursor;
        cursor = coll.find(query);

        try
        {
            while(cursor.hasNext())
            {
                user = String.valueOf(cursor.next());
                return user;
            }
        }
        finally
        {
            cursor.close();
        }
        return error;
    }


    public void pushUser(String user, String pass, String email, String fname, String lname)
    {
        MongoClient mongo = new MongoClient("localhost", 27017);
        //  MongoCredential credential = MongoCredential.createMongoCRCredential(userName, database, password);
        //   MongoClient mongoClient = new MongoClient(new ServerAddress(), Arrays.asList(credential));
        MongoDatabase db = mongo.getDatabase("workoutbuddies");
        DBCollection coll = (DBCollection) db.getCollection("registeredUsers");
        BasicDBObject doc = new BasicDBObject("FirstName", fname)
                .append("LastName", lname)
                .append("User", user)
                .append("Pass",pass)
                .append("Email", email);
        coll.insert(doc);
    }
}

