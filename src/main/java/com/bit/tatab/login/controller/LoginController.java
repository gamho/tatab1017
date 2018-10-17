package com.bit.tatab.login.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.bit.tatab.login.service.LoginService;
import com.bit.tatab.login.vo.LoginVO;
import com.bit.tatab.main.vo.CommentVO;

@Controller
public class LoginController {

   @Inject
   LoginService loginService;

   // google login
/*   
    @RequestMapping(value="/googleLogin.do", method=RequestMethod.GET)
    public ModelAndView gooleLogin() throws Exception {
      
       System.out.println("구글 로그인 GET");
     
        ModelAndView mav = new ModelAndView("userMain");
      
        return mav;
    }
*/
   
   // google login
    @RequestMapping(value="/googleLogin.do", method=RequestMethod.POST)
    public ModelAndView gooleLogin(HttpServletRequest request, HttpServletResponse response) throws Exception {
      
       System.out.println("구글 로그인 POST");
     
        String googleId = request.getParameter("googleId");
        String name = request.getParameter("name");
      
        LoginVO loginVO = new LoginVO(googleId, name);
        
        System.out.println(loginVO.toString());
        
       
        //////
       List<LoginVO> checkLoginVOList = loginService.memberInfoFind(loginVO);

        // 세션객체 얻어오기
        HttpSession session = request.getSession();
        session.setAttribute("login_email", loginVO.getLoginEmail());
        System.out.println("login_email: " + session.getAttribute("login_email"));

        // 코멘트 관련 세션 활용하는 작업 (원석)
        CommentVO commentVO = loginService.mainCommentFind(loginVO);
        
        System.out.println("수정된 commentVO : " + commentVO);
        
       if(checkLoginVOList.size() == 0) {
          System.out.println("로그인vo 객체 db에 없음!");
            // 로그인 정보 db에 추가
            loginService.memberInfoInsert(loginVO);
            // 코멘트 관련 기본 내용 삽입
            CommentVO commentVO3 = new CommentVO(session.getAttribute("login_email").toString(),
            		"TATAB에 오신 것을 환영합니다!", 
            		"회원님의 코멘트는 이 곳에 생성됩니다.", 
            		"코멘트 설정을 위해 야구공 버튼을 눌러주세요 →→→→→→→→→→→→→→→→→→→→→→→→→→→");
           
            // 코멘트 관련 정보 db에 추가
            loginService.mainCommentInsert(commentVO3);
//            if(commentVO.equals(null)) {
//            	System.out.println("코멘트vo 객체 db에 없음!");
//            	System.out.println("db에 코멘트 관련 정보 등록 완료");
//            }
            session.setAttribute("mainTitle", commentVO3.getMain_title());
            session.setAttribute("subTitle", commentVO3.getSub_title());
            session.setAttribute("subComment", commentVO3.getSub_comment());
            System.out.println("db에 로그인 정보 등록 완료");
       } else {
          System.out.println("vo 객체 db에 있음!");

          // 코멘트 관련 세션 활용하는 작업 (원석)
          CommentVO commentVO2 = loginService.mainCommentFind(loginVO);
          session.setAttribute("mainTitle", commentVO2.getMain_title());
          session.setAttribute("subTitle", commentVO2.getSub_title());
          session.setAttribute("subComment", commentVO2.getSub_comment());
          System.out.println("코멘트야 올라갔니? : " + commentVO2.toString());
           
       } 
       
////////분명히 해당 아이디에 딸려 있는 값들을 깡그리 vo객체로 넘겨와서 아래에 찍혀야 하는데... 거 참 이상하네...
       
        ModelAndView mav = new ModelAndView("userMain");
      
        return mav;
    }
    
    
    // google logout
    @RequestMapping("/googleLogout.do")
    public ModelAndView googleLogout(HttpServletRequest request, HttpServletResponse response) throws Exception {
       
       // 세션객체 얻어오기
       HttpSession session = request.getSession();
       session.invalidate();
       
       ModelAndView mav = new ModelAndView("redirect:/");
       System.out.println("viewName 확인: " + mav.getViewName());
       
       System.out.println("구글 로그아웃 성공");
       return mav;
    }
    
    
}