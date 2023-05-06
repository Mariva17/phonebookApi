package api.addresses;

import api.ApiBase;
import io.restassured.response.Response;
import schemas.addresses.AddressesDto;

public class AddressesApi extends ApiBase {

    Response response;
    AddressesDto dto;

    public AddressesDto randomDataBodyForCreateAddress(Integer contactId) {

        dto = new AddressesDto();
        dto.setCity("Berlin");
        dto.setCountry("Germany");
        dto.setStreet("Lindenstrasse");
        dto.setZip("444555");
        dto.setContactId(contactId);
        return dto;
    }

    public AddressesDto randomDataBodyForEditAddress(Integer addressId, Integer contactId) {
        dto = new AddressesDto();
        dto.setId(addressId);
        dto.setCity("Hamburg");
        dto.setCountry("Germany");
        dto.setStreet("Lindenplatz");
        dto.setZip("22356");
        dto.setContactId(contactId);
        return dto;
    }

    public Response createAddress(Integer code, Integer contactId) {
        String endPoint = "/api/address/";
        response = postRequest(endPoint, code, randomDataBodyForCreateAddress(contactId));
        return response;
    }

    public Response getAllAddresses(Integer code, Integer contactId) {
        String endPoint = "/api/address/{id}/all";
        response = getRequestWithParam(endPoint, code, contactId);
        return response;
    }

    public Response getAddress(Integer code, Integer addressId) {
        String endPoint = "/api/address/{id}/";
        response = getRequestWithParam(endPoint, code, addressId);
        return response;
    }

    public void editExistingAddress(Integer code, Integer addressId, Integer contactId) {
        String endPoint = "/api/address/";
        putRequest(endPoint, code, randomDataBodyForEditAddress(addressId, contactId));
    }

    public void deleteExistingAddress(Integer code, int addressId) {
        String endPoint = "/api/address/{id}";
        deleteRequest(endPoint, code, addressId);
    }


}
