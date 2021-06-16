import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
public class Battleship2D implements ActionListener {
    private ArrayList<Battleship>sea;
    public int guessNum=0;
    public int totalSegs=0;
    public boolean ifWon=false;
    Random random=new Random();
        JFrame frame=new JFrame();
        JPanel title_panel=new JPanel();
        JPanel big_panel=new JPanel();
        JPanel reset_panel=new JPanel();
        JPanel button_panel=new JPanel();
        JLabel textfield =new JLabel();
        JLabel winfield =new JLabel();
        JButton resetButton = new JButton();
        int width=10;
        int height=10;
        int totalBattleships=0;
        JButton[][] buttons = new JButton[height][width];
   public Battleship2D()
    {
      sea=new ArrayList<Battleship>();
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     frame.setSize(1600,1600);
     frame.getContentPane().setBackground(new Color(50,50,50));
     frame.setLayout(new BorderLayout());
     frame.setVisible(true);
     
     
     
        
     
        
        
        
     textfield.setBackground(new Color(25,25,25));
     textfield.setForeground(new Color(230,0,0));
     textfield.setFont(new Font("Ink Free",Font.BOLD,75));
     //font MS PMincho
     textfield.setHorizontalAlignment(JLabel.CENTER);
     textfield.setText("Battleship");
     textfield.setOpaque(true);
     
     winfield.setBackground(new Color(25,25,25));
     winfield.setForeground(new Color(230,0,0));
     winfield.setFont(new Font("Ink Free",Font.BOLD,75));
     //font MS PMincho
     winfield.setHorizontalAlignment(JLabel.CENTER);
     winfield.setText("Your Score was: ");
     winfield.setOpaque(true);
     
     
     
     title_panel.setLayout(new BorderLayout());
     title_panel.setBounds(0,0,500,100);
     reset_panel.setLayout(new BorderLayout());
     reset_panel.setBounds(500,100,600,100);
     
     resetButton.setFont(new Font("MV Boli",Font.BOLD,50));
     resetButton.addActionListener(this);
     resetButton.setFocusable(false);
     resetButton.setHorizontalAlignment(JButton.RIGHT);
     resetButton.setText("Reset");
     
     
     button_panel.setLayout(new GridLayout(width,height));
     button_panel.setBackground(new Color(150,150,150));
     
     for(int i=0;i<height;i++){
         for(int j=0;j<width;j++){
         buttons[i][j]=new JButton();
         button_panel.add(buttons[i][j]);
         buttons[i][j].setFont(new Font("MV Boli",Font.BOLD,120));
         // font Imprint MT Shadow
         buttons[i][j].setFocusable(false);
         buttons[i][j].addActionListener(this);
        }
     }
     title_panel.add(textfield);
     reset_panel.add(resetButton);
     big_panel.add(textfield);
     big_panel.add(resetButton);
     frame.add(big_panel,BorderLayout.NORTH);
     frame.add(button_panel);
     
     
     Battleship ship = new Battleship(5);
     Battleship ship2 = new Battleship(4);
     Battleship ship3 = new Battleship(4);
     Battleship ship4 = new Battleship(3);
     Battleship ship5 = new Battleship(3);
     this.setShipRan(ship);
     this.setShipRan(ship2);
     this.setShipRan(ship3);
     this.setShipRan(ship4);
     this.setShipRan(ship5);
    }
    @Override
    public void actionPerformed(ActionEvent e){
        for(int i=0;i<height;i++){
            for(int k=0;k<width;k++){
            if(e.getSource()==buttons[i][k] && !ifWon){
               if(guess(i,k)){
                  totalBattleships=0;
                  for(int j=0;j<this.getSea().size();j++){
                    if(this.checkLife(j)==true){
                        totalBattleships++;    
                    }
                  }
                  if(totalBattleships==0){
                      textfield.setText("Your Score was: "+((int)(((double)totalSegs*10000)/guessNum))/100.0+"pts");
                           big_panel.remove(title_panel);
                           big_panel.remove(reset_panel);
                           big_panel.add(reset_panel);
                           frame.add(big_panel,BorderLayout.NORTH);
                           guessNum=0;
                           ifWon=true;
                  }
               }
            }
            if(e.getSource()==resetButton){
                for(int l=0;l<height*width;l++){
                   button_panel.remove(0);
                }
                for(int u=0;u<height;u++){
                    for(int j=0;j<width;j++){
                        buttons[u][j]=new JButton();
                        button_panel.add(buttons[u][j]);
                        buttons[u][j].setFont(new Font("MV Boli",Font.BOLD,120));
                        // font Imprint MT Shadow
                        buttons[u][j].setFocusable(false);
                        buttons[u][j].addActionListener(this);
                      }
                 }
                textfield.setText("Battleship");
                int j=this.getSea().size();
                while(j>0){
                    j--;
                    this.removeShip(0);
                 }
                Battleship ship = new Battleship(5);
                Battleship ship2 = new Battleship(4);
                Battleship ship3 = new Battleship(4);
                Battleship ship4 = new Battleship(3);
                Battleship ship5 = new Battleship(3);
                this.setShipRan(ship);
                this.setShipRan(ship2);
                this.setShipRan(ship3);
                this.setShipRan(ship4);
                this.setShipRan(ship5);
                ifWon=false;
            }
        }
      }
    }
    public boolean guess(int target1,int target2){
        int negativeTotal=0;
        for(int j=0;j<sea.size();j++){
                for(int h = 0;h<sea.get(j).getLength();h++){
                    if(this.sea.get(j).getPos(h,0)==target1 && this.sea.get(j).getPos(h,1)==target2){
                        //HIT!!!
                       this.sea.get(j).setNPos(h,0,this.sea.get(j).getPos(h,0)-(height*width));
                       this.sea.get(j).setNPos(h,1,this.sea.get(j).getPos(h,1)-(height*width));
                       for(int u = 0;u<sea.get(j).getLength();u++){
                            if(this.sea.get(j).getPos(u,0)<=-1 && this.sea.get(j).getPos(u,1)<=-1)
                            negativeTotal++;
                       }
                       if(negativeTotal==sea.get(j).getLength()){
                           this.sea.get(j).die();
                           //SINK!!!
                           for(int s[]: sea.get(j).getSegs()){
                               buttons[(s[0]+(height*width))][(s[1]+(height*width))].setText("O");
                               buttons[(s[0]+(height*width))][(s[1]+(height*width))].setForeground(new Color(0,230,0));
                           }
                           guessNum++;
                           totalSegs=0;
                           for(int l=0;l<sea.size();l++){
                            totalSegs+=sea.get(l).getLength();
                           }
                           return true;
                       }else{
                          buttons[target1][target2].setForeground(new Color(230,0,0));
                          buttons[target1][target2].setText("[]");
                          guessNum++;
                        }
                       return true;
                    }else if(this.sea.get(j).getPos(h,0)+(height*width)==target1 && this.sea.get(j).getPos(h,1)+(height*width)==target2){
                        return true;
                    }
                }
        }
        buttons[target1][target2].setForeground(new Color(0,0,230));
        buttons[target1][target2].setText("X");
        guessNum++;
        return false;    
    }
    /**
    public void setShip(Battleship a,int x,int y){
     boolean error = true;
     while(error){ 
        a.setPos(x,y);
        this.checkConflict(a);
       }  
     sea.add(a);
    }
    **/
    public void setShipRan(Battleship a){  
     boolean error = true;
     while(error){ 
        a.setPosRan(width,height);
        error=this.checkConflict(a);
     }  
     sea.add(a);
    } 
    public int getSeaSize(){
        return sea.size();
    }
    public Battleship getShip(int i){
        return sea.get(i);
    }
    public ArrayList getSea(){
        return sea;
    }
    public boolean checkLife(int j){
        return this.sea.get(j).getLife(); 
    }
    public void removeShip(int i){
        sea.remove(i);
    }
    public boolean checkConflict(Battleship a){
        boolean error = false;
        for(int m =0;m<a.getLength();m++){
            for(int j=0;j<sea.size();j++){
                for(int h = 0;h<sea.get(j).getLength();h++){
                    if(this.sea.get(j).getPos(h,0)==a.getPos(m,0) && this.sea.get(j).getPos(h,1)==a.getPos(m,1)){
                        error=true;
                    }
                }
            }
        }
        return error;
    }
}