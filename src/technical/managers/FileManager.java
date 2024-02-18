package technical.managers;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import necessary.Movie;
import technical.exceptions.FileException;
import technical.exceptions.PartlyCorrectDataFileException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Vector;

public class FileManager {

    public static class CustomLocalDateSerializer extends StdSerializer<LocalDate> {
        private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

        public CustomLocalDateSerializer() {
            this(null);
        }
        public CustomLocalDateSerializer(Class<LocalDate> t) {
            super(t);
        }

        @Override
        public void serialize(
                LocalDate value,
                JsonGenerator gen,
                SerializerProvider arg2)
                throws IOException, JsonProcessingException {
            gen.writeString(formatter.format(value));
        }
    }

    public static class CustomLocalDateDeserializer extends StdDeserializer<LocalDate> {

        public CustomLocalDateDeserializer() {
            this(null);
        }

        public CustomLocalDateDeserializer(Class<?> vc) {
            super(vc);
        }

        @Override
        public LocalDate deserialize(JsonParser jsonParser, DeserializationContext context) throws IOException {
            String value = jsonParser.getText();
            if (!"".equals(value)) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
                return LocalDate.parse(value, formatter);
            }
            return null;
        }
    }

    private final ObjectMapper mapper;
    private final File file;

    public FileManager(String filename){
        mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
//        mapper.setDateFormat(new SimpleDateFormat("dd.MM.yyyy"));
        mapper.setDateFormat(DateFormat.getDateInstance(DateFormat.SHORT));

        if (!filename.endsWith(".json")) {
            throw new FileException("Указан файл недопустимого формата.");
        }
        file = new File(filename);

        if (!file.exists() || !file.isFile()){
            throw new FileException("Нет файла с указанным именем");
        } else if (!file.canRead() || !file.canWrite()){
            throw new FileException("Файл недоступен для чтения и/или записи.");
        }
    }

    public Movie elemFromFile() throws IOException {
        return mapper.readValue(file, Movie.class);
    }

    public Vector<Movie> collectionFromFile() throws IOException {
        TypeReference<Vector<Movie>> type = new TypeReference<>() {};

        Vector<Movie> given_vec = mapper.readValue(file, type);
        Vector<Movie> ok_vec = new Vector<>(given_vec);
        for (Movie i : given_vec){
            if(!i.checkItself()){
                ok_vec.remove(i);
            }
        }
        if (ok_vec.isEmpty()){
            throw new FileException("Файл не содержит корректных данных. Коллекция не была изменена.");
        }
//        else if(ok_vec.size() != given_vec.size()){
//            throw new PartlyCorrectDataFileException("Некоторые данные не были загружены.");
//        }
        return ok_vec;
    }

    public void writeToFile(Object o) throws JsonProcessingException {
        try(FileWriter fw = new FileWriter(file)) {
            fw.write(mapper.writeValueAsString(o));
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
}
