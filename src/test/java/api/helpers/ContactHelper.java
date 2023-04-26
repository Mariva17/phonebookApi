package api.helpers;

import api.contact.ContactApi;
import io.restassured.response.Response;
import org.testng.Assert;

public class ContactHelper extends ContactApi {

    public void deleteContact(Integer contactId) {
        deleteExistingContact(200, contactId);
        Response actualDeletedResponse = getContact(500, contactId);
        Assert.assertEquals(actualDeletedResponse.jsonPath().getString("message"), "Error! This contact doesn't exist in our DB");

    }

}
