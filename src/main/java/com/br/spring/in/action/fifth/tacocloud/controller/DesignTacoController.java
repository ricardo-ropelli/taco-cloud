package com.br.spring.in.action.fifth.tacocloud.controller;

import com.br.spring.in.action.fifth.tacocloud.entity.Design;
import com.br.spring.in.action.fifth.tacocloud.entity.Ingredient;
import com.br.spring.in.action.fifth.tacocloud.entity.Ingredient.Type;
import com.br.spring.in.action.fifth.tacocloud.entity.Taco;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.stream.Stream;

@Slf4j
@Controller
@RequestMapping("/design")
public class DesignTacoController {

    @GetMapping
    public String showDesignForm(Model model) {
        Stream.of(Type.values())
                .forEach(type -> model.addAttribute(type.toString().toLowerCase(), Ingredient.filterByType(type)));

        model.addAttribute("design", new Taco());
        return "design";
    }

    @PostMapping
    public String processDesign(Design design) {
        log.info("Processing design name {}, with ingredients id(s) '{}'",
                design.getName(),
                String.join(", ", design.getIngredients()));

        return "redirect:/orders/current";
    }
}
