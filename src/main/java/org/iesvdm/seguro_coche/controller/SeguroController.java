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

    private final HttpSession httpSession;

    public SeguroController(HttpSession httpSession) {
        this.httpSession = httpSession;
    }

    @GetMapping("/calculos/cotizacion/paso1")
    public String paso1 (Model model, HttpSession httpSession){

       var cotizacionSeguroHttpSession =  (CotizacionSeguro)httpSession.getAttribute("cotizacionSeguro");

       if(cotizacionSeguroHttpSession!=null){

           model.addAttribute("cotizacionSeguro",cotizacionSeguroHttpSession);

       }else{
           model.addAttribute("cotizacionSeguro",new CotizacionSeguro());

       }

        return "paso1";
    }

    @GetMapping("/calculos/cotizacion/paso2")
        public String anteriorPaso3(Model model, HttpSession httpSession){

            var cotizacionSeguroHttpSession = (CotizacionSeguro)httpSession.getAttribute("cotizacionSeguro");

            model.addAttribute("cotizacionSeguro",cotizacionSeguroHttpSession);

            return "paso2";
        }

    @PostMapping("/calculos/cotizacion/paso2")
    public String paso1Post (Model model, @ModelAttribute CotizacionSeguro cotizacionSeguro, HttpSession httpSession){


        //recuerda que podrias utilizar un mecanismo más abstracto mediante @SessionAttributes
        httpSession.setAttribute("cotizacionSeguro",cotizacionSeguro);
        //entre peticiones consecuticas se man tiene el mapa de httpSession<String,Object>

        model.addAttribute("cotizacionSeguro",cotizacionSeguro);

        return "paso2";
    }

    @PostMapping("/calculos/cotizacion/paso3")
    public String paso2Post (Model model, @ModelAttribute CotizacionSeguro cotizacionSeguro, HttpSession httpSession){

        var cotizacionSeguroHttpSession = (CotizacionSeguro)httpSession.getAttribute("cotizacionSeguro");
        if(cotizacionSeguro.getTipoCobertura() == null) {
            cotizacionSeguroHttpSession.setMarca(cotizacionSeguro.getMarca());
            cotizacionSeguroHttpSession.setModelo(cotizacionSeguro.getModelo());
            cotizacionSeguroHttpSession.setAnioMat(cotizacionSeguro.getAnioMat());
            cotizacionSeguroHttpSession.setUso(cotizacionSeguro.getUso());
        }
        //entre peticiones consecuticas se man tiene el mapa de httpSession<String,Object>

        model.addAttribute("cotizacionSeguro",cotizacionSeguroHttpSession);

        model.addAttribute("datosConductor", cotizacionSeguroHttpSession.getNombre()+" "
        +cotizacionSeguroHttpSession.getEdad()+" "+cotizacionSeguroHttpSession.getAniosCarnet());
        model.addAttribute("datosVehiculo",cotizacionSeguroHttpSession.getMarca()+" "+
                cotizacionSeguroHttpSession.getModelo()+" "+cotizacionSeguroHttpSession.getAnioMat()+" "+cotizacionSeguroHttpSession.getUso());


        if(cotizacionSeguro.getTipoCobertura() !=null){

            cotizacionSeguroHttpSession.setTipoCobertura(cotizacionSeguro.getTipoCobertura());
            cotizacionSeguroHttpSession.setAsistencia(cotizacionSeguro.isAsistencia());
            cotizacionSeguroHttpSession.setVehSustitucion(cotizacionSeguro.isVehSustitucion());

            model.addAttribute("datosCobertura",cotizacionSeguroHttpSession.getTipoCobertura()+" "+cotizacionSeguroHttpSession.isAsistencia()+" "+
                    cotizacionSeguroHttpSession.isVehSustitucion());
        }

        return "paso3";
    }


}
