package com.example.demo.controllers;

import com.example.demo.models.*;
import com.example.demo.repositories.*;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

import java.sql.Timestamp;
import java.util.*;


@Controller
public class MainController
{
    @Autowired
    AuthorityRepo authorityRepo;
    @Autowired
    private UserService userService;
    @Autowired
    PersonRepo personRepo;
    @Autowired
    CourseRepo courseRepo;
    @Autowired
    AttendanceRepo attendanceRepo;
    @Autowired
    RegistrationTimestampRepo registrationTimestampRepo;
    @Autowired
    EvaluationRepo evaluationRepo;





//    @RequestMapping("/databasetesting")
//    public String dbTest() {
//
//
//        if(authorityRepo.count()==0) {
//            // create two authorities/roles
//            Authority adminAuth = new Authority();
//            adminAuth.setRole("ADMIN");
//            authorityRepo.save(adminAuth);
//
//            Authority teacherAuth = new Authority();
//            teacherAuth.setRole("TEACHER");
//            authorityRepo.save(teacherAuth);
//
//            Authority studentAuth = new Authority();
//            studentAuth.setRole("STUDENT");
//            authorityRepo.save(studentAuth);
//
//            // create some people, first a teacher
//            Set<Authority> auths = new HashSet<>();
//
//            auths.add(teacherAuth);
//            Person teacherPerson = new Person();
//            teacherPerson.setAuthorities(auths);
//            teacherPerson.setContactNum("1234567890");
//            teacherPerson.setEmail("teachermail@abc.com");
//            teacherPerson.setEnabled(true);
//            teacherPerson.setNameFirst("TeacherSueFN");
//            teacherPerson.setNameLast("TeacherSueLN");
//            teacherPerson.setPassword("pass");
//            teacherPerson.setUsername("teacher");
//            teacherPerson.setSelectVal("some value");
//            personRepo.save(teacherPerson);
//            System.out.println("================================== created teacher Sue");
//
//            // creat another teacher
//            Person teacherPerson2 = new Person();
//            teacherPerson2.setAuthorities(auths);
//            teacherPerson2.setContactNum("1234567890");
//            teacherPerson2.setEmail("teachermail2@abc.com");
//            teacherPerson2.setEnabled(true);
//            teacherPerson2.setNameFirst("TeacherBobFN");
//            teacherPerson2.setNameLast("TeacherBobLN");
//            teacherPerson2.setPassword("pass");
//            teacherPerson2.setUsername("teacher2");
//            teacherPerson2.setSelectVal("some value");
//            personRepo.save(teacherPerson2);
//            System.out.println("================================== created teacher Bob");
//
//            auths.add(adminAuth);
//            Person adminPerson = new Person();
//            adminPerson.setAuthorities(auths);
//            adminPerson.setContactNum("789012345");
//            adminPerson.setEmail("adminmail@abc.com");
//            adminPerson.setEnabled(true);
//            adminPerson.setNameFirst("AdminSueFN");
//            adminPerson.setNameLast("AdminSueLN");
//            adminPerson.setPassword("pass");
//            adminPerson.setUsername("admin");
//            adminPerson.setSelectVal("some value");
//            personRepo.save(adminPerson);
//            System.out.println("================================== created admin Person");
//
//            // create a new Course
//            Course courseJava = new Course();
//            courseJava.setName("Java 101");
//            courseJava.setDateStart(new Date());// set to todays date with new Date object
//            courseJava.setDateEnd(new Date());// for testing, set end date to be the same
//            courseJava.setNumEvaluationsCompleted(10);
//            courseJava.setCourseRegistrationNum(1000);
//            // attach teacher Sue to this course
//            courseJava.addPerson(teacherPerson); // this is teacher Sue
//            courseRepo.save(courseJava);
//
//            // create another new course
//            Course coursePython = new Course();
//            coursePython.setName("Python 500");
//            coursePython.setDateStart(new Date());// set to todays date with new Date object
//            coursePython.setDateEnd(new Date());// for testing, set end date to be the same
//            coursePython.setNumEvaluationsCompleted(20);
//            coursePython.setCourseRegistrationNum(2000);
//            // attach teacher Bob to this course
//            coursePython.addPerson(teacherPerson2);
//            courseRepo.save(coursePython);
//
//            // create an evaluation
//            Evaluation evaluation1 = new Evaluation();
//            evaluation1.setClassroomEnvironment("Excellent");
//            evaluation1.setCourseContentRating("Average");
//            evaluation1.setEquipmentRating("Good");
//            evaluation1.setHowDidYouFindOut("internet");
//            evaluation1.setInstructionQualityRating("Poor");
//            evaluation1.setTrainingExperienceRating("Fair");
//            evaluation1.setTextBookRating("Average");
//            evaluation1.setWhatDidntYouLike("slow computers");
//            // attach a course to this evaluation
//            evaluation1.setCourse(courseJava);
//            evaluationRepo.save(evaluation1);
//
//
//            // create an bunch of Attendance
//            Date d = new Date();
//            // create as student
//            Person studentJoe = new Person();
//            studentJoe.setNameFirst("Joe");
//            studentJoe.setNameLast("Dimaggio");
//            studentJoe.setEmail("abc@def.ghi");
//            studentJoe.setContactNum("1723894836");
//            studentJoe.setSelectVal("some value");
//            personRepo.save(studentJoe);
//            for (int i = 0; i < 20; i++) {
//                Attendance att = new Attendance();
//                att.setDate(d);
//                att.setCourse(coursePython);
//                att.setPerson(studentJoe);
//                att.setAstatus("Present");
//                attendanceRepo.save(att);
//            }
//
//
//            // create a new registration timestamp
//            RegistrationTimestamp timestamp = new RegistrationTimestamp();
//            timestamp.setTimestamp(new Date());
//            timestamp.setPerson(studentJoe);
//            timestamp.setCourse(courseJava);
//            registrationTimestampRepo.save(timestamp);
//        }
////        return "dbtest";
//        return "welcome";
//    }




