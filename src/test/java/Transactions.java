import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.commons.configuration.ConfigurationException;
import org.testng.Assert;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import static io.restassured.RestAssured.given;

public class Transactions {
    Properties props = new Properties();
    FileInputStream file = new FileInputStream("./src/test/resources/config.properties");

    public Transactions() throws FileNotFoundException {
    }

    public void userLogin() throws IOException, ConfigurationException {
        props.load(file);
        RestAssured.baseURI = props.getProperty("baseUrl");
        Response response =
                given()
                        .contentType("application/json")
                        .body("{\n" +
                                "    \"email\":\"salmansrabon@gmail.com\",\n" +
                                "    \"password\":\"1234\"\n" +
                                "}")
                        .when()
                        .post("/user/login")
                        .then()
                        .assertThat().statusCode(200).extract().response();

        JsonPath resObj = response.jsonPath();
        String token = resObj.get("token");
        Utils.setEnvVariable("token", token);
        System.out.println(token);
    }

    public void invalidEmail() throws ConfigurationException, IOException {
        props.load(file);
        RestAssured.baseURI = props.getProperty("baseUrl");
        Response response =
                given()
                        .contentType("application/json")
                        .body("{\n" +
                                "    \"email\":\"jondoe@test.com\",\n" +
                                "    \"password\":\"1234\"\n" +
                                "}")
                        .when()
                        .post("/user/login")
                        .then()
                        .assertThat().statusCode(404).extract().response();
    }

    public void invalidPassword() throws ConfigurationException, IOException {
        props.load(file);
        RestAssured.baseURI = props.getProperty("baseUrl");
        Response response =
                given()
                        .contentType("application/json")
                        .body("{\n" +
                                "    \"email\":\"salmansrabon@gmail.com\",\n" +
                                "    \"password\":\"blabla\"\n" +
                                "}")
                        .when()
                        .post("/user/login")
                        .then()
                        .assertThat().statusCode(401).extract().response();
    }

    public void callingList() throws IOException {
        props.load(file);
        RestAssured.baseURI = props.getProperty("baseUrl");
        Response response =
                given()
                        .contentType("application/json")
                        .header("Authorization", props.getProperty("token"))
                        .header("X-AUTH-SECRET-KEY", props.getProperty("secretAuthKey"))
                        .when()
                        .get("/user/list")
                        .then().assertThat().statusCode(200).extract().response();

        System.out.println(response.asString());
        JsonPath jsonObj = response.jsonPath();
        int id = jsonObj.get("users[0].id");
        Assert.assertEquals(1, id);
    }

    public void listByRole() throws IOException {
        props.load(file);
        RestAssured.baseURI = props.getProperty("baseUrl");
        Response response =
                given()
                        .contentType("application/json")
                        .header("Authorization", props.getProperty("token"))
                        .header("X-AUTH-SECRET-KEY", props.getProperty("secretAuthKey"))
                        .when()
                        .get("/user/search/Agent")
                        .then().assertThat().statusCode(200).extract().response();

        System.out.println(response.asString());
        JsonPath jsonObj = response.jsonPath();
        String name = jsonObj.get("users[1].name");
        Assert.assertEquals("SYSTEM", name);
    }

    public void searchUser() throws IOException {
        props.load(file);
        RestAssured.baseURI = props.getProperty("baseUrl");
        Response response =
                given()
                        .contentType("application/json")
                        .header("Authorization", props.getProperty("token"))
                        .header("X-AUTH-SECRET-KEY", props.getProperty("secretAuthKey"))
                        .when()
                        .get("/user/search?id=11")
                        .then().assertThat().statusCode(200).extract().response();

        System.out.println(response.asString());
        JsonPath jsonObj = response.jsonPath();
        String name = jsonObj.get("user.name");
        Assert.assertEquals("Ella Schowalter II", name);
    }

    public Integer Random;
    public String name;
    public String email;
    public String nid;

    public void generateUser() throws IOException, ConfigurationException {
        props.load(file);
        RestAssured.baseURI = "https://randomuser.me";
        Response response =
                given()
                        .contentType("application/json")
                        .when()
                        .get("/api")
                        .then()
                        .assertThat().statusCode(200).extract().response();

        JsonPath resObj = response.jsonPath();
        Random = (int) Math.floor(Math.random() * (9999999 - 1000000) + 1);
        String phone = "0152" + Random;
        name = "Mr. " + resObj.get("results[0].name.last");
        email = resObj.get("results[0].email");
        nid = resObj.get("results[0].login.uuid");
        Utils.setEnvVariable("name", name);
        Utils.setEnvVariable("email", email);
        Utils.setEnvVariable("nid", nid);
        Utils.setEnvVariable("phone_number", phone);
        System.out.println(response.asString());
    }

