import java.awt.*;
import java.util.*;
public class Enemy extends Entity{
	
	private AABB sense;
	private int r;
	public Date d = new Date();
	public long data = 0;
	GameLauncher game = new GameLauncher(0);
	/*private int LastCounter = game.counter;
	private int LastCounter2 = game.counter;*/
	public int TotalHealthEnemy;
	private int sw = 0;
	//public int LEVEL;
	private int LEV;
	
	//public int TotalHealthEnemy = 405;
	
	

	public Enemy(Sprite sprite, Vector2f orgin, int size, float maxSpeed, int TotalHealthEnemy, int eDmg){
		super(sprite, orgin, size, maxSpeed, TotalHealthEnemy, eDmg);
		//System.out.println("ENEMY " + maxSpeed);
		acc = 1f;
		//maxSpeed = 2f;
		r = 300;
		//this.LEVEL = LEVEL;
		this.TotalHealthEnemy = TotalHealthEnemy;
		
		
		bounds.setWidth(30);
		bounds.setHeight(20);
		bounds.setXOffset(5);
		bounds.setYOffset(20);
		sense = new AABB(new Vector2f(orgin.x + size / 2 - r / 2, orgin.y + size / 2 - r / 2), r);
		//sense = new AABB(new Vector2f(500, 500), r);
	}
	/*	public Enemy(int health, double dmg){
		this.Ehealth = health;
		this.Edmg = dmg;
		
	}
	*/
	public void move(Player player){
		//System.out.println("DMG " + eDmg);
		if(sense.colCircleBox(player.getBounds())){
			if(pos.y > player.pos.y + 20){
				dy -= acc;				
				up = true;
				down = false;
				if(pos.x > player.pos.x + 20){
				//	System.out.println("Enemy is going up and left");
					dx -= acc;
					
					left = true;
					right = false;
					 
					if(dx < -maxSpeed){
						dx = -maxSpeed;
					}					
				}else if(pos.x < player.pos.x - 20){
				//	System.out.println("Enemy is up and rigth");
					dx += acc;
					right = true;
					left = false;
					if(dx > maxSpeed){
						dx = maxSpeed;
					}
				}else{
				//	System.out.println("Enemy is going up");
					dx = 0;
					right = false;
					left = false;
				}
				
				if(dy < -maxSpeed){
					dy = -maxSpeed;
				}
			}else if(pos.y < player.pos.y - 20){
				dy += acc;				
				down = true;
				up = false;
				if(pos.x > player.pos.x + 20){
				//	System.out.println("Enemy is going down and left");
					dx -= acc;
					
					left = true;
					right = false;
					 
					if(dx < -maxSpeed){
						dx = -maxSpeed;
					}					
				}else if(pos.x < player.pos.x - 20){
					dx += acc;
					right = true;
					left = false;
					if(dx > maxSpeed){
						dx = maxSpeed;
					}
				}else{
					dx = 0;
					right = false;
					left = false;
				}
				if(dy >  maxSpeed){
					dy = maxSpeed;
				}
			}else{
				dy = 0;
				up = false;
				down = false;
			}
			
			

			if(pos.x > player.pos.x + 20){
				dx -= acc;
				
				left = true;
				right = false;
				if(dx < -maxSpeed){
					dx = -maxSpeed;
				}
			}else if(pos.x < player.pos.x - 20){
				dx += acc;
				right = true;
				left = false;
				if(dx > maxSpeed){
					dx = maxSpeed;
				}
			}else{
				dx = 0;
				right = false;
				left = false;
			}
		}else{
			up = false;
			down = false;
			right = false;
			left = false;
			dx = 0;
			dy = 0; 
		}
    }
	
