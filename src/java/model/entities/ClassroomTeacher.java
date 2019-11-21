/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author NATWORPONGLOYSWAI
 */
@Entity
@Table(name = "ClassroomTeacher")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ClassroomTeacher.findAll", query = "SELECT c FROM ClassroomTeacher c")
    , @NamedQuery(name = "ClassroomTeacher.findById", query = "SELECT c FROM ClassroomTeacher c WHERE c.id = :id")
    , @NamedQuery(name = "ClassroomTeacher.findClassroomTeacherByClassroomMember", 
                query = "SELECT c FROM ClassroomTeacher c JOIN c.classroomMemberId cm WHERE cm = :classroomMember")})
public class ClassroomTeacher implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "classroom_member_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private ClassroomMember classroomMemberId;

    public ClassroomTeacher() {
    }

    public ClassroomTeacher(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ClassroomMember getClassroomMemberId() {
        return classroomMemberId;
    }

    public void setClassroomMemberId(ClassroomMember classroomMemberId) {
        this.classroomMemberId = classroomMemberId;
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
        if (!(object instanceof ClassroomTeacher)) {
            return false;
        }
        ClassroomTeacher other = (ClassroomTeacher) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpa.entities.ClassroomTeacher[ id=" + id + " ]";
    }
    
}
