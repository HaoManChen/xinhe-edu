package cn.xinhe.web.app.support;


import cn.xinhe.domain.Student;
import cn.xinhe.dto.MemberDTO;
import cn.xinhe.exception.AppException;
import cn.xinhe.mapper.StudentMapper;
import cn.xinhe.mapper.WxAppSessionMapper;
import cn.xinhe.web.support.code.AppCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@Slf4j
@RestControllerAdvice("cn.xinhe.web.app")
public class SessionControllerAdvice {
	@Autowired
	WxAppSessionMapper wxAppSessionMapper;
	@Autowired
	StudentMapper studentMapper;
	@ModelAttribute
	public void addReqBodyModelAttr(@RequestHeader(value = "X-SESSION",required = false) String sessionId,
			Model model) {

		log.info("传入的sessionId数据:{}",sessionId);
		if (!StringUtils.isEmpty(sessionId)){
			MemberDTO memberDTO = wxAppSessionMapper.getMemberIdAndOpenidAndAppidBySessionId(sessionId);
			if (memberDTO==null){
				throw new AppException(AppCode.LOGIN_OUT_OF_TIME,"织面根据请求头中的sessionId={},未获取到对应的wxAppSessionDTO",sessionId);
			}
			model.addAttribute("memberId",memberDTO.getMemberId());
			model.addAttribute("openid",memberDTO.getOpenid());
			model.addAttribute("appid",memberDTO.getAppid());
			model.addAttribute("wxUserId",memberDTO.getWxUserId());
			log.info("会员{}的openid为{}",memberDTO.getMemberId(),memberDTO.getOpenid());
			if (!StringUtils.isEmpty(memberDTO.getMemberId())){
				List<Student> studentList = studentMapper.getByMemberId(memberDTO.getMemberId());
				if (studentList!=null&&studentList.size()>=1){
					//暂时只支持一个家长对一个学生，所以做筛选出第一个
					Student student = studentList.get(0);
					model.addAttribute("autoStudentId",student.getId());
				}
			}else {
				model.addAttribute("memberId","8ec9fb312bb54c90b51feea2327cd41b");
			}
		}else {
			model.addAttribute("memberId","8ec9fb312bb54c90b51feea2327cd41b");
		}
	}

}
