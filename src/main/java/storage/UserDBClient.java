package storage;

import com.amazon.ask.model.Session;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class UserDBClient {

    private static AmazonDynamoDBClient dynamoDBClient;
    private static DynamoDBMapper mapper;

    private static UserDBClient INSTANCE = null;

    public static UserDBClient getInstance(){
        if(INSTANCE == null){
            INSTANCE = new UserDBClient();
            UserDBClient.dynamoDBClient = new AmazonDynamoDBClient();
            dynamoDBClient.setRegion(Region.getRegion(Regions.US_EAST_2));
            UserDBClient.mapper = new DynamoDBMapper(dynamoDBClient);
        }
        return INSTANCE;
    }

    private UserDBClient(){}

    public UserData saveUser(Session session, String cpfData) {

        UserData userData = new UserData();
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        userData.setUserId(session.getUser().getUserId());
        userData.setCpf(cpfData);
        userData.setPassword("Alo");

        mapper.save(userData);
        return userData;
    }

    public UserData getUser(Session session){
        return null;
    }
}
