package jinoru.spring.mvc.dao;

import jinoru.spring.mvc.vo.GalleryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
/**
 * Created by jinoru on 2020-06-03.
 */

@Repository("gdao")
public class GalleryDAO {

    private JdbcTemplate jdbcTemplate = null;

    @Value("#{jdbc['insertGallerySQL']}") private String insertGallerySQL;
    @Value("#{jdbc['selectGallerySQL']}") private String selectGallerySQL;
    @Value("#{jdbc['selectOneGallerySQL']}") private String selectOneGallerySQL;
    @Value("#{jdbc['lastGalleryIdSQL']}") private String lastGalleryIdSQL;


    @Autowired
    public GalleryDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public String insertGallery(GalleryVO gvo) {

        Object[] params = new Object[] {
                gvo.getTitle(), gvo.getUserid(), gvo.getContents(), gvo.getFname1(),
                gvo.getFname2(), gvo.getFname3()
        };

        // 갤러리 데이터를 gallery 테이블이 저장
        jdbcTemplate.update(insertGallerySQL, params);

        // 방금 입력한 갤러리 데이터의 gno값을 조사해서 반환
        return jdbcTemplate.queryForObject(lastGalleryIdSQL, String.class);
    }
}
