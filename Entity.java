import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Date;
public abstract class Entity{
	public Date lev = new Date();
	public int LEVEL = 0;
	//public long lastTime = 0;

    private final int UP = 2;
	private final int FALLEN = 10;
    private final int DOWN = 3;
    private final int LEFT = 1;
    protected int RIGHT = 0;
	
	protected int DEAD = 9;
	
	/*
    protected int RIGHT_UP = 8;
    protected int LEFT_UP = 9;
    protected int DOWN_LEFT = 11;	
	*/
	
	protected int Attack_right = 5;
	protected int Attack_up = 7;
	protected int Attack_left = 6;
	protected int Attack_down = 4;
	
	
    protected int currentAnimation;

    protected Animation ani;
    protected Sprite sprite;
    protected Vector2f pos;
    protected int size;

    protected boolean up;
    protected boolean down;
    protected boolean right;
    protected boolean left;
 //   protected boolean attack_right;
	//protected boolean attack_left;
	//protected boolean attack_up;
	//protected boolean attack_down;
	protected boolean attack;
	protected boolean fallen;
	protected boolean dead;
	
	public boolean xCol = false;
	public boolean yCol = false;
	/*
    protected int attackSpeed;
    protected int attackDuration;
*/
    protected float dx;
    protected float dy;

    protected float maxSpeed = 4f;
    protected float acc = 3f;
    protected float deacc = 3f;
	protected int pHealth = 40, eHealth = 40;
	protected double pDmg = 1.5, eDmg = 1;
	
	
	public int TotalHealthPlayer;
	public int TotalHealthEnemy;

    protected AABB hitBounds;
    protected AABB bounds;
	
	protected TileCollision tc;

    public Entity(Sprite sprite, Vector2f orgin, int size, float maxSpeed, int TotalHealth, int DMG){
        this.sprite = sprite;
        pos = orgin;
        this.size = size;
		
		this.maxSpeed = maxSpeed;
		
        bounds = new AABB(orgin, size, size);

		
        hitBounds = new AABB(orgin, size, size);
		hitBounds.setXOffset(size / 2);
        ani = new Animation();
        setAnimation(RIGHT, sprite.getSpriteArray(RIGHT), 10);
		
		tc = new TileCollision(this);
    }

	

    public void setSprite(Sprite sprite)
    {
        this.sprite = sprite;
    }
	
	public void setFallen(boolean b){fallen = b;}
	public void setDead(boolean b){dead = b;}
    public void setSize(int i ){size = i;}
    public void setMaxSpeed(float f){maxSpeed = f;}
    public void setAcc(float f ){acc = f;}
    public void setDeacc(float f ){deacc = f;}

	public float getDeacc(){return deacc;}
	public float getMaxSpeed(){return maxSpeed;}

	public float getDx(){return dx;}
	public float getDy(){return dy;}

    public AABB getBounds(){return bounds;}

    public int getSize(){
        return size;
    }

    public Animation getAnimation(){return ani;}

    public void setAnimation(int i, BufferedImage[] frames, int delay){
        currentAnimation = i;
        ani.setFrames(frames);
        ani.setDelay(delay);
    }

    public void animate(){
        if(up && !attack){
            if(currentAnimation != UP || ani.getDelay() == -1){
                setAnimation(UP, sprite.getSpriteArray(UP), 5);
            }
        }else if(down && !attack){
            if(currentAnimation != DOWN || ani.getDelay() == -1){
                setAnimation(DOWN, sprite.getSpriteArray(DOWN), 5);
            }
        }
        else if(right && !attack){
            if(currentAnimation != RIGHT || ani.getDelay() == -1){
                setAnimation(RIGHT, sprite.getSpriteArray(RIGHT), 5);
            }
        }
        else if(left && !attack){
            if(currentAnimation != LEFT || ani.getDelay() == -1){
                setAnimation(LEFT, sprite.getSpriteArray(LEFT), 5);
            }
        }else if(fallen){
			if(currentAnimation != FALLEN || ani.getDelay() == -1){
				setAnimation(FALLEN, sprite.getSpriteArray(FALLEN), 15);		
			}
		}else if(dead){
			if(currentAnimation != DEAD || ani.getDelay() == -1){
				setAnimation(DEAD, sprite.getSpriteArray(DEAD), 15);		
			}
		}
		else if(right && attack){
          //  System.out.println("right attack");
			if(currentAnimation != Attack_right || ani.getDelay() == -1){
				setAnimation(Attack_right, sprite.getSpriteArray(Attack_right), 15);
			}
		}else if(left && attack){
		//	System.out.println("left attack");
			if(currentAnimation != Attack_left || ani.getDelay() == -1){
				setAnimation(Attack_left, sprite.getSpriteArray(Attack_left), 15);
			}
		}else if(down && attack){
		//	System.out.println("Down attack");
			if(currentAnimation != Attack_down || ani.getDelay() == -1){
				setAnimation(Attack_down, sprite.getSpriteArray(Attack_down), 15);
			}
		}else if(attack && up){///////////////////////////////////////////////////////////
		//	System.out.println("up attack");
			if(currentAnimation != Attack_up || ani.getDelay() == -1){
				setAnimation(Attack_up, sprite.getSpriteArray(Attack_up), 15);
			}
		}else if(attack && !up && !down && !left && !right){
			if(currentAnimation == RIGHT){
				setAnimation(Attack_right, sprite.getSpriteArray(Attack_right), 15);
				currentAnimation = RIGHT;
			}if(currentAnimation == LEFT){
				setAnimation(Attack_left, sprite.getSpriteArray(Attack_left), 15);
				currentAnimation = LEFT;
			}if(currentAnimation == UP){
				setAnimation(Attack_up, sprite.getSpriteArray(Attack_up), 15);
				currentAnimation = UP;
			}if(currentAnimation == DOWN){
				setAnimation(Attack_down, sprite.getSpriteArray(Attack_down), 15);
				currentAnimation = DOWN;
			}
		}
        else{
            setAnimation(currentAnimation, sprite.getSpriteArray(currentAnimation), -1);
        }
    }

    private void setHitBoxDirection(){
        if(up){
            hitBounds.setYOffset(-size / 2 );
            hitBounds.setXOffset(0);
        }
        else if(down){
            hitBounds.setYOffset(size / 2 );
            hitBounds.setXOffset(0);
        }
        else if(left){
            hitBounds.setXOffset(-size / 2);
            hitBounds.setYOffset(0);	
        }
        else if(right){
            hitBounds.setXOffset(size / 2);
            hitBounds.setYOffset(0);
        }
    }

    public void update(){

		
        animate();
        setHitBoxDirection();
        ani.update();
    }

    public abstract void render (Graphics2D g);
}