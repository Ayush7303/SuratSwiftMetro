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
 * Jersey REST client generated for REST resource:AdminResource [admin]<br>
 * USAGE:
 * <pre>
 *        AdminClient client = new AdminClient();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author ayush
 */
public class AdminClient {

    private WebTarget webTarget;
    private Client client;
    private static final String BASE_URI = "http://localhost:8080/SuratSwiftMetro/resources";

    public AdminClient() {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        client.register(RestFilter.class);
        webTarget = client.target(BASE_URI).path("admin");
    }

    public void updateRole(Object requestEntity) throws ClientErrorException {
        webTarget.path("role/update").request(javax.ws.rs.core.MediaType.APPLICATION_JSON).put(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON));
    }

    public <T> T getAllRouteStations(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("route_station/get");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T getAllSchedules(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("schedule/get");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void deleteInterchange(String interchangeId) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("interchange/delete/{0}", new Object[]{interchangeId})).request().delete();
    }

    public <T> T getAllNotifications(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("notification/get");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void assignMetroToSchedule() throws ClientErrorException {
        webTarget.path("schedule/assignSchedule").request().post(null);
    }

    public void addSchedule(Object requestEntity) throws ClientErrorException {
        webTarget.path("schedule/add").request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON));
    }

    public <T> T getNotificationById(Class<T> responseType, String notificationId) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("notification/get/{0}", new Object[]{notificationId}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T getAllStations(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("station/get");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void deleteScheme(String schemeId) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("schemes/delete/{0}", new Object[]{schemeId})).request().delete();
    }

    public <T> T getFareById(Class<T> responseType, String fareId) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("fare/get/{0}", new Object[]{fareId}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T getAllInterchanges(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("interchange/get");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T getAllIssues(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("isuue/get");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void addNotification(Object requestEntity) throws ClientErrorException {
        webTarget.path("notification/add").request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON));
    }

    public <T> T getAllCollectionStations(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("collectionStation/get");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T getInterchangeById(Class<T> responseType, String interchangeId) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("interchange/get/{0}", new Object[]{interchangeId}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void updateRoute(Object requestEntity) throws ClientErrorException {
        webTarget.path("route/update").request(javax.ws.rs.core.MediaType.APPLICATION_JSON).put(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON));
    }

    public void updateMetro(Object requestEntity) throws ClientErrorException {
        webTarget.path("metro/update").request(javax.ws.rs.core.MediaType.APPLICATION_JSON).put(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON));
    }

    public void updateInterchange(Object requestEntity) throws ClientErrorException {
        webTarget.path("interchange/update").request(javax.ws.rs.core.MediaType.APPLICATION_JSON).put(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON));
    }

    public <T> T getAllUsers(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("user/get");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void deleteNotification(String notificationId) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("notification/delete/{0}", new Object[]{notificationId})).request().delete();
    }

    public <T> T getAllPayments(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("payment");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T getStationById(Class<T> responseType, String stationId) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("station/get/{0}", new Object[]{stationId}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T getStationLinkById(Class<T> responseType, String stationLinkId) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("stationLink/get/{0}", new Object[]{stationLinkId}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void addMetro(Object requestEntity) throws ClientErrorException {
        webTarget.path("metro/add").request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON));
    }

    public <T> T getMetroById(Class<T> responseType, String metroId) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("metro/get/{0}", new Object[]{metroId}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T getAllRoutes(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("route/get");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void deleteMetro(String metroId) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("metro/delete/{0}", new Object[]{metroId})).request().delete();
    }

    public <T> T getRoleById(Class<T> responseType, String roleId) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("role/get/{0}", new Object[]{roleId}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void addInterchange(Object requestEntity) throws ClientErrorException {
        webTarget.path("interchange/add").request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON));
    }

    public <T> T getTotalCollections(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("collection/get");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void addRoute(Object requestEntity) throws ClientErrorException {
        webTarget.path("route/add").request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON));
    }

    public void deleteRoute(String routeId) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("route/delete/{0}", new Object[]{routeId})).request().delete();
    }

    public <T> T getScheduleForMetro(Class<T> responseType, String metroid) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("schedule/getSchedule/{0}", new Object[]{metroid}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T getSchemeById(Class<T> responseType, String schemeId) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("schemes/get/{0}", new Object[]{schemeId}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void deleteStation(String stationId) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("station/delete/{0}", new Object[]{stationId})).request().delete();
    }

    public void addScheme(Object requestEntity) throws ClientErrorException {
        webTarget.path("schemes/add").request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON));
    }

    public void addRole(Object requestEntity) throws ClientErrorException {
        webTarget.path("role/add").request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON));
    }

    public <T> T searchByFromTo(Class<T> responseType, String from, String to) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("searchFareStationLink/get/{0}/{1}", new Object[]{from, to}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T getAllStationLinks(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("stationLink/get");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void updateFare(Object requestEntity) throws ClientErrorException {
        webTarget.path("fare/update").request(javax.ws.rs.core.MediaType.APPLICATION_JSON).put(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON));
    }

    public <T> T getAllRoles(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("role/get");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void updateSchedule(Object requestEntity) throws ClientErrorException {
        webTarget.path("schedule/update").request(javax.ws.rs.core.MediaType.APPLICATION_JSON).put(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON));
    }

    public <T> T getAllPassangers(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("passanger/get");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void addStationToRoute(String routeid, String stationid) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("route_station/add/{0}/{1}", new Object[]{routeid, stationid})).request().post(null);
    }

    public void updateNotification(Object requestEntity) throws ClientErrorException {
        webTarget.path("notification/update").request(javax.ws.rs.core.MediaType.APPLICATION_JSON).put(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON));
    }

    public void updateScheme(Object requestEntity) throws ClientErrorException {
        webTarget.path("schemes/update").request(javax.ws.rs.core.MediaType.APPLICATION_JSON).put(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON));
    }

    public <T> T getIssuesForUser(Class<T> responseType, String userId) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("issue/getByUser/{0}", new Object[]{userId}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void updateStationLink(Object requestEntity) throws ClientErrorException {
        webTarget.path("stationLink/update").request(javax.ws.rs.core.MediaType.APPLICATION_JSON).put(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON));
    }

    public <T> T getScheduleById(Class<T> responseType, String scheduleId) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("schedule/get/{0}", new Object[]{scheduleId}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void deleteFare(String fareId) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("fare/delete/{0}", new Object[]{fareId})).request().delete();
    }

    public <T> T getMetroForSchedule(Class<T> responseType, String scheduleid) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("schedule/getMetro/{0}", new Object[]{scheduleid}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T getAllSubcriptions(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("subscription/get");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T getAllMetros(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("metro/get");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T getRouteById(Class<T> responseType, String routeId) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("route/get/{0}", new Object[]{routeId}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void addStationLink(Object requestEntity) throws ClientErrorException {
        webTarget.path("stationLink/add").request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON));
    }

    public void updateStation(Object requestEntity) throws ClientErrorException {
        webTarget.path("station/update").request(javax.ws.rs.core.MediaType.APPLICATION_JSON).put(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON));
    }

    public void deleteSchedule(String scheduleId) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("schedule/delete/{0}", new Object[]{scheduleId})).request().delete();
    }

    public void addFare(Object requestEntity) throws ClientErrorException {
        webTarget.path("fare/add").request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON));
    }

    public void deleteRole(String roleId) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("role/delete/{0}", new Object[]{roleId})).request().delete();
    }

    public void removeMetroFromSchedule() throws ClientErrorException {
        webTarget.path("schedule/removeSchedule").request().delete();
    }

    public <T> T getAllSchemes(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("schemes/get");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void deleteStationLink(String stationLinkId) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("stationLink/delete/{0}", new Object[]{stationLinkId})).request().delete();
    }

    public void addStation(Object requestEntity) throws ClientErrorException {
        webTarget.path("station/add").request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON));
    }

    public <T> T getAllFares(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("fare/get");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T getAllBookings(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("booking/get");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void resolveIssue(String issueId) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("issue/resolve/{0}", new Object[]{issueId})).request().post(null);
    }

    public void close() {
        client.close();
    }
    
}
