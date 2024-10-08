/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/JerseyClient.java to edit this template
 */
package Client;

import filter.RestFilter;
import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;

/**
 * Jersey REST client generated for REST resource:CustomerResource
 * [customer]<br>
 * USAGE:
 * <pre>
 *        PassengerClient client = new PassengerClient();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author ayush
 */
public class PassengerClient {

    private WebTarget webTarget;
    private Client client;
    private static final String BASE_URI = "http://localhost:8080/SuratSwiftMetro/resources";

    public PassengerClient() {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        client.register(RestFilter.class);
        webTarget = client.target(BASE_URI).path("customer");
    }

    public <T> T getSubscriptionsByPassaanger(Class<T> responseType, String passangerId) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("subscription/getByPassanger/{0}", new Object[]{passangerId}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void makePayment(Object requestEntity) throws ClientErrorException {
        webTarget.path("payment").request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON));
    }

    public <T> T getBookingsForPassanger(Class<T> responseType, String passangerId) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("booking/getByPassanger/{0}", new Object[]{passangerId}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T getActiveSubscriptionByPassenger(Class<T> responseType, String passangerId) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("subscription/getActiveByPassanger/{0}", new Object[]{passangerId}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void bookTicket(Object requestEntity) throws ClientErrorException {
        webTarget.path("booking/book").request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON));
    }

    public void subscribeToScheme(Object requestEntity) throws ClientErrorException {
        webTarget.path("scheme/subscribe").request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON));
    }

    public <T> T getPassangerById(Class<T> responseType, String username) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("passanger/get/{0}", new Object[]{username}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void close() {
        client.close();
    }
    
}
