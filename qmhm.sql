DROP TABLE users;

CREATE TABLE users (
  userid int NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
  username varchar(255) NOT NULL UNIQUE,
  password varchar(255) NOT NULL,
  fullname varchar(255) NOT NULL,
  role varchar(255) NOT NULL,
  PRIMARY KEY (userid)
);
/*Data for the table student  */
INSERT INTO users (username, password, fullname, role)  VALUES ('Adam','1234','Adam Warlock','student'),('Bitt','6789','Bitt Roma','teacher'),('Coco','5555','Coco Fotoru','student'),('Dave','1010','Dave Dane','teacher');
