package study.mongodb;

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

import static java.lang.String.format;


public class StudyDentasNakliyeFiyatlariRaporlama {
    public static void main(String[] args) throws ParseException {
        studyGetRates_GroupByName();
        studyGetRatesValid();
        studyLoadTable();
        studyGetRatesByDepotName();
//        studyAggregateQueryUsingJsonAndMatchAndSelectAllFields();
//        studyAggregateQueryUsingJsonAndMatch();
//        studyQueryByValidFromDate();
//        studyQueryByValidFromDateByJson();
//        studyQueryByValidFromDateByJsonByMilliseconds();
//        studyQueryUsingJongo();
//        studyQueryUsingJson();
//        studyIsoFormat();
//        studyAggregateQueryUsingJsonAllFields();
//        studyQueryByValidFromDateUsingJson();
    }

    private static void studyAggregateQueryUsingJson2() {
        MongoCollection<Document> collection = getMongoCollection();
        String query = "[{'$sort': {'name':1, 'validFromD': -1}}, {'$group': {'_id': '$name', 'validFrom': { '$first': '$validFrom'}}}]";
        mongoAggregate(collection, query);
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
        String queryMatch = format("{ 'validFromD' : { '$gte' : { '$date' : %s } } }", start.getTime());
        String query = format("[" +
                        "{'$match': %s}, " +
                        "{'$sort': {'name':1, 'validFromD': -1}}, " +
                        "{'$group': {'_id': '$name', 'validFrom': { '$first': '$validFrom'}}}]",
                queryMatch);

        mongoAggregate(collection, query);
    }

    private static void studyAggregateQueryUsingJsonAndMatchAndSelectAllFields() throws ParseException {
        MongoCollection<Document> collection = getMongoCollection();
        Date start = new SimpleDateFormat("dd.MM.yyyy").parse("17.08.2017");
        String match = format("{'town' : 'ACIPAYAM', 'validFromD' : { '$lte' : { '$date' : %s } } }", start.getTime());
//        String match = format("{'validFromD' : { '$lte' : { '$date' : %s } } }", start.getTime());
        String query = format("[" +
                "{'$match': %s}, " +
                "{'$sort': {'name':1, 'validFromD': -1}}, " +
                "{'$group': {'_id': '$name', 'businessLocation': { '$first': '$businessLocation'}, 'cityType': { '$first': '$cityType'}, 'depotName': { '$first': '$depotName'}, 'distanceKM': { '$first': '$distanceKM'}, 'name': { '$first': '$name'}, 'province': { '$first': '$province'}, 'rateLargeTruck': { '$first': '$rateLargeTruck'}, 'ratePerExtraDrop': { '$first': '$ratePerExtraDrop'}, 'rateSmallTruck': { '$first': '$rateSmallTruck'}, 'rateTIR': { '$first': '$rateTIR'}, 'surchargeSideDoorPct': { '$first': '$surchargeSideDoorPct'}, 'town': { '$first': '$town'}, 'validFrom': { '$first': '$validFrom'}}}" +
                "]", match);

        System.out.println("studyAggregateQueryUsingJsonAndMatchAndSelectAllFields");
        mongoAggregate(collection, query);
    }

    private static void studyGetRates_GroupByName() throws ParseException {
        MongoCollection<Document> collection = getMongoCollection();
        String name = "DENIZLI->DENIZLI-ACIPAYAM";
        String match = format("{ 'name' : '%s' }", name);
        String query = format("[" +
                        "{'$match': %s}, " +
                        "{'$sort': {'name':1, 'validFromD': -1}}, " +
                        "{'$group': " +
                        "{'_id': '$name', " +
//                        "'businessLocation': { '$first': '$businessLocation'}, " +
//                        "'cityType': { '$first': '$cityType'}, " +
//                        "'depotName': { '$first': '$depotName'}, " +
//                        "'distanceKM': { '$first': '$distanceKM'}, " +
//                        "'name': { '$first': '$name'}, " +
//                        "'province': { '$first': '$province'}, " +
//                        "'rateLargeTruck': { '$first': '$rateLargeTruck'}, " +
//                        "'ratePerExtraDrop': { '$first': '$ratePerExtraDrop'}, " +
//                        "'rateSmallTruck': { '$first': '$rateSmallTruck'}, " +
//                        "'rateTIR': { '$first': '$rateTIR'}, " +
//                        "'surchargeSideDoorPct': { '$first': '$surchargeSideDoorPct'}, " +
//                        "'town': { '$first': '$town'}, " +
//                        "'validFrom': { '$first': '$validFrom'}" +
                        "}}" +
                        "]",
                match
        );

        mongoAggregate(collection, query);
    }

