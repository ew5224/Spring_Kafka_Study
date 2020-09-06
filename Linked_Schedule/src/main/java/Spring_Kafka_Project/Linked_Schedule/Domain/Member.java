package Spring_Kafka_Project.Linked_Schedule.Domain;

public class Member {

    private Long id;
    private Long pw;
    private Long name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPw() {
        return pw;
    }

    public void setPw(Long pw) {
        this.pw = pw;
    }

    public Long getName() {
        return name;
    }

    public void setName(Long name) {
        this.name = name;
    }
}
