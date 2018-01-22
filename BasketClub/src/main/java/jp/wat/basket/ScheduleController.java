package jp.wat.basket;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import jp.wat.basket.dao.ScheduleDao;
import jp.wat.basket.dao.ScheduleDaoImpl;
import jp.wat.basket.dao.UserDao;
import jp.wat.basket.entity.UserMaster;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class ScheduleController {
	   
	@Autowired
	ScheduleService scheduleService;
     
//    @PostConstruct
//    public void init() {
//    	scheduleDao = new ScheduleDaoImpl();
//    }

	@RequestMapping("/schedule")
	public ModelAndView index(ModelAndView mav){
		mav.setViewName("shedule");
		
		Integer nendo = Integer.valueOf(2017);
		
		List<ScheduleData> scheduleList = scheduleService.getScheduleData(nendo);

		mav.addObject("scheduleList", scheduleList);
		mav.addObject("msg", "引数が渡っていることを確認");
		return mav;
		
	}    
	
	// ******************************************************************************************
	// 下記を参考に作成するのが良さそう。
	// Serviceクラスと、Repositoryクラスを作成
	// http://inaz2.hatenablog.com/entry/2017/04/05/200206
	// ******************************************************************************************
	
	
	// ******************************************************************************************
	// TODO 　spring boot では　xml　は不要
	// ******************************************************************************************
//	private static ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
//	@SuppressWarnings("unchecked")
//	private static ScheduleDao<ScheduleData> scheduleDao = (ScheduleDao<ScheduleData>)context.getBean("scheduleDao");
    
//	@RequestMapping(value="/schedule", method=RequestMethod.GET)
//	public String schedule (Model model){
//		
//		int nendo = 2017;
//		List<ScheduleData> scheduleList = scheduleDao.getMonthlyList(nendo);
//		ScheduleData scheduleData = scheduleList.get(0);
//		model.addAttribute("scheduleDto", scheduleData);	
//		model.addAttribute("scheduleList", scheduleList);
//		model.addAttribute("msg", "引数が渡っていることを確認");
//		return "schedule";
//		
//		//System.out.println("");
//	}
//	
	
	@RequestMapping("/schedule2")
	public ModelAndView attend(ModelAndView mav){
		mav.setViewName("attendList");
				
		@SuppressWarnings("unCheck")
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring.xml");
		UserDao userDao = (UserDao) context.getBean("userDao");
		
		List<UserMaster> userList = null;
		try{
			
			// DBからユーザー一覧を全件取得する
			userList = userDao.getUserList();
			
			// 対象データがない場合はエラーメッセージを設定する
			if(userList.size() == 0){
				mav.addObject("error", "データがありませんでした。");
			}
			
		}catch (SQLException e){
			e.printStackTrace();
		}
		
		mav.addObject("userList", userList);
		return mav;
	}
}
