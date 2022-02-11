package com.folcamp.hechopornosotros.models.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table (name = "user_emprendimiento")
@SQLDelete(sql = "UPDATE user_emprendimiento SET deleted_at = true WHERE id=?")
@Where(clause = "deleted_at = false")
public class UserEmprendimientoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "uid_firebase")
    private String uid;

    @Column(name = "deleted_at")
    private boolean deletedAt = false;

    @OneToOne
    @JoinColumn(name = "emprendimientos", referencedColumnName = "id")

    private EmprendimientoEntity emprendimiento;

}
