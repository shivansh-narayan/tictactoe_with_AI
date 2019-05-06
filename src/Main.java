import java.util.Random;
import java.util.Scanner;
public class Main {
     static int no(String s,char x)
    {
        int e=0;
        for(int i=1;i<=9;i++)
        {
            if(s.charAt(i)==x)
                e++;
        }
        return e;
    }
    static int count(String s,char x)
    {
        int r=0;
        if(s.charAt(1)==x && s.charAt(2)==x && s.charAt(3)==x)
            r++;
        if(s.charAt(4)==x && s.charAt(5)==x && s.charAt(6)==x)
            r++;
        if(s.charAt(7)==x && s.charAt(8)==x && s.charAt(9)==x)
            r++;

        if(s.charAt(1)==x && s.charAt(4)==x && s.charAt(7)==x)
            r++;
        if(s.charAt(2)==x && s.charAt(5)==x && s.charAt(8)==x)
            r++;
        if(s.charAt(3)==x && s.charAt(6)==x && s.charAt(9)==x)
            r++;

        if(s.charAt(1)==x && s.charAt(5)==x && s.charAt(9)==x)
            r++;
        if(s.charAt(3)==x && s.charAt(5)==x && s.charAt(7)==x)
            r++;
        return r;
    }
    static void print(String s)
    {
        System.out.println("---------");
        System.out.println("| "+s.charAt(1)+" "+s.charAt(2)+" "+s.charAt(3)+" |");
        System.out.println("| "+s.charAt(4)+" "+s.charAt(5)+" "+s.charAt(6)+" |");
        System.out.println("| "+s.charAt(7)+" "+s.charAt(8)+" "+s.charAt(9)+" |");
        System.out.println("---------");
    }
    static boolean check(String s)
    {


        int x,o,e=0;
        x=count(s,'X');
        o=count(s,'O');
        e=no(s,' ');
        int nx=no(s,'X');
        int no=no(s,'O');
       // System.out.println(x+" "+o+" "+nx+" "+no+" "+e);
        if(x>0 && o>0)
        {
            return false;
           // System.out.println("Impossible");
        }
        else if(Math.abs(nx-no)>1)
        {
            return false;
           // System.out.println("Impossible");
        }
        else if(x==0 && o==0 && e>0)
        {
            return false;
           // System.out.println("Game not finished");
        }
        else if(x==0 && o==0 && e==0)
        {
            System.out.println("Draw");
            return true;
        }
        else if(x>0)
        {
            System.out.println("X wins");
            return true;
        }
        else
            {
            System.out.println("O wins");
                return true;
        }

    }
    static String human(String s,char o)
    {
        Scanner sc=new Scanner(System.in);
        int flag=0;
        int x,y,index=0;
        while(flag!=2)
        {
            System.out.println("Enter the coordinates :");
            if(sc.hasNextInt()) {
                x = sc.nextInt();
                flag++;
            }
            else {
                System.out.println("You should enter numbers!");
                sc.nextLine();
                continue;
            }
            if(sc.hasNextInt()) {
                y = sc.nextInt();
                flag++;
            }
            else {
                System.out.println("You should enter numbers!");
                sc.nextLine();
                continue;
            }
            if(x>3 || y>3)
            {
                System.out.println("Between 1-3");
                flag=0;
                continue;
            }
            index=x+3*(3-y); //index logic
            if(s.charAt(index)==' ') {
                break;
            }
            else {
                flag = 0;
                sc.nextLine();
                System.out.println("This cell is occupied! Choose another one!");
            }

        }
        s=s.substring(0,index)+o+s.substring(index+1);
        return s;
    }
    static int index(int x,int y)
    {
        return x+3*(3-y);
    }
    static String easy(String s,char o)
    {
        Random r=new Random();
        while(true)
        {
            int index=r.nextInt(9)+1;
            if(s.charAt(index)==' ')
            {
                s=s.substring(0,index)+o+s.substring(index+1);
                return s;
            }

        }
    }
    static String hard(String s,char o)
    {
        int index=bestmove(s,o);
        s=s.substring(0,index)+o+s.substring(index+1);
        return s;
    }
    static String medium(String s,char o)
    {
        //when comp wins
        for(int x=1;x<=3;x++)
        {
            for(int y=1;y<=3;y++)
            {
                int index=index(x,y);
                if(s.charAt(index)==' ')
                {
                    String str=s.substring(0,index)+o+s.substring(index+1);
                    if(count(str,o)>0)
                        return str;
                }
            }
        }
        //when player wins
        char comp; // complimentary character
        if(o=='X')
            comp='O';
        else
            comp='X';
        for(int x=1;x<=3;x++)
        {
            for (int y=1;y<=3;y++)
            {
                int index=index(x,y);
                if(s.charAt(index)==' ')
                {
                    String str=s.substring(0,index)+comp+s.substring(index+1);
                    if(count(str,comp)>0)
                        return s.substring(0,index)+o+s.substring(index+1);
                }
            }
        }

        // random
        return easy(s,o);

    }
    private static void game_easy_user(String s)
    {
        print(s);
        while(true)
        {
            System.out.println("Making move level \"easy\" ");
            s=easy(s,'X');
            print(s);
            if(check(s))
            {
                return;
            }
            s = human(s,'O');
            print(s);
            if(check(s))
            {
                break;
            }

        }
    }
    private static void game_user_easy(String s)
    {
        print(s);
        while(true)
        {

            s = human(s,'X');
            print(s);
            if(check(s))
            {
                break;
            }
            System.out.println("Making move level \"easy\" ");
            s=easy(s,'O');
            print(s);
            if(check(s))
            {
                return;
            }

        }
    }

