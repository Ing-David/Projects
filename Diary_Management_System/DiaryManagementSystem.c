/*  I3-GIC ( Group : 4 )
    Mini Project : Diary management system
    Member :    1. Be David  (Group : A)
                2. Ing David (Group : A)
                3. Keat Chanmony (Group : A)
                4. Sopheap Sopheadavid (Group : B)
                5. Sorn Dane (Group : B)
                6. Vong Pheaktrey (Group : B)
*/
#include <stdio.h>
#include <conio.h>
#include <stdlib.h>
#include <string.h>

void creatediary();
void viewdiary();
void modifieddiary();
void deletediary();

struct diary {

    char time[20];

    char place[20];

    char title[20];

    char detail[1000];

};

main (){
    int ch;
    in:

    printf("\t\t\t\t***************************");
    printf("\n\t\t\t\t* DIARY MANAGEMENT SYSTEM *");
    printf("\n\t\t\t\t***************************");

    printf("\n\n 1. CREATE DIARY");
    printf("\n 2. VIEW DIARY");
    printf("\n 3. MODIFIED DIARY");
    printf("\n 4. DELETE DIARY");
    printf("\n\nENTER YOUR CHOICE [1-4] :");
    scanf("%d", &ch);

    switch (ch)
    {
        case 1:

            creatediary();
            break;

        case 2:

            viewdiary();
            break;

        case 3:

            modifieddiary();
            break;

        case 4:

            deletediary();
            break;

        default :

            printf("YOUR CHOICE ARE WRONG...");
            printf("\nENTER YOUR CHOICE AGAIN BRO :)");
            getch();
            break;
    }
    system("cls");
    goto in;
}
void creatediary()
{
    system("cls");
    FILE *fp ;

    char another='Y';
    char filename[20], time[20];
    struct diary c;
    int choice;
    in:

    printf("\n\n\t\t******************************\n");
    printf("\t\t* WELCOME TO THE CREATE MENU *");
    printf("\n\t\t******************************\n\n");
    printf("\nEnter your [ddd-mmm-yyy] : ");
    fflush(stdin);
    gets(filename);

    fp = fopen(filename,"ab+");

    if (fp == NULL )
    {
        fp = fopen(filename,"wb+");

        if (fp == NULL)
        {
            printf("\nSYSTEM ERROR...");
            printf("\n\nTRY AGAIN...");
            getch();
            system("cls");
            goto in;
        }
    }

        while (another == 'Y' || another == 'y')
        {
            choice =0;
            printf("Enter time [hh:mm] :");
            fflush(stdin);
            gets(time);
            rewind(fp);
            while(fread(&c,sizeof(c),1,fp)==1)
            {
                if ( strcmp(c.time,time) == 0)
                {
                    printf("THE CREATE ARE EXIST.");
                    choice=1;
                }
            }
            if ( choice == 0)
            {
                strcpy(c.time,time);
                printf("Enter place :");
                fflush(stdin);
                gets(c.place);

                printf("Enter title :");
                fflush(stdin);
                gets(c.title);

                printf("Enter detail :");
                fflush(stdin);
                gets(c.detail);

                fwrite( &c, sizeof(c), 1, fp );

                printf("\n\nYOUR RECORD IS CREATED...");
            }

            printf("\nDO YOU WANT TO ADD ANOTHER ? (Y/N)\n");
            fflush(stdin);
            scanf("%c", &another);
        }
        fclose(fp);
        printf("\n\nPRESS ANY KEY TO EXIT...");
        getch();
    }

