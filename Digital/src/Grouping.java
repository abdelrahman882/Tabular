import java.util.*;

public class Grouping {
    HashMap<Integer, LinkedList<String>> grouping =  new HashMap <Integer, LinkedList<String>>();
    int flag=0;
    int getnumberofones(char ar[][],int x)
    {
        int answer=0;
       for(int i=0;i<ar[x].length;i++) 
       {
           if(ar[x][i]=='1')
           {
               answer++;
               
           }
           
       }
       return answer;
        
    }



    String makestring(char ar[][],int x)
    {
        String answer ="";
        for(int i=0;i<ar[x].length;i++)
        {
            answer+=ar[x][i];
            
        }
        
        return answer;
    }


    void search()
    {flag=1;
    int x=1;
    while(flag==1)
    {
        LinkedList<String> container=new LinkedList<String>();

        flag=0;
        for (HashMap.Entry<Integer, LinkedList<String>> entry : grouping.entrySet())
        if(grouping.get(x)!=null)
        {
            container=(LinkedList<String>)grouping.get(x);
    
            
            x++;
        }   
    }
        
        
    }
void grouping(int n,char ar[][])
{
    Integer numberofones=0;
    String h;
    for(int i=0;i<ar.length;i++)
    {
        LinkedList<String> container=new LinkedList<String>();
       numberofones= getnumberofones(ar,i);
       h=makestring(ar,i);
       if(grouping.get(numberofones)!=null)
       container=(LinkedList<String>)grouping.get(numberofones.hashCode());
       container.addFirst(h);
        grouping.put(numberofones,container);       
        
    }    
    
    
}
    
    
    
}