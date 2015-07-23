package minesweeper;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.lang.*;
class minesweeper extends WindowAdapter implements WindowListener,MouseListener,ActionListener
{
 int row=12,col=12;
 Frame f1;
 Button b[][]=new Button[row][col];
 Button b1,b2;
 boolean bomb[][]=new boolean[row][col];
 boolean flag[][]=new boolean[row][col];
 boolean show[][]=new boolean[row][col];
Color c=new Color(220,220,220);
TextField t[]=new TextField[2];
int sbomb;Panel p1,p2;
Label l1,l2;
int count=0;
int rx,ry,score,x;
static int numbombs;
minesweeper()
{
	
p1=new Panel();p2=new Panel();
f1=new Frame("minesweeper");
p1.setLayout(new GridLayout(row,col));
p2.setLayout(new GridLayout(1,6));
l1=new Label("Score");l2=new Label("no. of bombs(<93)");
for(int i=0;i<row;i++)
{
for(int j=0;j<col;j++)
{
b[i][j]=new Button();
p1.add(b[i][j]);
b[i][j].addMouseListener(this);
b[i][j].setBackground(c);
b[i][j].setForeground(Color.black);

} 
}
for(int i=0;i<2;i++)
{
t[i]=new TextField();
} 
 b1=new Button("Restart");
 b2=new Button("Submit");

p2.add(l1);p2.add(t[0]);
p2.add(l2);p2.add(t[1]);
p2.add(b1);b1.addActionListener(this);p2.add(b2);b2.addActionListener(this);
f1.add(p1,BorderLayout.CENTER);
f1.add(p2,BorderLayout.SOUTH);
f1.addWindowListener(this);
f1.setVisible(true);f1.setSize(900,600);
f1.setResizable(false);
JOptionPane.showMessageDialog(f1,"Enter no. of bombs");

}
public int getbomb()
{
	int nb=Integer.parseInt(t[1].getText());
	x=(row*col)-nb;
	t[0].setText(""+score+"/"+x);
	return nb;
	}
public void random()
{
	count=0;
	while (count < numbombs)
  {
   rx = (int) (Math.random () * (row));
   ry = (int) (Math.random () * (col));
   if (bomb[rx][ry] == false)
   {
   bomb[rx][ry] = true;
  count++;
   }
     }
}
public void gameover () 
{
    for (int i=0;i<row;i++)
    {
        for (int j= 0 ;j< col ;j++)
        {
            if (bomb [i] [j] == true)
            {
                b[i][j].setLabel ("X"); 
                b[i][j].setBackground (Color.red);
            }
            b[i][j].setEnabled (false);
                    }
    }
    JOptionPane.showMessageDialog(f1,"Game over");

}
public int surbomb(int x,int y)
{
	int sbomb=0;
	for(int i=x-1;i<=x+1;i++)
	{
		for(int j=y-1;j<=y+1;j++)
		{
			if(i>-1&&j>-1&&i<row&&j<col){
if(bomb[i][j]==true)	
{sbomb++;}
		}}	}
return sbomb;	
	}



public void checkwin()
{
	int f=0,over=0;
	for(int i=0;i<row;i++){
		for(int j=0;j<col;j++){
			//JOptionPane.showMessageDialog(f1,flag[i][j]);
             if(show[i][j]==false)
              {
	           f=1;
              }}}			
if(f==0)
{
	for(int i=0;i<row;i++){

	for(int j=0;j<col;j++){
	
if(flag[i][j]==true&&bomb[i][j]==false)	
{
	over=1;
	break;
	}
}}
	if(over==1){JOptionPane.showMessageDialog(f1,"Game over");}
	}
else{JOptionPane.showMessageDialog(f1,"You won");}
    
}



public void mousePressed(MouseEvent e) 
	{int ch=0;
		Button bb=(Button)e.getSource();
		if(e.getButton() == MouseEvent.BUTTON1)
	    {
			bb.setForeground(Color.black);
			
		for(int i=0;i<row;i++)
		{
			for(int j=0;j<col;j++)
			{
				if((bomb[i][j]==true)&&(bb==b[i][j]))
				{
					//JOptionPane.showMessageDialog(f1,i+","+j+";");
                bb.setLabel("X");ch=1;
                gameover();
                break;
		     	}
			}
		}
		if(ch==0){
			for(int i=0;i<row;i++)
			{
				for(int j=0;j<col;j++)
				{
				if(bb==b[i][j])
			{
					show[i][j]=true;
					sbomb=surbomb(i,j);
					  bb.setBackground(Color.blue);
			          bb.setForeground(Color.black);
			          bb.setLabel(""+sbomb);
			     	 bb.setEnabled (false);
			     	 score++;
			     	t[0].setText(""+score+"/"+x);

			}
			}
			}
			    }	    
		}	
			
			
			
	    else if(e.getButton() == MouseEvent.BUTTON3)
	    {
	    	bb.setForeground(Color.red);
			bb.setLabel("F");
			for(int i=0;i<row;i++)
			{
				for(int j=0;j<col;j++)
				{
				if(bb==b[i][j])
			{
					show[i][j]=true;
					flag[i][j]=true;
			}}}		
			}
	}
public void actionPerformed(ActionEvent e)
{
	Button bb=(Button)e.getSource();
	if(bb==b1){
		x=0;score=0;
	numbombs=getbomb();

	for(int i=0;i<row;i++){
		for(int j=0;j<col;j++){
			p1.remove(b[i][j]);
			p1.add(b[i][j]);
			b[i][j].setEnabled (true);
			b[i][j].setLabel("");
			bomb[i][j]=false;
			flag[i][j]=false;
			b[i][j].setBackground(c);
			b[i][j].setForeground(Color.black);
			
		}
	}
	random();
	}
	if(bb==b2){	//JOptionPane.showMessageDialog(f1,"m");
        
		checkwin();}
	

}
	public void mouseReleased(MouseEvent e)
	{}
	public void mouseEntered(MouseEvent e) 
	{}
	public void mouseExited(MouseEvent e) 
	{}
	public void mouseClicked(MouseEvent e) 
	{}
public static void main(String ar[])
{
 minesweeper ob=new minesweeper();
 
}
public void windowClosing(WindowEvent e){
int a=JOptionPane.showConfirmDialog(f1,"Are You Sure???");
if(a==0)
{
JOptionPane.showMessageDialog(f1,"Byeeeeee :)");
System.exit(0);
}}
}