void viewdiary()
{
    system("cls");
    FILE *fpte;
    struct diary v;
    char choice, time[20], filename[20];
    int ch;
    printf("\n\n\t\t****************************\n");
    printf("\t\t* HERE IS THE VIEWING MENU *");
    printf("\n\t\t****************************\n\n");

    do
    {
        printf("\nENTER THE DATE OF CREATE TO BE VIWED [ddd-mmm-yyy] : ");
        fflush(stdin);
        gets(filename);

        fpte = fopen(filename,"rb+");
        if (fpte == NULL)
        {
            printf("\nTHE INPUT DOES NOT EXIST...");
            printf("\n\nPRESS ANY KEY TO EXIT...");
            getch();
            system("cls");
            return ;
        }
        while (fread(&v,sizeof(v),1,fpte)==1)
            {       printf("\n");
                    printf("\nTime: %s",v.time);
                    printf("\nPlace: %s",v.place);
                    printf("\nTitle: %s",v.title);
                    printf("\nDetail: %s",v.detail);
            }
        printf("\n\n");
        printf("\nDO YOU WANT TO CONTINUE VIEWING... (Y/N)");
        fflush(stdin);
        scanf("%c", &choice);
    }while(choice =='Y' || choice == 'y');
    fclose(fpte);
}
void modifieddiary()
{
    system("cls");

    FILE *fpte;

    struct diary customer ;

    char time[10], choice , filename[15];

    int num, count=0;

    printf("\n\n\t\t********************************\n");

    printf("\t\t* WELCOME TO THE MODIFIED MENU *");

    printf("\n\t\t********************************\n\n");

    do

    {

        printf("\nENTER THE DATE OF RECORD TO BE EDITED [ddd-mmm-yyy]:");
        fflush(stdin);
        gets(filename);
        printf("\nENTER TIME:[hh:mm]:");
        gets(time);
        fpte = fopen ( filename, "rb+" ) ;

        if ( fpte == NULL )
        {
            printf( "\nRECORD DOES NOT EXISTS..." ) ;
            printf("\nPRESS ANY KEY TO GO BACK...");
            getch();
            return ;
        }

        while ( fread ( &customer, sizeof ( customer ), 1, fpte ) == 1 )
        {
            if(strcmp(customer.time,time)==0)
            {
                printf("\nYOUR OLD RECORD WAS AS:");
                printf("\nTIME: %s",customer.time);
                printf("\nPLACE: %s",customer.place);
                printf("\nTITLE: %s",customer.title);
                printf("\nDETAIL: %s",customer.detail);

                printf("\n\n\t\tWHAT WOULD YOU LIKE TO EDIT..");
                printf("\n1.TIME.");
                printf("\n2.PLACE.");
                printf("\n3.TITLE.");
                printf("\n4.DETAIL.");
                printf("\n5.WHOLE RECORD.");
                printf("\n6.GO BACK TO MAIN MENU.");

                        do
                        {

                            printf("\n\tENTER YOUR CHOICE:");
                            fflush(stdin);
                            scanf("%d",&num);
                            fflush(stdin);
                            switch(num)
                            {
                                    case 1: printf("\nENTER THE NEW DATA:");
                                            printf("\nNEW TIME:[hh:mm]:");
                                            gets(customer.time);
                                            break;
                                    case 2: printf("\nENTER THE NEW DATA:");
                                            printf("\nNEW PLACE:");
                                            gets(customer.place);
                                            break;
                                    case 3: printf("\nENTER THE NEW DATA:");
                                            printf("\nNEW TITLE:");
                                            gets(customer.title);
                                            break;
                                    case 4: printf("\nENTER THE NEW DATA:");
                                            printf("\nDETAIL:");
                                            gets(customer.detail);
                                            break;
                                    case 5: printf("\nENTER THE NEW DATA:");
                                            printf("\nNEW TIME:[hh:mm]:");
                                            gets(customer.time);
                                            printf("\nNEW PLACE:");
                                            gets(customer.place);
                                            printf("\nNEW TITLE:");
                                            gets(customer.title);
                                            printf("\nNEW DETAIL:");
                                            gets(customer.detail);
                                            break;
                                    case 6: printf("\nPRESS ANY KEY TO GO BACK...\n");
                                            getch();
                                            return ;
                                            break;
                                    default: printf("\nYOU TYPED SOMETHING ELSE...TRY AGAIN\n");
                                            break;

                            }


                        }while(num<1||num>8);

                        fseek(fpte,-sizeof(customer),SEEK_CUR);
                        fwrite(&customer,sizeof(customer),1,fpte);
                        fseek(fpte,-sizeof(customer),SEEK_CUR);
                        fread(&customer,sizeof(customer),1,fpte);
                        choice=5;
                        break;

                }

            }

            if(choice==5)
            {
                system("cls");
                printf("\n\t\tEDITING COMPLETED...\n");
                printf("--------------------\n");
                printf("THE NEW RECORD IS:\n");
                printf("--------------------\n");
                printf("\nTIME: %s",customer.time);
                printf("\nPLACE: %s",customer.place);
                printf("\nTITLE: %s",customer.title);
                printf("\nDETAIL: %s",customer.detail);

                fclose(fpte);

                printf("\n\n\tWOULD YOU LIKE TO EDIT ANOTHER RECORD.(Y/N)");
                scanf("%c",&choice);
                count++;

            }
            else
            {
                printf("\nTHE RECORD DOES NOT EXIST::\n");
                printf("\nWOULD YOU LIKE TO TRY AGAIN...(Y/N)");
                scanf("%c",&choice);
            }


    }while(choice=='Y'||choice=='y');
    fclose ( fpte );
    if(count==1)
        printf("\n%d FILE IS EDITED...\n",count);

    else if(count>1)
        printf("\n%d FILES ARE EDITED..\n",count);

    else
        printf("\nNO FILES EDITED...\n");
        printf("\tPRESS ENTER TO EXIT EDITING MENU.");
        getch();

}
void deletediary()
{
    system("cls");
    FILE *fp;
    struct diary file;
    char filename[20], another='Y';

    printf("\n\n\t\t**************************\n");
    printf("\t\t* WELCOME TO DELETE MENU *");
    printf("\n\t\t**************************\n\n");

    while (another=='Y' || another=='y')
    {
        printf("\nENTER THE [ddd-mmm-yyy] TO BE DELETE:");
        fflush(stdin);
        gets(filename);

        fp = fopen (filename, "rb" ) ;

        if(!fp )
        {
            printf("\nTHE FILE DOES NOT EXISTS");
            printf("\nPRESS ANY KEY TO GO BACK.");
            getch();
            return ;

        }
        fclose(fp);
        remove(filename);
        printf("\nDELETED SUCCESFULLY...");

        printf("\n\tDO YOU LIKE TO DELETE ANOTHER RECORD.(Y/N):");
        fflush(stdin);
        scanf("%c",&another);
    }
    printf("\n\n\tPRESS ANY KEY TO EXIT...");
    getch();
}

