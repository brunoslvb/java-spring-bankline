package bankline.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import bankline.dtos.DashBoardDto;
import bankline.repository.AccountRepository;
import bankline.repository.LancamentoRepository;
import bankline.repository.PlanAccountRepository;
import bankline.repository.UserRepository;
import bankline.service.DashboardService;

@RestController
@RequestMapping("/dashboard")
public class DadhboardController {

    @Autowired
    private UserRepository uRepository;

    @Autowired
    private AccountRepository aRepository;
    
    @Autowired
    private LancamentoRepository lRepository;

    @Autowired
    private PlanAccountRepository pRepository;
    
    @GetMapping
    private ResponseEntity<DashBoardDto> dashLancamentos(@RequestParam("inicio") @DateTimeFormat(pattern="yyyy-MM-dd")Date inicio, @RequestParam("fim") @DateTimeFormat(pattern="yyyy-MM-dd") Date fim, String login) {


        DashboardService dashboardService = new DashboardService();
        DashBoardDto dashBoardDto = dashboardService.servico(inicio, fim, login, uRepository, aRepository, lRepository, pRepository);

        
        
        return ResponseEntity.ok().body(dashBoardDto);
    }

}
