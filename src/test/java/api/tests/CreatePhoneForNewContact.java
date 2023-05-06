package api.tests;

import api.contact.ContactApi;
import api.helpers.ContactHelper;
import api.helpers.PhoneHelper;
import api.phone.PhoneApi;
import org.testng.annotations.Test;

public class CreatePhoneForNewContact {

    ContactApi contactApi = new ContactApi();
    PhoneApi phoneApi = new PhoneApi();
    ContactHelper contactHelper = new ContactHelper();
    PhoneHelper phoneHelper = new PhoneHelper();

    @Test
    public void createPhoneForNewContact() {
        Integer contactId = contactHelper.createContact();
        Integer phoneId = phoneHelper.createPhone(contactId);
        phoneHelper.editPhone(phoneId, contactId);
        phoneHelper.deletePhone(phoneId);

        contactHelper.deleteContact(contactId);
    }
}
