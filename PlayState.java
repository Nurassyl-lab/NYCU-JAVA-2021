import java.awt.*;
import java.util.*;
import java.util.Date;
public class PlayState extends GameState{
	private int c = 0;
    private Font font;
	private Player player; 
	private TileManager tm;
	private Enemy enemy0;
	private Enemy enemy1;
	private Enemy enemy2;
	private Enemy enemy3;
	private Enemy enemy4;
	private Enemy enemy5;
	private Enemy enemy6;
	private Enemy enemy7;
	private Enemy enemy8;
	private Enemy enemy9;
	public static Vector2f map;
	private Camera cam;
	private int LEVEL = 0;
	private long lastTime = 0;
	private Entity entity;
	
    public PlayState(GameStateManager gsm)
    {
        super(gsm);
		enemy6  = new Enemy( new Sprite("elfEnemyFat.png"), new Vector2f(0 + (GamePanel.width/2) - 32 + 500, 0 + (GamePanel.width/2) - 32 +  500), 80, 0.5f, 1000, 2);
		map = new Vector2f();
		Vector2f.setWorldVar(map.x, map.y);
		cam = new Camera(new AABB(new Vector2f(2420, 2819), 600, 600));
		tm = new TileManager("cave2.xml", cam);
        font = new Font("Letters3.png", 18, 22);
		
		enemy0  = new Enemy( new Sprite("elfEnemyFat.png"), new Vector2f(1413, 695), 80, 0.5f, 1000, 2);
		enemy1  = new Enemy( new Sprite("elfEnemySmall.png"), new Vector2f(2098, 1007), 40, 2.7f, 1000, 1);
		enemy2  = new Enemy( new Sprite("elfEnemySmall.png"), new Vector2f(1186, 1007), 40, 2.7f, 1000, 1);
		enemy3  = new Enemy( new Sprite("elfEnemyFat.png"), new Vector2f(790, 1343), 80, 1f, 750, 1);
		enemy4  = new Enemy( new Sprite("elfEnemyFat.png"), new Vector2f(1492, 1301), 100, 0.3f, 1000, 3);
		enemy5  = new Enemy( new Sprite("elfEnemySmall.png"), new Vector2f(2356, 1325), 80, 3f, 1000, 2);
		
		enemy6  = new Enemy( new Sprite("elfEnemyFat.png"), new Vector2f(1646, 1595), 80, 0.5f, 1000, 2);
		enemy7  = new Enemy( new Sprite("elfEnemySmall.png"), new Vector2f(2990, 1805), 80, 4f, 1000, 2);
		enemy8  = new Enemy( new Sprite("elfEnemyFat.png"), new Vector2f(593, 2026), 80, 0.5f, 1000, 2);
		enemy9  = new Enemy( new Sprite("elfEnemyFat.png"), new Vector2f(1592, 2912), 80, 0.5f, 1000, 2);
		
		player = new Player(new Sprite("elfMain.png"), new Vector2f(2811,281), 60, 0f, 1000, 20);
		cam.target(player);
    }

    public void update(){
		if(player.PLEV == 5 && c == 0){
			tm = new TileManager("cave2_1.xml", cam);
			c = 1;
		}else if(player.PLEV == 3 && c == 1){
			tm = new TileManager("cave2_2.xml", cam);
			c = 2;
		}else if(player.PLEV == 1 && c == 0){
			c = 3;
			tm = new TileManager("cave3.xml", cam);
		}
		
		
		Vector2f.setWorldVar(map.x, map.y);
		player.update(enemy0);
		player.update(enemy1);
		player.update(enemy2);
		player.update(enemy3);
		player.update(enemy4);
		player.update(enemy5);
		player.update(enemy6);
		player.update(enemy7);
		player.update(enemy8);
		player.update(enemy9);
		
		enemy0.update(player);
		enemy1.update(player);
		enemy2.update(player);
		enemy3.update(player);
		enemy4.update(player);
		enemy5.update(player);
		enemy6.update(player);
		enemy7.update(player);
		enemy8.update(player);
		enemy9.update(player);
		
		cam.update();	
    }

    public void input(MouseHandler mouse, KeyHandler key)
    {
		player.input(mouse, key);
		cam.input(mouse, key);
    }

	private String test = "public static void main(String args[]){}";
	
    public void render(Graphics2D g)
    {
		tm.render(g);
		Sprite.drawArray(g, font, GamePanel.oldFrameCount + " FPS", new Vector2f(480, 18), 18,18);
		Sprite.drawArray(g, font, player.PLEV + " LEVEL", new Vector2f(240, 18), 18,18);
		if(player.PLEV == 1){
			Sprite.drawArray(g, font, "Help blue stranger release", new Vector2f(0, 40), 10,10);
			Sprite.drawArray(g, font, "his friends!", new Vector2f(0, 50), 10,10);
			Sprite.drawArray(g, font, "Survive 15 minutes, kill all enemies", new Vector2f(0, 90), 10,10);

		}
		Sprite.drawArray(g, font, "x: " + player.pos.x, new Vector2f(500, 550), 10,10);
		Sprite.drawArray(g, font, "y: " + player.pos.y, new Vector2f(500, 560), 10,10);
		player.render(g);
		
		enemy1.render(g);
		enemy0.render(g);
		enemy2.render(g);
		enemy3.render(g);
		enemy4.render(g);
		enemy5.render(g);
		enemy6.render(g);
		enemy7.render(g);
		enemy8.render(g);
		enemy9.render(g);
		
		cam.render(g);
    }
}
