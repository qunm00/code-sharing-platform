package codesharingplatform;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Entity
class Code {
    @Id
    @JsonIgnore
    private String id = UUID.randomUUID().toString();
    private String code;
    private LocalDateTime date;
    private LocalDateTime time;
    private long views;
    @JsonIgnore
    private boolean timeExpirable;
    @JsonIgnore
    private boolean viewsExpirable;

    public Code() { }

    public Code(String code, Long time, Long views) {
        this.code = code;
        this.date = LocalDateTime.now();
        this.time = this.date.plusSeconds(time);
        this.views = views > 0 ? views : 0;
        this.timeExpirable = time > 0;
        this.viewsExpirable = views > 0;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDate() {
        return date.format(
                DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")
        );
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public long getTime() {
        long timeLeft = timeToSeconds(this.time) - timeToSeconds(LocalDateTime.now());
        return timeLeft > 0 ? timeLeft : 0 ;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public long getViews() {
        return views;
    }

    public void setViews(long views) {
        this.views = views;
    }

    public boolean isTimeExpirable() {
        return timeExpirable;
    }

    public void setTimeExpirable(boolean timeExpirable) {
        this.timeExpirable = timeExpirable;
    }

    public boolean isViewsExpirable() {
        return viewsExpirable;
    }

    public void setViewsExpirable(boolean viewsExpirable) {
        this.viewsExpirable = viewsExpirable;
    }

    public long timeToSeconds(LocalDateTime date) {
        return date.getHour() * 3600 + date.getMinute() * 60 + date.getSecond();
    }

    @Override
    public String toString() {
        return "Code{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", date=" + date +
                ", time=" + time +
                ", views=" + views +
                ", timeExpirable=" + timeExpirable +
                ", viewsExpirable=" + viewsExpirable +
                '}';
    }
}