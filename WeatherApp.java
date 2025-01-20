import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner; 
import org.json.JSONObject;

public class WeatherApp {

    private static final String API_KEY = "your_api_key"; 
    private static final String BASE_URL = "http://api.openweathermap.org/data/2.5/weather?q=";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter city name: ");
        String cityName = scanner.nextLine();
        String response = getWeatherData(cityName);

        if (response != null) {
            parseAndDisplayWeather(response);
        } else {
            System.out.println("Unable to get weather data. Please try again.");
        }
    }
    private static String getWeatherData(String cityName) {
        try {
            URL url = new URL(BASE_URL + cityName + "&appid=" + API_KEY + "&units=metric");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);  
            Scanner scanner = new Scanner(url.openStream());
            StringBuilder response = new StringBuilder();
            while (scanner.hasNext()) {
                response.append(scanner.nextLine());
            }
            scanner.close();
            return response.toString();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    private static void parseAndDisplayWeather(String response) {
        try {
            JSONObject jsonResponse = new JSONObject(response);
            String cityName = jsonResponse.getString("name");
            JSONObject main = jsonResponse.getJSONObject("main");
            double temp = main.getDouble("temp");
            int humidity = main.getInt("humidity");
            double tempMin = main.getDouble("temp_min");
            double tempMax = main.getDouble("temp_max");
            JSONObject weather = jsonResponse.getJSONArray("weather").getJSONObject(0);
            String description = weather.getString("description");
            System.out.println("Weather Report for: " + cityName);
            System.out.println("Description: " + description);
            System.out.println("Temperature: " + temp + "°C");
            System.out.println("Min Temperature: " + tempMin + "°C");
            System.out.println("Max Temperature: " + tempMax + "°C");
            System.out.println("Humidity: " + humidity + "%");
        } catch (Exception e) {
            System.out.println("Error parsing weather data.");
            e.printStackTrace();
        }
    }
}
