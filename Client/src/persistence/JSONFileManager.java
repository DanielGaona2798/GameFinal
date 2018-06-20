package persistence;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import constants.ConstantsUI;
import models.Game;

public class JSONFileManager{


	public JSONFileManager() {
	}

	public ArrayList<Game> readFile() throws FileNotFoundException, IOException{
		JSONParser parser = new JSONParser();  
		Object obj = null;
		try {
			obj = parser.parse(new FileReader(ConstantsUI.PATH));
		} catch (ParseException e) {
			e.printStackTrace();
		}  
		JSONArray listJSON = (JSONArray) obj;
		ArrayList<Game> list = new ArrayList<>();
		for (Object object : listJSON) {
			JSONObject objCyclist = new JSONObject();

			objCyclist = (JSONObject) object;

			JSONObject o = (JSONObject) objCyclist.get("player");
			Game g = new Game(o.get("nombre").toString());
			g.setLocation(Integer.parseInt(o.get("posx").toString()), Integer.parseInt(o.get("posy").toString()));
			g.setX(Integer.parseInt(o.get("x").toString()));
			g.setY(Integer.parseInt(o.get("y").toString()));
			list.add(g);
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public void writeFile(Game game) throws IOException {
		JSONObject object = new JSONObject();

		object.put("nombre", game.getName());

		FileWriter writer = new FileWriter(ConstantsUI.PATH_SEND);
		writer.write(object.toJSONString());
		writer.flush();
		writer.close();
	}
}