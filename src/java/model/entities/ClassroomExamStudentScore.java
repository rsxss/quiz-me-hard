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
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author NATWORPONGLOYSWAI
 */
@Entity
@Table(name = "ClassroomExamStudentScore")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ClassroomExamStudentScore.findAll", query = "SELECT c FROM ClassroomExamStudentScore c")
    , @NamedQuery(name = "ClassroomExamStudentScore.findById", query = "SELECT c FROM ClassroomExamStudentScore c WHERE c.id = :id")
    , @NamedQuery(name = "ClassroomExamStudentScore.findByScore", query = "SELECT c FROM ClassroomExamStudentScore c WHERE c.score = :score")
    , @NamedQuery(name = "ClassroomExamStudentScore.findByExamByStudent", query = "SELECT c FROM ClassroomExamStudentScore c WHERE c.examId = :examId AND c.studentId = :studentId")
    , @NamedQuery(name = "ClassroomExamStudentScore.findByMaxTotalScore", query = "SELECT c FROM ClassroomExamStudentScore c WHERE c.maxTotalScore = :maxTotalScore")})
public class ClassroomExamStudentScore implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Lob
    @Size(max = 65535)
    @Column(name = "code")
    private String code;
    @Basic(optional = false)
    @NotNull
    @Column(name = "score")
    private float score;
    @Basic(optional = false)
    @NotNull
    @Column(name = "max_total_score")
    private float maxTotalScore;
    @JoinColumn(name = "exam_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private ClassroomExam examId;
    @JoinColumn(name = "student_id", referencedColumnName = "id")
    @ManyToOne
    private Student studentId;

    public ClassroomExamStudentScore() {
    }

    public ClassroomExamStudentScore(Integer id) {
        this.id = id;
    }

    public ClassroomExamStudentScore(Integer id, float score, float maxTotalScore) {
        this.id = id;
        this.score = score;
        this.maxTotalScore = maxTotalScore;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public float getMaxTotalScore() {
        return maxTotalScore;
    }

    public void setMaxTotalScore(float maxTotalScore) {
        this.maxTotalScore = maxTotalScore;
    }

    public ClassroomExam getExamId() {
        return examId;
    }

    public void setExamId(ClassroomExam examId) {
        this.examId = examId;
    }

    public Student getStudentId() {
        return studentId;
    }

    public void setStudentId(Student studentId) {
        this.studentId = studentId;
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
        if (!(object instanceof ClassroomExamStudentScore)) {
            return false;
        }
        ClassroomExamStudentScore other = (ClassroomExamStudentScore) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.entities.ClassroomExamStudentScore[ id=" + id + " ]";
    }
    
}
