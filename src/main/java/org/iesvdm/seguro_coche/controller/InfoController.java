package org.iesvdm.seguro_coche.controller;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.iesvdm.seguro_coche.dto.CotizacionDTO;
import org.iesvdm.seguro_coche.dto.CotizacionDTOImpl;
import org.iesvdm.seguro_coche.dto.CotizacionRepository;
import org.iesvdm.seguro_coche.service.CalcularPrecioService;
import org.iesvdm.seguro_coche.user.Cobertura;
import org.iesvdm.seguro_coche.user.Coche;
import org.iesvdm.seguro_coche.user.Conductor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping("/inicio")
public class InfoController {

        private final CalcularPrecioService calcularPrecioService;

        private final CotizacionRepository  cotizacionRepository;

    @Autowired
    public InfoController(CalcularPrecioService calcularPrecioService, CotizacionRepository cotizacionRepository) {
        this.calcularPrecioService = calcularPrecioService;

        this.cotizacionRepository = cotizacionRepository;
    }


    //primera ventana
    @GetMapping("")
    public String inicio(Model model, @ModelAttribute Conductor conductor) {
        model.addAttribute("conductor", conductor);
        return "inicio";
    }

    @PostMapping("")
    public String inicioSubmit( @ModelAttribute Conductor conductor, HttpSession session) {
        CotizacionDTO cotizacionDTO = (CotizacionDTO) session.getAttribute("cotizacionDTO");
        if(cotizacionDTO == null) {
            cotizacionDTO = new CotizacionDTOImpl();
        }
       cotizacionDTO.setCondunctor(conductor);
        session.setAttribute("cotizacionDTO", cotizacionDTO);
       return "redirect:/inicio/vehiculo";
    }

    @GetMapping("/vehiculo")
    public String vehiculo(Model model, @ModelAttribute Coche coche) {
        model.addAttribute("coche", coche);
        return "vehiculo";
    }

    @PostMapping("/vehiculo")
    public String vehiculoSubmit( @ModelAttribute Coche coche, HttpSession session) {
        CotizacionDTO cotizacionDTO =  (CotizacionDTO) session.getAttribute("cotizacionDTO");
        if(cotizacionDTO == null) {
            cotizacionDTO = new CotizacionDTOImpl();
        }
        cotizacionDTO.setCoche(coche);
        session.setAttribute("cotizacionDTO", cotizacionDTO);
        return "redirect:/inicio/resumen";
    }

    @GetMapping("/resumen")
    public String resumen(Model model, HttpSession session) {
        CotizacionDTO cotizacionDTO =  (CotizacionDTO) session.getAttribute("cotizacionDTO");
        if(cotizacionDTO == null) {
            cotizacionDTO = new CotizacionDTOImpl();
            session.setAttribute("cotizacionDTO", cotizacionDTO);
        }
        model.addAttribute("cotizacionDTO", cotizacionDTO);
        model.addAttribute("cobertura", cotizacionDTO.getCobertura());
        return "resumen";
    }

    @PostMapping("/resumen")
    public String resumenSubmit(Model model,@ModelAttribute Cobertura cobertura, HttpSession session) {
        CotizacionDTO cotizacionDTO = (CotizacionDTO) session.getAttribute("cotizacionDTO");
        if(cotizacionDTO == null) {
            return "redirect:/inicio";
        }
        cotizacionDTO.setCobertura(cobertura);

        double precio = calcularPrecioService.calcularPrecioTotal(cotizacionDTO);
        cotizacionDTO.setPrecioTotal(precio);

        Long idCotizacion = cotizacionRepository.guardarCotizacion(cotizacionDTO);

        session.setAttribute("cotizacionDTO", cotizacionDTO);

        model.addAttribute("idCotizacion", idCotizacion);
        model.addAttribute("cotizacionDTO", cotizacionDTO);
        model.addAttribute("cobertura", cotizacionDTO.getCobertura());

        return "resumen";

    }


}
