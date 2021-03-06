package jinoru.spring.mvc.controller;

import jinoru.spring.mvc.service.GalleryService;
import jinoru.spring.mvc.vo.GalleryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class GalleryController {

    private GalleryService gsrv;

    @Autowired
    public GalleryController(GalleryService gsrv) {
        this.gsrv = gsrv;
    }

    @RequestMapping(value = "gallery/list", method = RequestMethod.GET)
    public ModelAndView list(String gno) {

        ModelAndView mv = new ModelAndView();
        mv.setViewName("layout/layout"); // 뷰이름 지정

        mv.addObject("action", "../gallery/list.jsp");
        // 뷰로 넘길 데이터를 modelandview 객체에 담음

        mv.addObject("glist", gsrv.showGallery());

        return mv;
    }

    @RequestMapping(value = "gallery/write")
    public ModelAndView write() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("layout/layout"); // 뷰이름 지정

        mv.addObject("action", "../gallery/write.jsp");

        return mv;
    }


    // 새글쓰기
    // 여러 개의 이미지를 업로드 하는 경우
    // 이미지 폼 이름은 모두 동일하게 설정한다.
    // 이미지를 여러개 설정할필요 없이 배열로 설정
    // MultipartFile img1, MultipartFile img2, MultipartFile img3
    // 이렇게 여러개쓰는것보다 배열로 (MultipartFile[] img) 하는것이 좋다.
    @PostMapping(value = "gallery/write")
    public String writeok(GalleryVO gvo, MultipartFile[] img) {
        gsrv.newGallery(gvo ,img);

        return "redirect:/gallery/list";
    }

    @RequestMapping(value = "gallery/view", method = RequestMethod.GET)
    public ModelAndView view(String gno) {

        ModelAndView mv = new ModelAndView();
        mv.setViewName("layout/layout"); // 뷰이름 지정

        mv.addObject("action", "../gallery/view.jsp");
        // 뷰로 넘길 데이터를 modelandview 객체에 담음

        mv.addObject("g", gsrv.showOneGallery(gno));

        return mv;
    }


}
