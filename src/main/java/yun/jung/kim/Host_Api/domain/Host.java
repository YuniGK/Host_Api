package yun.jung.kim.Host_Api.domain;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.core.annotation.Order;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import yun.jung.kim.Host_Api.config.JpaConfig;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@ToString(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "HOST",
        indexes = {
        @Index(columnList = "name"),
        @Index(columnList = "createdAt"),
        @Index(columnList = "createdBy")
})
@EntityListeners(AuditingEntityListener.class)
public class Host {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Setter @Column(nullable = false)
    private String name;
    @Setter @Column(nullable = false)
    private String ip;

    @Setter @Column(nullable = false)
    @ColumnDefault("false")
    private boolean deleteFlag;//삭제 여부

    @CreatedDate @Column(nullable = false)
    private LocalDateTime createdAt;//등록 시간
    @CreatedBy @Column(nullable = false)
    private String createdBy;//등록자

    @LastModifiedDate @Column(nullable = false)
    private LocalDateTime modifiedAt;//수정 시간
    @LastModifiedBy @Column(nullable = false)
    private String modifiedBy;//수정자

    private Host(String name, String ip, boolean deleteFlag) {
        this.name = name;
        this.ip = ip;
        this.deleteFlag = deleteFlag;
    }

    public static Host of(String name, String ip, boolean deleteFlag) {
        return new Host(name, ip, deleteFlag);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Host host)) return false;
        return id == host.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
