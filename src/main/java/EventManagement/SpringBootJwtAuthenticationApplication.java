package EventManagement;

import EventManagement.model.GeneralEvent;
import EventManagement.repository.GeneralEventRepository;
import EventManagement.repository.SpecialEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootApplication
public class SpringBootJwtAuthenticationApplication  {

    @Autowired
    private GeneralEventRepository generalEventRepository;
    @Autowired
    private SpecialEventRepository specialEventRepository;
    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    Date date = new Date();

    public static void main(String[] args) {
        SpringApplication.run(SpringBootJwtAuthenticationApplication.class, args);
    }
}