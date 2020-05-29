package jinoru.spring.mvc.controller;

import jinoru.spring.mvc.service.FileUpDownUtil;
import jinoru.spring.mvc.service.PdsService;
import jinoru.spring.mvc.vo.PdsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Map;

/**
 * Created by jinoru on 2020-05-28.
 */
@Controller
public class PdsController {

    private PdsService psrv;

    @Autowired
    public PdsController(PdsService psrv) {
        this.psrv = psrv;
    }

    @RequestMapping(value = "/pds/list", method = RequestMethod.GET)
    public ModelAndView list() {

        ModelAndView mv = new ModelAndView();

        mv.setViewName("layout/layout"); // 뷰이름 지정
        mv.addObject("action", "../pds/list.jsp");

        // 목록 불러오기
        ArrayList<PdsVO> plist = psrv.showPds();
        mv.addObject("plist", plist);

        return mv;
    }

    @RequestMapping(value = "/pds/modify", method = RequestMethod.GET)
    public ModelAndView modify() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("layout/layout");
        mv.addObject("action", "../pds/modify.jsp");

        return mv;
    }

    @RequestMapping(value = "/pds/view", method = RequestMethod.GET)
    public ModelAndView view(String pno) {

        ModelAndView mv = new ModelAndView();
        mv.setViewName("layout/layout");
        mv.addObject("action", "../pds/view.jsp");

        PdsVO p = psrv.showOnePds(pno);
        mv.addObject("p", p);


        return mv;
    }

    @RequestMapping(value = "/pds/write", method = RequestMethod.GET)
    public ModelAndView write() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("layout/layout");
        mv.addObject("action", "../pds/write.jsp");

        return mv;
    }

    // 새글쓰기
    @RequestMapping(value = "/pds/write", method = RequestMethod.POST)
    public String writeok(PdsVO pv, HttpServletRequest req) {


        // 업로드 처리
        FileUpDownUtil util = new FileUpDownUtil();
        Map<String, String > frmdata = util.procUpload(req);

        // multipart 폼 데이터 처리
        for(String key:frmdata.keySet()) {
            String val = frmdata.get(key);
            switch (key) {
                case "title":pv.setTitle(val); break;
                case "userid":pv.setUserid(val); break;
                case "contents":pv.setContents(val); break;

                case "file1":pv.setFname(val); break;
            }
        }

        // 서비스 객체로 넘김
        psrv.newPds(pv);

        return "redirect:/pds/list";
    }

    // 수정하기
    @RequestMapping(value = "/pds/update")
    public ModelAndView update() {

        ModelAndView mv = new ModelAndView();
        mv.setViewName("layout/layout"); // 뷰이름 지정

        mv.addObject("action", "../pds/modify.jsp");

        return mv;
    }

    // 삭제하기
    @RequestMapping(value = "/pds/delete")
    public String delete() {

        return "redirect:/pds/list";
    }
}
