package yun.jung.kim.Host_Api.domain;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import yun.jung.kim.Host_Api.domain.constant.EventType;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Getter
@ToString(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "WATCH", indexes = {
        @Index(columnList = "eventOccurrence"),
        @Index(columnList = "createdAt"),
        @Index(columnList = "createdBy")
})
public class Watch extends AuditingFields{//감시
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Setter @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Host host;

    @Setter
    @JoinColumn(name = "userId")
    @ManyToOne(optional = false)
    private UserAccount userAccount;//유저정보 ID

    @Setter
    @Column(nullable = false)
    private String eventOccurrence;//사건 발생

    @Setter @Column(nullable = false, name= "event_type")
    @Enumerated(EnumType.STRING)
    private EventType eventType;//사건 유형

    @Setter
    private String eventResult;//사건 결과

    private Watch(String eventOccurrence, EventType eventType, String eventResult, UserAccount userAccount) {
        this.eventOccurrence = eventOccurrence;
        this.eventType = eventType;
        this.eventResult = eventResult;
        this.userAccount = userAccount;
    }

    public static Watch of(String eventOccurrence, EventType eventType, String eventResult, UserAccount userAccount) {
        return new Watch(eventOccurrence, eventType, eventResult, userAccount);
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
