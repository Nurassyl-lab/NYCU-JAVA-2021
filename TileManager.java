import java.util.ArrayList;
import java.awt.Graphics2D;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import java.io.File;


public class TileManager{
	public static ArrayList<TileMap> tm;
	
		
	public TileManager(){
		tm = new ArrayList<TileMap>();
	}
	
	public TileManager(String path, Camera cam){
		tm	= new ArrayList<TileMap>();
		addTileMap(path, 64, 64, cam);
		//System.out.println("---------------------------------is " + path);
	}
	
	public TileManager(String path, int blockWidth, int blockHeight, Camera cam){
		tm	= new ArrayList<TileMap>();
		addTileMap(path, blockWidth, blockHeight, cam);
	}
	
	
	private void addTileMap(String path, int  blockWidth, int blockHeight, Camera cam){
		String imagePath;
		//System.out.println("---------------------------------path is " + path);
		int width = 0;
		int height = 0;
		int tileWidth;
		int tileHeight;
		int tileCount;
		int tileColumns;
		int layers = 0;
		Sprite sprite;
		
		String[] data = new String[10];
		
		try{
			DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = builderFactory.newDocumentBuilder();
			Document doc = builder.parse(new File(getClass().getClassLoader().getResource(path).toURI()));
			//System.out.println("---------------------------------doc is " + doc);
			doc.getDocumentElement().normalize();
			
			NodeList list = doc.getElementsByTagName("tileset");
			Node node = list.item(0);
			Element eElement = (Element) node;
			//System.out.println("---------------------------------node is " + node);
			
			imagePath = eElement.getAttribute("name");
			//System.out.println("---------------------------------imagePath is " + imagePath);
			tileWidth = Integer.parseInt(eElement.getAttribute("tilewidth"));
			tileHeight = Integer.parseInt(eElement.getAttribute("tileheight"));
			tileCount = Integer.parseInt(eElement.getAttribute("tilecount"));
			tileColumns = Integer.parseInt(eElement.getAttribute("columns"));
			//System.out.println("---------------------------------tileHeight is " + tileHeight);
			
			sprite = new Sprite(imagePath + ".png", tileWidth, tileHeight);
			
			list = doc.getElementsByTagName("layer");
			//System.out.println("---------------------------------list is " + list);
			layers = list.getLength();	
			
			for (int i = 0; i < layers; i++){
				node = list.item(i);
				eElement = (Element) node;
				if(i <= 0){
					width = Integer.parseInt(eElement.getAttribute("width"));
					height = Integer.parseInt(eElement.getAttribute("height"));
				}
				data[i] = eElement.getElementsByTagName("data").item(0).getTextContent();
				if(i >= 1){
					tm.add(new TileMapNorm(data[i], sprite, width, height, blockWidth, blockHeight, tileColumns));
				}else{
					tm.add(new TileMapObj(data[i], sprite, width, height, blockWidth, blockHeight, tileColumns));
				}
				cam.setLimit(width * blockWidth, height * blockHeight);
			}
		}catch(Exception e){
			System.out.println("Error: TileManager: can't read tile map ");
		}
	}
	
	public void render(Graphics2D g){
		for (int i = 0; i < tm.size(); i++){
			tm.get(i).render(g);
		}
	}
}