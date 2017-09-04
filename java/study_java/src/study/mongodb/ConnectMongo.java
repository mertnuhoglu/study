package study.mongodb;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoDatabase;

import java.util.Arrays;

public class ConnectMongo {
    public static void main(String[] args) {
//        getMongoDatabase();
        connectMongoOnDockerContainer();
    }

    private static void connectMongoOnDockerContainer() {
        MongoDatabase db = getMongoDatabase("mongo", 27017, "beyp_poc", "myUserAdmin", "12345");
        System.out.println("db = " + db);
    }

    public static MongoDatabase getMongoDatabase() {
        String host = "localhost";
        int port = 27018;
        String db = "dentas";
        String user = "myUserAdmin";
        String password = "12345";
        return getMongoDatabase(host, port, db, user, password);
    }

    public static MongoDatabase getMongoDatabase(String host, int port, String db, String user, String password) {
        MongoCredential credential = MongoCredential.createCredential(user, db, password.toCharArray());

        MongoClient mongoClient = new MongoClient(new ServerAddress(host, port), Arrays.asList(credential));
        MongoDatabase mongoDB = mongoClient.getDatabase(db);
        return mongoDB;
    }
}
