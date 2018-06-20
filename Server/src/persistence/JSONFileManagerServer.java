package persistence;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import constants.ConstantsUI;
import models.entities.Game;

public class JSONFileManagerServer{


	public JSONFileManagerServer() {

	}

	public Game readGame() {
		JSONParser parser = new JSONParser();  
		Object obj = null;
		try {
			obj = parser.parse(new FileReader(ConstantsUI.PATH_SEND));
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}
		JSONObject o = (JSONObject) obj;

		return new Game(100, 500,
				500, o.get("nombre").toString());

	}

	@SuppressWarnings("unchecked")
	public void writeGameList(ArrayList<Game> gameList) throws IOException {

		JSONArray array = new JSONArray();

		for (Game game : gameList) {
			JSONObject object = new JSONObject();
			JSONObject o  = new JSONObject();
			object.put("nombre", game.getName());
			object.put("x", new Integer(game.getX()));
			object.put("y", new Integer(game.getY()));
			object.put("posx", new Integer((int) game.getPlayer().getX()));
			object.put("posy", new Integer((int) game.getPlayer().getY()));
			o.put("player", object);
			array.add(o);
			FileWriter writer = new FileWriter(ConstantsUI.PATH_FILE, false);
			writer.write(array.toJSONString());
			writer.flush();
			writer.close();
		}
	}

}