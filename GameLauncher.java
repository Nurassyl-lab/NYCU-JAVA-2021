import java.util.Timer;
import java.util.TimerTask;
import java.util.Date;
public class GameLauncher {
	public static int LEV = 1;
	public static int counter = 1;
    public GameLauncher()
    {
        new Window();
    }
	
	public GameLauncher(int x){
		this.counter = counter;
	}

    public static void main(String args[])
    {
		Timer timer = new Timer();
		TimerTask task = new TimerTask(){
		
		Date data = new Date();
			@Override
			public void run(){
				TikTak t = new TikTak();
				if(counter % 10 == 0){
					LEV += 1;
					//System.out.println(counter +" seconds");
					}
				
				counter++;
				long timeMilli = data.getTime();
				//System.out.println(counter + " is Counter");
				if(counter == 3){
					new GameLauncher();
				}
			}
		};
		timer.scheduleAtFixedRate(task, 0 , 1000);
	}
}
