#include<stdio.h>
# include <graphics.h>
//set size arrow and balloon
# define ARROW_SIZE 7
# define BALLOON_SIZE 3
#include<conio.h>
#include<stdlib.h>
#include<windows.h>
#include<MMsystem.h>

int key,i,score=0,x,y,z=100;
void load (){
    int i;
    //initwindow(1360,710,"Lop Test");
    cleardevice();
    readimagefile("loading.jpg",0,0,1360,710);
    for(i=50;i<1300;i=i+12){
        setcolor(LIGHTBLUE);
        setfillstyle(SOLID_FILL,GREEN);
        delay(20);
        rectangle(i,666,i+10,682);
        floodfill(i+3,669,LIGHTBLUE);

        if(i<420){
            setcolor(BROWN);
            setfillstyle(SOLID_FILL,WHITE);
            circle(700,629,7);
            floodfill(705,628,BROWN);
        }
        else if(i>650){
            setcolor(BROWN);
            setfillstyle(SOLID_FILL,WHITE);
            circle(735,629,7);
            floodfill(740,628,BROWN);
        }
        if(i>1280){
            setcolor(BROWN);
            setfillstyle(SOLID_FILL,WHITE);
            circle(770,629,7);
            floodfill(775,628,BROWN);
        }
    }
    PlaySound(TEXT("startgame.wav"),NULL,SND_SYNC);
}
void draw_balloon(int x,int y)
{
    setcolor(RED);
    setfillstyle(1,RED);
    fillellipse(x,y,6*BALLOON_SIZE,6.5*BALLOON_SIZE);
    line(x,y+6*BALLOON_SIZE,x,y+7*BALLOON_SIZE);
}
void draw_balloon_break(int x,int y)
{
    setcolor(BLACK);
    setfillstyle(1,BLACK);
    fillellipse(x,y,5*BALLOON_SIZE,6*BALLOON_SIZE) ;
    line(x,y+6*BALLOON_SIZE,x,y+7*BALLOON_SIZE);
    delay(80);
    PlaySound(TEXT("Fail.wav"),NULL,SND_SYNC);
    setfillstyle(1,WHITE);
    fillellipse(x,y,5*BALLOON_SIZE,6*BALLOON_SIZE) ;
    line(x,y+6*BALLOON_SIZE,x,y+7*BALLOON_SIZE);
}

