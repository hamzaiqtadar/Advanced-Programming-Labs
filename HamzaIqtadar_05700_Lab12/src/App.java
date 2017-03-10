import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.context.support.ClassPathXmlApplicationContext;



/**
 * My main App. 
 * <p>
 This executes everything.
 */

public class App {

    public static void main(String[] args) 
    {
    	ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
    	CloDao clodao = context.getBean(CloDao.class);
        // Add new clo
        Clo clo = new Clo();
        clo.setName("CLO 1");
        clo.setDescription("Design efficient solutions for algorithmic problems");
        clo.setPlo("2");
        clodao.addClo(clo);

        Clo clo2 = new Clo();
        clo.setName("CLO 2");
        clo.setDescription("efficient solutions for algorithmis");
        clo.setPlo("3");
        clodao.addClo(clo2);
        
        Clo clo3 = new Clo();
        clo.setName("CLO 3");
        clo.setDescription("Design efficient solutions for algorithms");
        clo.setPlo("4");
        clodao.addClo(clo3);       
        
        Clo clo4 = new Clo();
        clo.setName("CLO 4");
        clo.setDescription("Design efficient solutions ");
        clo.setPlo("5");
        clodao.addClo(clo4);
        
        Clo clo5 = new Clo();
        clo.setName("CLO 5");
        clo.setDescription("Design ");
        clo.setPlo("6");
        clodao.addClo(clo5);
        
        HashSet<Object> clos=new HashSet<Object>();
        clos.add(clo);
        clos.add(clo2);
        clos.add(clo3);
        clos.add(clo4);
        clos.add(clo5);
        
        clodao.updateClo(clo);
        
        CourseDao coursedao=new CourseDao();
     	Course course1=new Course();
     	course1.setClasstitle("AP");
        int hours=4;
        course1.setCreditHours(hours);
        
     	Course course2=new Course();
     	course2.setClasstitle("SE");
        course2.setCreditHours(3);
     	
        //course1.setTeacher(teacher);
//        coursedao.addCourse(course1);
        //course2.setTeacher(teacher);
//        coursedao.addCourse(course2);
        
     	Set<Object> courses=new HashSet<Object>();
        courses.add(course1);
        courses.add(course2);
        
        TeacherDao teacherdao=new TeacherDao();
     	Teacher teacher=new Teacher();
     	Teacher teacher2=new Teacher();
     	
     	teacher.setName("Fahad Satti");
     	teacher.setCourses(courses);
//     	teacherdao.addTeacher(teacher);
     	
     	teacher2.setName("Aakash Ahmad");
     	teacher2.setCourses(courses);
//     	teacherdao.addTeacher(teacher2);
     	
     	Set<Object> teachers=new HashSet<Object>();
     	teachers.add(teacher);
     	teachers.add(teacher2);
        
        StudentDao studentdao=new StudentDao();
        Student student=new Student();
        student.setName("Safie");
        student.setCourses(courses);
//        studentdao.addStudent(student);
        
        ContentDao contentdao=new ContentDao();
        Content content1=new Content();
        content1.setTitle("AP");
        content1.setDescription("description");
//        content1.setClo(clos);
//        contentdao.addContent(content1);
        
        GradeDao gradedao=new GradeDao();
        Grade grade1=new Grade();
        grade1.setName("A");
        grade1.setScore(80);
//        gradedao.addGrade(grade1);
        
        Grade grade2=new Grade();
        grade1.setName("B");
        grade1.setScore(70);
//        gradedao.addGrade(grade2);
        
        Grade grade3=new Grade();
        grade1.setName("C");
        grade1.setScore(60);
//        gradedao.addGrade(grade3);
        
        Grade grade4=new Grade();
        grade1.setName("D");
        grade1.setScore(50);
//        gradedao.addGrade(grade4);
        
        Grade grade5=new Grade();
        grade1.setName("E");
        grade1.setScore(40);
//        gradedao.addGrade(grade5);
        
        Grade grade6=new Grade();
        grade1.setName("F");
        grade1.setScore(30);
//        gradedao.addGrade(grade6);
        // Delete an existing clo
        //dao.deleteClo(1);

        // Get all clos
        for (Clo iter : clodao.getAllClos()) {
            System.out.println(iter);
        }

        // Get clo by id
        for(int i=0;i<6;i++)
        {
            System.out.println(clodao.getCloById(i));
        }

        
    }

}