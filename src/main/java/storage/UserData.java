package storage;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.ToString;

import static storage.UserData.TABLE_NAME;

@DynamoDBTable(tableName = TABLE_NAME)
@ToString
public class UserData {

    public final static String TABLE_NAME = "dados_login";
    public final static String USER_ID = "user_id";
    public final static String CPF = "cpf";
    public final static String PASSWORD = "password";

    private String userId;
    private String cpf;
    private String password;
    private String cookies;

    @DynamoDBHashKey(attributeName = USER_ID)
    public String getUserId(){
        return userId;
    }
    @DynamoDBAttribute(attributeName = CPF)
    public String getCpf(){
        return cpf;
    }

    @DynamoDBAttribute(attributeName = PASSWORD)
    public String getPassword(){
        return password;
    }

    public String getCookies() {
        return cookies;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCookies(String cookies) {
        this.cookies = cookies;
    }
}
