package demo.domain;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.Random;

@Data
@Entity
@Table(name = "RUNNING_ANALYSIS")
//@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties({"latitude","longitude","runningDistance"})
public class RunningInformation {
    enum HealthWarningLevel {
        HIGH, NORMAL, LOW;
    }

    @Id
    @GeneratedValue
    private Long userId;

    private final String runningId;

    //@JsonIgnore
    private double latitude;
    //@JsonIgnore
    private double longitude;
    //@JsonIgnore
    private double runningDistance;

    private int heartRate = 0;
    private double totalRunningTime;

    private HealthWarningLevel healthWarningLevel;
    private Date timestamp = new Date();

//    @AttributeOverrides({
//            @AttributeOverride(name = "username", column = @Column(name = "userName")),
//            @AttributeOverride(name = "address", column = @Column(name = "userAddress")),
//    })
    @Embedded
    private UserInfo userInfo;


    public RunningInformation() {
        this.runningId = "";
    }

//    public RunningInformation(String runningId) {
//        this.runningId = runningId;
//    }

    @JsonCreator
    public RunningInformation(@JsonProperty("userInfo") UserInfo userInfo) {
        this.runningId = "";
        this.userInfo = userInfo;
    }

    public String getUserName() {
        return this.userInfo == null? null : this.userInfo.getUsername();
    }
    public String getUserAddress() {
        return this.userInfo == null? null : this.userInfo.getAddress();

    }
    @JsonIgnore
    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setHeartRate(int heartRate) {
        int max=200;
        int min=60;
        Random random = new Random();
        this.heartRate= random.nextInt(max)%(max-min+1) + min;
        if (this.heartRate >= 60 && this.heartRate <= 75) {
            healthWarningLevel = HealthWarningLevel.LOW;
        } else if (this.heartRate > 75 && this.heartRate <= 120) {
            healthWarningLevel = HealthWarningLevel.NORMAL;
        } else {
           healthWarningLevel = HealthWarningLevel.HIGH;
        }
    }



}
