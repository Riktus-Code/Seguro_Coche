package org.iesvdm.seguro_coche.controller;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.iesvdm.seguro_coche.model.CotizacionSeguro;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/seguros")
public class SeguroController {

    @GetMapping("/calculos/cotizacion/paso1")
    public String paso1 (Model model, @ModelAttribute CotizacionSeguro cotizacionSeguro){

        return "paso1";
    }


    @PostMapping("/calculos/cotizacion/paso1")
    public String paso1 (Model model, @ModelAttribute CotizacionSeguro cotizacionSeguro, HttpSession httpSession){


        httpSession.setAttribute("cotizacionSeguro",cotizacionSeguro);

        return "paso1";
    }

}
