package bg.softuni.yummyshare.web;

import bg.softuni.yummyshare.service.LogService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class StatisticsController {
    private final LogService logService;

    public StatisticsController(LogService logService) {
        this.logService = logService;
    }

    @GetMapping("/statistics")
    public String stats(Model model){
        model.addAttribute("logs", logService.findAll());
        return "statistics";
    }
}
