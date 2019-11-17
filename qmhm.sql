DROP TABLE users;
DROP TABLE Tag;
DROP TABLE ClassroomTeacher;
DROP TABLE ExamTag;
DROP TABLE ClassroomExamStudentScore;
DROP TABLE ClassroomExam;
DROP TABLE Student;
DROP TABLE Classroom;
DROP TABLE ClassroomMember;

CREATE TABLE users (
  userid int NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
  username varchar(255) NOT NULL UNIQUE,
  password varchar(255) NOT NULL,
  fullname varchar(255) NOT NULL,
  role varchar(255) NOT NULL,
  PRIMARY KEY (userid)
);
INSERT INTO users (username, password, fullname, role)  VALUES ('Adam','1234','Adam Warlock','student'),('Bitt','6789','Bitt Roma','teacher'),('Coco','5555','Coco Fotoru','student'),('Dave','1010','Dave Dane','teacher');

CREATE TABLE Exam (
   eid INT NOT NULL NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
   name VARCHAR(255) NOT NULL,
   description  TEXT,
   level  TINYINT NOT NULL,
   test_case  TEXT NOT NULL,
  PRIMARY KEY (eid)
);
INSERT INTO Exam (name, description, level, test_case)  VALUES 
  ('Basic Python','Plese print out "Hello World"',1,''),
  ('Advance Python','Plese calculate student grade',3,'');
