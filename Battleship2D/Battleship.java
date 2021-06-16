import java.util.*;
public class Battleship {
   int length;
   int[][] segs;
   public boolean alive;
   Random random=new Random();
   public Battleship(int l){
      this.length=l;
      this.segs = new int[l][2];
      this.alive = true;
   }
   public int getLength(){
        return this.length;
   }
   /**
   public void setPos(int x,int y){
      for(int i=this.segs.length-1;i>=0;i--){
        this.segs[i][0] = x+i;
        this.segs[i][1] = y;
      }
   }
   **/
   public void setPosRan(int width,int height){
  int bol = random.nextInt(2);
      if(bol==0){
        int x = random.nextInt(height-1);
        int y = random.nextInt(width-this.length);
        for(int i=this.segs.length-1;i>=0;i--){
              this.segs[i][0] = x;
              this.segs[i][1] = y+i;
        }
      }else{
        int y = random.nextInt(height-1);
        int x = random.nextInt(width-this.length);
        for(int i=this.segs.length-1;i>=0;i--){
              this.segs[i][0] = x+i;
              this.segs[i][1] = y;
        }
      }
    }
   
    public void setNPos(int x,int y, int b){
        this.segs[x][y]=b;
   }
   public int getPos(int x,int y){
       return this.segs[x][y];
   }
   public boolean getLife(){
        return this.alive;
   }
   public void die(){
        this.alive =false;
   }   
   public int[][] getSegs(){
        return this.segs;
   }
}