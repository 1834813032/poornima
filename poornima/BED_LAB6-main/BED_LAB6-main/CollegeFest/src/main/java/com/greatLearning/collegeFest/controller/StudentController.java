package com.greatLearning.collegeFest.controller;
import java.util.List;
import com.greatLearning.collegeFest.entity.Student;
import com.greatLearning.collegeFest.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/students")
public class StudentController {

	/*private final StudentService studentService;*/

	@Autowired
	/*public StudentController(StudentService studentService) {
		this.studentService = studentService;
	}*/
	StudentService service;

	@GetMapping("/list")
	public String listStudents(Model model) {
		List<Student> students = service.findAllStudents();
		model.addAttribute("students", students);
		return "student-list";
	}


	@GetMapping("/add")
	public String showStudentForm(Model model) {
		Student student = new Student();
		model.addAttribute("student", student);
		return "student-form";
	}

	@PostMapping("/save")
	public String saveStudent(@ModelAttribute("student") Student student) {
		if(student.getId() !=0)
		{
			service.updateStudent(student);
		}
		else {
			service.saveStudent(student);
		}
		
		return "redirect:/students/list";
	}

	/*@GetMapping("/edit/{id}")
	public String showEditForm(@PathVariable int id, Model model) {
		Student student = studentService.findStudentById(id);
		model.addAttribute("student", student);
		return "student/student-edit-form";
	}*/

	@PostMapping("/edit")
	public String updateStudent(Model model,@RequestParam ("studentId") int id) {
		Student student =  service.findStudentById(id);	
		model.addAttribute("student", student);
		return "student-form";
	}

	@PostMapping("/delete")
	public String deleteStudent(@RequestParam ("studentId") int id) {
		service.deleteStudent(id);
		return "redirect:/students/list";
	}
	

	@PostMapping("/403")
	public String access (Model model) {
		return "403";
	}
}