    public static List<Document> studyLoadTable() {
        MongoCollection<Document> coll = getMongoCollection();

        MongoCursor<Document> cursor = coll.find().iterator();

        List<Document> docsList = new ArrayList<Document>();

        try {
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                docsList.add(doc);
            }
        } finally {
            cursor.close();
        }

        return docsList;
    }

    private static void studyGetRatesValid() throws ParseException {
        MongoCollection<Document> collection = getMongoCollection();
        String query = format("[" +
                "{'$sort': {'name':1, 'validFromD': -1}}, " +
                "{'$group': " +
                "{'_id': '$name', " +
                "'id': { '$first': '$_id'}, " +
                "'businessLocation': { '$first': '$businessLocation'}, " +
                "'cityType': { '$first': '$cityType'}, " +
                "'depotName': { '$first': '$depotName'}, " +
                "'distanceKM': { '$first': '$distanceKM'}, " +
                "'name': { '$first': '$name'}, " +
                "'province': { '$first': '$province'}, " +
                "'rateLargeTruck': { '$first': '$rateLargeTruck'}, " +
                "'ratePerExtraDrop': { '$first': '$ratePerExtraDrop'}, " +
                "'rateSmallTruck': { '$first': '$rateSmallTruck'}, " +
                "'rateTIR': { '$first': '$rateTIR'}, " +
                "'surchargeSideDoorPct': { '$first': '$surchargeSideDoorPct'}, " +
                "'town': { '$first': '$town'}, " +
                "'validFrom': { '$first': '$validFrom'}" +
                "}}" +
                "]"
        );

        mongoAggregate(collection, query);
    }

    private static void studyGetRatesByDepotName() throws ParseException {
        MongoCollection<Document> collection = getMongoCollection();
        String depotName = "ADANA";
        //String match = format("{ 'depotName' : '%s' }", depotName);
        String match = format(" 'depotName' : '%s' ", depotName) +
                format(", 'validFromD' : { '$lte' : { '$date' : %s } } ", new Date().getTime());
        String query = format("[" +
                    "{'$match': {%s}}, " +
                    "{'$sort': {'name':1, 'validFromD': -1}}, " +
                    "{'$group': " +
                        "{'_id': '$name', " +
                        "'businessLocation': { '$first': '$businessLocation'}, " +
                        "'cityType': { '$first': '$cityType'}, " +
                        "'depotName': { '$first': '$depotName'}, " +
                        "'distanceKM': { '$first': '$distanceKM'}, " +
                        "'name': { '$first': '$name'}, " +
                        "'province': { '$first': '$province'}, " +
                        "'rateLargeTruck': { '$first': '$rateLargeTruck'}, " +
                        "'ratePerExtraDrop': { '$first': '$ratePerExtraDrop'}, " +
                        "'rateSmallTruck': { '$first': '$rateSmallTruck'}, " +
                        "'rateTIR': { '$first': '$rateTIR'}, " +
                        "'surchargeSideDoorPct': { '$first': '$surchargeSideDoorPct'}, " +
                        "'town': { '$first': '$town'}, " +
                        "'validFrom': { '$first': '$validFrom'}" +
                    "}}" +
                "]",
                match
        );

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
        return mongoClient.getDB("dentas");
    }


    private static void studyQueryByValidFromDateByJsonByMilliseconds() throws ParseException {
        MongoCollection<Document> collection = getMongoCollection();

        Date start = new SimpleDateFormat("dd.MM.yyyy").parse("01.03.2017");
        String query = format("{ 'validFromD' : { '$gte' : { '$date' : %s } } }", start.getTime());

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
        String db = "dentas";
        mongoDB = mongoClient.getDatabase(db);
        return mongoDB;
    }

    private static MongoClient getMongoClient() {
        MongoCredential credential;
        MongoClient mongoClient;
        MongoDatabase mongoDB;
        String host = "localhost";
        int port = 27018;
        String user = "myUserAdmin";
        String password = "12345";
        credential = MongoCredential.createCredential(user, "dentas", password.toCharArray());

        mongoClient = new MongoClient(new ServerAddress(host, port), Arrays.asList(credential));
        return mongoClient;
    }

}
