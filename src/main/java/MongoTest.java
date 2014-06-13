import com.mongodb.*;

import java.net.UnknownHostException;
import java.util.Date;

/**
 * Created by Silvia on 13. 6. 2014.
 */

public class MongoTest {
    public static void main(String[] args) {

        try {

            /**** Connect to MongoDB ****/
            MongoClient mongo = new MongoClient("127.0.0.1", 27017);

            /**** Get database ****/
            DB db = mongo.getDB("testdb");

            /**** Get collection / table from 'testdb' ****/
            DBCollection table = db.getCollection("user");

            /**** Insert ****/
            BasicDBObject document = new BasicDBObject();
            document.put("name", "silvia");
            document.put("age", 20);
            document.put("createdDate", new Date());
            table.insert(document);

            /**** Find and display ****/
            BasicDBObject searchQuery = new BasicDBObject();
            searchQuery.put("name", "silvia");

            DBCursor cursor = table.find(searchQuery);

            while (cursor.hasNext()) {
                System.out.println(cursor.next());
            }

            /**** Update ****/
            BasicDBObject query = new BasicDBObject();
            query.put("name", "silvia");

            BasicDBObject newDocument = new BasicDBObject();
            newDocument.put("name", "silvia-updated");

            BasicDBObject updateObj = new BasicDBObject();
            updateObj.put("$set", newDocument);

            table.update(query, updateObj);

            /**** Find and display ****/
            BasicDBObject searchQuery2
                    = new BasicDBObject().append("name", "silvia-updated");

            DBCursor cursor2 = table.find(searchQuery2);

            while (cursor2.hasNext()) {
                System.out.println(cursor2.next());
            }

            /**** Done ****/
            System.out.println("Done");

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (MongoException e) {
            e.printStackTrace();
        }

    }
}