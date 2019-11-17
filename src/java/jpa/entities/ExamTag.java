/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa.entities;

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
@Table(name = "ExamTag")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ExamTag.findAll", query = "SELECT e FROM ExamTag e")
    , @NamedQuery(name = "ExamTag.findById", query = "SELECT e FROM ExamTag e WHERE e.id = :id")})
public class ExamTag implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "exam_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private ClassroomExam examId;
    @JoinColumn(name = "tag_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Tag tagId;

    public ExamTag() {
    }

    public ExamTag(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ClassroomExam getExamId() {
        return examId;
    }

    public void setExamId(ClassroomExam examId) {
        this.examId = examId;
    }

    public Tag getTagId() {
        return tagId;
    }

    public void setTagId(Tag tagId) {
        this.tagId = tagId;
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
        if (!(object instanceof ExamTag)) {
            return false;
        }
        ExamTag other = (ExamTag) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpa.entities.ExamTag[ id=" + id + " ]";
    }
    
}
