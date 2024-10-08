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
 * Jersey REST client generated for REST resource:CashierResource [cashier]<br>
 * USAGE:
 * <pre>
 *        CashierClient client = new CashierClient();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author ayush
 */
public class CashierClient {

    private WebTarget webTarget;
    private Client client;
    private static final String BASE_URI = "http://localhost:8080/SuratSwiftMetro/resources";

    public CashierClient() {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        client.register(RestFilter.class);
        webTarget = client.target(BASE_URI).path("cashier");
    }

    public void collectPayment(Object requestEntity) throws ClientErrorException {
        webTarget.path("collection/collect").request(javax.ws.rs.core.MediaType.APPLICATION_JSON).put(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON));
    }

    public void updateCollection(Object requestEntity) throws ClientErrorException {
        webTarget.path("collection/update").request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON));
    }

    public <T> T getAllCollectionStationOfCashier(Class<T> responseType, String username) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("fare/getAllCollectionStationOfCashier/{0}", new Object[]{username}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T getFareBySourceAndDestination(Class<T> responseType, String source, String destination) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("fare/getBySourceDestination/{0}/{1}", new Object[]{source, destination}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T getCollectionStationByCashier(Class<T> responseType, String cashier) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("collection/getBycashier/{0}", new Object[]{cashier}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void close() {
        client.close();
    }

}