    public void createUser() throws IOException {
        props.load(file);
        RestAssured.baseURI = props.getProperty("baseUrl");
        Response response =
                given()
                        .contentType("application/json")
                        .header("Authorization", props.getProperty("token"))
                        .header("X-AUTH-SECRET-KEY", props.getProperty("secretAuthKey"))
                        .body("" +
                                "{\"name\":" + props.getProperty("name") + ",\n" +
                                "    \"email\":\"" + props.getProperty("email") + "\", \n" +
                                "    \"password\":\"" + "1234" + "\",\n" +
                                "    \"phone_number\":\"" + props.getProperty("phone_number") + "\",\n" +
                                "    \"nid\":\"" + props.getProperty("nid") + "\",\n" +
                                "    \"role\":\"" + "Customer" + "\"}")
                        .when()
                        .post("/user/create")
                        .then()
                        .extract().response();

        System.out.println(response.asString());
    }

    public void createAgent() throws IOException {
        props.load(file);
        RestAssured.baseURI = props.getProperty("baseUrl");
        Response response =
                given()
                        .contentType("application/json")
                        .header("Authorization", props.getProperty("token"))
                        .header("X-AUTH-SECRET-KEY", props.getProperty("secretAuthKey"))
                        .body("" +
                                "{\"name\":" + "Jobbar" + ",\n" +
                                "    \"email\":\"" + "jobbar@test,com" + "\", \n" +
                                "    \"password\":\"" + "1234" + "\",\n" +
                                "    \"phone_number\":\"" + "01971856225" + "\",\n" +
                                "    \"nid\":\"" + props.getProperty("nid") + "\",\n" +
                                "    \"role\":\"" + "Agent" + "\"}")
                        .when()
                        .post("/user/create")
                        .then()
                        .assertThat().statusCode(201).extract().response();

        System.out.println(response.asString());
    }

    public void updateUser() throws IOException {
        props.load(file);
        RestAssured.baseURI = props.getProperty("baseUrl");
        Response response =
                given()
                        .contentType("application/json")
                        .header("Authorization", props.getProperty("token"))
                        .header("X-AUTH-SECRET-KEY", props.getProperty("secretAuthKey"))
                        .body("" +
                                "{\"name\":" + "Alfred" + ",\n" +
                                "    \"email\":\"" + "jondoe@test,com" + "\", \n" +
                                "    \"password\":\"" + "1234" + "\",\n" +
                                "    \"phone_number\":\"" + "372-315-9957" + "\",\n" +
                                "    \"nid\":\"" + "1a189c8a-b3e4-4534-90fc-b3ae76507a9d" + "\",\n" +
                                "    \"role\":\"" + "Customer" + "\"}")
                        .when()
                        .put("/user/update/15")
                        .then()
                        .assertThat().statusCode(200).extract().response();

        System.out.println(response.asString());
    }

    public void agentBalance() throws IOException {
        props.load(file);
        RestAssured.baseURI = props.getProperty("baseUrl");
        Response response =
                given()
                        .contentType("application/json")
                        .header("Authorization", props.getProperty("token"))
                        .header("X-AUTH-SECRET-KEY", props.getProperty("secretAuthKey"))
                        .when()
                        .get("/transaction/balance/01753851797")
                        .then().assertThat().statusCode(200).extract().response();

        System.out.println(response.asString());
    }

    public void deposit() throws IOException {
        props.load(file);
        RestAssured.baseURI = props.getProperty("baseUrl");
        Response response =
                given()
                        .contentType("application/json")
                        .header("Authorization", props.getProperty("token"))
                        .header("X-AUTH-SECRET-KEY", props.getProperty("secretAuthKey"))
                        .body("" +
                                "{\"from_account\":" + "SYSTEM" + ",\n" +
                                "    \"to_account\":\"" + "01525124847" + "\", \n" +
                                "    \"amount\":\"" + 1000 + "\"}")
                        .when()
                        .post("/transaction/deposit")
                        .then()
                        .assertThat().statusCode(201).extract().response();

        System.out.println(response.asString());
    }

