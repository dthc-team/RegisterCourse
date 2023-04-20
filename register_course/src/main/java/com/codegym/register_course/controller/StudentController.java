package com.codegym.register_course.controller;

import com.codegym.register_course.model.Student;
import com.codegym.register_course.service.IStudentService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin/student")
public class StudentController {
    private final IStudentService iStudentService;

    public StudentController(IStudentService iStudentService) {
        this.iStudentService = iStudentService;
    }

    @GetMapping("")
    public String listStudent(Model model, @PageableDefault(size = 5) Pageable pageable, @RequestParam(defaultValue = "") String name) {
        Pageable sortedPage = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize());
        Page<Student> studentPage = iStudentService.findAll(name, (PageRequest) sortedPage);
        model.addAttribute("total", iStudentService.findAllStudent());
        model.addAttribute("student", studentPage);
        List<Integer> pageNumberList = new ArrayList<>();
        for (int i = 1; i <= studentPage.getTotalPages(); i++) {
            pageNumberList.add(i);
        }
        model.addAttribute("pageNumberList", pageNumberList);
        return "/admin/student/student";
    }

    @GetMapping("/create")
    public String showPageCreate(Model model) {
        model.addAttribute("student", new Student());
        return "/admin/student/create-student";
    }

    @PostMapping("/create")
    public String createStudent(@Valid @ModelAttribute("student") Student student,
                                BindingResult bindingResult,
                                RedirectAttributes redirectAttributes,
                                Model model) {
        if (bindingResult.hasErrors()) {
            return "/admin/student/create-student";
        }else {
            model.addAttribute("student", iStudentService.save(student));
            redirectAttributes.addFlashAttribute("message", "Thêm mới thành công");
            return "redirect:/admin/student";
        }
    }

    @GetMapping("update/{studentID}")
    public String showPageUpdate(@PathVariable Integer studentID, Model model) {
        model.addAttribute("student", iStudentService.findById(studentID));
        return "/admin/student/update-student";
    }

    @PostMapping("/update")
    public String updateStudent(@Valid @ModelAttribute("student") Student student, BindingResult bindingResult,
                                Model model,
                                RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "/admin/student/update-student";
        } else {
            model.addAttribute("student", iStudentService.save(student));
            redirectAttributes.addFlashAttribute("message", "Cập nhật thành công");
            return "redirect:/admin/student";
        }
    }

    @GetMapping("/delete")
    public String deleteStudent(@RequestParam Integer studentID) {
        iStudentService.delete(studentID, iStudentService.getStudentByID(studentID));
        return "redirect:/admin/student";
    }
}
