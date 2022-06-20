package hellojpa;

import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
public class BaseEntity {

    private LocalDateTime writeTime;
    private LocalDateTime modifiedTime;
    private String writer;
    private String modifiedWriter;

    public LocalDateTime getWriteTime() {
        return writeTime;
    }

    public void setWriteTime(LocalDateTime writeTime) {
        this.writeTime = writeTime;
    }

    public LocalDateTime getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(LocalDateTime modifiedTime) {
        this.modifiedTime = modifiedTime;
    }
}