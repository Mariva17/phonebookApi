package api.tests;

import api.addresses.AddressesApi;
import api.helpers.AddressHelper;
import org.testng.annotations.Test;

public class CommonAddressTests extends AddressesApi {

    AddressHelper addressHelper = new AddressHelper();
    Integer contactId = 4911;

    @Test
    public void createEditDeleteNewAddress() {
        Integer addressId = addressHelper.createAddress(contactId);
        addressHelper.editAddress(addressId, contactId);
        addressHelper.deleteAddress(addressId);
    }

}