    /************************
     *
     *  Welcome/login pages
     *
     ************************/
    @RequestMapping("/")
    public String welcomePage()
    {
//        if(authorityRepo.count()==0) {
//            Authority adminAuth = new Authority();
//            adminAuth.setRole("ADMIN");
//            authorityRepo.save(adminAuth);
//
//            Authority teacherAuth = new Authority();
//            teacherAuth.setRole("TEACHER");
//            authorityRepo.save(teacherAuth);
//        }
        return "welcome";
    }

    @GetMapping("/welcome")
    public String showHomePage() {
        return "welcome";
    }

    @RequestMapping("/login")
    public String login()
    {
        return "login";
    }


    @RequestMapping("/signup")
    public String addUserInfo(Model model) {
        model.addAttribute("newPerson", new Person());
        model.addAttribute("listRoles", authorityRepo.findByRoleIsOrRoleIs("TEACHER","ADMIN"));
        return "signup";
    }

    @PostMapping("/signup")
    public String addUserInfo(@Valid @ModelAttribute("newPerson") Person person, BindingResult bindingResult,Model model){
        model.addAttribute("listRoles", authorityRepo.findByRoleIsOrRoleIs("TEACHER","ADMIN"));
        if(bindingResult.hasErrors()) {
           return "signup";
       }


       // manually validate for username is null and also make sure it is unique
       if(person.getUsername().isEmpty() || personRepo.countByUsername(person.getUsername()) > 0) {
           model.addAttribute("usernameWasNull",true);
           return "signup";

       }
       // manually check to make sure password was not null
        if(person.getPassword().isEmpty()){
            model.addAttribute("passwordWasNull",true);
            return "signup";

        }

        if(person.getSelectVal().equalsIgnoreCase("TEACHER")  )      {

            userService.saveTeacher(person);
            model.addAttribute("message", "Teacher Account Successfully Created");
        }
        else{

            userService.saveAdmin(person);
            model.addAttribute("message","Admin Account Successfully Created");
        }

        return "redirect:/login;";
    }


    @GetMapping("/evaluation")
    public String eval(Model model)
    {
    	model.addAttribute("evaluation", new Evaluation());
        return "evaluation";
    }
    @PostMapping("/evaluation")
    public String submitEvaluation(@ModelAttribute("evaluation") Evaluation eval)
    {
    	evaluationRepo.save(eval);
    	return "welcome";
    }

//Teacher and admin
    //Modified by Yun on 09/15, show teacher information in coursedetail page
    @RequestMapping("/coursedetail/{courseid}")
    public String courseDetail(@PathVariable ("courseid") long id, Model model)
    {
        model.addAttribute("course", courseRepo.findOne(id));
//        System.out.println("course after coursedetail:"+courseRepo.findOne(id));
        model.addAttribute("teachers", personRepo.findByCoursesIs(courseRepo.findOne(id)));
//        System.out.println("teacher after coursedetail---: "+personRepo.findByCoursesIs(courseRepo.findOne(id)));
        return "coursedetail";
    }



}
