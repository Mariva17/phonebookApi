package api.tests;

import api.contact.ContactApi;
import api.email.EmailApi;
import api.helpers.ContactHelper;
import api.helpers.EmailHelper;
import org.testng.annotations.Test;

public class CreateEmailForNewContactTest {

    ContactApi contactApi = new ContactApi();
    EmailApi emailApi = new EmailApi();
    EmailHelper emailHelper = new EmailHelper();
    ContactHelper contactHelper = new ContactHelper();

    @Test
    public void createEmailForNewContact() {
        Integer contactId = contactHelper.createContact();
        // Work with Email
        Integer emailId = emailHelper.createEmail(contactId);
        emailHelper.editEmail(emailId, contactId);
        emailHelper.deleteEmail(emailId);

        contactHelper.deleteContact(contactId);
    }
}
