//FONT LOADING CLASS
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.lang.reflect.Array;
import java.nio.Buffer;
import java.util.ArrayList;

public class Font {

    private BufferedImage FONTSHEET = null;
    private BufferedImage[][] spriteArray;
    private final int TILE_SIZE = 10000;
    public int w;
    public int h;
    private int wLetter;
    private int hLetter;

    public Font(String file)
    {
        w = TILE_SIZE;
        h = TILE_SIZE;

        //System.out.println("Loading: " + file + "...");
        FONTSHEET = loadFont(file);

        loadSpriteArray();
    }
    public Font(String file, int w, int h)
    {
        this.w = w;
        this.h = h;

       // System.out.println("Loading: " + file + "...");
        //System.out.println("WAT: " + w + " " + h);
		FONTSHEET = loadFont(file);
		//System.out.println("Height: " + FONTSHEET.getHeight() / h);
        wLetter = FONTSHEET.getWidth() / w;
        hLetter = FONTSHEET.getHeight() / h;
        loadSpriteArray();
    }

    public void setSize(int width, int height)
    {
        setWidth(width);
        setHeight(height);
    }

    public void setWidth(int i)
    {
        w = i;
        wLetter = FONTSHEET.getWidth() / w;
    }
    public void setHeight(int i)
    {
        h = i;
        hLetter = FONTSHEET.getHeight() / h;
    }
    public int getWidth(){return w;}
    public int getHeight(){return h;}

    private BufferedImage loadFont(String file)
    {
        BufferedImage sprite = null;
        try{
            sprite = ImageIO.read(getClass().getClassLoader().getResourceAsStream(file));
        }
        catch (Exception e)
        {
            System.out.println("Error, couldn't load the file: " + file);
        }
        return sprite;
    }
    public void loadSpriteArray()
    {
        spriteArray = new BufferedImage[wLetter][hLetter];

        for (int x = 0; x < wLetter; x++)
        {
			//System.out.println("X is " + x);
            for (int y = 0; y < hLetter; y++)
            {
                spriteArray[x][y] = getLetter(x, y);
				//System.out.println("WWOOOWW" + x*w + " " + y*h);
            }
        }
    }
    public BufferedImage getFontSheet(){return FONTSHEET;}

    public BufferedImage getLetter(int x, int y)
    {
        return FONTSHEET.getSubimage(x*w,y*h, w, h);
		//System.out.println("WWOOOWW");
    }
	
    public BufferedImage getFont(char letter)
    {
        int value = letter - 25;
        //System.out.println(value);
        int x = value % wLetter;
        int y = value / wLetter;

        //return FONTSHEET.getSubimage(x, y, w , h);
        //System.out.println("x: " + x + " " + "y: " + y);
        return getLetter(x,y);
    }
}

