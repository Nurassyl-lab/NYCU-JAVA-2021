import java.awt.Graphics2D;

import java.awt.Color;
public class Camera{
	
	private AABB collisionCam;
	private AABB bounds;
	
	private boolean up;
	private boolean down;
	private boolean left;
	private boolean right;
	
	private float dx;
	private float dy;
	private float maxSpeed = 10f;
	private float acc = 4f;
	private float deacc = 4f;

	private float widthLimit;
	private float heightLimit;
	
	private Entity e;
	
	public Camera(AABB collisionCam){
		this.collisionCam = collisionCam;
	}
	
	public void setLimit(float widthLimit, float heightLimit){
		this.widthLimit = widthLimit;
		this.heightLimit = heightLimit;
	}
	
	public void update(){
		move();
		if(!e.xCol){
			PlayState.map.x += dx;
		}
		if(!e.yCol){
			PlayState.map.y += dy;
		}
		
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
	
	public void target(Entity e){
		this.e = e;
		deacc = e.getDeacc();
		maxSpeed = 10f;
	}
	
	public void input(MouseHandler mouse, KeyHandler key){
		if(mouse.getButton() == 1){
        }
		if(e == null){
			if(key.up.down){//
				up = true;
			}else{
				up = false;
			}
			
			if(key.down.down){//
				down = true;
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
			
		}else {
			if(!e.yCol){
				if(PlayState.map.y + GamePanel.height/2 - e.getSize() / 2 + dy > e.getBounds().getPos().y + e.getDy() + 2){
					up = true;
					down = false;
				}else if(PlayState.map.y + GamePanel.height/2 - e.getSize() / 2 + dy < e.getBounds().getPos().y + e.getDy() - 2){
					down = true;
					up = false;
				}else{
					dy = 0;
					up = false;
					down = false;
				}
			}
			
			
			if(!e.xCol){
				if(PlayState.map.x + GamePanel.width/2 - e.getSize() / 2 + dx > e.getBounds().getPos().x + e.getDx() + 2){
					left = true;
					right = false;
				}else if(PlayState.map.x + GamePanel.width/2 - e.getSize() / 2 + dx < e.getBounds().getPos().x + e.getDx() - 2){
					right = true;
					left = false;
				}else{
					dx = 0;
					right = false;
					left = false;
				}
			}		
		}
	}
	
	public void render(Graphics2D g){
		g.setColor(Color.blue);
		g.drawRect((int) collisionCam.getPos().x, (int) collisionCam.getPos().y, (int) collisionCam.getWidth(), (int) collisionCam.getHeight());
	}
}