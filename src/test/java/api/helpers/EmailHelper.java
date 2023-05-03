package api.helpers;

import api.email.EmailApi;
import io.restassured.response.Response;
import org.testng.Assert;

public class EmailHelper extends EmailApi {

    public int createEmail(Integer contactId) {
        createNewEmail(201, contactId);
        Response actualResponse = getEmails(200, contactId);
        int emailId = actualResponse.jsonPath().getInt("[0].id");
        String email = actualResponse.jsonPath().getString("[0].email");
        Assert.assertEquals(email, randomDataBodyForCreateEmail(contactId).getEmail(), "Email not equal");
        return emailId;
    }

    public void editEmail(Integer emailId, Integer contactId) {
        editExistingEmail(200, emailId, contactId);
        Response editedEmails = getEmails(200, contactId);
        String editedEmail = editedEmails.jsonPath().getString("[0].email");
        Assert.assertEquals(editedEmail, randomDataBodyForEditEmail(emailId, contactId).getEmail(), "Email not equal");
    }

    public void deleteEmail(Integer emailId) {
        deleteExistingEmail(200, emailId);
        Response actualDeletedResponse = getEmail(500, emailId);
        Assert.assertEquals(actualDeletedResponse.jsonPath().getString("message"), "Error! This email doesn't exist in our DB");

    }


}
