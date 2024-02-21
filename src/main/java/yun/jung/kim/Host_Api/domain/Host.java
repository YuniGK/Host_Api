package yun.jung.kim.Host_Api.domain;

import jakarta.persistence.*;
import lombok.*;
import yun.jung.kim.Host_Api.domain.constant.DeteleteFlag;

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
public class Host extends AuditingFields{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Setter
    @JoinColumn(name = "userId")
    @ManyToOne(optional = false)
    private UserAccount userAccount;//유저정보 ID

    @Setter @Column(nullable = false)
    private String name;
    @Setter @Column(nullable = false)
    private String ip;

    @OrderBy("createdAt DESC")
    @OneToMany(mappedBy = "host")
    @ToString.Exclude
    private List<Watch> watches = new ArrayList<Watch>();

    @Setter @Column(nullable = false, name = "delete_flag")
    @Enumerated(EnumType.STRING)
    private DeteleteFlag deleteFlag;//삭제 여부

    private Host(String name, String ip, DeteleteFlag deleteFlag, UserAccount userAccount) {
        this.name = name;
        this.ip = ip;
        this.deleteFlag = deleteFlag;
        this.userAccount = userAccount;
    }

    public static Host of(String name, String ip, UserAccount userAccount) {
        return new Host(name, ip, null, userAccount);
    }

    public static Host of(String name, String ip, DeteleteFlag deleteFlag, UserAccount userAccount) {
        return new Host(name, ip, deleteFlag, userAccount);
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
