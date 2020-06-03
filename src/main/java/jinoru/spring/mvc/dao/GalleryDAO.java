package jinoru.spring.mvc.dao;

import jinoru.spring.mvc.vo.GalleryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.xml.transform.Templates;

/**
 * Created by jinoru on 2020-06-03.
 */

@Repository("gdao")
public class GalleryDAO {

    private JdbcTemplate jdbcTemplate = null;

    @Autowired
    public GalleryDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void insertGallery(GalleryVO gvo) {
        Object[] params = new Object[] {
        };
    }
}
