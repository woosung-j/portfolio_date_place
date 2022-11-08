package com.my.date.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.my.date.domain.Declaration;
import com.my.date.domain.Detail;
import com.my.date.domain.Menu;
import com.my.date.domain.Place;
import com.my.date.domain.User;
import com.my.date.service.DeclarationService;
import com.my.date.service.DetailService;
import com.my.date.service.MenuService;
import com.my.date.service.PlaceService;
import com.my.date.service.UserService;

@RestController
@RequestMapping("admin")
public class AdminController {
    @Autowired private DeclarationService declarationService;
    @Autowired private MenuService menuService;
    @Autowired private PlaceService placeService;
    @Autowired private DetailService detailService;
    @Autowired private UserService userService;

    private boolean isAdmin(HttpServletRequest request) {
        HttpSession sessionCheck = request.getSession(false);
        if(sessionCheck == null || sessionCheck.getAttribute("isAdmin") == null) {
            return false;
        }

        return (boolean) sessionCheck.getAttribute("isAdmin");
    }

    @GetMapping("")
    public ModelAndView main(HttpServletRequest request, ModelAndView mv) {
        if(isAdmin(request) == true) {
            mv.setViewName("admin/main");
        } else {
            mv.setViewName("redirect:/admin/login");
        }

        return mv;
    }

    @GetMapping("login")
    public ModelAndView login(ModelAndView mv) {
        mv.setViewName("admin/user/login");
        return mv;
    }

    @PostMapping("login")
    public ModelAndView login(User loginUser, HttpServletRequest request, ModelAndView mv) {
        User user = userService.adminLoginValidate(loginUser);

        if(user != null) {
            HttpSession session = request.getSession(false);
            if (session != null) {
                session.invalidate();
            }

            session = request.getSession();
            if (user != null) {
                session.setAttribute("userId", user.getUserId());
                session.setAttribute("id", user.getId());
                session.setAttribute("nickname", user.getNickname());
                session.setAttribute("email", user.getEmail());
                session.setAttribute("isAdmin", true);
                session.setMaxInactiveInterval(86400);
            }
            mv.setViewName("redirect:/admin");
        } else {
            mv.addObject("errMsg", "아이디 또는 비밀번호가 틀렸습니다.");
            mv.setViewName("admin/user/login");
        }

        return mv;
    }

    @GetMapping("logout")
    public ModelAndView logout(HttpSession session, ModelAndView mv) {
        session.invalidate();
        mv.setViewName("redirect:/admin/login");
        return mv;
    }

    @GetMapping("place")
    public ModelAndView placeList(ModelAndView mv) {
        mv.setViewName("place/placelist");
        return mv;
    }

    @GetMapping("/placelist")
    public List<Place> getPlaces() {
        return placeService.getPlaces();
    }

    @GetMapping("declare")
    public ModelAndView declare(HttpServletRequest request, ModelAndView mv) {
        if(isAdmin(request) == true) {
            mv.setViewName("admin/declaration/declareList");
        } else {
            mv.setViewName("redirect:/admin/login");
        }
        return mv;
    }

    @GetMapping("declare/list")
    public List<Declaration> declareList() {
        return declarationService.getDeclareList();
    }

    @PatchMapping("declare/toggleconfirm/{declareId}/{confirm}")
    public int toggleConfirm(@PathVariable int declareId, @PathVariable int confirm) {
        return declarationService.fixConfirm(declareId, confirm);
    }

    @GetMapping("menu")
    public ModelAndView menu(ModelAndView mv) {
    	mv.addObject("placeId", 3);
        mv.setViewName("admin/menu/patchmenu");
        return mv;
    }

    @GetMapping("menu/getMenus")
    public List<Menu> getMenus() {
        return menuService.getMenus();
    }
    
    @PostMapping("addMenu")
    public int addMenu(@RequestBody List<Menu> menu) {
    	return menuService.addMenu(menu);
    }

	@GetMapping("detail")
	public ModelAndView detail(ModelAndView mv) {
		mv.setViewName("admin/detail/patchdetail");
		return mv;
	}

	@GetMapping("detail/getDetails")
	public List<Detail> getDetails() {
		return detailService.getDetails();
	}
}