    public void depositInsufBalance() throws IOException {
        props.load(file);
        RestAssured.baseURI = props.getProperty("baseUrl");
        Response response =
                given()
                        .contentType("application/json")
                        .header("Authorization", props.getProperty("token"))
                        .header("X-AUTH-SECRET-KEY", props.getProperty("secretAuthKey"))
                        .body("" +
                                "{\"from_account\":" + "01971819289" + ",\n" +
                                "    \"to_account\":\"" + "01525124847" + "\", \n" +
                                "    \"amount\":\"" + 1000 + "\"}")
                        .when()
                        .post("/transaction/deposit")
                        .then()
                        .assertThat().statusCode(208).extract().response();

        System.out.println(response.asString());
        JsonPath jsonObj = response.jsonPath();
        String m = jsonObj.get("message");
        Assert.assertEquals("Insufficient balance", m);
    }

    public void userBalance() throws IOException {
        props.load(file);
        RestAssured.baseURI = props.getProperty("baseUrl");
        Response response =
                given()
                        .contentType("application/json")
                        .header("Authorization", props.getProperty("token"))
                        .header("X-AUTH-SECRET-KEY", props.getProperty("secretAuthKey"))
                        .when()
                        .get("/transaction/balance/01525124847")
                        .then().assertThat().statusCode(200).extract().response();

        System.out.println(response.asString());
    }

    public void sendMoney() throws IOException {
        props.load(file);
        RestAssured.baseURI = props.getProperty("baseUrl");
        Response response =
                given()
                        .contentType("application/json")
                        .header("Authorization", props.getProperty("token"))
                        .header("X-AUTH-SECRET-KEY", props.getProperty("secretAuthKey"))
                        .body("" +
                                "{\"from_account\":" + "01525124847" + ",\n" +
                                "    \"to_account\":\"" + "01526809412" + "\", \n" +
                                "    \"amount\":\"" + 500 + "\"}")
                        .when()
                        .post("/transaction/sendmoney")
                        .then()
                        .assertThat().statusCode(201).extract().response();
    }

    public void userBalance2() throws IOException {
        props.load(file);
        RestAssured.baseURI = props.getProperty("baseUrl");
        Response response =
                given()
                        .contentType("application/json")
                        .header("Authorization", props.getProperty("token"))
                        .header("X-AUTH-SECRET-KEY", props.getProperty("secretAuthKey"))
                        .when()
                        .get("/transaction/balance/01525124847")
                        .then().assertThat().statusCode(200).extract().response();

        System.out.println(response.asString());
    }

    public void cashOut() throws IOException {
        props.load(file);
        RestAssured.baseURI = props.getProperty("baseUrl");
        Response response =
                given()
                        .contentType("application/json")
                        .header("Authorization", props.getProperty("token"))
                        .header("X-AUTH-SECRET-KEY", props.getProperty("secretAuthKey"))
                        .body("" +
                                "{\"from_account\":" + "01526809412" + ",\n" +
                                "    \"to_account\":\"" + "01971819475" + "\", \n" +
                                "    \"amount\":\"" + 100 + "\"}")
                        .when()
                        .post("/transaction/withdraw")
                        .then()
                        .assertThat().statusCode(201).extract().response();
    }

    public void showTransactionList() throws IOException {
        props.load(file);
        RestAssured.baseURI = props.getProperty("baseUrl");
        Response response =
                given()
                        .contentType("application/json")
                        .header("Authorization", props.getProperty("token"))
                        .header("X-AUTH-SECRET-KEY", props.getProperty("secretAuthKey"))
                        .when()
                        .get("/transaction/statement/01525124847")
                        .then().assertThat().statusCode(200).extract().response();

        System.out.println(response.asString());
    }

    public void showTransactionListid() throws IOException {
        props.load(file);
        RestAssured.baseURI = props.getProperty("baseUrl");
        Response response =
                given()
                        .contentType("application/json")
                        .header("Authorization", props.getProperty("token"))
                        .header("X-AUTH-SECRET-KEY", props.getProperty("secretAuthKey"))
                        .when()
                        .get("/transaction/search/TXN249957")
                        .then().assertThat().statusCode(200).extract().response();

        System.out.println(response.asString());
    }

    public void showWholeTransactionList() throws IOException {
        props.load(file);
        RestAssured.baseURI = props.getProperty("baseUrl");
        Response response =
                given()
                        .contentType("application/json")
                        .header("Authorization", props.getProperty("token"))
                        .header("X-AUTH-SECRET-KEY", props.getProperty("secretAuthKey"))
                        .when()
                        .get("/transaction/list")
                        .then().assertThat().statusCode(200).extract().response();

        System.out.println(response.asString());
    }
}
