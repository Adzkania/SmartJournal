import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

public class SentimentAPI {
    
    public static String analyzeSentiment(String text) {
        try {
            // Load token from .env
            Map<String, String> env = EnvLoader.loadEnv(".env");
            String bearerToken = env.get("BEARER_TOKEN");
            
            if (bearerToken == null || bearerToken.isEmpty()) {
                return "Mood unavailable";
            }
            
            String apiURL = "https://router.huggingface.co/hf-inference/models/distilbert/distilbert-base-uncased-finetuned-sst-2-english";
            
            URL url = new URL(apiURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Accept", "application/json");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Authorization", "Bearer " + bearerToken);
            conn.setDoOutput(true);
            
            // Escape quotes in the text
            String escapedText = text.replace("\"", "\\\"").replace("\n", " ");
            String jsonBody = "{\"inputs\": \"" + escapedText + "\"}";
            
            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = jsonBody.getBytes("utf-8");
                os.write(input, 0, input.length);
            }
            
            int responseCode = conn.getResponseCode();
            if (responseCode != 200 && responseCode != 201) {
                return "Mood unavailable";
            }
            
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
            StringBuilder sb = new StringBuilder();
            String responseLine;
            while ((responseLine = br.readLine()) != null) {
                sb.append(responseLine.trim());
            }
            conn.disconnect();
            
            // Parse response to get the label (POSITIVE or NEGATIVE)
            String response = sb.toString();
            
            // Very basic score comparison (no JSON library)
int negIndex = response.indexOf("\"NEGATIVE\"");
int posIndex = response.indexOf("\"POSITIVE\"");

if (negIndex == -1 && posIndex == -1) {
    return "Neutral";
}

// Extract scores
double negScore = 0.0;
double posScore = 0.0;

try {
    if (negIndex != -1) {
        String negPart = response.substring(negIndex);
        negScore = Double.parseDouble(
            negPart.split("\"score\":")[1].split("[,}]")[0]
        );
    }

    if (posIndex != -1) {
        String posPart = response.substring(posIndex);
        posScore = Double.parseDouble(
            posPart.split("\"score\":")[1].split("[,}]")[0]
        );
    }
} catch (Exception e) {
    return "Mood unavailable";
}

return negScore > posScore ? "Negative" : "Positive";

            
        } catch (Exception e) {
            System.out.println("Error analyzing sentiment: " + e.getMessage());
            return "Mood unavailable";
        }
    }
}
