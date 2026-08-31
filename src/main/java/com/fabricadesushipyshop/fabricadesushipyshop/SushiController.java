package com.fabricadesushipyshop.fabricadesushipyshop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class SushiController {

    @Autowired
    private SushiRepository sushiRepository;

    @GetMapping("/menu")
    public String verMenu(Model model) {
        if (sushiRepository.count() == 0) {
            sushiRepository.save(new Sushi("Acevichado Roll", "Relleno de langostino empanizado y palta, cubierto de atún", 12.50));
            sushiRepository.save(new Sushi("California Roll", "Cangrejo, aguacate y pepino cubierto de ajonjolí", 9.90));
        }
        
        model.addAttribute("platillos", sushiRepository.findAll());
        model.addAttribute("nuevoSushi", new Sushi());
        return "menu";
    }

    @PostMapping("/menu/guardar")
    public String guardarSushi(@ModelAttribute("nuevoSushi") Sushi sushi) {
        sushiRepository.save(sushi);
        return "redirect:/menu";
    }
    @GetMapping("/menu/eliminar/{id}")
    public String eliminarSushi(@PathVariable("id") Long id) {
    sushiRepository.deleteById(id);
    return "redirect:/menu";
}
    @GetMapping("/menu/editar/{id}")
public String editarSushi(@PathVariable("id") Long id, Model model) {
    model.addAttribute("platillos", sushiRepository.findAll());
    model.addAttribute("nuevoSushi", sushiRepository.findById(id).orElse(new Sushi()));
    return "menu";
}
}