package api.tests;

import api.contact.ContactApi;
import api.helpers.ContactHelper;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CommonContactTests extends ContactApi {

    ContactHelper contactHelper = new ContactHelper();

    @Test
    public void createEditDeleteNewContact() {
        // 1. Проверка создания контакта
        // создаем контакт, записываем ответ после создания контакта
        Integer contactId = contactHelper.createContact();
        // 2. Проверка изменения контакта
        // Изменяем данные контакта, но данный эндпоин не имеет ответа (см.сваггер)
        editExistingContact(200, contactId);

        // Получаем измененный, отредактированный контакт
        Response actualEditedResponse = getContact(200, contactId);
        // Сравниваем актуальные данные (те, которые вытащили с помощью запроса GET)
        Assert.assertEquals(actualEditedResponse.jsonPath().getString("firstName"), randomDataBodyForEditContact(contactId).getFirstName(), "First name not equal");
        Assert.assertEquals(actualEditedResponse.jsonPath().getString("lastName"), randomDataBodyForEditContact(contactId).getLastName(), "Last name not equal");
        Assert.assertEquals(actualEditedResponse.jsonPath().getString("description"), randomDataBodyForEditContact(contactId).getDescription(), "Description not equal");

        // 3. Удаляем существующий контакт
        contactHelper.deleteContact(contactId);
    }
}
