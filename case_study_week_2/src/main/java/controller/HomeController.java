package controller;

import java.sql.SQLException;
import java.util.List;

import dao.media.BookDAO;
import dao.media.MediaDAO;
import entity.media.Media;

/**
 * This class controls the flow of events in homescreen
 * @author nguyenlm
 */
public class HomeController extends BaseController {


    /**
     * this method gets all Media in DB and return back to home to display
     * @return List[Media]
     * @throws SQLException
     */
    public List getAllMedia() throws SQLException{
        return new MediaDAO().getAllMedia();
    }
    
    /**
     * this method return concrete Media entity (Book, CD, DVD) by id
     * @return Media
     * @throws SQLException
     */
    public Media getMediaDetail(int id) {
    	// TODO: get media detail by id
		return null;
	}
}
