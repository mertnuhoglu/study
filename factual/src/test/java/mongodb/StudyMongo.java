package mongodb;

import com.mongodb.*;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.util.JSON;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;
import static java.lang.String.format;
import static mongodb.StudyDentasNakliyeFiyatlariRaporlama.mongoFind;

public class StudyMongo {
    public static void main(String[] args) throws ParseException {
        studyUpsertDocumentWithId();
//        studyReplaceDocumentWithId();
//        studyUpdateDocumentWithId();
//        studyFindByObjectId();
//        studyUpdateDocument();
//        studyInsertDocument();
//        studySaveDateToMongo();
//        studyParseDateStringAndSave();
//        studyReadDate();
//        studyQueryDate();
    }

    private static void studyQueryDate() throws ParseException {
        MongoCollection<Document> collection = getMongoCollection();

        Date start = new SimpleDateFormat("dd.MM.yyyy").parse("05.04.2017");
        BasicDBObject query = new BasicDBObject();
        query.put("current_date", new BasicDBObject("$gte", start));

        MongoCursor<Document> cursor = collection.find(query).sort(new BasicDBObject("current_date", -1)).iterator();

        List<Document> docsList = getDocuments(cursor);
        System.out.println("docsList.size() = " + docsList.size());
    }

    private static MongoCollection<Document> getMongoCollection() {
        MongoDatabase mongoDB = getMongoDatabase();
        return mongoDB.getCollection("test");
    }

    private static List<Document> getDocuments(MongoCursor<Document> cursor) {
        List<Document> docsList = new ArrayList<Document>();

        try {
            while (cursor.hasNext()) {
                Document doc = cursor.next();

                doc.remove("_id");

                docsList.add(doc);
            }
        } finally {
            cursor.close();
        }
        return docsList;
    }

    public static void studyReadDate() {
        MongoCollection<Document> collection = getMongoCollection();
        Document doc = collection.find(eq("name", "date example")).first();
        System.out.println("doc.toJson() = " + doc.toJson());
        Date date = (Date) doc.get("current_date");
        SimpleDateFormat inFormat = new SimpleDateFormat("dd.MM.yyyy");
        String dateS = inFormat.format(date);
        System.out.println("dateS = " + dateS);
    }
    public static void studyParseDateStringAndSave() {
        MongoCollection<Document> collection = getMongoCollection();

        SimpleDateFormat inFormat = new SimpleDateFormat("dd.MM.yyyy");
        Date validFrom;
        try {
            validFrom = inFormat.parse("3.7.2017");
        } catch (ParseException e) {
            e.printStackTrace();
            validFrom = new Date();
        }
        Document doc = new Document("name", "date example2")
                .append("current_date", validFrom);
        collection.insertOne(doc);
    }

    public static void studySaveDateToMongo() {
        MongoCollection<Document> collection = getMongoCollection();
        Document doc = new Document("name", "date example")
                .append("current_date", new Date());
        collection.insertOne(doc);
    }

    public static MongoDatabase getMongoDatabase() {
        MongoCredential credential;
        MongoClient mongoClient;
        MongoDatabase mongoDB;
        String host = "localhost";
        int port = 27017;
        String db = "demo";
        String user = "myUserAdmin";
        String password = "12345";
        credential = MongoCredential.createCredential(user, "demo", password.toCharArray());

        mongoClient = new MongoClient(new ServerAddress(host, port), Arrays.asList(credential));
        mongoDB = mongoClient.getDatabase(db);
        return mongoDB;
    }

    public static void studyInsertDocument() {
        MongoCollection<Document> collection = getMongoCollection();

        Document doc = new Document("name", "MongoDB")
                .append("type", "database")
                .append("count", 1)
                .append("versions", Arrays.asList("v3.2", "v3.0", "v2.6"))
                .append("info", new Document("x", 203).append("y", 102));

        collection.insertOne(doc);
    }

    public static void studyUpdateDocument() {
        MongoCollection<Document> collection = getMongoCollection();

        BasicDBObject query = new BasicDBObject();
        query.put("name", "MongoDB");

        BasicDBObject newDocument = new BasicDBObject();
        newDocument.put("name", "mkyong-updated");

        BasicDBObject updateObj = new BasicDBObject();
        updateObj.put("$set", newDocument);

        collection.updateMany(query, updateObj);
    }

    public static void studyFindByObjectId() {
        MongoCollection<Document> collection = getMongoCollection();

        Document doc = collection.find(eq("name", "date example")).first();
        ObjectId oid = (ObjectId) doc.get("_id");

        final Document id = new Document("_id", oid);
        Document doc2 = collection.find(id).first();

        String query = format("{ '_id': {'$oid': '%s'}}", oid.toString());
        Document doc3 = mongoFind(collection, query).get(0);
    }

    public static void studyUpdateDocumentWithId() {
        MongoCollection<Document> collection = getMongoCollection();

        Document doc = collection.find(eq("name", "date example")).first();
        ObjectId oid = (ObjectId) doc.get("_id");

        // opt1: not works
        final Document id = new Document("_id", oid);
        Document newDoc = new Document().append("name2", "updated example");
//        collection.findOneAndUpdate(id, newDoc);

        // opt2: works
        BasicDBObject newDocument = new BasicDBObject();
        newDocument.append("$set", new BasicDBObject().append("clients", 110));
        collection.findOneAndUpdate(id, newDocument);

        // opt3: works
        String query = "{ '$set' : { 'clients' : 120 } }";
        BasicDBObject dbObject = (BasicDBObject) JSON.parse(query);
        collection.findOneAndUpdate(id, dbObject);
    }

    public static void studyReplaceDocumentWithId() {
        MongoCollection<Document> collection = getMongoCollection();

        Document doc = collection.find(eq("name", "date example")).first();
        ObjectId oid = (ObjectId) doc.get("_id");

        final Document id = new Document("_id", oid);

        // opt2: works
        doc.put("clients", 99);
        collection.findOneAndReplace(id, doc);

        // opt3: works
        String query = "{ '_id' : { '$oid' : '595a2096332bca5474eae183' }, 'name' : 'date example', 'current_date' : { '$date' : 1499078806609 }, 'clients' : 101 }";
        String query2 = doc.toJson(); // same as query
        Document doc2 = Document.parse(query);
        collection.findOneAndReplace(id, doc2);
    }

    public static void studyUpsertDocumentWithId() {
        MongoCollection<Document> collection = getMongoCollection();

        Document newDoc = new Document("name", "upsert example")
                .append("current_date", new Date());
        upsertDocument(collection, newDoc);

        Document existing = collection.find(eq("name", "date example")).first();
        existing.put("clients", 102);
        upsertDocument(collection, existing);

    }

    public static void upsertDocument(MongoCollection<Document> collection, Document document) {
        ObjectId oid = (ObjectId) document.get("_id");
        final Document id = new Document("_id", oid);
        if (oid == null) {
            collection.insertOne(document);
        } else {
            collection.findOneAndReplace(id, document);
        }
    }
}
