package EventManagement.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Set;

@Document(collection = "specialEvent")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SpecialEvent {

    private String id;
    private String eventName;
    private String eventAddress;
    private String eventDescription;
    private String url;
    private String organizerName;
    private String organizerDetails;
    private String buyEvent;
    private String country;
    private String date;
    private String startTime;
    private String endTime;
    private int vacancy;
    private String category;
    private Set<SubEvent> subEvents = new HashSet<>();

}