void randomballoon(int *x,int*y){
    int ranx,rany;
    ranx= (rand()%700 +1);
    rany= (rand()%700 +1);
    if(ranx<500){
        ranx=ranx+500;
    }
    if(rany<50){
        rany=rany+50;
    }
    *x=ranx;
    *y=rany;
}
void draw_arrow(int *x, int *y)
{
    setlinestyle(0,0,2);
    setcolor(BLUE);
    moveto(*x, *y);
    linerel(15*ARROW_SIZE, 0);
    linerel(-2*ARROW_SIZE, -1*ARROW_SIZE+1);
    linerel(0, 2*ARROW_SIZE-1);
    linerel(2*ARROW_SIZE, -1*ARROW_SIZE);
}
void draw_archer(int x,int y)
{
    setcolor(BLACK);
    setlinestyle(0,0,1);
    line(x+32,y-49,x,y);
    line(x+32,y+49,x,y);
    setlinestyle(0,0,3);
    arc(x,y,300,60,60);
    arc(x+34,y-56,100,220,6);
    arc(x+34,y+56,120,240,6);
}
void gameover()
{
    readimagefile("hello.jpg",0,0,1360,710);
    settextstyle(10,0,1);
    setusercharsize(50,80,40,80);
    setbkcolor(WHITE);
    setcolor(RED);
    outtextxy(20,600,"OUT OFF ARROWS");
    //outtextxy(620,320,"GAME OVER");
    outtextxy(20,620,"PRESS 1 TO PLAY AGAIN...");
    PlaySound(TEXT("GameOver.wav"),NULL,SND_SYNC);
}
void playgame();
void help()
{
    readimagefile("help.jpg",0,100,850,500);
    settextstyle(10,0,3);
    setbkcolor(WHITE);
    setcolor(BLUE);
    outtextxy(40,200,"Press Arrow Key 'Up' and 'Down' to scroll Arrow and Bow");
    setcolor(BLUE);
    outtextxy(40,230,"Press Enter to hit Balloon");
    setcolor(RED);
    outtextxy(40,260,"5 points per balloon hit");
    settextstyle(10,0,3);
    setcolor(BLACK);
    outtextxy(40,290,"For More Information Contact:Group3@gamer.com");
    setcolor(BLACK);
    outtextxy(300,450,"Press 1 to Main Menu");
    settextstyle(10,0,3);
}
void draw_arrow_menu(int *x, int *y)
{
    setlinestyle(0,0,2);
    setcolor(WHITE);
    moveto(*x,*y);
    linerel(8*ARROW_SIZE, 0);
    linerel(-2*ARROW_SIZE,-1*ARROW_SIZE+1);
    linerel(0,2*ARROW_SIZE-1);
    linerel(2*ARROW_SIZE,-1*ARROW_SIZE);
}
void press()
{
    readimagefile("loading1.jpg",0,0,1360,710);
}
void loading()
{
    readimagefile("starting.jpg",0,0,1360,710);
    for(i=302;i<1075;i=i+12){
        setcolor(LIGHTBLUE);
        setfillstyle(SOLID_FILL,GREEN);
        delay(30);
        rectangle(i,666,i+10,682);
        floodfill(i+3,669,LIGHTBLUE);
    }
}
void congratulation()
{
   setbkcolor(WHITE);
    setcolor(BLACK);
    outtextxy(590,250,"CONGRATULATION!!!");
     outtextxy(640,300,"YOU WIN");
    setcolor(BLACK);
    outtextxy(20,620,"PRESS 1 TO MAIN MENU...");
}
int main(){
    int n=1,j,k;
    initwindow(1360,710,"THE ARROW");
    cleardevice();
    int x=760,y=245;
    load();
    for(j=1;j!=0;j++){
        //setbkcolor(BLACK);
        draw_arrow_menu(&x,&y);
        readimagefile("loading1.jpg",0,0,1360,710);
        draw_arrow_menu(&x,&y);
        for(i=1;i>0;i++){
            key=getch();
            if(key==13 && y==245){
            PlaySound(TEXT("Click.wav"),NULL,SND_SYNC);
            loading();
            playgame();
            n=1;
            break;
            }
            else if(key == 80){
               PlaySound(TEXT("Click.wav"),NULL,SND_SYNC);
            if(y<440){
                y=y+100;
                readimagefile("loading1.jpg",0,0,1360,710);
                press();
                draw_arrow_menu(&x,&y);
                key = getch();
                if (key == 13 && y == 245){
                    PlaySound(TEXT("select.wav"),NULL,SND_SYNC);
                    loading();
                    playgame();
                    n=1;
                    break;
                }
                else if (key == 13 && y == 345){
                      PlaySound(TEXT("select.wav"),NULL,SND_SYNC);
                    return 0;
                }
                else if (key == 13 && y == 445){
                      PlaySound(TEXT("select.wav"),NULL,SND_SYNC);
                    help();
                    n=1;
                    for(k=1;k>0;k++){
                        key=getch();
                        if(key==49){
                            break;
                        }
                    }
                break;}
            }
        }
        else if(key == 72){
                          PlaySound(TEXT("Click.wav"),NULL,SND_SYNC);
            if(y>245){
                readimagefile("loading1.jpg",0,0,1360,710);
                y=y-100;
                press();
                draw_arrow_menu(&x,&y);
                key = getch();
                if (key == 13 && y == 245){
                    PlaySound(TEXT("select.wav"),NULL,SND_SYNC);
                    loading();
                    playgame();
                    n=1;
                    break;
                }
                else if (key == 13 && y == 345){
                    PlaySound(TEXT("select.wav"),NULL,SND_SYNC);
                    return 0;
                }
                else if (key == 13 && y == 445){
                    PlaySound(TEXT("select.wav"),NULL,SND_SYNC);
                    //loading();
                    help();
                    n=1;
                    for(k=1;k>0;k++){
                        key=getch();
                        if(key==49){
                            break;
                        }
                    }
                    break;
                }
            }

        }
    }
    if(n==1){continue;}
    else{break;}
    }
    closegraph();
    return 0;
}
void playgame()
{
    int num=0;
    cleardevice();
    int x=100,y=100,ballx=850,bally=500,num_of_arrow=5,level=1;
    char arr[30];
    readimagefile("1.jpg",0,0,1360,710);
    settextstyle(8,0,1);
    setusercharsize(50,80,50,80);
    setcolor(WHITE);
    draw_arrow(&x,&y);
    draw_balloon(ballx,bally);
    draw_archer(100,100);

    for(i=1;i>0;i++){
        int x1=0;
        setbkcolor(YELLOW);
        setcolor(BLACK);
        sprintf(arr, "SCORE: %d", score);
        outtextxy(1193,60,arr);
        setcolor(BLACK);
        sprintf(arr,"LEVEL: %d",level);
        outtextxy(1193,105,arr);
        setcolor(BLACK);
        sprintf(arr,"ARROW: %d",num_of_arrow);
        outtextxy(1210,150,arr);
        key=getch();
        if(key==80 && level==1){
            //cleardevice();
            if(y<700){
            readimagefile("1.jpg",0,0,1360,710);
            y=y+20;
            draw_archer(x,y);
            draw_arrow(&x,&y);
            }
        }
        else if(key==80 && level==2){
        if(y<700){
            readimagefile("2.jpg",0,0,1360,710);
            y=y+20;
            draw_archer(x,y);
            draw_arrow(&x,&y);
        }}
        else if(key==80 && level==3){
        if(y<700){
            readimagefile("3.jpg",0,0,1360,710);
            y=y+20;
            draw_archer(x,y);
            draw_arrow(&x,&y);
        }}
        else if(key==80 && level==4){
        if(y<700){
            readimagefile("4.jpg",0,0,1360,710);
            y=y+20;
            draw_archer(x,y);
            draw_arrow(&x,&y);
        }}
        else if(key==80 && level==5){
            if(y<700){
            readimagefile("5.jpg",0,0,1360,710);
            y=y+20;
            draw_archer(x,y);
            draw_arrow(&x,&y);
        }}

        else if(key==72 && level==1){
        if(y>10){
            //cleardevice();
            readimagefile("1.jpg",0,0,1360,710);
            y=y-20;
            draw_archer(x,y);
            draw_arrow(&x,&y);
        }}
        else if(key==72 && level==2){
        if(y>10){
            //cleardevice();
            readimagefile("2.jpg",0,0,1360,710);
            y=y-20;
            draw_archer(x,y);
            draw_arrow(&x,&y);
        }}
        else if(key==72 && level==3){
        if(y>10){
            //cleardevice();
            readimagefile("3.jpg",0,0,1360,710);
            y=y-20;
            draw_archer(x,y);
            draw_arrow(&x,&y);
        }}
        else if(key==72 && level==4){
        if(y>10){
            //cleardevice();
            readimagefile("4.jpg",0,0,1360,710);
            y=y-20;
            draw_archer(x,y);
            draw_arrow(&x,&y);
        }}
        else if(key==72 && level==5){
            if(y>10){
            //cleardevice();
            readimagefile("5.jpg",0,0,1360,710);
            y=y-20;
            draw_archer(x,y);
            draw_arrow(&x,&y);
        }}
    else if (key==13 && num_of_arrow>0 && level==1){
        for(i=x;i<1300;i=i+200){
            if (i >= ballx-100 && y >=bally-25 && y <= bally+23){
                score=score+5;
                num=num+1;
                if(num>0){
                    level++;
                    num=0;
                }
                readimagefile("1.jpg",0,0,1360,710);
                draw_arrow(&i,&y);
                PlaySound(TEXT("Fail.wav"),NULL,SND_SYNC);
                draw_balloon_break(ballx,bally);
                x1=1;
                randomballoon(&ballx,&bally);
                num_of_arrow++;
            break;
            }
            readimagefile("1.jpg",0,0,1360,710);
            draw_arrow(&i,&y);
            draw_balloon(ballx,bally);
            draw_arrow(&x,&y);
            draw_archer(x,y);
        }
        readimagefile("1.jpg",0,0,1360,710);
        draw_arrow(&x,&y);
        draw_archer(x,y);
        num_of_arrow--;
    }
    else if (key==13 && num_of_arrow>0 && level==2){
        for(i=x;i<1300;i=i+200){
            if (i >= ballx-100 && y >=bally-25 && y <= bally+23){
                score=score+5;
                num++;
                if(num>0){
                    level++;
                    num=0;
                }
                readimagefile("2.jpg",0,0,1360,710);\
                draw_arrow(&i,&y);
                PlaySound(TEXT("Fail.wav"),NULL,SND_SYNC);
                draw_balloon_break(ballx,bally);
                x1=1;
                randomballoon(&ballx,&bally);
                num_of_arrow++;
                break;
            }
            readimagefile("2.jpg",0,0,1360,710);
            draw_arrow(&i,&y);
            draw_balloon(ballx,bally);
            draw_arrow(&x,&y);
            draw_archer(x,y);
        }
        readimagefile("2.jpg",0,0,1360,710);
        draw_arrow(&x,&y);
        draw_archer(x,y);
        num_of_arrow--;
    }
    else if (key==13 && num_of_arrow>0 && level==3){
        for(i=x;i<1300;i=i+200){
            if (i >= ballx-100 && y >=bally-25 && y <= bally+23){
                score=score+5;
                num++;
                if(num>0){
                    level++;
                    num=0;
                }
                readimagefile("3.jpg",0,0,1360,710);
                draw_arrow(&i,&y);
                PlaySound(TEXT("Fail.wav"),NULL,SND_SYNC);
                draw_balloon_break(ballx,bally);
                x1=1;
                randomballoon(&ballx,&bally);
                num_of_arrow++;
                break;
            }
            readimagefile("3.jpg",0,0,1360,710);
            draw_arrow(&i,&y);
            draw_balloon(ballx,bally);
            draw_arrow(&x,&y);
            draw_archer(x,y);
        }
        readimagefile("3.jpg",0,0,1360,710);
        draw_arrow(&x,&y);
        draw_archer(x,y);
        num_of_arrow--;
    }
    else if (key==13 && num_of_arrow>0 && level==4){
        for(i=x;i<1300;i=i+200){
            if (i >= ballx-100 && y >=bally-25 && y <= bally+23){
                score=score+5;
                num++;
                if(num>0){
                    level++;
                    num=0;
                }
                readimagefile("4.jpg",0,0,1360,710);
                draw_arrow(&i,&y);
                PlaySound(TEXT("Fail.wav"),NULL,SND_SYNC);
                draw_balloon_break(ballx,bally);
                x1=1;
                randomballoon(&ballx,&bally);
                num_of_arrow++;
                break;
            }
            readimagefile("4.jpg",0,0,1360,710);
            draw_arrow(&i,&y);
            draw_balloon(ballx,bally);
            draw_arrow(&x,&y);
            draw_archer(x,y);
        }
        readimagefile("4.jpg",0,0,1360,710);
        draw_arrow(&x,&y);
        draw_archer(x,y);
        num_of_arrow--;
    }
    else if (key==13 && num_of_arrow>0 && level==5){
        for(i=x;i<1300;i=i+200){
            if (i >= ballx-100 && y >=bally-25 && y <= bally+23){
                score=score+5;
                num++;
                if(num>0){

                }
                readimagefile("5.jpg",0,0,1360,710);
                draw_arrow(&i,&y);
                PlaySound(TEXT("Fail.wav"),NULL,SND_SYNC);
                draw_balloon_break(ballx,bally);
                x1=1;
                randomballoon(&ballx,&bally);
                num_of_arrow++;
            break;
            }
            readimagefile("5.jpg",0,0,1360,710);
            draw_arrow(&i,&y);
            draw_balloon(ballx,bally);
            draw_arrow(&x,&y);
            draw_archer(x,y);
        }
        readimagefile("5.jpg",0,0,1360,710);
        draw_arrow(&x,&y);
        draw_archer(x,y);
        num_of_arrow--;
        //congratulation();
//        setbkcolor(BLACK);
//        setcolor(WHITE);
//        outtextxy(590,250,"CONGRATULATION!!!");
//        outtextxy(620,320,"YOU WIN");
//        outtextxy(20,620,"PRESS ANY KEY TO MAIN MANU.");
        getch();
        return;
    }
        else if (num_of_arrow==0 && level==1){
            readimagefile("1.jpg",0,0,1360,710);
            draw_arrow(&x,&y);
            draw_archer(x,y);

            if(key==49){
            return;
            }
            else{
            }
        gameover();}
        else if (num_of_arrow==0 && level==2){
            readimagefile("2.jpg",0,0,1360,710);
            draw_arrow(&x,&y);
            draw_archer(x,y);

            if(key==49){
                return;
            }
            else{
            }
        gameover();}
        else if (num_of_arrow==0 && level==3){
            readimagefile("3.jpg",0,0,1360,710);
            draw_arrow(&x,&y);
            draw_archer(x,y);

            if(key==49){
                return;
            }
            else{
            }
        gameover();
        }

        else if (num_of_arrow==0 && level==4){
            readimagefile("4.jpg",0,0,1360,710);
            draw_arrow(&x,&y);
            draw_archer(x,y);

            if(key==49){
                return;
            }
            else{
            }
        gameover();
        }
        else if ((num_of_arrow==0 && level==5)||(num_of_arrow!=0 && level==5)){
            readimagefile("5.jpg",0,0,1360,710);
            draw_arrow(&x,&y);
            draw_archer(x,y);
            if((score==25 && num_of_arrow==0)||(score==25 && num_of_arrow !=0)){
                readimagefile("5.jpg",0,0,1360,710);
                settextstyle(10,0,1);
                setusercharsize(50,80,40,80);
                draw_arrow(&x,&y);
                draw_archer(x,y);

                congratulation();
        }}
        else if(score<25 && num_of_arrow==0){
            readimagefile("5.jpg",0,0,1360,710);
            draw_arrow(&x,&y);
            draw_archer(x,y);

            if(key==49){
                return;
            }
            else{
            }
        gameover();}
        draw_balloon(ballx,bally);
    }

    getch();
    closegraph();
}


