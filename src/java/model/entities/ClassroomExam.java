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
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
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
@Table(name = "ClassroomExam")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ClassroomExam.findAll", query = "SELECT c FROM ClassroomExam c")
    , @NamedQuery(name = "ClassroomExam.findById", query = "SELECT c FROM ClassroomExam c WHERE c.id = :id")
    , @NamedQuery(name = "ClassroomExam.findByName", query = "SELECT c FROM ClassroomExam c WHERE c.name = :name")
    , @NamedQuery(name = "ClassroomExam.findByLevel", query = "SELECT c FROM ClassroomExam c WHERE c.level = :level")})
public class ClassroomExam implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "name")
    private String name;
    @Lob
    @Size(max = 65535)
    @Column(name = "description")
    private String description;
    @Basic(optional = false)
    @NotNull
    @Column(name = "level")
    private short level;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "test_case")
    private String testCase;
    @JoinColumn(name = "classroom_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Classroom classroomId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "examId")
    private Collection<ExamTag> examTagCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "examId")
    private Collection<ClassroomExamStudentScore> classroomExamStudentScoreCollection;

    public ClassroomExam() {
    }

    public ClassroomExam(Integer id) {
        this.id = id;
    }

    public ClassroomExam(Integer id, String name, short level, String testCase) {
        this.id = id;
        this.name = name;
        this.level = level;
        this.testCase = testCase;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public short getLevel() {
        return level;
    }

    public void setLevel(short level) {
        this.level = level;
    }

    public String getTestCase() {
        return testCase;
    }

    public void setTestCase(String testCase) {
        this.testCase = testCase;
    }

    public Classroom getClassroomId() {
        return classroomId;
    }

    public void setClassroomId(Classroom classroomId) {
        this.classroomId = classroomId;
    }

    @XmlTransient
    public Collection<ExamTag> getExamTagCollection() {
        return examTagCollection;
    }

    public void setExamTagCollection(Collection<ExamTag> examTagCollection) {
        this.examTagCollection = examTagCollection;
    }

    @XmlTransient
    public Collection<ClassroomExamStudentScore> getClassroomExamStudentScoreCollection() {
        return classroomExamStudentScoreCollection;
    }

    public void setClassroomExamStudentScoreCollection(Collection<ClassroomExamStudentScore> classroomExamStudentScoreCollection) {
        this.classroomExamStudentScoreCollection = classroomExamStudentScoreCollection;
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
        if (!(object instanceof ClassroomExam)) {
            return false;
        }
        ClassroomExam other = (ClassroomExam) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpa.entities.ClassroomExam[ id=" + id + " ]";
    }
    
}