    static void game_user_medium(String s)
    {
        print(s);
        while(true)
        {

            s = human(s,'X');
            print(s);
            if(check(s))
            {
                break;
            }
            System.out.println("Making move level \"medium\" ");
            s=medium(s,'O');
            print(s);
            if(check(s))
            {
                return;
            }

        }
    }
    static void game_medium_user(String s)
    {
        print(s);
        while(true)
        {
            System.out.println("Making move level \"medium\" ");
            s=medium(s,'X');
            print(s);
            if(check(s))
            {
                return;
            }
            s = human(s,'O');
            print(s);
            if(check(s))
            {
                break;
            }

        }
    }

    static void game_user_user(String s)
    {
        print(s);
        while(true)
        {

            s = human(s,'X');
            print(s);
            if(check(s))
            {
                break;
            }
            s = human(s,'O');
            print(s);
            if(check(s))
            {
                break;
            }

        }
    }
    static void game_easy_easy(String s)
    {
        print(s);
        while(true)
        {
            System.out.println("Making move level \"easy\" ");
            s=easy(s,'X');
            print(s);
            if(check(s))
            {
                return;
            }
            System.out.println("Making move level \"easy\" ");
            s=easy(s,'O');
            print(s);
            if(check(s))
            {
                return;
            }
        }
    }
    static void game_easy_medium(String s)
    {
        print(s);
        while(true)
        {
            System.out.println("Making move level \"easy\" ");
            s=easy(s,'X');
            print(s);
            if(check(s))
            {
                return;
            }
            System.out.println("Making move level \"medium\" ");
            s=medium(s,'O');
            print(s);
            if(check(s))
            {
                return;
            }
        }
    }

    static void game_easy_hard(String s)
    {
        print(s);
        while(true)
        {
            System.out.println("Making move level \"easy\" ");
            s=easy(s,'X');
            print(s);
            if(check(s))
            {
                return;
            }
            System.out.println("Making move level \"hard\" ");
            s=hard(s,'O');
            print(s);
            if(check(s))
            {
                return;
            }
        }
    }
    static void game_medium_hard(String s)
    {
        print(s);
        while(true)
        {
            System.out.println("Making move level \"medium\" ");
            s=medium(s,'X');
            print(s);
            if(check(s))
            {
                return;
            }
            System.out.println("Making move level \"hard\" ");
            s=hard(s,'O');
            print(s);
            if(check(s))
            {
                return;
            }
        }
    }
    static void game_hard_medium(String s)
    {
        print(s);
        while(true)
        {
            System.out.println("Making move level \"hard\" ");
            s=hard(s,'X');
            print(s);
            if(check(s))
            {
                return;
            }
            System.out.println("Making move level \"medium\" ");
            s=medium(s,'O');
            print(s);
            if(check(s))
            {
                return;
            }

        }
    }
    static void game_hard_hard(String s)
    {
        print(s);
        while(true)
        {
            System.out.println("Making move level \"hard\" ");
            s=hard(s,'X');
            print(s);
            if(check(s))
            {
                return;
            }
            System.out.println("Making move level \"hard\" ");
            s=hard(s,'O');
            print(s);
            if(check(s))
            {
                return;
            }

        }
    }
    static void game_medium_easy(String s)
    {
        print(s);
        while(true)
        {
            System.out.println("Making move level \"medium\" ");
            s=medium(s,'X');
            print(s);
            if(check(s))
            {
                return;
            }
            System.out.println("Making move level \"easy\" ");
            s=easy(s,'O');
            print(s);
            if(check(s))
            {
                return;
            }

        }
    }
    static void game_hard_easy(String s)
    {
        print(s);
        while(true)
        {
            System.out.println("Making move level \"hard\" ");
            s=hard(s,'X');
            print(s);
            if(check(s))
            {
                return;
            }
            System.out.println("Making move level \"easy\" ");
            s=easy(s,'O');
            print(s);
            if(check(s))
            {
                return;
            }

        }
    }
    static void game_user_hard(String s)
    {
        print(s);
        while(true)
        {
            s = human(s,'X');
            print(s);
            if(check(s))
            {
                break;
            }
            System.out.println("Making move level \"hard\" ");
            s=hard(s,'O');
            print(s);
            if(check(s))
            {
                return;
            }

        }
    }
    static void game_hard_user(String s)
    {
        print(s);
        while(true)
        {

            System.out.println("Making move level \"hard\" ");
            s=hard(s,'X');
            print(s);
            if(check(s))
            {
                return;
            }
            s = human(s,'O');
            print(s);
            if(check(s))
            {
                break;
            }

        }
    }

