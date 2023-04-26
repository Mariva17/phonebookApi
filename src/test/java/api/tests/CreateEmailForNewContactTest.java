package api.tests;

import api.contact.ContactApi;
import api.email.EmailApi;
import api.helpers.ContactHelper;
import api.helpers.EmailHelper;
import io.restassured.response.Response;
import org.testng.annotations.Test;

public class CreateEmailForNewContactTest {

    ContactApi contactApi = new ContactApi();
    EmailApi emailApi = new EmailApi();
    EmailHelper emailHelper = new EmailHelper();
    ContactHelper contactHelper = new ContactHelper();

    @Test
    public void createEmailForNewContact() {
        Response newContactResponse = contactApi.createContact(201);
        Integer contactId = newContactResponse.jsonPath().getInt("id");
        emailApi.createEmail(201, contactId);
        Response newEmailResponse = emailApi.getEmails(200, contactId);
        int emailId = newEmailResponse.jsonPath().getInt("[0].id");
        emailApi.editExistingEmail(200, emailId, contactId);

        emailHelper.deleteEmail(emailId);

        contactHelper.deleteContact(contactId);
    }
}
