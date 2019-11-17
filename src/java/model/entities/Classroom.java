/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author NATWORPONGLOYSWAI
 */
@Entity
@Table(name = "Classroom")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Classroom.findAll", query = "SELECT c FROM Classroom c")
    , @NamedQuery(name = "Classroom.findById", query = "SELECT c FROM Classroom c WHERE c.id = :id")
    , @NamedQuery(name = "Classroom.findByClassroomName", query = "SELECT c FROM Classroom c WHERE c.classroomName = :classroomName")})
public class Classroom implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "classroom_name")
    private String classroomName;
    @Lob
    @Size(max = 65535)
    @Column(name = "classroom_description")
    private String classroomDescription;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "classroomId")
    private Collection<ClassroomMember> classroomMemberCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "classroomId")
    private Collection<ClassroomExam> classroomExamCollection;

    public Classroom() {
    }

    public Classroom(Integer id) {
        this.id = id;
    }

    public Classroom(Integer id, String classroomName) {
        this.id = id;
        this.classroomName = classroomName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getClassroomName() {
        return classroomName;
    }

    public void setClassroomName(String classroomName) {
        this.classroomName = classroomName;
    }

    public String getClassroomDescription() {
        return classroomDescription;
    }

    public void setClassroomDescription(String classroomDescription) {
        this.classroomDescription = classroomDescription;
    }

    @XmlTransient
    public Collection<ClassroomMember> getClassroomMemberCollection() {
        return classroomMemberCollection;
    }

    public void setClassroomMemberCollection(Collection<ClassroomMember> classroomMemberCollection) {
        this.classroomMemberCollection = classroomMemberCollection;
    }

    @XmlTransient
    public Collection<ClassroomExam> getClassroomExamCollection() {
        return classroomExamCollection;
    }

    public void setClassroomExamCollection(Collection<ClassroomExam> classroomExamCollection) {
        this.classroomExamCollection = classroomExamCollection;
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
        if (!(object instanceof Classroom)) {
            return false;
        }
        Classroom other = (Classroom) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpa.entities.Classroom[ id=" + id + " ]";
    }
    
}