	public void update(Player player ){
		LEV = player.PLEV;
		Date d = new Date();
		super.update();
		if(d.getTime() - data < 500 && hitBounds.collides(player.getBounds())){
			attack = true;
			if(player.TotalHealthPlayer <= 0){player.dead = true;}
		}else if(d.getTime() - data >= 500 && d.getTime() - data < 1000){
			
			attack = false;
		}else if(d.getTime() - data >= 1000 && d.getTime() - data < 1500){
			attack = true;
			//player.TotalHealthPlayer  -= eDmg;
		}else if(d.getTime() - data >= 1500 && d.getTime() - data < 2000){
			attack = false;
			
		}else if(hitBounds.collides(player.getBounds())){
			data = d.getTime();
			System.out.println("HIT");
			player.TotalHealthPlayer  -= eDmg;
			player.TotalHealthPlayer  -= eDmg;
		}
		if(data != d.getTime()){
		}
		//move(player);
		//System.out.print(dead);
		//System.out.println("dead = " + dead + " Fallen " + fallen + " Helth " + TotalHealthEnemy);
		if(!fallen && !dead && TotalHealthEnemy > 0){
			if(!tc.collisionTile(dx, 0)){
				sense.getPos().x += dx;
				pos.x += dx;
			}
			if(!tc.collisionTile(0, dy)){
				sense.getPos().y += dy;
				pos.y += dy;
			}
		}
		else{
			
			xCol = true;
			yCol = true;
			
			sense.getPos().x += 0;
			pos.x += 0;
			
			sense.getPos().y += 0;
			pos.y += 0;
			fallen = false;
			dead = false;
			if(player.PLEV < 15){
			resetPosition();}
			dx = 0;
			dy = 0;
			
			/*if(ani.hasPlayedOnes()){
				resetPosition();
				dx = 0;
				dy = 0;
				fallen = false;
				dead = false;
			}*/
		}
	}
	