    static void game_medium_medium(String s)
    {
        print(s);
        while(true)
        {
            System.out.println("Making move level \"medium\" ");
            s=medium(s,'X');
            print(s);
            if(check(s))
            {
                return;
            }
            System.out.println("Making move level \"medium\" ");
            s=medium(s,'O');
            print(s);
            if(check(s))
            {
                return;
            }


        }
    }


    static int minimax(String s,int depth,char player)
    {
        if(count(s,'X')>0)
            return 10;
        if(count(s,'O')>0)
            return -10;
        if(no(s,' ')==0)
            return 0;
        if (player == 'X')
        {
            int best=-1000;

            for(int x=1;x<=3;x++)
            {
                for(int y=1;y<=3;y++)
                {
                    int index=index(x,y);
                    if(s.charAt(index)==' ')
                    {
                        //make move
                        s=s.substring(0,index)+'X'+s.substring(index+1);
                        best=Math.max(best,minimax(s,depth+1,'O'));
                        //undo move
                        s=s.substring(0,index)+' '+s.substring(index+1);
                    }
                }
            }
            return best;
        }
        else
        {
            int best=1000;

            for(int x=1;x<=3;x++)
            {
                for(int y=1;y<=3;y++)
                {
                    int index=index(x,y);
                    if(s.charAt(index)==' ')
                    {
                        //make move
                        s=s.substring(0,index)+'O'+s.substring(index+1);
                        best=Math.min(best,minimax(s,depth+1,'X'));
                        //undo move
                        s=s.substring(0,index)+' '+s.substring(index+1);
                    }
                }
            }
            return best;
        }
    }

    //The best move function which calls the minimax function
    static int bestmove(String s,char o)
    {
        int bestval=0,bestindex=0;
        if(o=='X')
        {
            bestval = -1000;
            for (int x = 1; x <= 3; x++)
            {
                for (int y = 1; y <= 3; y++)
                {
                    int index = index(x, y);
                    if (s.charAt(index) == ' ')
                    {
                        s = s.substring(0, index) + 'X' + s.substring(index + 1);
                        int val = minimax(s, 0, 'O');
                        s = s.substring(0, index) + ' ' + s.substring(index + 1);
                        if (val > bestval) {
                            bestval = val;
                            bestindex = index;

                        }
                    }
                }
            }
            return bestindex;
        }
        else
        {
            bestval=1000;
            for (int x = 1; x <= 3; x++)
            {
                for (int y = 1; y <= 3; y++)
                {
                    int index = index(x, y);
                    if (s.charAt(index) == ' ')
                    {
                        s = s.substring(0, index) + 'O' + s.substring(index + 1);
                        int val = minimax(s, 0, 'X');
                        s = s.substring(0, index) + ' ' + s.substring(index + 1);
                        if (val < bestval) {
                            bestval = val;
                            bestindex = index;

                        }
                    }
                }
            }
            return bestindex;
        }
    }

    public static void main(String[] args)
    {
        Scanner sc=new Scanner(System.in);
        String s="\"         \"";  //initiall state of the board
        //driver code
        while(true)
        {
            System.out.println("Input command :");
            String commmand=sc.nextLine();
            if(commmand.equalsIgnoreCase("start easy easy"))
            {
                game_easy_easy(s);
            }
            else if(commmand.equalsIgnoreCase("start easy user"))
            {
                game_easy_user(s);
            }
            else if(commmand.equalsIgnoreCase("start user user"))
            {
                game_user_user(s);
            }
            else if(commmand.equalsIgnoreCase("start user easy"))
            {
                game_user_easy(s);
            }
            else if(commmand.equalsIgnoreCase("start user medium"))
            {
                game_user_medium(s);
            }
            else if(commmand.equalsIgnoreCase("start medium user"))
            {
                game_medium_user(s);
            }
            else if(commmand.equalsIgnoreCase("start easy medium"))
            {
                game_easy_medium(s);
            }
            else if(commmand.equalsIgnoreCase("start medium medium"))
            {
                game_medium_medium(s);
            }
            else if(commmand.equalsIgnoreCase("start medium easy"))
            {
                game_medium_easy(s);
            }
            else if(commmand.equalsIgnoreCase("start user hard"))
            {
                game_user_hard(s);
            }
            else if(commmand.equalsIgnoreCase("start hard user"))
            {
                game_hard_user(s);
            }
            else if(commmand.equalsIgnoreCase("start easy hard"))
            {
                game_easy_hard(s);

            }
            else if(commmand.equalsIgnoreCase("start medium hard"))
            {
                game_medium_hard(s);

            }
            else if(commmand.equalsIgnoreCase("start hard easy"))
            {
                game_hard_easy(s);

            }
            else if(commmand.equalsIgnoreCase("start hard medium"))
            {
                game_hard_medium(s);

            }
            else if(commmand.equalsIgnoreCase("start hard hard"))
            {
                game_hard_hard(s);

            }
            else if(commmand.equalsIgnoreCase("exit"))
                break;
            else
                System.out.println("Bad parameters !");
        }

    }
}