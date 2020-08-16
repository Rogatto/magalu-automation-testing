package utils;

import lombok.NonNull;
import lombok.SneakyThrows;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.PropertiesConfiguration;

public final class PropertiesUtils {

  private static final Configuration configuration = loadConfiguration();

  private PropertiesUtils() {
    // Utils can not be initialized
  }

  @SneakyThrows
  private static Configuration loadConfiguration() {
    return new PropertiesConfiguration("project.properties");
  }

  public static String getString(@NonNull String propertyName) {
    return configuration.getString(propertyName);
  }

  public static String extractHostMagalu() {
    return getString("host_magalu");
  }
  public static String extractHostZalenium() {
    return getString("host_zalenium");
  }
}