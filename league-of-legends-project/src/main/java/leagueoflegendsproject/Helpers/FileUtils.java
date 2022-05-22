package leagueoflegendsproject.Helpers;

import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

public class FileUtils {

    public static <T> T parseFileToObject(String path, Class<T> clazz) throws IOException {
        StringBuilder builder = loadFileToStringBuilder(path);
        var response = RiotHttpClient.parseResponseToClassObject(builder.toString(), clazz);
        return response;
    }

    public static <T> T parseFileToObject(String path, TypeToken<T> type) throws IOException {
        StringBuilder builder = loadFileToStringBuilder(path);
        var response = RiotHttpClient.parseResponseToClassObject(builder.toString(), type);
        return response;
    }

    private static StringBuilder loadFileToStringBuilder(String path) throws IOException {
        File file = new File(path);
        StringBuilder builder = new StringBuilder();
        Files.lines(Path.of(file.getPath())).forEach(e -> builder.append(e).append('\n'));
        return builder;
    }

}
