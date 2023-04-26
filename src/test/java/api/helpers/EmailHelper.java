package api.helpers;

import api.email.EmailApi;
import io.restassured.response.Response;
import org.testng.Assert;

public class EmailHelper extends EmailApi {

    public void deleteEmail(Integer emailId) {
        deleteExistingEmail(200, emailId);
        Response actualDeletedResponse = getEmail(500, emailId);
        Assert.assertEquals(actualDeletedResponse.jsonPath().getString("message"), "Error! This email doesn't exist in our DB");

    }

}
