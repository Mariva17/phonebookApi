package api.tests;

import api.contact.ContactApi;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CommonContactTests extends ContactApi {

    @Test
    public void createEditDeleteNewContact() {
        // 1. Проверка создания контакта
        // создаем контакт, записываем ответ после создания контакта
        Response actualResponse = createContact(201);
        // из ответа вытаскиваем id, для того чтобы использовать его для получения данных (get запрос)
        int contactId = actualResponse.jsonPath().getInt("id");

        // Получаем данные по созданному контакту
        Response expectedResponse = getContact(200, contactId);
        // Проверка! Сравниваем ответ эндпоинта по созданию контакта с ответом эндпоинта, который получает когтакт
        Assert.assertEquals(actualResponse.jsonPath().getString("firstName"), expectedResponse.jsonPath().getString("firstName"), "First name not equal");
        Assert.assertEquals(actualResponse.jsonPath().getString("lastName"), expectedResponse.jsonPath().getString("lastName"), "Last name not equal");
        Assert.assertEquals(actualResponse.jsonPath().getString("description"), expectedResponse.jsonPath().getString("description"), "Description not equal");

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
        deleteExistingContact(200, contactId);

        Response actualDeletedResponse = getContact(500, contactId);
        Assert.assertEquals(actualDeletedResponse.jsonPath().getString("message"), "Error! This contact doesn't exist in our DB");

    }
}
