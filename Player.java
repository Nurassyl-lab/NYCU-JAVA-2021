import java.awt.*;
import java.util.*;
public class Player extends Entity{
	Date LEV = new Date();
    public int PLEV = 0;
	public  long lastTime  = 0;
	public int TotalHealthPlayer = 1000;
	private float maxSpeed;
	int c = 0;
    public Player(Sprite sprite, Vector2f orgin, int size, float maxSpeed, int TotalHealthPlayer, int pDmg) {
        super(sprite, orgin, size, maxSpeed, TotalHealthPlayer, pDmg);
		acc = 2f;
		bounds.setWidth(30);
		bounds.setHeight(20);
		bounds.setXOffset(15);
		bounds.setYOffset(40);
		this.maxSpeed = maxSpeed;
    }

    private void move(){
        if(up){
            dy -= acc;
            if(dy < -maxSpeed){
                dy = -maxSpeed;
            }
        }else{
            if (dy < 0){
                dy += deacc;
                if(dy>0) {
                    dy = 0;
                }
            }
        }

        if(down){
            dy += acc;
            if(dy >  maxSpeed){
                dy = maxSpeed;
            }
        }else{
            if (dy > 0){
                dy -= deacc;
                if(dy < 0) {
                    dy = 0;
                }
            }
        }

        if(left){
            dx -= acc;
            if(dx < -maxSpeed){
                dx = -maxSpeed;
            }
        }else{
            if (dx < 0){
                dx += deacc;
                if(dx>0) {
                    dx = 0;
                }
            }
        }

        if(right){
            dx += acc;
            if(dx > maxSpeed){
                dx = maxSpeed;
            }
        }else{
            if (dx > 0){
                dx -= deacc;
                if(dx < 0) {
                    dx = 0;
                }
            }
        }
    }

	private void resetPosition(){
		System.out.println("Reset player...");
		pos.x = GamePanel.width / 2 - 32 + 500;
		PlayState.map.x = 0;
		pos.y = GamePanel.height / 2 - 32 + 500;
		PlayState.map.y = 0;
		TotalHealthPlayer = 1000;
		pDmg = pDmg * 2;
		//System.out.println(pDmg);
		setAnimation(RIGHT, sprite.getSpriteArray(RIGHT), 10);
	}

    public void update(Enemy enemy){
        super.update();
		this.maxSpeed = 0.5f; 
		
		//System.out.println(TotalHealthPlayer + "        " + enemy.TotalHealthEnemy);
		//System.out.println(pos.x + " " + pos.y);
		if(enemy.TotalHealthEnemy <= 0 && enemy.dead == true){
			
			enemy.dead = true;
			//enemy.TotalHealthEnemy = 400;
			}/*else {
				enemy.dead = false;
			}*/
		if(attack && hitBounds.collides(enemy.getBounds()) && enemy.TotalHealthEnemy > 0){
			//System.out.println("hitBounds : Collided");
			enemy.TotalHealthEnemy = (int) enemy.TotalHealthEnemy - (int)pDmg;
			System.out.println("HEALTH is " + enemy.TotalHealthEnemy);
		}
		//System.out.println(enemy.TotalHealthEnemy);
		//System.out.println("Total " + enemy.TotalHealthEnemy);
		
		if(!fallen && !dead){
			//System.out.println(dead + " Status");
			move();
			if(!tc.collisionTile(dx, 0)){
				//PlayState.map.x +=  dx;
				pos.x += dx;
				xCol = false;
			}else{
				xCol = true;
			}
			if(!tc.collisionTile(0, dy)){
				//PlayState.map.y += dy;
				pos.y += dy;
				yCol = false;
			}else{
				yCol = true;
			}
		}else{
			System.exit(0);
			xCol = true;
			yCol = true;
			//if(ani.hasPlayedOnes()){
				resetPosition();
				dx = 0;
				dy = 0;
				fallen = false;
				dead = false;
			
		}
		LEV = new Date();
		//System.out.println(lev.getTime() - lastTime);
		if(LEV.getTime() - lastTime > 60000){
			PLEV += 1;
			//System.out.println(lev.getTime() - lastTime);
			lastTime = LEV.getTime();
			pDmg += 2 * PLEV;
			TotalHealthEnemy += 12 * PLEV;
			eDmg += 3 * PLEV;
			TotalHealthPlayer+= 12 * PLEV;
			System.out.println("LEV is " + PLEV);
			if(PLEV % 5 == 0){
				//this.maxSpeed = 3f;
			}if(PLEV % 2 == 0){
				TotalHealthPlayer = TotalHealthPlayer + 500;
				System.out.println("Healed");
			}
			if(PLEV % 10 == 0){
				//pDmg += 2;
			}else{
				this.maxSpeed = 1f;
				//pDmg = ;
			}
		}
		
    }

