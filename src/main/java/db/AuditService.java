package db;

import pao.course.CourseDao;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AuditService {

    private FileWriter writer;
    private final DateTimeFormatter dateTimeFormatter;

    public AuditService() {
        try{
            this.writer = new FileWriter("src/main/resources/data/audit.csv");
        }catch (IOException e){
            System.out.println("Failed opening the audit.csv file.");
            System.out.println(e.getMessage());
        }
        this.dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    }

    public void logAction(String action) {
        try{
            writer.append(action);
            writer.append(", ");
            writer.append(dateTimeFormatter.format(LocalDateTime.now()));
            writer.append("\n");
            writer.flush();
        }catch (IOException e) {
            System.out.println("Failed writing to csv file.");
        }

    }
}
