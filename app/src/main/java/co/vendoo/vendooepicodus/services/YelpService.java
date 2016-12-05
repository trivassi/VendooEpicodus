package co.vendoo.vendooepicodus.services;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import co.vendoo.vendooepicodus.Constants;
import co.vendoo.vendooepicodus.models.Store;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import se.akerfeldt.okhttp.signpost.OkHttpOAuthConsumer;
import se.akerfeldt.okhttp.signpost.SigningInterceptor;

/**
 * Created by T on 12/4/16.
 */

public class YelpService {
    public static void findStores(String location, Callback callback) {

        OkHttpOAuthConsumer consumer = new OkHttpOAuthConsumer(Constants.YELP_CONSUMER_KEY, Constants.YELP_CONSUMER_SECRET);
        consumer.setTokenWithSecret(Constants.YELP_TOKEN, Constants.YELP_TOKEN_SECRET);

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new SigningInterceptor(consumer))
                .build();

        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.YELP_BASE_URL).newBuilder();
        urlBuilder.addQueryParameter(Constants.YELP_LOCATION_QUERY_PARAMETER, location);
        String url = urlBuilder.build().toString();

        Request request = new Request.Builder()
                .url(url)
                .build();

        Call call = client.newCall(request);
        call.enqueue(callback);
    }

    public ArrayList<Store> processResults(Response response) {
        ArrayList<Store> stores = new ArrayList<>();

        try {
            String jsonData = response.body().string();
            if (response.isSuccessful()) {
                JSONObject yelpJSON = new JSONObject(jsonData);
                JSONArray businessesJSON = yelpJSON.getJSONArray("businesses");
                for (int i = 0; i < businessesJSON.length(); i++) {
                    JSONObject storeJSON = businessesJSON.getJSONObject(i);
                    String name = storeJSON.getString("name");
                    String phone = storeJSON.optString("display_phone", "Phone not available");
                    String website = storeJSON.getString("url");
                    double rating = storeJSON.getDouble("rating");
                    String imageUrl = storeJSON.getString("image_url");
                    double latitude = storeJSON.getJSONObject("location")
                            .getJSONObject("coordinate").getDouble("latitude");
                    double longitude = storeJSON.getJSONObject("location")
                            .getJSONObject("coordinate").getDouble("longitude");
                    ArrayList<String> address = new ArrayList<>();
                    JSONArray addressJSON = storeJSON.getJSONObject("location")
                            .getJSONArray("display_address");
                    for (int y = 0; y < addressJSON.length(); y++) {
                        address.add(addressJSON.get(y).toString());
                    }

                    Store store = new Store(name, phone, website, rating,
                            imageUrl, address, latitude, longitude);
                    stores.add(store);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return stores;
    }

}
