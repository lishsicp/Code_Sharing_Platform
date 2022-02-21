package platform.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;



@Entity
@NoArgsConstructor
public class Code {
    @Id
    @JsonIgnore
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Getter
    private String id;

    @Getter
    @Setter
    @JsonProperty
    private String code;

    @Getter
    @Setter
    private String date;

    @Getter
    @Setter
    private long time;

    @Getter
    @Setter
    private long views;

    @Getter
    @Setter
    @JsonIgnore
    private boolean viewRestricted;

    @Getter
    @Setter
    @JsonIgnore
    private boolean timeRestricted;

}
