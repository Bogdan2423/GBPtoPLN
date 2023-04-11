package project;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

public class CurrencyConverter {
    public static Double[] convertGBP_PLN(double price, boolean isGbp) throws Exception {
        URL url = new URL("http://api.nbp.pl/api/exchangerates/rates/a/gbp/?format=json");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuilder content = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();
        con.disconnect();

        JSONObject json = new JSONObject(content.toString());
        double rate = json.getJSONArray("rates").getJSONObject(0).getDouble("mid");

        Double[] result = new Double[2];
        result[0] = rate;
        if (isGbp)
            result[1] = price * rate;
        else
            result[1] = price / rate;
        return result;
    }
}