package net.muroc;

/**
 * Created by Brendan on 9/24/2017.
 */
import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

public class SearchCriteria {

private final Map<String, Predicate<FlightAwareTrackType>> searchMap = new HashMap<>();

private SearchCriteria(String hex, String flight, String squawk) {
super();
initSearchMap(hex, flight, squawk);
}

private void initSearchMap(String hex, String flight, String squawk) {

Predicate<FlightAwareTrackType> allFlights = p -> p.getHex() == hex && p.getFlight()==flight && p.getSquawk()==squawk;


     searchMap.put("allFlights", allFlights);


   }

           public Predicate<FlightAwareTrackType> getCriteria(String PredicateName) {
             Predicate<FlightAwareTrackType> target;

             target = searchMap.get(PredicateName);

             if (target == null) {

               System.out.println("Search Criteria not found... ");
               System.exit(1);

            }
                    return target;

           }
           public static SearchCriteria getInstance(String hex, String flight, String squawk) {
             return new SearchCriteria(hex, flight, squawk);
           }
 }