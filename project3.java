package javaproject;

public class ergasia {    

    
    public static void main(String[] args)
    {
    
        int N=Integer.parseInt(args[0]);
        String mov=args[1];
        String cond=args[2];
        System.out.println(N+"/////"+mov+"/////"+cond);
        //static arrays for values 
        String[] movementStrings = new String[]{"impressionism","expressionism","naturalism"};    
        String[] conditionStrings = new String[]{"bad","good","excellent"};
        String[] techniqueStrings = new String[]{"oil","aquarelle","tempera"};
        String[] creatorStrings = new String[]{"Picasso","Giotto","Leonardo","Cézanne","Rembrandt","Velázquez","Kandinsky","Monet"};
        int[] yearsNumbers = new int[]{1881,1888,1852,1874,1869,1855,1865,1866,1844,1866,1844,1879,1800};
        String[] materials = new String[]{"iron","stone","wood"};

       

        Artifacts[] array = new Artifacts[N];
        for (int i=0;i<N;i++)
        {
            int k=randNum(0, 2);
            if (k==0)
            {
                array[i]= new Paintings(
                    i,
                    creatorStrings[randNum(0, 8)], 
                    yearsNumbers[randNum(0, 13)], 
                    movementStrings[randNum(0,3)], 
                    conditionStrings[randNum(0, 3)], 
                    randNum(20, 100), 
                    randNum(20, 100), 
                    techniqueStrings[randNum(0, 3)]
                );
            }
            else 
            {
                array[i]=new Sculptures(
                    i, 
                    creatorStrings[randNum(0, 8)], 
                    yearsNumbers[randNum(0, 13)], 
                    movementStrings[randNum(0, 3)], 
                    conditionStrings[randNum(0, 3)], 
                    randNum(20, 100), 
                    materials[randNum(0, 3)]);
            }
            
        }
        

        auction(array,mov,cond,N);

    }

    static void auction(Artifacts[] array,String mov,String cond,int N)
    {
        for (int i=0;i< N ;i++ )   
        {
            array[i].getInfo();
            System.out.println(array[i].evaluate(mov, cond) + " \n");

        }

    }
    static public int randNum(int min ,int max)  // generate random numbers from min to max
    {
            return (int) ((Math.random() * (max - min)) + min);
    }    

}

abstract class Artifacts
{

    int index;
    String creator;
    int year;
    Artifacts (int ind,String cr,int y)
    {
        index=ind;
        creator=cr;
        year=y;
        System.out.print("Creating an instance of Artifact\n");    
    }
    public void getIndex()
    {
        System.out.print(" Index :  "+index );
        
    }
    
    public  void getInfo()
    {
        System.out.println("\n Creator:  "+creator+" Year:  "+year);
  
    }

    
    public abstract boolean evaluate(String mov,String cond);
    
}

abstract class Masterpiece extends Artifacts
{
    
    String movement; 
    String condition;
    
    Masterpiece(int ind,String cr,int y,String mov,String cond)
    {
        super(ind, cr, y);
        this.movement = mov;
        this.condition = cond;
        System.out.println("Creating an instance of Masterpiece\n ");

    }
    @Override
    public void getInfo(){
        //use super and give the informations about superclass
        super.getIndex();
        super.getInfo();
        

        //extra infos about the masterpiece
        System.out.println(" movement:  "+ movement);
        System.out.println(" condition:  "+condition);
    }
    public abstract boolean evaluate(String mov,String cond);
}

class Paintings extends Masterpiece
{
    int length;
    int width;
    String technique;
    
    Paintings(int ind,String cr,int y,String mov,String cond,int len,int wid , String technq)
    {
        super(ind, cr, y, mov, cond);
       
        length=len;
        width=wid;
        technique=technq;
        System.out.println("Creating an instance of Paintings\n ");
    }
    @Override
    public void getInfo()
    {
        //use super and give the informations about superclass
        super.getInfo();
        
        //prints surface
        System.out.println(" surface is :  "+length*width+ "  technique  : "+technique);
        
    }
    public boolean evaluate(String mov,String cond)
    {
        boolean movCheck=false;
        boolean condCheck=false;
        if (cond==null)
        {
            cond="good";
        }

        if (mov==super.movement)
        {
            movCheck=true;
        }
        if(cond==super.condition)
        {
            condCheck=true;
        }else if (super.condition=="excellent" && cond=="good")
        {
            condCheck=true;
        }
        if (movCheck == true && condCheck == true)
        {
            return true;
        }
        else
        {
            return false;
        }
        
    }
}

class Sculptures extends Masterpiece
{
    int volume;
    String material;
    Sculptures(int ind,String cr,int y,String mov,String cond,int vol,String mat)
    {
        super(ind, cr, y, mov, cond);
        volume=vol;
        material=mat;
        System.out.println("Creating an instance of Sculpture  \n");
    }
    @Override
    public void getInfo() 
    {
        super.getInfo();
        System.out.println("volume:"+volume+"material:"+material);
    }
    public boolean evaluate(String mov,String cond)
    {   
        boolean move=false;
        boolean condi=false;
        if (cond==null)
        {
            cond="good";
        }
        if (mov==super.movement)
        {
            move=true;
        }
        if(cond==super.condition)
        {
            condi=true;
        }

        if (condi==true&&move==true)
        {
            return true;

        }
        else
        {
            return false;
        }
    }

}