    @Override
    public void render(Graphics2D g){
		
		//g.setColor(Color.green);
		//g.drawRect((int) (pos.getWorldVar().x + bounds.getXOffset()), (int) (pos.getWorldVar().y + bounds.getYOffset()), (int) bounds.getWidth(), (int) bounds.getHeight());
        
		if(attack){
		//	g.setColor(Color.red);
		//	g.drawRect((int) (hitBounds.getPos().getWorldVar().x + hitBounds.getXOffset()), (int) (hitBounds.getPos().getWorldVar().y + hitBounds.getYOffset()), (int)(hitBounds.getWidth()), (int)hitBounds.getHeight());
		}
		
		g.setColor(Color.red);
		if(TotalHealthPlayer > 0){
			//System.out.println(TotalHealthPlayer);
		if(TotalHealthPlayer > 800){
			g.fillRect((int) (pos.getWorldVar().x + bounds.getXOffset()) - 5, (int) (pos.getWorldVar().y - bounds.getYOffset()) + 30, 40, (int) bounds.getHeight() - 10);}
		}else if(TotalHealthPlayer <= 800 && TotalHealthPlayer > 650){
			g.fillRect((int) (pos.getWorldVar().x + bounds.getXOffset()) - 5, (int) (pos.getWorldVar().y - bounds.getYOffset()) + 30, 30, (int) bounds.getHeight() - 10);
		}else if(TotalHealthPlayer <= 650 && TotalHealthPlayer > 400){
			g.fillRect((int) (pos.getWorldVar().x + bounds.getXOffset()) - 5, (int) (pos.getWorldVar().y - bounds.getYOffset()) + 30, 20, (int) bounds.getHeight() - 10);
		}else if(TotalHealthPlayer <= 400){
			g.fillRect((int) (pos.getWorldVar().x + bounds.getXOffset()) - 5, (int) (pos.getWorldVar().y - bounds.getYOffset()) + 30, 10, (int) bounds.getHeight() - 10);
		}
	
		g.drawImage(ani.getImage(), (int) (pos.getWorldVar().x), (int) (pos.getWorldVar().y), size, size, null);
    }

    public void input (MouseHandler mouse, KeyHandler key){
		
        if(mouse.getButton() == 1){
            //System.out.println("Player: " + pos.x + ", " + pos.y);
        }
		if(!fallen && !dead){
			if(key.up.down){//
				up = true;
				if(key.right.down){
					right = true;
				}
				else if(key.left.down){
					left = true;
				}
				else{
					right = false;
					left = false;
				}
			}else{
				up = false;
			}
			
			if(key.down.down){//
				down = true;
				if(key.right.down){
					right = true;
				}
				else if(key.left.down){
					left = true;
				}
				else{
					right = false;
					left = false;
				}
			}else{
				down = false;
			}
			
			if(key.left.down){//
				left = true;
			}else{
				left = false;
			}
			
			if(key.right.down){//
				right = true;
			}else{
				right = false;
			}
			
			if(key.attack.down){//
			    //System.out.println("HEALTH : " + TotalHealthPlayer);
				attack = true;
				
				if(key.up.down){
					down = false;
					up = true;
					right = false;
					left = false;
				}else if(key.down.down){
					up = false;
					down = true;
					right = false;
					left = false;
				}else if(key.right.down){
					up = false;
					down = false;
					right = true;
					left = false;					
				}else if(key.left.down){
					up = false;
					down = false;
					right = false;
					left = true;					
				}else{
					up = false;
					down = false;
					right = false;
					left = false;
				}
				
			}else{
				attack = false;
			}
	
		}else {
			up = false;
			down = false;
			right = false;
			left = false;
			attack = false;
		}
    }
}
