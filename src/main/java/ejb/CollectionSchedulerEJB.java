/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB31/SingletonEjbClass.java to edit this template
 */
package ejb;

import entity.Booking;
import entity.CollectionStation;
import entity.Station;
import entity.Subscripton;
import entity.Collection;
import entity.Metro;
import entity.Route;
import entity.User;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author ayush
 */
@Singleton
public class CollectionSchedulerEJB {

    @PersistenceContext(unitName = "ssmpu")
    private EntityManager em;

    @Schedule(hour = "6", minute = "0", second = "0", persistent = false)
    public void addCollectionDetails() {
        Date curDate = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(curDate);
        cal.add(Calendar.DATE, -1);
        Date previousDate = cal.getTime();
        System.out.println(previousDate);
        List<CollectionStation> previousDayCollections = em.createQuery(
                "SELECT c FROM CollectionStation c WHERE c.collectiondate = :previousDate", CollectionStation.class)
                .setParameter("previousDate", previousDate)
                .getResultList();

        for (CollectionStation cs : previousDayCollections) {
            cs.setStatus(false);
            em.merge(cs);
        }

        List<Station> stations = em.createNamedQuery("Station.findAll", Station.class).getResultList();
        Date currentDate = new Date();
        System.out.println("Schedule");
        for (Station s : stations) {

            String cUsername = "cashier" + s.getStationid() + "SSM";
            User cashier = em.find(User.class, cUsername);

            CollectionStation collection = new CollectionStation();
            collection.setCashier(cashier);
            collection.setStationid(s);
            collection.setCollectiondate(currentDate);
            collection.setTotalcollection(0.0);
            collection.setStatus(true);

            em.persist(collection);
        }
        
        Collection c=new Collection();
        c.setCollectiondate(new Date());
        c.setTotalcollection(0);
        em.persist(c);
        
    }

    @Schedule(hour = "22", minute = "0", second = "0", persistent = false)
    public void changeStatus() {
        LocalDate currentDate = LocalDate.now();
        List<Booking> bookings = em.createNamedQuery("Booking.findAll").getResultList();
        for (Booking b : bookings) {
            LocalDate bookingDate = b.getBookingdate().toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate();
            if (bookingDate.equals(currentDate) && b.getStatus().equals("Active")) {
                b.setStatus("Expired");
                em.merge(b);
            }
        }
        List<Subscripton> subs = em.createNamedQuery("Subscripton.findAll").getResultList();
        for (Subscripton s : subs) {
            LocalDate subEndDate = s.getEnddate().toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate();
            if (subEndDate.equals(currentDate) && s.getStatus() == true) {
                s.setStatus(false);
                em.merge(s);
            }
        }

    }
    @Schedule(hour = "6", minute = "0", second = "0", persistent = false)
    public void SetMetroSchedule(){
        ArrayList<Date> scheduletimes = new ArrayList<>();
        Date start = new Date(new Date().getYear(), new Date().getMonth(), new Date().getDate(), 6, 0, 0);
        Date end = new Date(new Date().getYear(), new Date().getMonth(), new Date().getDate(), 22, 0, 0);

        long diff = start.getTime();
        while (diff <= end.getTime()) {
            scheduletimes.add(new Date(diff));
            diff += 3600000;
        }

        List<Metro> metros = em.createNamedQuery("Metro.findAll", Metro.class).getResultList();
        List<Route> routes = em.createNamedQuery("Route.findAll", Route.class).getResultList();

        int i = 0;
        int j = 3;
        int k = 0;
       
        Date date1 = new Date();
        Date date2 = new Date();
        for (Route r : routes) {
            i = j - 3;
            int inc = 0;
            System.out.println("\nRoute " + r.getRoutenumber());
            while (i < j) {
                for (Date d : scheduletimes) {
                    Calendar calendar = Calendar.getInstance();
                    calendar.set(Calendar.HOUR_OF_DAY, d.getHours());
                    calendar.set(Calendar.MINUTE, d.getMinutes()+inc);
                    calendar.set(Calendar.SECOND, d.getSeconds());
                    
                    date1 = calendar.getTime();
                    date2.setTime(date1.getTime()+1800000);
                    
                    entity.Schedule schedule = new entity.Schedule();
                    schedule.setRouteid(r);
                    schedule.setMetroid(metros.get(i));
                    schedule.setScheduledate(new Date());
                    System.out.println("start Time : "+date1.toString());
                    schedule.setStarttime(date1);
                    schedule.setExpectedendtime(date2);
                    schedule.setStatus(true);
                    
                    em.persist(schedule);
                    
                    System.out.println("Metro " + 1 + " : " + metros.get(i).getMetroname() + " Time : " + date1.toLocaleString());
                }
                inc += 20;
                i++;
            }
            System.out.println("j : "+j);
            j += 3;
        }
    }

}
