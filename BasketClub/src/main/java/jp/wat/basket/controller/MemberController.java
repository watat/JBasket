package jp.wat.basket.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jp.wat.basket.ScheduleData;
import jp.wat.basket.ScheduleService;
import jp.wat.basket.entity.Member;
import jp.wat.basket.form.InformationForm;
import jp.wat.basket.form.TopForm;
import jp.wat.basket.service.MemberService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class MemberController {
	
	@Autowired
	MemberService memberService;
	
	@RequestMapping("/member")
	public ModelAndView index(ModelAndView mav){
		
		mav.setViewName("member");
	
		List<Member> memberList = memberService.getInformation();
		
		mav.addObject("memberList", memberList);
		return mav;
		
	} 
		
}
