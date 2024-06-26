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

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;


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
    public String showQuestion(Model model){
        model.addAttribute("encuesta", questionService.findAll());
        return "encuesta/showenc";
    }
    @GetMapping("/createQ")
    public String createQuestion(){
        return "encuesta/createenc";
    }

    @PostMapping("/saveQ")
    public String saveProducto(QuestionSatisfactionSur sur, HttpSession session){
        LOGGER.info("Este es el objeto Pregunta {}", sur);
        //Users u = usuarioService.findById(Long.parseLong(session.getAttribute("idUsuario").toString())).get();
        //sur.setUsuario(u);
        questionService.save(sur);
        return "redirect:/encuestaSatisfaccion";
    }
    @GetMapping("/edit/{idPregunta}")
    public String editQuestion(@PathVariable Long idPregunta, Model model){
        QuestionSatisfactionSur questionSatisfactionSur = new QuestionSatisfactionSur();
        Optional<QuestionSatisfactionSur> optionalQuestionSatisfactionSur = questionService.get(idPregunta);
        questionSatisfactionSur = optionalQuestionSatisfactionSur.get();

        LOGGER.info("Question find: {}", questionSatisfactionSur);
        model.addAttribute("encuesta", questionSatisfactionSur);
        return "encuesta/editenc";
    }
    @PostMapping("/updateQuestion")
    public String updateQuestion(QuestionSatisfactionSur questionSatisfactionSur) {
        QuestionSatisfactionSur q = new QuestionSatisfactionSur();
        q = questionService.get(questionSatisfactionSur.getIdPregunta()).get();

        questionSatisfactionSur.setUsuario(q.getUsuario());
        questionService.update(questionSatisfactionSur);
        return "redirect:/encuestaSatisfaccion";
    }

    @GetMapping("/delete/{idPregunta}")
    public String delete(@PathVariable Long idPregunta){
        QuestionSatisfactionSur q = new QuestionSatisfactionSur();
        q = questionService.get(idPregunta).get();

        questionService.delete(idPregunta);
        return "redirect:/encuestaSatisfaccion";
    }

    //users Answer
    @GetMapping("/showAnswer")
    public String showAnswer(Model model){
        List<QuestionSatisfactionSur> questions = questionService.findAll();
        model.addAttribute("questions", questions);
        return "encuesta/answercli";
    }
    @PostMapping("/answer")
    public String answerEncuesta(AnswerSatisfactionSur satisfactionSur, HttpSession session){
        LOGGER.info("Este es el ojeto producto {}", satisfactionSur);
            satisfactionSur.setFechaEncuesta(new Date());

//        Users u = usuarioService.findById(Long .parseLong(session.getAttribute("idUsuario").toString())).get();
//        satisfactionSur.setUsuario(u);

        answerService.save(satisfactionSur);
        return "redirect:/home";
    }


    //to admin view
    @GetMapping("/opiniones")
    public String mostrarOpiniones(Model model) {
        // Obtener las respuestas de la encuesta
        List<AnswerSatisfactionSur> opinionProducts = answerService.findAll();

        // Calcular porcentaje de rating para cada opción (de 1 a 5)
        int[] ratingsCount = new int[5]; // Contador para cada nivel de rating
        int totalAnswers = opinionProducts.size();

        for (AnswerSatisfactionSur answer : opinionProducts) {
            if (answer.getRespuesta() != null && !answer.getRespuesta().isEmpty()) {
                int rating = Integer.parseInt(answer.getRespuesta());
                if (rating >= 1 && rating <= 5) {
                    ratingsCount[rating - 1]++;
                }
            }
        }

        // Calcular porcentaje de respuestas para cada nivel de rating
        double[] ratingsPercentages = new double[5];
        for (int i = 0; i < 5; i++) {
            ratingsPercentages[i] = (double) ratingsCount[i] / totalAnswers * 100;
        }

        // Poner los datos en el modelo para la vista
        model.addAttribute("opinionProducts", opinionProducts);
        model.addAttribute("ratingsPercentages", ratingsPercentages);

        return "administrador/reseñas"; // Nombre de tu vista Thymeleaf
    }
}
