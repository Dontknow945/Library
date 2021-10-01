package a3;

import java.util.Scanner;

public class Library 
{
	public static class book
	{	
		int book_num;
		String book_name;
		String booktype;
		String author;
		int page;
		boolean yesno;
		int people;
			
		book(int a,String b,String c,String d,int e)
		{
			book_num=a;
			book_name=b;
			booktype=c;
			author=d;
			page=e;
			yesno=false;
			people=0;
		}
	}
	
	public static void main(String[] args)
	{
		book[]a=new book[20];					//書庫裡原書單
		a[0]=new book(82101,"Cihai","Reference","Shu Xincheng",20000);
		a[1]=new book(80001,"WW2 History","History","Winston Churchill",971);
		a[2]=new book(00003,"Egg 100","Cookbook","Su yuan ma",104);
		a[3]=new book(50001,"Be a honest man","Political","Ma Ying jeou",520);
		a[4]=new book(85719,"Sword Art Online","Novel","Reki Kawahara",8763);
		a[5]=new book(85728,"Spice and Wolf","Novel","Isuna Hasekura",510);
		a[6]=new book(85707,"The Old Man and the Sea","Novel","Ernest Hemingway",127);
		a[7]=new book(85703,"Romance of the Three Kingdoms","Novel","Luo Guanzhong",480);
		a[8]=new book(80005,"Records of the Grand Historian","History","Sima Qian",6000);
		String book;
		String bname;
		String ynres;
		String btype;
		String bauth;
		String ex;
		int bpage=0;
		int bnum=0;
		int count=9;							//書庫原書量
		int count2=0;							//借閱書量
		int count3=0;							//預約書量
		int s=0;
		book[]bor=new book[20];					//借閱書單
		book[]res=new book[20];					//預約書單
		
		while(true)
		{
			String person;
			Scanner sc = new Scanner(System.in);
				
			System.out.println("Please enter student,staff or exit.");
			person=sc.nextLine();
		
			if(person.equals("student"))							//學生介面
			{	
				String sctrl;
				while(true)
				{	
					System.out.println("Student number : 105502501");
					System.out.println("Borrowed book : ");
					for(int j=0;j<count2;j++)
					{
						System.out.println("\t"+bor[j].book_num+"\t"+bor[j].book_name);
					}
					System.out.println("Reserved book : ");
					for(int k=0;k<count3;k++)
					{
						System.out.println("\t"+res[k].book_name);
					}
					System.out.println("What do you want to do?(borrow,return,reserve,reservecancel,search or exit)");
				
					sctrl=sc.nextLine();
				
					if(sctrl.equals("borrow"))						//借書
					{
						System.out.println("bookname or booknumber?");
						book=sc.nextLine();
						if(book.equals("bookname"))
						{
							System.out.println("Enter the bookname.");
							bname=sc.nextLine();
							for(int i=0;i<count;i++)
							{
								if(bname.equals(a[i].book_name))
								{
									if(a[i].yesno==false)
									{
										System.out.println("Successful!");
										a[i].yesno=true;
										bor[count2]=a[i];
										count2++;
									}
									else if(a[i].yesno==true)
									{
										System.out.println("Failed");
										System.out.println("Do you want to reserve this book?");
										ynres=sc.nextLine();
										if(ynres.equals("yes"))
										{
											if(a[i].people==10)
											{
												System.out.println("Over 10 people reserved.");
											}
											else
											{
												System.out.println("Reserve completed.");
												a[i].people++;
												res[count3]=a[i];
												count3++;
											}
										}
										else if(ynres.equals("no"))
										{
											break;
										}
										else
										{
											System.out.println("Error!");
										}
									}
									break;
								}
								else if(i==count-1)
								{
									System.out.println("This book does not exist.");
								}
							}
						}
						else if(book.equals("booknumber"))
						{
							System.out.println("Enter the booknumber.");
							bnum=sc.nextInt();
							for(int i=0;i<count;i++)
							{
								if(bnum==a[i].book_num)
								{
									if(a[i].yesno==false)
									{
										System.out.println("Successful!");
										a[i].yesno=true;
										bor[count2]=a[i];
										count2++;
										sctrl=sc.nextLine();
									}
									else if(a[i].yesno==true)
									{
										System.out.println("Failed, this book has been borrowed.");
										System.out.println("Do you want to reserve this book?");
										ynres=sc.nextLine();
										ynres=sc.nextLine();
										if(ynres.equals("yes"))
										{
											if(a[i].people==10)
											{
												System.out.println("Over 10 people reserved.");
											}
											else
											{
												System.out.println("Reserve completed.");
												a[i].people++;
												res[count3]=a[i];
												count3++;
											}
										}
										else if(ynres.equals("no"))
										{
											break;
										}
										else
										{
											System.out.println("Error!");
										}
									}
									break;
								}
								else if(i==count-1)
								{
									System.out.println("This book does not exist.");
									sctrl=sc.nextLine();
								}
							}
						}
						else
						{
							System.out.println("Error!");
						}
					}
					else if(sctrl.equals("return"))					//還書
					{
						System.out.println("bookname or booknumber?");
						book=sc.nextLine();
						if(book.equals("bookname"))
						{
							System.out.println("Enter the bookname.");
							bname=sc.nextLine();
							for(int i=0;i<count2;i++)
							{
								if(bname.equals(bor[i].book_name))
								{
									System.out.println("Successful!");
									for(int j=i;j<count2;j++)
									{
										bor[j]=bor[j+1];
									}
									for(int k=0;k<count;k++)
									{
										if(bname.equals(a[k].book_name))
										{
											a[k].yesno=false;
											break;
										}
									}
									count2--;
									break;
								}
								else if(i==count2-1)
								{
									System.out.println("Failed, you didn't borrow this book.");
								}
							}
						}
						else if(book.equals("booknumber"))
						{
							System.out.println("Enter the booknumber.");
							bnum=sc.nextInt();
							for(int i=0;i<count2;i++)
							{
								if(bnum==bor[i].book_num)
								{
									System.out.println("Successful!");
									for(int j=i;j<count2;j++)
									{
										bor[j]=bor[j+1];
									}
									for(int k=0;k<count;k++)
									{
										if(bnum==a[k].book_num)
										{
											a[k].yesno=false;
											break;
										}
									}
									count2--;
									sctrl=sc.nextLine();
									break;
								}
								else
								{
									System.out.println("Failed, you didn't borrow this book.");
									sctrl=sc.nextLine();
								}
							}
						}
						else
						{
							System.out.println("Error!");
						}
					}
					else if(sctrl.equals("reserve"))				//預約
					{
						System.out.println("bookname or booknumber?");
						book=sc.nextLine();
						if(book.equals("bookname"))
						{
							System.out.println("Enter the bookname.");
							bname=sc.nextLine();
							for(int i=0;i<count;i++)
							{
								if(bname.equals(a[i].book_name))
								{
									if(a[i].yesno==false)
									{
										System.out.println("Failed, no one borrowed this book.");
										System.out.println("Do you want to borrow this book?");
										ynres=sc.nextLine();
										if(ynres.equals("yes"))
										{
											System.out.println("Successful.");
											a[i].yesno=true;
											bor[count2]=a[i];
											count2++;
											break;
										}
										else if(ynres.equals("no"))
										{
											break;
										}
										else
										{
											System.out.println("Error!");
											break;
										}
									}
									else if(a[i].yesno==true)
									{
										if(a[i].people==10)
										{
											System.out.println("Over 10 people reserved.");
											break;
										}
										else
										{
											System.out.println("Reserve completed.");
											a[i].people++;
											res[count3]=a[i];
											count3++;
											break;
										}
									}
								}
								else if(i==count-1)
								{
									System.out.println("This book does not exist.");
									break;
								}
							}
						}
						else if(book.equals("booknumber"))
						{
							System.out.println("Enter the booknumber.");
							bnum=sc.nextInt();
							for(int i=0;i<count;i++)
							{
								if(bnum==a[i].book_num)
								{
									if(a[i].yesno==false)
									{
										System.out.println("Failed, no one borrowed this book.");
										System.out.println("Do you want to borrow this book?");
										ynres=sc.nextLine();
										ynres=sc.nextLine();
										if(ynres.equals("yes"))
										{
											System.out.println("Successful.");
											a[i].yesno=true;
											bor[count2]=a[i];
											count2++;
											break;
										}
										else if(ynres.equals("no"))
										{
											break;
										}
										else
										{
											System.out.println("Error!");
											break;
										}
									}
									else if(a[i].yesno==true)
									{
										if(a[i].people==10)
										{
											System.out.println("Over 10 people reserved.");
											sctrl=sc.nextLine();
											break;
										}
										else
										{
											System.out.println("Reserve completed.");
											a[i].people++;
											res[count3]=a[i];
											count3++;
											sctrl=sc.nextLine();
											break;
										}
									}
								}
								else if(i==count-1)
								{
									System.out.println("This book does not exist.");
									sctrl=sc.nextLine();
									break;
								}
							}
						}
						else
						{
							System.out.println("Error!");
						}
					}
					else if(sctrl.equals("reservecancel"))			//取消預約
					{
						System.out.println("bookname or booknumber?");
						book=sc.nextLine();
						if(book.equals("bookname"))
						{
							System.out.println("Enter the bookname.");
							bname=sc.nextLine();
							for(int i=0;i<count3;i++)
							{
								if(bname.equals(res[i].book_name))
								{
									System.out.println("Successful!");
									for(int j=i;j<count2;j++)
									{
										res[j]=res[j+1];
									}
									for(int k=0;k<count;k++)
									{
										if(bname.equals(a[k].book_name))
										{
											a[k].people--;
											break;
										}
									}
									count3--;
									break;
								}
								else if(i==count3-1)
								{
									System.out.println("Failed, you didn't reserve this book.");
								}
							}
						}
						else if(book.equals("booknumber"))
						{
							System.out.println("Enter the booknumber.");
							bnum=sc.nextInt();
							for(int i=0;i<count3;i++)
							{
								if(bnum==res[i].book_num)
								{
									System.out.println("Successful!");
									for(int j=i;j<count3;j++)
									{
										res[j]=res[j+1];
									}
									for(int k=0;k<count;k++)
									{
										if(bnum==a[k].book_num)
										{
											a[k].people--;
											break;
										}
									}
									count3--;
									sctrl=sc.nextLine();
									break;
								}
								else
								{
									System.out.println("Failed, you didn't reserve this book.");
									sctrl=sc.nextLine();
								}
							}
						}
						else
						{
							System.out.println("Error!");
						}
					}
					else if(sctrl.equals("search"))					//查詢書籍
					{
						System.out.println("How to search?(bookname,booknumber,booktype or all)");
						book=sc.nextLine();
						if(book.equals("bookname"))
						{
							System.out.println("Enter the bookname.");
							bname=sc.nextLine();
							for(int i=0;i<count;i++)
							{
								if(bname.equals(a[i].book_name))
								{
									System.out.print(a[i].book_num+"\t");
									System.out.print(a[i].book_name+"\t\t");
									System.out.print(a[i].booktype+"\t\t");
									System.out.print(a[i].author+"\t");
									System.out.print(a[i].page+" pages\t");
									if(a[i].yesno==false)
									{
										System.out.print("Available\t");
									}
									else if(a[i].yesno==true)
									{
										System.out.println("Nonavailable\t");
									}
									System.out.println(a[i].people+" people reserved");
									break;
								}
								else if(i==count-1)
								{
									System.out.println("Failed, this book isn't in the list.");
									break;
								}
							}
						}
						else if(book.equals("booknumber"))
						{
							System.out.println("Enter the booknumber.");
							bnum=sc.nextInt();
							for(int i=0;i<count;i++)
							{
								if(bnum==a[i].book_num)
								{
									System.out.print(a[i].book_num+"\t");
									System.out.print(a[i].book_name+"\t\t");
									System.out.print(a[i].booktype+"\t\t");
									System.out.print(a[i].author+"\t");
									System.out.print(a[i].page+" pages\t");
									if(a[i].yesno==false)
									{
										System.out.print("Available\t");
									}
									else if(a[i].yesno==true)
									{
										System.out.println("Nonavailable\t");
									}
									System.out.println(a[i].people+" people reserved");
									sctrl=sc.nextLine();
									break;
								}
								else if(i==count-1)
								{
									System.out.println("Failed, this book isn't in the list.");
									sctrl=sc.nextLine();
									break;
								}
							}
						}
						else if(book.equals("booktype"))
						{
							System.out.println("Enter the booktype.");
							btype=sc.nextLine();
							for(int i=0;i<count;i++)
							{
								if(btype.equals(a[i].booktype))
								{
									System.out.print(a[i].book_num+"\t");
									System.out.print(a[i].book_name+"\t\t");
									System.out.print(a[i].booktype+"\t\t");
									System.out.print(a[i].author+"\t");
									System.out.print(a[i].page+" pages\t");
									if(a[i].yesno==false)
									{
										System.out.print("Available\t");
									}
									else if(a[i].yesno==true)
									{
										System.out.println("Nonavailable\t");
									}
									System.out.println(a[i].people+" people reserved");
									s=1;
								}
								else if(i==count-1&&s==0)
								{
									System.out.println("Failed, this book isn't in the list.");
									break;
								}
							}
						}
						else if(book.equals("all"))
						{
							for(int i=0;i<count;i++)
							{
								System.out.print(a[i].book_num+"\t");
								System.out.print(a[i].book_name+"\t\t");
								System.out.print(a[i].booktype+"\t\t");
								System.out.print(a[i].author+"\t");
								System.out.print(a[i].page+" pages\t");
								if(a[i].yesno==false)
								{
									System.out.print("Available\t");
								}
								else if(a[i].yesno==true)
								{
									System.out.println("Nonavailable\t");
								}
								System.out.println(a[i].people+" people reserved");
							}
						}
						else
						{
							System.out.println("Error!");
						}
					}
					else if(sctrl.equals("exit"))
					{
						break;
					}
					else
					{
						System.out.println("Error!");
					}
				}
			}
			else if(person.equals("staff"))							//管理員介面
			{
				String fctrl;
				while(true)
				{
					System.out.println("This is staff page");
					System.out.println("What do you want to do?(bookregister,bookdelete,bookedit,search,viewstudent or exit)");
				
					fctrl=sc.nextLine();
				
					if(fctrl.equals("bookregister"))				//登錄新書籍
					{
						System.out.println("Please enter booknumber:");
						bnum=sc.nextInt();
						for(int i=0;i<count;i++)
						{
							if(bnum==a[i].book_num)
							{
								System.out.println("This booknumber has been used.");
								break;
							}
							else if(i==count-1)
							{
								System.out.println("Please enter bookname:");
								bname=sc.nextLine();
								bname=sc.nextLine();
								for(int j=0;j<count;j++)
								{
									if(bname.equals(a[j].book_name))
									{
										System.out.println("This bookname has been used.");
										break;
									}
									else if(j==count-1)
									{
										System.out.println("Please enter booktype:");
										btype=sc.nextLine();
										System.out.println("Please enter the author:");
										bauth=sc.nextLine();
										System.out.println("Please enter how many pages it have:");
										bpage=sc.nextInt();
										a[count]=new book(bnum,bname,btype,bauth,bpage);
										count++;
										fctrl=sc.nextLine();
										System.out.println("Successful!");
										System.out.println("This is the new book:");
										System.out.println("\tBooknumber: "+a[count-1].book_num);
										System.out.println("\tBookname: "+a[count-1].book_name);
										System.out.println("\tBooktype: "+a[count-1].booktype);
										System.out.println("\tAuther: "+a[count-1].author);
										System.out.println("\tPages: "+a[count-1].page);
										while(true)
										{
											System.out.println("Please enter 'exit' to return.");
											ex=sc.nextLine();
											if(ex.equals("exit"))
												break;
											else
												System.out.println("Error!");
										}
										break;
									}
								}
								break;
							}
						}
					}
					else if(fctrl.equals("bookdelete"))				//刪除書籍
					{
						System.out.println("bookname or booknumber?");
						book=sc.nextLine();
						if(book.equals("bookname"))
						{
							System.out.println("Enter the bookname.");
							bname=sc.nextLine();
							for(int i=0;i<count;i++)
							{
								if(bname.equals(a[i].book_name))
								{
									System.out.println("Successful!");
									for(int j=i;j<count;j++)
									{
										a[j]=a[j+1];
									}
									count--;
									break;
								}
								else if(i==count-1)
								{
									System.out.println("Failed.");
									break;
								}
							}
						}
						else if(book.equals("booknumber"))
						{
							System.out.println("Enter the booknumber.");
							bnum=sc.nextInt();
							for(int i=0;i<count;i++)
							{
								if(bnum==a[i].book_num)
								{
									System.out.println("Successful!");
									for(int j=i;j<count;j++)
									{
										a[j]=a[j+1];
									}
									count--;
									for(int k=0;k<count;k++)
									{
										System.out.println(a[k].book_name);
									}
									fctrl=sc.nextLine();
									break;
								}
								else if(i==count-1)
								{
									System.out.println("Failed.");
									fctrl=sc.nextLine();
									break;
								}
							}
						}
						else
						{
							System.out.println("Error!");
						}
					}
					else if(fctrl.equals("bookedit"))				//更新書籍資料
					{
						System.out.println("bookname or booknumber?");
						book=sc.nextLine();
						if(book.equals("bookname"))
						{
							System.out.println("Enter the bookname.");
							bname=sc.nextLine();
							for(int i=0;i<count;i++)
							{
								if(bname.equals(a[i].book_name))
								{
									System.out.println("Enter new booknumber:");
									bnum=sc.nextInt();
									a[i].book_num=bnum;
									System.out.println("Enter new bookname:");
									bname=sc.nextLine();
									bname=sc.nextLine();
									a[i].book_name=bname;
									System.out.println("Enter new booktype:");
									btype=sc.nextLine();
									a[i].booktype=btype;
									System.out.println("Enter new author:");
									bauth=sc.nextLine();
									a[i].author=bauth;
									System.out.println("Enter new pages:");
									bpage=sc.nextInt();
									a[i].page=bpage;
									fctrl=sc.nextLine();
									break;
								}
								else if(i==count-1)
								{
									System.out.println("Failed, this book doesn't exist.");
								}
							}
						}
						else if(book.equals("booknumber"))
						{
							System.out.println("Enter the booknumber.");
							bnum=sc.nextInt();
							for(int i=0;i<count;i++)
							{
								if(bnum==a[i].book_num)
								{
									System.out.println("Enter new booknumber:");
									bnum=sc.nextInt();
									a[i].book_num=bnum;
									System.out.println("Enter new bookname:");
									bname=sc.nextLine();
									bname=sc.nextLine();
									a[i].book_name=bname;
									System.out.println("Enter new booktype:");
									btype=sc.nextLine();
									a[i].booktype=btype;
									System.out.println("Enter new author:");
									bauth=sc.nextLine();
									a[i].author=bauth;
									System.out.println("Enter new pages:");
									bpage=sc.nextInt();
									a[i].page=bpage;
									fctrl=sc.nextLine();
									break;
								}
								else if(i==count-1)
								{
									System.out.println("Failed, this book doesn't exist.");
								}
							}
						}
						else
						{
							System.out.println("Error!");
						}
					}
					else if(fctrl.equals("search"))					//查詢書單
					{
						System.out.println("How to search?(bookname,booknumber,booktype or all)");
						book=sc.nextLine();
						if(book.equals("bookname"))
						{
							System.out.println("Enter the bookname.");
							bname=sc.nextLine();
							for(int i=0;i<count;i++)
							{
								if(bname.equals(a[i].book_name))
								{
									System.out.print(a[i].book_num+"\t");
									System.out.print(a[i].book_name+"\t\t");
									System.out.print(a[i].booktype+"\t\t");
									System.out.print(a[i].author+"\t");
									System.out.print(a[i].page+" pages\t");
									if(a[i].yesno==false)
									{
										System.out.print("Available\t");
									}
									else if(a[i].yesno==true)
									{
										System.out.println("Nonavailable\t");
									}
									System.out.println(a[i].people+" people reserved");
									break;
								}
								else if(i==count-1)
								{
									System.out.println("Failed, this book isn't in the list.");
									break;
								}
							}
						}
						else if(book.equals("booknumber"))
						{
							System.out.println("Enter the booknumber.");
							bnum=sc.nextInt();
							for(int i=0;i<count;i++)
							{
								if(bnum==a[i].book_num)
								{
									System.out.print(a[i].book_num+"\t");
									System.out.print(a[i].book_name+"\t\t");
									System.out.print(a[i].booktype+"\t\t");
									System.out.print(a[i].author+"\t");
									System.out.print(a[i].page+" pages\t");
									if(a[i].yesno==false)
									{
										System.out.print("Available\t");
									}
									else if(a[i].yesno==true)
									{
										System.out.println("Nonavailable\t");
									}
									System.out.println(a[i].people+" people reserved");
									fctrl=sc.nextLine();
									break;
								}
								else if(i==count-1)
								{
									System.out.println("Failed, this book isn't in the list.");
									fctrl=sc.nextLine();
									break;
								}
							}
						}
						else if(book.equals("booktype"))
						{
							System.out.println("Enter the booktype.");
							btype=sc.nextLine();
							for(int i=0;i<count;i++)
							{
								if(btype.equals(a[i].booktype))
								{
									System.out.print(a[i].book_num+"\t");
									System.out.print(a[i].book_name+"\t\t");
									System.out.print(a[i].booktype+"\t\t");
									System.out.print(a[i].author+"\t");
									System.out.print(a[i].page+" pages\t");
									if(a[i].yesno==false)
									{
										System.out.print("Available\t");
									}
									else if(a[i].yesno==true)
									{
										System.out.println("Nonavailable\t");
									}
									System.out.println(a[i].people+" people reserved");
									s=1;
								}
								else if(i==count-1&&s==0)
								{
									System.out.println("Failed, this book isn't in the list.");
									break;
								}
							}
						}
						else if(book.equals("all"))
						{
							for(int i=0;i<count;i++)
							{
								System.out.print(a[i].book_num+"\t");
								System.out.print(a[i].book_name+"\t\t");
								System.out.print(a[i].booktype+"\t\t");
								System.out.print(a[i].author+"\t");
								System.out.print(a[i].page+" pages\t");
								if(a[i].yesno==false)
								{
									System.out.print("Available\t");
								}
								else if(a[i].yesno==true)
								{
									System.out.println("Nonavailable\t");
								}
								System.out.println(a[i].people+" people reserved");
							}
						}
						else
						{
							System.out.println("Error!");
						}
					}
					else if(fctrl.equals("viewstudent"))			//查詢學生資料
					{
						System.out.println("Student number: 105502501");
						System.out.println("Borrowed book:");
						for(int j=0;j<count2;j++)
						{
							System.out.println("\t"+bor[j].book_num+"\t"+bor[j].book_name);
						}
						System.out.println("Reserved book : ");
						for(int k=0;k<count3;k++)
						{
							System.out.println("\t"+res[k].book_num+"\t"+res[k].book_name);
						}
					}
					else if(fctrl.equals("exit"))
					{
						break;
					}
					else
					{
						System.out.println("Error!");
					}
				}
			}
			else if(person.equals("exit"))
			{
				System.out.println("Thanks for your using.");
				break;
			}
			else
			{
				System.out.println("Error!");
			}
		}
	}
}
