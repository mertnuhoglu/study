package mongodb;

import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.util.JSON;
import org.bson.Document;
import org.jongo.Jongo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


public class StudyDentasNakliyeFiyatlariRaporlama {
    public static void main(String[] args) throws ParseException {
        studyAggregateQueryUsingJsonAndMatch();
//        studyQueryByValidFromDate();
//        studyQueryByValidFromDateByJson();
//        studyQueryByValidFromDateByJsonByMilliseconds();
//        studyQueryUsingJongo();
//        studyQueryUsingJson();
//        studyIsoFormat();
//        studyAggregateQueryUsingJsonAllFields();
//        studyQueryByValidFromDateUsingJson();
    }

    public static void studyIsoFormat() {
        Date d = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'");
        BasicDBObject q = new BasicDBObject("$date", format.format(d));
    }

    private static void studyQueryByValidFromDateUsingJson() throws ParseException {
        // empty result
        MongoCollection<Document> collection = getMongoCollection();

        String query = "{'validFromD' : { '$gte' : \"new Date('2015-01-02')\"}}";
        mongoFind(collection, query);
    }

    public static List<Document> mongoFind(MongoCollection<Document> collection, String query) {
        BasicDBObject dbObject = (BasicDBObject) JSON.parse(query);
        MongoCursor<Document> cursor = collection.find(dbObject).iterator();

        List<Document> docsList = getDocuments(cursor);
        System.out.println("docsList.size() = " + docsList.size());
        return docsList;
    }

    private static void studyAggregateQueryUsingJsonAllFields() {
        MongoCollection<Document> collection = getMongoCollection();
        String query = "[{'$sort': {'name':1, 'validFromD': -1}}, {'$group': {'_id': '$name', 'businessLocation': { '$first': '$businessLocation'}, 'cityType': { '$first': '$cityType'}, 'depotName': { '$first': '$depotName'}, 'distanceKM': { '$first': '$distanceKM'}, 'name': { '$first': '$name'}, 'province': { '$first': '$province'}, 'rateLargeTruck': { '$first': '$rateLargeTruck'}, 'ratePerExtraDrop': { '$first': '$ratePerExtraDrop'}, 'rateSmallTruck': { '$first': '$rateSmallTruck'}, 'rateTIR': { '$first': '$rateTIR'}, 'surchargeSideDoorPct': { '$first': '$surchargeSideDoorPct'}, 'town': { '$first': '$town'}, 'validFrom': { '$first': '$validFrom'}}}]";
        mongoAggregate(collection, query);
    }

    private static void studyAggregateQueryUsingJsonAndMatch() throws ParseException {
        MongoCollection<Document> collection = getMongoCollection();
        Date start = new SimpleDateFormat("dd.MM.yyyy").parse("01.03.2017");
        String queryMatch = String.format("{ 'validFromD' : { '$gte' : { '$date' : %s } } }", start.getTime());
        String query = String.format("[{'$match': %s}, {'$sort': {'name':1, 'validFromD': -1}}, {'$group': {'_id': '$name', 'validFrom': { '$first': '$validFrom'}}}]", queryMatch);

        mongoAggregate(collection, query);
    }

    private static void studyAggregateQueryUsingJson() {
        MongoCollection<Document> collection = getMongoCollection();
        String query = "[{'$sort': {'name':1, 'validFromD': -1}}, {'$group': {'_id': '$name', 'validFrom': { '$first': '$validFrom'}}}]";
        mongoAggregate(collection, query);
    }

    public static void mongoAggregate(MongoCollection<Document> collection, String query) {
        List<BasicDBObject> q= (List<BasicDBObject>) JSON.parse(query);
        MongoCursor<Document> cursor = collection.aggregate(q).iterator();

        List<Document> docsList = getDocuments(cursor);
        System.out.println("docsList.size() = " + docsList.size());
    }

    private static void studyQueryUsingJson() {
        MongoCollection<Document> collection = getMongoCollection();
        String query = "{'depotName': 'CORLU'}";
        mongoFind(collection, query);
    }

    private static void studyQueryUsingJongo() {
        Jongo jongo = getJongo();
        org.jongo.MongoCollection friends = jongo.getCollection("master_rates");

//        MongoCursor<Friend> all = friends.find("{name: 'Joe'}").as(Friend.class);
//        Friend one = friends.findOne("{name: 'Joe'}").as(Friend.class);

    }

    private static Jongo getJongo() {
        DB db = getMongoDb();

        return new Jongo(db);
    }

    private static DB getMongoDb() {
        MongoClient mongoClient = getMongoClient();
        return mongoClient.getDB("demo");
    }


    private static void studyQueryByValidFromDateByJsonByMilliseconds() throws ParseException {
        MongoCollection<Document> collection = getMongoCollection();

        Date start = new SimpleDateFormat("dd.MM.yyyy").parse("01.03.2017");
        String query = String.format("{ 'validFromD' : { '$gte' : { '$date' : %s } } }", start.getTime());

        mongoFind(collection, query);
    }

    private static void studyQueryByValidFromDateByJson() throws ParseException {
        MongoCollection<Document> collection = getMongoCollection();

        Date start = new SimpleDateFormat("dd.MM.yyyy").parse("01.03.2017");
        String query = "{ 'validFromD' : { '$gte' : { '$date' : 1488315600000 } } }";

        mongoFind(collection, query);
    }

    private static void studyQueryByValidFromDate() throws ParseException {
        MongoCollection<Document> collection = getMongoCollection();

        Date start = new SimpleDateFormat("dd.MM.yyyy").parse("01.03.2017");
        BasicDBObject query = new BasicDBObject();
        query.put("validFromD", new BasicDBObject("$gte", start));

        MongoCursor<Document> cursor = collection.find(query).sort(new BasicDBObject("validFromD", -1)).iterator();

        List<Document> docsList = getDocuments(cursor);
        System.out.println("docsList.size() = " + docsList.size());
    }

    private static MongoCollection<Document> getMongoCollection() {
        MongoDatabase mongoDB = getMongoDatabase();
        return mongoDB.getCollection("master_rates");
    }

    public static List<Document> getDocuments(MongoCursor<Document> cursor) {
        List<Document> docsList = new ArrayList<Document>();

        try {
            while (cursor.hasNext()) {
                Document doc = cursor.next();

//                doc.remove("_id");

                docsList.add(doc);
            }
        } finally {
            cursor.close();
        }
        return docsList;
    }

    public static MongoDatabase getMongoDatabase() {
        MongoClient mongoClient = getMongoClient();
        MongoDatabase mongoDB;
        String db = "demo";
        mongoDB = mongoClient.getDatabase(db);
        return mongoDB;
    }

    private static MongoClient getMongoClient() {
        MongoCredential credential;
        MongoClient mongoClient;
        MongoDatabase mongoDB;
        String host = "localhost";
        int port = 27017;
        String user = "myUserAdmin";
        String password = "12345";
        credential = MongoCredential.createCredential(user, "demo", password.toCharArray());

        mongoClient = new MongoClient(new ServerAddress(host, port), Arrays.asList(credential));
        return mongoClient;
    }

}
