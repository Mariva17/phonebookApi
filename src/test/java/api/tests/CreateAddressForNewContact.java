package api.tests;

import api.addresses.AddressesApi;
import api.contact.ContactApi;
import api.helpers.AddressHelper;
import api.helpers.ContactHelper;
import org.testng.annotations.Test;

public class CreateAddressForNewContact {

    ContactApi contactApi = new ContactApi();
    AddressesApi addressesApi = new AddressesApi();
    ContactHelper contactHelper = new ContactHelper();
    AddressHelper addressHelper = new AddressHelper();

    @Test
    public void createAddressForNewContact() {
        Integer contactId = contactHelper.createContact();
        Integer addressId = addressHelper.createAddress(contactId);

        addressHelper.editAddress(addressId, contactId);
        addressHelper.deleteAddress(addressId);

        contactHelper.deleteContact(contactId);

    }

}
