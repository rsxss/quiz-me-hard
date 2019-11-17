/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author NATWORPONGLOYSWAI
 */
@Entity
@Table(name = "ClassroomMember")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ClassroomMember.findAll", query = "SELECT c FROM ClassroomMember c")
    , @NamedQuery(name = "ClassroomMember.findById", query = "SELECT c FROM ClassroomMember c WHERE c.id = :id")})
public class ClassroomMember implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "classroom_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Classroom classroomId;
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private User userId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "classroomMemberId")
    private Collection<ClassroomTeacher> classroomTeacherCollection;

    public ClassroomMember() {
    }

    public ClassroomMember(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Classroom getClassroomId() {
        return classroomId;
    }

    public void setClassroomId(Classroom classroomId) {
        this.classroomId = classroomId;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    @XmlTransient
    public Collection<ClassroomTeacher> getClassroomTeacherCollection() {
        return classroomTeacherCollection;
    }

    public void setClassroomTeacherCollection(Collection<ClassroomTeacher> classroomTeacherCollection) {
        this.classroomTeacherCollection = classroomTeacherCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ClassroomMember)) {
            return false;
        }
        ClassroomMember other = (ClassroomMember) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpa.entities.ClassroomMember[ id=" + id + " ]";
    }
    
}
