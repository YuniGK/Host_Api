package yun.jung.kim.Host_Api.domain;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "WATCH", indexes = {
        @Index(columnList = "eventOccurrence"),
        @Index(columnList = "createdAt"),
        @Index(columnList = "createdBy")
})
public class Watch{//감시
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Setter @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Host host;

    @Setter @OneToOne(mappedBy = "watch", fetch = FetchType.LAZY)
    private WatchResult watchResult;

    @Setter
    @Column(nullable = false)
    private String eventOccurrence;//사건 발생
    @Setter @Column(nullable = false)
    private String eventType;//사건 유형

    @CreatedDate @Column(nullable = false)
    private LocalDateTime createdAt;//사건 발생 일시
    @CreatedBy @Column(nullable = false)
    private String createdBy;//사건 발생 주체의 사원
    @LastModifiedDate @Column(nullable = false)
    private LocalDateTime modifiedAt;//사건 수정 일시
    @LastModifiedBy @Column(nullable = false)
    private String modifiedBy;//사건 수정 주체의 사원

    public Watch(String eventOccurrence, String eventType) {
        this.eventOccurrence = eventOccurrence;
        this.eventType = eventType;
    }

    public static Watch of(String eventOccurrence, String eventType) {
        return new Watch(eventOccurrence, eventType);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Watch watch)) return false;
        return id == watch.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}