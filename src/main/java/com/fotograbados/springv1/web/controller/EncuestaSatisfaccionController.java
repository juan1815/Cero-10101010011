package com.fotograbados.springv1.web.controller;

import com.fotograbados.springv1.domain.service.IUsuarioService;
import com.fotograbados.springv1.domain.service.agc.IAnswerService;
import com.fotograbados.springv1.domain.service.agc.IQuestionService;
import com.fotograbados.springv1.persistence.entities.Users;
import com.fotograbados.springv1.persistence.entities.agc.AnswerSatisfactionSur;
import com.fotograbados.springv1.persistence.entities.agc.QuestionSatisfactionSur;
import jakarta.servlet.http.HttpSession;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("/encuestaSatisfaccion")
public class EncuestaSatisfaccionController {
    private final Logger LOGGER = LoggerFactory.getLogger(EncuestaSatisfaccionController.class);
    @Autowired
    private IUsuarioService usuarioService;
    @Autowired
    private IAnswerService answerService;

    @Autowired
    private IQuestionService   questionService;

    @GetMapping("")
    private String showQuestion(Model model){
        model.addAttribute("encuesta", questionService.findAll());
        return "encuesta/showenc";
    }
    @GetMapping("/createQ")
    private String createQuestion(){
        return "encuesta/createenc";
    }

    @PostMapping("/saveQ")
    private String saveProducto(QuestionSatisfactionSur sur, HttpSession session){
        LOGGER.info("Este es el objeto Pregunta {}", sur);
        //Users u = usuarioService.findById(Long.parseLong(session.getAttribute("idUsuario").toString())).get();
        //sur.setUsuario(u);
        questionService.save(sur);
        return "redirect:/encuestaSatisfaccion";
    }
    @GetMapping("/edit/{idPregunta}")
    private String editQuestion(@PathVariable Long idPregunta, Model model){
        QuestionSatisfactionSur questionSatisfactionSur = new QuestionSatisfactionSur();
        Optional<QuestionSatisfactionSur> optionalQuestionSatisfactionSur = questionService.get(idPregunta);
        questionSatisfactionSur = optionalQuestionSatisfactionSur.get();

        LOGGER.info("Question find: {}", questionSatisfactionSur);
        model.addAttribute("encuesta", questionSatisfactionSur);
        return "encuesta/editenc";
    }
    @PostMapping("/updateQuestion")
    private String updateQuestion(QuestionSatisfactionSur questionSatisfactionSur) {
        QuestionSatisfactionSur q = new QuestionSatisfactionSur();
        q = questionService.get(questionSatisfactionSur.getIdPregunta()).get();

        questionSatisfactionSur.setUsuario(q.getUsuario());
        questionService.update(questionSatisfactionSur);
        return "redirect:/encuestaSatisfaccion";
    }

    @GetMapping("/delete/{idPregunta}")
    private String delete(@PathVariable Long idPregunta){
        QuestionSatisfactionSur q = new QuestionSatisfactionSur();
        q = questionService.get(idPregunta).get();

        questionService.delete(idPregunta);
        return "redirect:/encuestaSatisfaccion";
    }

    //users Answer
    @GetMapping("/showAnswer")
    private String showAnswer(Model model){
        List<QuestionSatisfactionSur> questions = questionService.findAll();
        model.addAttribute("questions", questions);
        return "encuesta/answercli";
    }
    @PostMapping("/answer")
    private String answerEncuesta(AnswerSatisfactionSur satisfactionSur, HttpSession session){
        LOGGER.info("Este es el ojeto producto {}", satisfactionSur);

        Users u = usuarioService.findById(Long .parseLong(session.getAttribute("idUsuario").toString())).get();
        satisfactionSur.setUsuario(u);

        answerService.save(satisfactionSur);
        return "";
    }

}
