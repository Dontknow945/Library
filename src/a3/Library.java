package a3;

import java.util.Scanner;

public class Library 
{
	public static class book
	{	
		String book_num;
		String book_name;
		String booktype;
		String author;
		int page;
		boolean yesno;
		int people;
			
		book(String a,String b,String c,String d,int e)
		{
			book_num=a;		//書號
			book_name=b;	//書名
			booktype=c;		//書籍類型
			author=d;		//作者
			page=e;			//頁數
			yesno=false;	//是否有人借閱
			people=0;		//預約人數
		}
	}
	
	public static void main(String[] args)
	{
		///書庫裡原書單///
		book[]a=new book[20];
		a[0]=new book("82101","Cihai","Reference","Shu Xincheng",20000);
		a[1]=new book("80001","WW2 History","History","Winston Churchill",971);
		a[2]=new book("00003","Egg 100","Cookbook","Su yuan ma",104);
		a[3]=new book("50001","Be a honest man","Political","Ma Ying jeou",520);
		a[4]=new book("85719","Sword Art Online","Novel","Reki Kawahara",8763);
		a[5]=new book("85728","Spice and Wolf","Novel","Isuna Hasekura",510);
		a[6]=new book("85707","The Old Man and the Sea","Novel","Ernest Hemingway",127);
		a[7]=new book("85703","Romance of the Three Kingdoms","Novel","Luo Guanzhong",480);
		a[8]=new book("80005","Records of the Grand Historian","History","Sima Qian",6000);
		
		String book;
		String bname;
		String ynres;
		String btype;
		String bauth;
		String ex;
		String person;
		String bnum;
		int bpage=0;
		
		int bamount=9;							//書庫原書量
		int boramount=0;						//借閱書量
		int resamount=0;						//預約書量
		book[]bor=new book[20];					//借閱書單
		book[]res=new book[20];					//預約書單
		Scanner sc = new Scanner(System.in);
		
		library: while(true)
		{
			System.out.println("Please enter student,staff or exit.");
			person=sc.nextLine();
			
			switch (person) {
			
				/*學生介面*/
				case "student":
					String sctrl;
					student: while(true)
					{	
						System.out.println("Student number : 105502501");
						
						//列出已借閱書籍
						System.out.println("Borrowed book : ");
						for(int j=0;j<boramount;j++)
							System.out.println("\t"+bor[j].book_num+"\t"+bor[j].book_name);
						
						//列出已預約書籍
						System.out.println("Reserved book : ");
						for(int k=0;k<resamount;k++)
							System.out.println("\t"+res[k].book_num+"\t"+res[k].book_name);
						
						System.out.println("What do you want to do?(borrow,return,reserve,reservecancel,search or exit)");
						sctrl=sc.nextLine();

						switch (sctrl) {
						
							///借書///
							case "borrow":
								System.out.println("bookname or booknumber?");
								book=sc.nextLine();
								switch (book) {
									case "bookname":	//用書名查詢
										System.out.println("Enter the bookname.");
										bname=sc.nextLine();
										for(int i=0;i<bamount;i++)
										{
											if(bname.equals(a[i].book_name))
											{
												if(a[i].yesno==false)	//無人借閱 成功借書
												{
													System.out.println("Successful!");
													a[i].yesno=true;
													bor[boramount]=a[i];
													boramount++;
												}
												else if(a[i].yesno==true)	//有人借閱 詢問是否預約
												{
													System.out.println("Failed");
													System.out.println("Do you want to reserve this book?");
													ynres=sc.nextLine();
													switch (ynres) {
														case "yes":
															if(a[i].people==10)	//已有十人預約 則預約失敗
																System.out.println("Over 10 people reserved.");
															else
															{
																System.out.println("Reserve completed.");
																a[i].people++;
																res[resamount]=a[i];
																resamount++;
															}
															break;
														case "no":
															break;
														default:
															System.out.println("Error!");
															break;
													}
												}
												break;
											}
											else if(i==bamount-1)
												System.out.println("This book does not exist.");
										}
										break;
									case "booknumber":		//用書號查詢
										System.out.println("Enter the booknumber.");
										bnum=sc.nextLine();
										for(int i=0;i<bamount;i++)
										{
											if(bnum.equals(a[i].book_num))
											{
												if(a[i].yesno==false)
												{
													System.out.println("Successful!");
													a[i].yesno=true;
													bor[boramount]=a[i];
													boramount++;
												}
												else if(a[i].yesno==true)
												{
													System.out.println("Failed, this book has been borrowed.");
													System.out.println("Do you want to reserve this book?");
													ynres=sc.nextLine();
													switch (ynres) {
														case "yes":
															if(a[i].people==10)
																System.out.println("Over 10 people reserved.");
															else
															{
																System.out.println("Reserve completed.");
																a[i].people++;
																res[resamount]=a[i];
																resamount++;
															}
															break;
														case "no":
															break;
														default:
															System.out.println("Error!");
															break;
													}
												}
												break;
											}
											else if(i==bamount-1)
												System.out.println("This book does not exist.");
										}
										break;
									default:
										System.out.println("Error!");
										break;
								}
								break;
								
							///還書///
							case "return":
								System.out.println("bookname or booknumber?");
								book=sc.nextLine();
								switch (book) {
									case "bookname":
										System.out.println("Enter the bookname.");
										bname=sc.nextLine();
										for(int i=0;i<boramount;i++)
										{
											if(bname.equals(bor[i].book_name))
											{
												System.out.println("Successful!");
												for(int j=i;j<boramount;j++)
													bor[j]=bor[j+1];
												for(int k=0;k<bamount;k++)
												{
													if(bname.equals(a[k].book_name))
													{
														a[k].yesno=false;
														break;
													}
												}
												boramount--;
												break;
											}
											else if (i==boramount-1)
												System.out.println("Failed, you didn't borrow this book.");
										}
										break;
									case "booknumber":
										System.out.println("Enter the booknumber.");
										bnum=sc.nextLine();
										for(int i=0;i<boramount;i++)
										{
											if(bnum.equals(bor[i].book_num))
											{
												System.out.println("Successful!");
												for(int j=i;j<boramount;j++)
													bor[j]=bor[j+1];
												for(int k=0;k<bamount;k++)
												{
													if(bnum.equals(a[k].book_num))
													{
														a[k].yesno=false;
														break;
													}
												}
												boramount--;
												break;
											}
											else if (i==boramount-1)
												System.out.println("Failed, you didn't borrow this book.");
										}
										break;
									default:
										System.out.println("Error!");
										break;
								}
								break;
								
							///預約///
							case "reserve":
								System.out.println("bookname or booknumber?");
								book=sc.nextLine();
								switch (book) {
									case "bookname":
										System.out.println("Enter the bookname.");
										bname=sc.nextLine();
										for(int i=0;i<bamount;i++)
										{
											if(bname.equals(a[i].book_name))
											{
												if(a[i].yesno==false)
												{
													System.out.println("Failed, no one borrowed this book.");
													System.out.println("Do you want to borrow this book?");
													ynres=sc.nextLine();
													switch (ynres) {
														case "yes":
															System.out.println("Successful.");
															a[i].yesno=true;
															bor[boramount]=a[i];
															boramount++;
															break;
														case "no":
															break;
														default:
															System.out.println("Error!");
															break;
													}
												}
												else if(a[i].yesno==true)
												{
													if(a[i].people==10)
														System.out.println("Over 10 people reserved.");
													else
													{
														System.out.println("Reserve completed.");
														a[i].people++;
														res[resamount]=a[i];
														resamount++;
													}
												}
												break;
											}
											else if(i==bamount-1)
												System.out.println("This book does not exist.");
										}
										break;
									case "booknumber":
										System.out.println("Enter the booknumber.");
										bnum=sc.nextLine();
										for(int i=0;i<bamount;i++)
										{
											if(bnum.equals(a[i].book_num))
											{
												if(a[i].yesno==false)
												{
													System.out.println("Failed, no one borrowed this book.");
													System.out.println("Do you want to borrow this book?");
													ynres=sc.nextLine();
													switch (ynres) {
														case "yes":
															System.out.println("Successful.");
															a[i].yesno=true;
															bor[boramount]=a[i];
															boramount++;
															break;
														case "no":
															break;
														default:
															System.out.println("Error!");
															break;
													}
												}
												else if(a[i].yesno==true)
												{
													if(a[i].people==10)
														System.out.println("Over 10 people reserved.");
													else
													{
														System.out.println("Reserve completed.");
														a[i].people++;
														res[resamount]=a[i];
														resamount++;
													}
												}
												break;
											}
											else if(i==bamount-1)
												System.out.println("This book does not exist.");
										}
										break;
									default:
										System.out.println("Error!");
										break;
								}
								break;
								
							///取消預約///
							case "reservecancel":
								System.out.println("bookname or booknumber?");
								book=sc.nextLine();
								switch (book) {
									case "bookname":
										System.out.println("Enter the bookname.");
										bname=sc.nextLine();
										for(int i=0;i<resamount;i++)
										{
											if(bname.equals(res[i].book_name))
											{
												System.out.println("Successful!");
												for(int j=i;j<resamount;j++)
													res[j]=res[j+1];
												for(int k=0;k<bamount;k++)
												{
													if(bname.equals(a[k].book_name))
													{
														a[k].people--;
														break;
													}
												}
												resamount--;
												break;
											}
											else if(i==resamount-1)
												System.out.println("Failed, you didn't reserve this book.");
										}
										break;
									case "booknumber":
										System.out.println("Enter the booknumber.");
										bnum=sc.nextLine();
										for(int i=0;i<resamount;i++)
										{
											if(bnum.equals(res[i].book_num))
											{
												System.out.println("Successful!");
												for(int j=i;j<resamount;j++)
													res[j]=res[j+1];
												for(int k=0;k<bamount;k++)
												{
													if(bnum.equals(a[k].book_num))
													{
														a[k].people--;
														break;
													}
												}
												resamount--;
												break;
											}
											else if(i==resamount-1)
												System.out.println("Failed, you didn't reserve this book.");
										}
										break;
									default:
										System.out.println("Error!");
										break;
								}
								break;
								
							///查詢書籍///
							case "search":
								System.out.println("How to search?(bookname,booknumber,booktype or all)");
								book=sc.nextLine();
								switch (book) {
									case "bookname":
										System.out.println("Enter the bookname.");
										bname=sc.nextLine();
										for(int i=0;i<bamount;i++)
										{
											if(bname.equals(a[i].book_name))
											{
												System.out.print(a[i].book_num+"\t");
												System.out.print(a[i].book_name+"\t\t");
												System.out.print(a[i].booktype+"\t\t");
												System.out.print(a[i].author+"\t");
												System.out.print(a[i].page+" pages\t");
												if(a[i].yesno==false)
													System.out.print("Available\t");
												else if(a[i].yesno==true)
													System.out.println("Nonavailable\t");
												System.out.println(a[i].people+" people reserved");
												break;
											}
											else if(i==bamount-1)
												System.out.println("Failed, this book isn't in the list.");
										}
										break;
									case "booknumber":
										System.out.println("Enter the booknumber.");
										bnum=sc.nextLine();
										for(int i=0;i<bamount;i++)
										{
											if(bnum.equals(a[i].book_num))
											{
												System.out.print(a[i].book_num+"\t");
												System.out.print(a[i].book_name+"\t\t");
												System.out.print(a[i].booktype+"\t\t");
												System.out.print(a[i].author+"\t");
												System.out.print(a[i].page+" pages\t");
												if(a[i].yesno==false)
													System.out.print("Available\t");
												else if(a[i].yesno==true)
													System.out.println("Nonavailable\t");
												System.out.println(a[i].people+" people reserved");
												break;
											}
											else if(i==bamount-1)
												System.out.println("Failed, this book isn't in the list.");
										}
										break;
									case "booktype":
										System.out.println("Enter the booktype.");
										btype=sc.nextLine();
										for(int i=0;i<bamount;i++)
										{
											if(btype.equals(a[i].booktype))
											{
												System.out.print(a[i].book_num+"\t");
												System.out.print(a[i].book_name+"\t\t");
												System.out.print(a[i].booktype+"\t\t");
												System.out.print(a[i].author+"\t");
												System.out.print(a[i].page+" pages\t");
												if(a[i].yesno==false)
													System.out.print("Available\t");
												else if(a[i].yesno==true)
													System.out.println("Nonavailable\t");
												System.out.println(a[i].people+" people reserved");
											}
											else if(i==bamount-1)
												System.out.println("Failed, this book isn't in the list.");
										}
										break;
									case "all":
										for(int i=0;i<bamount;i++)
										{
											System.out.print(a[i].book_num+"\t");
											System.out.print(a[i].book_name+"\t\t");
											System.out.print(a[i].booktype+"\t\t");
											System.out.print(a[i].author+"\t");
											System.out.print(a[i].page+" pages\t");
											if(a[i].yesno==false)
												System.out.print("Available\t");
											else if(a[i].yesno==true)
												System.out.println("Nonavailable\t");
											System.out.println(a[i].people+" people reserved");
										}
										break;
									default:
										System.out.println("Error!");
										break;
								}
								break;
							
							///離開///
							case "exit":
								break student;
							default:
								System.out.println("Error!");
								break;
						}
					}
					break;
					
				/*管理員介面*/
				case "staff":
					String fctrl;
					staff: while(true)
					{
						System.out.println("This is staff page");
						System.out.println("What do you want to do?(bookregister,bookdelete,bookedit,search,viewstudent or exit)");
						fctrl=sc.nextLine();
					
						switch (fctrl) {
						
							///登錄新書籍///
							case "bookregister":
								System.out.println("Please enter booknumber:");
								bnum=sc.nextLine();
								register: for(int i=0;i<bamount;i++)
								{
									if(bnum.equals(a[i].book_num))
									{
										System.out.println("This booknumber has been used.");
										break;
									}
									else if(i==bamount-1)
									{
										System.out.println("Please enter bookname:");
										bname=sc.nextLine();
										for(int j=0;j<bamount;j++)
										{
											if(bname.equals(a[j].book_name))
											{
												System.out.println("This bookname has been used.");
												break;
											}
											else if(j==bamount-1)
											{
												System.out.println("Please enter booktype:");
												btype=sc.nextLine();
												System.out.println("Please enter the author:");
												bauth=sc.nextLine();
												System.out.println("Please enter how many pages it have:");
												bpage=sc.nextInt();
												a[bamount]=new book(bnum,bname,btype,bauth,bpage);
												bamount++;
												fctrl=sc.nextLine();	//為了解決輸入完整數後，無法接著輸入下一行的問題
												System.out.println("Successful!");
												System.out.println("This is the new book:");
												System.out.println("\tBooknumber: "+a[bamount-1].book_num);
												System.out.println("\tBookname: "+a[bamount-1].book_name);
												System.out.println("\tBooktype: "+a[bamount-1].booktype);
												System.out.println("\tAuther: "+a[bamount-1].author);
												System.out.println("\tPages: "+a[bamount-1].page);
												while(true)
												{
													System.out.println("Please enter 'exit' to return.");
													ex=sc.nextLine();
													if(ex.equals("exit"))
														break register;
													else
														System.out.println("Error!");
												}
											}
										}
									}
								}
								break;
								
							///刪除書籍///
							case "bookdelete":
								System.out.println("bookname or booknumber?");
								book=sc.nextLine();
								switch (book) {
									case "bookname":
										System.out.println("Enter the bookname.");
										bname=sc.nextLine();
										for(int i=0;i<bamount;i++)
										{
											if(bname.equals(a[i].book_name))
											{
												System.out.println("Successful!");
												for(int j=i;j<bamount;j++)
													a[j]=a[j+1];
												bamount--;
												break;
											}
											else if(i==bamount-1)
												System.out.println("Failed.");
										}
										break;
									case "booknumber":
										System.out.println("Enter the booknumber.");
										bnum=sc.nextLine();
										for(int i=0;i<bamount;i++)
										{
											if(bnum.equals(a[i].book_num))
											{
												System.out.println("Successful!");
												for(int j=i;j<bamount;j++)
													a[j]=a[j+1];
												bamount--;
												break;
											}
											else if(i==bamount-1)
												System.out.println("Failed.");
										}
										break;
									default:
										System.out.println("Error!");
										break;
								}
								break;
								
							///更新書籍資料///
							case "bookedit":
								System.out.println("bookname or booknumber?");
								book=sc.nextLine();
								switch (book) {
									case "bookname":
										System.out.println("Enter the bookname.");
										bname=sc.nextLine();
										for(int i=0;i<bamount;i++)
										{
											if(bname.equals(a[i].book_name))
											{
												System.out.println("Enter new booknumber:");
												bnum=sc.nextLine();
												a[i].book_num=bnum;
												System.out.println("Enter new bookname:");
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
											else if(i==bamount-1)
												System.out.println("Failed, this book doesn't exist.");
										}
										break;
									case "booknumber":
										System.out.println("Enter the booknumber.");
										bnum=sc.nextLine();
										for(int i=0;i<bamount;i++)
										{
											if(bnum.equals(a[i].book_num))
											{
												System.out.println("Enter new booknumber:");
												bnum=sc.nextLine();
												a[i].book_num=bnum;
												System.out.println("Enter new bookname:");
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
											else if(i==bamount-1)
												System.out.println("Failed, this book doesn't exist.");
										}
										break;
									default:
										System.out.println("Error!");
										break;
								}
								break;
								
							///查詢書單///
							case "search":
								System.out.println("How to search?(bookname,booknumber,booktype or all)");
								book=sc.nextLine();
								switch (book) {
									case "bookname":
										System.out.println("Enter the bookname.");
										bname=sc.nextLine();
										for(int i=0;i<bamount;i++)
										{
											if(bname.equals(a[i].book_name))
											{
												System.out.print(a[i].book_num+"\t");
												System.out.print(a[i].book_name+"\t\t");
												System.out.print(a[i].booktype+"\t\t");
												System.out.print(a[i].author+"\t");
												System.out.print(a[i].page+" pages\t");
												if(a[i].yesno==false)
													System.out.print("Available\t");
												else if(a[i].yesno==true)
													System.out.println("Nonavailable\t");
												System.out.println(a[i].people+" people reserved");
												break;
											}
											else if(i==bamount-1)
												System.out.println("Failed, this book isn't in the list.");
										}
										break;
									case "booknumber":
										System.out.println("Enter the booknumber.");
										bnum=sc.nextLine();
										for(int i=0;i<bamount;i++)
										{
											if(bnum.equals(a[i].book_num))
											{
												System.out.print(a[i].book_num+"\t");
												System.out.print(a[i].book_name+"\t\t");
												System.out.print(a[i].booktype+"\t\t");
												System.out.print(a[i].author+"\t");
												System.out.print(a[i].page+" pages\t");
												if(a[i].yesno==false)
													System.out.print("Available\t");
												else if(a[i].yesno==true)
													System.out.println("Nonavailable\t");
												System.out.println(a[i].people+" people reserved");
												break;
											}
											else if(i==bamount-1)
												System.out.println("Failed, this book isn't in the list.");
										}
										break;
									case "booktype":
										System.out.println("Enter the booktype.");
										btype=sc.nextLine();
										for(int i=0;i<bamount;i++)
										{
											if(btype.equals(a[i].booktype))
											{
												System.out.print(a[i].book_num+"\t");
												System.out.print(a[i].book_name+"\t\t");
												System.out.print(a[i].booktype+"\t\t");
												System.out.print(a[i].author+"\t");
												System.out.print(a[i].page+" pages\t");
												if(a[i].yesno==false)
													System.out.print("Available\t");
												else if(a[i].yesno==true)
													System.out.println("Nonavailable\t");
												System.out.println(a[i].people+" people reserved");
											}
											else if(i==bamount-1)
												System.out.println("Failed, this book isn't in the list.");
										}
										break;
									case "all":
										for(int i=0;i<bamount;i++)
										{
											System.out.print(a[i].book_num+"\t");
											System.out.print(a[i].book_name+"\t\t");
											System.out.print(a[i].booktype+"\t\t");
											System.out.print(a[i].author+"\t");
											System.out.print(a[i].page+" pages\t");
											if(a[i].yesno==false)
												System.out.print("Available\t");
											else if(a[i].yesno==true)
												System.out.println("Nonavailable\t");
											System.out.println(a[i].people+" people reserved");
										}
										break;
									default:
										System.out.println("Error!");
										break;
								}
								break;
								
							///查詢學生資料///
							case "viewstudent":
								System.out.println("Student number: 105502501");
								System.out.println("Borrowed book:");
								for(int j=0;j<boramount;j++)
									System.out.println("\t"+bor[j].book_num+"\t"+bor[j].book_name);
								System.out.println("Reserved book : ");
								for(int k=0;k<resamount;k++)
									System.out.println("\t"+res[k].book_num+"\t"+res[k].book_name);
								break;
								
							///離開///
							case "exit":
								break staff;
								
							default:
								System.out.println("Error!");
								break;
						}
					}
					break;
				case "exit":
					System.out.println("Thanks for your using.");
					break library;
				default:
					System.out.println("Error!");
					break;
			}
		}
		sc.close();
	}
}
