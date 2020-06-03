package jinoru.spring.mvc.service;

import jinoru.spring.mvc.dao.GalleryDAO;
import jinoru.spring.mvc.vo.GalleryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by jinoru on 2020-06-03.
 */

@Service("gsrv")
public class GalleryService {

    private GalleryDAO gdao;

    @Autowired
    public GalleryService(GalleryDAO gdao) {
        this.gdao = gdao;
    }

    // 새 갤러리 쓰기
    public void newGallery(GalleryVO gvo) {
        gdao.insertGallery(gvo);
    }
}
