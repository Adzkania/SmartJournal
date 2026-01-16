import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class EnvLoader {

    public static Map<String, String> loadEnv(String filename) {
        Map<String, String> env = new HashMap<>();
        BufferedReader br = null;

        try {
            // 1️⃣ Try working directory (VS Code / terminal)
            File file = new File(filename);
            if (file.exists()) {
                br = new BufferedReader(new FileReader(file));
            } else {
                // 2️⃣ Try classpath (safer for future JAR usage)
                InputStream is = EnvLoader.class
                        .getClassLoader()
                        .getResourceAsStream(filename);

                if (is == null) {
                    throw new FileNotFoundException(filename + " not found in project root or classpath.");
                }

                br = new BufferedReader(new InputStreamReader(is));
            }

            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty() || line.startsWith("#")) continue;

                String[] parts = line.split("=", 2);
                if (parts.length == 2) {
                    env.put(parts[0].trim(), parts[1].trim());
                }
            }

        } catch (IOException e) {
            System.err.println("Error reading .env file: " + e.getMessage());
        } finally {
            try {
                if (br != null) br.close();
            } catch (IOException ignored) {}
        }

        return env;
    }
}

