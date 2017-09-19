package net.muroc;

import com.google.gson.Gson;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import java.util.List;

/**
 * Created by Brendan on 9/5/2017.
 */
public class fa_persistence {
    public static void main (String[] args)
    {
        Configuration configuration = new Configuration();
        configuration.configure();
        ServiceRegistryBuilder registry = new ServiceRegistryBuilder();
        registry.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = registry.buildServiceRegistry();
        SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        Session session = sessionFactory.openSession();

        String url = "http://192.168.0.4:8080/data/aircraft.json";
        InputStream source = retrieveStream(url);

        Gson gson = new Gson();
        Reader reader = new InputStreamReader(source);
        Flight_Aware_Messages messages = gson.fromJson(reader, Flight_Aware_Messages.class);
        List<FlightAwarePointType> AntennaReturn = messages.aircraft;
        AntennaReturn = messages.aircraft;
        System.out.println("Count = " + AntennaReturn.size());
    }

    private static InputStream retrieveStream(String url) {
        DefaultHttpClient client = new DefaultHttpClient();
        HttpGet getRequest = new HttpGet(url);
        try {
            HttpResponse getResponse = client.execute(getRequest);
            final int statusCode = getResponse.getStatusLine().getStatusCode();
            if (statusCode != HttpStatus.SC_OK) {
                //Log.w(getClass().getSimpleName(),
                //       "Error " + statusCode + " for URL " + url);
                return null;
            }
            HttpEntity getResponseEntity = getResponse.getEntity();
            return getResponseEntity.getContent();
        }
        catch (IOException e) {
            getRequest.abort();
            //Log.w(getClass().getSimpleName(), "Error for URL " + url, e);
        }
        return null;
    }
}
