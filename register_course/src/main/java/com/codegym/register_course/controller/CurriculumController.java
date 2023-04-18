package com.codegym.register_course.controller;

import com.codegym.register_course.model.Curriculum;
import com.codegym.register_course.service.ICurriculumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.stream.IntStream;

@Controller
@RequestMapping("/admin/curriculum")
public class CurriculumController {
    @Autowired
    private ICurriculumService curriculumService;

    @GetMapping("/delete")
    public String deleteStudent(@RequestParam Integer curriculumID) {
        curriculumService.delete(curriculumID, curriculumService.getByID(curriculumID));
        return "redirect:/admin/curriculum";
    }

    @GetMapping("")
    public String findAll(Model model, @RequestParam(defaultValue = "", required = false) String searchName,
                          @PageableDefault(size = 5) Pageable pageable) {
        Page<Curriculum> curriculumPage = null;
        model.addAttribute("searchName", searchName);
        model.addAttribute("total",curriculumService.findAll());
        if (searchName != null) {
            curriculumPage = curriculumService.findAllByName(searchName, pageable);
        } else {
            curriculumPage = curriculumService.findAllCurriculum(pageable);
        }
        model.addAttribute("curriculum", curriculumPage);
        model.addAttribute("pageNumberList", IntStream.rangeClosed(1, curriculumPage.getTotalPages()).toArray());
        model.addAttribute("pageNumber", pageable.getPageNumber());
        model.addAttribute("nameSearch", searchName);
        model.addAttribute("pageSize", pageable.getPageSize());
        return "admin/curriculum/curriculum";
    };
    @GetMapping("/create")
    public String showCreate(
            Model model
    ){
        model.addAttribute("curriculumCreate", new Curriculum());
        return "/admin/curriculum/create-curriculum";
    }

    @PostMapping("/create")
    public String createCurriculum(Curriculum curriculum){
        curriculumService.save(curriculum);
        return "redirect:/admin/curriculum";
    }

    @GetMapping("/edit/{id}")
    public String showEdit(
            Model model,
            @PathVariable("id") Integer id
    ){
        model.addAttribute("curriculumEdit", curriculumService.findById(id));
        return "/admin/curriculum/edit-curriculum";
    }

    @PostMapping("/edit")
    public String edit(Curriculum curriculum){
        curriculumService.save(curriculum);
        return "redirect:/admin/curriculum";
    }

}