		private void resetPosition(){
			sw = (int) (Math.random()*10) + 1;
			//Enemy enemy = null;
			//Enemy enemy = new Enemy(new Sprite("elfEnemyFat.png"), new Vector2f(500, 500), 80, 0.5f, 1000, 2);
			this.sense = new AABB(new Vector2f(pos.x, pos.y), r);
			
			//sw = 1;
			System.out.println(sw + " is SW");
			switch (sw){
				case 1:
					pos.x = (int) (Math.random()*50) + 1400;
					pos.y = (int) (Math.random()*50) + 650;
					this.sense = new AABB(new Vector2f(pos.x + size / 2 - r / 2, pos.y + size / 2 - r / 2), r);
					break;
				case 2:
					pos.x = (int) (Math.random()*50) + 2050;
					pos.y = (int) (Math.random()*50) + 980;
					this.sense = new AABB(new Vector2f(pos.x + size / 2 - r / 2, pos.y + size / 2 - r / 2), r);
					break;
				case 3:
					pos.x = (int) (Math.random()*50) + 1150;
					pos.y = (int) (Math.random()*50) + 980;
					this.sense = new AABB(new Vector2f(pos.x + size / 2 - r / 2, pos.y + size / 2 - r / 2), r);
					break;
				case 4:
					pos.x = (int) (Math.random()*50) + 750;
					pos.y = (int) (Math.random()*50) + 1320;
					this.sense = new AABB(new Vector2f(pos.x + size / 2 - r / 2, pos.y + size / 2 - r / 2), r);
					break;
				case 5:
					pos.x = (int) (Math.random()*50) + 1470;
					pos.y = (int) (Math.random()*50) + 1280;
					this.sense = new AABB(new Vector2f(pos.x + size / 2 - r / 2, pos.y + size / 2 - r / 2), r);
					break;
				case 6:
					pos.x = (int) (Math.random()*50) + 2340;
					pos.y = (int) (Math.random()*50) + 1300;
					this.sense = new AABB(new Vector2f(pos.x + size / 2 - r / 2, pos.y + size / 2 - r / 2), r);
					break;
				case 7:
					pos.x = (int) (Math.random()*50) + 1620;
					pos.y = (int) (Math.random()*50) + 1580;
					this.sense = new AABB(new Vector2f(pos.x + size / 2 - r / 2, pos.y + size / 2 - r / 2), r);
					break;
				case 8:
					pos.x = (int) (Math.random()*50) + 2950;
					pos.y = (int) (Math.random()*50) + 1790;
					this.sense = new AABB(new Vector2f(pos.x + size / 2 - r / 2, pos.y + size / 2 - r / 2), r);
					break;
				case 9:
					pos.x = (int) (Math.random()*50) + 580;
					pos.y = (int) (Math.random()*50) + 2000;
					this.sense = new AABB(new Vector2f(pos.x + size / 2 - r / 2, pos.y + size / 2 - r / 2), r);
					break;
				case 10:
					pos.x = (int) (Math.random()*50) + 1580;
					pos.y = (int) (Math.random()*50) + 2950;
					this.sense = new AABB(new Vector2f(pos.x + size / 2 - r / 2, pos.y + size / 2 - r / 2), r);
					break;
			}
			System.out.println("Reset bot...");
			System.out.println(pos.x + "  " + pos.y);
			TotalHealthEnemy = 500 * LEV;
			System.out.print("LEVEL is "  + LEV);
			eDmg = 1 * LEV;
			
			setAnimation(RIGHT, sprite.getSpriteArray(RIGHT), 10);
		}

	
	@Override
	public void render(Graphics2D g){
		if(attack){
			//System.out.println(TotalHealthPlayer);
			//TotalHealthPlayer = TotalHealthPlayer -  (int)eDmg;
		//	g.setColor(Color.blue);
		//	g.drawRect((int) (hitBounds.getPos().getWorldVar().x + hitBounds.getXOffset()), (int) (hitBounds.getPos().getWorldVar().y + hitBounds.getYOffset()), (int)hitBounds.getWidth(), (int)hitBounds.getHeight());
		}
		//g.setColor(Color.green);
		//g .drawRect((int) (pos.getWorldVar().x + bounds.getXOffset()), (int) (pos.getWorldVar().y + bounds.getYOffset()), (int) bounds.getWidth(), (int) bounds.getHeight());
		 
		 if(!dead){
			//g.setColor(Color.blue);
			//g.drawOval((int) (sense.getPos().getWorldVar().x), (int) (sense.getPos().getWorldVar().y), r, r);
			//g.drawOval((int) (sense.getPos().getWorldVar().x), (int) (sense.getPos().getWorldVar().y), r, r);
		 }
		
		double h= TotalHealthEnemy / 4;
		double T1 = TotalHealthEnemy - h;
		double T2 = T1 - h;
		double T3 = T2 - h;
		double T4 = T3 - h;
		g.setColor(Color.orange);
		if(TotalHealthEnemy > 800){
			g.fillRect((int) (pos.getWorldVar().x + bounds.getXOffset()) - 5, (int) (pos.getWorldVar().y - bounds.getYOffset()) + 10, 40, (int) bounds.getHeight() - 10);
		}else if(TotalHealthEnemy <= 800 && TotalHealthEnemy > 650){
			g.fillRect((int) (pos.getWorldVar().x + bounds.getXOffset()) - 5, (int) (pos.getWorldVar().y - bounds.getYOffset()) + 10, 30, (int) bounds.getHeight() - 10);
		}else if(TotalHealthEnemy <= 650 && TotalHealthEnemy > 400){
			g.fillRect((int) (pos.getWorldVar().x + bounds.getXOffset()) - 5, (int) (pos.getWorldVar().y - bounds.getYOffset()) + 10, 20, (int) bounds.getHeight() - 10);
		}else if(TotalHealthEnemy <= 400 && TotalHealthEnemy > 200){
			g.fillRect((int) (pos.getWorldVar().x + bounds.getXOffset()) - 5, (int) (pos.getWorldVar().y - bounds.getYOffset()) + 10, 10, (int) bounds.getHeight() - 10);
		}else if(TotalHealthEnemy <= 100){
			g.fillRect((int) (pos.getWorldVar().x + bounds.getXOffset()) - 5, (int) (pos.getWorldVar().y - bounds.getYOffset()) + 10, 5, (int) bounds.getHeight() - 10);
		}
		
		g.drawImage(ani.getImage(), (int) (pos.getWorldVar().x), (int) (pos.getWorldVar().y), size,size, null);
	}
}