CREATE TABLE Users (
  id INT NOT NULL,
  username VARCHAR(255) NOT NULL,
  password VARCHAR(255) NOT NULL,
  email VARCHAR(255),
  first_name VARCHAR(255),
  last_name VARCHAR(255),
  is_admin BOOLEAN,
  PRIMARY KEY (id),
  CONSTRAINT CK_User_username UNIQUE (username)
);

CREATE TABLE Student (
  id INT NOT NULL ,
  user_id INT NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT FK_Student_user_id FOREIGN KEY (user_id) REFERENCES Users(id) 
);

CREATE TABLE Classroom (
  id INT NOT NULL ,
  classroom_name VARCHAR(255) NOT NULL,
  classroom_description VARCHAR(255),
  PRIMARY KEY (id),
  CONSTRAINT CK_Classroom_classroom_name UNIQUE (classroom_name)
);

CREATE TABLE ClassroomMember (
  id INT NOT NULL,
  classroom_id INT NOT NULL,
  user_id INT NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT FK_ClassroomMember_classroom_id FOREIGN KEY (classroom_id) REFERENCES Classroom(id) ON DELETE CASCADE,
  CONSTRAINT FK_ClassroomMember_user_id FOREIGN KEY (user_id) REFERENCES User(id) ON DELETE CASCADE
);

CREATE TABLE ClassroomTeacher (
  id INT NOT NULL ,
  classroom_member_id INT NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT FK_ClassroomTeacher_classroom_member_id FOREIGN KEY (classroom_member_id) REFERENCES ClassroomMember(id) ON DELETE CASCADE
);

CREATE TABLE ClassroomExam (
  id INT NOT NULL ,
  classroom_id INT NOT NULL,
  name VARCHAR(255) NOT NULL,
  description VARCHAR(255),
  level TINYINT NOT NULL,
  test_case TEXT NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT FK_ClassroomExam_classroom_id FOREIGN KEY (classroom_id) REFERENCES Classroom(id)
);

CREATE TABLE ClassroomExamStudentScore (
  id INT NOT NULL ,
  exam_id INT NOT NULL,
  score TEXT NOT NULL,
  max_total_score FLOAT NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT FK_ClassroomExamStudentScore_exam_id FOREIGN KEY (exam_id) REFERENCES ClassroomExam(id)
);

CREATE TABLE Tag (
  id INT NOT NULL ,
  tag_name VARCHAR(50) NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT CK_Tag_tag_name UNIQUE (tag_name)
);

CREATE TABLE ExamTag (
  id INT NOT NULL ,
  exam_id INT NOT NULL,
  tag_id INT NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT FK_ExamTag_exam_id FOREIGN KEY (exam_id) REFERENCES ClassroomExam(id),
  CONSTRAINT FK_ExamTag_tag_id FOREIGN KEY (tag_id) REFERENCES Tag(id)
);