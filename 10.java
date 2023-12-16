import java.io.*;
class Thread1
{
int n;
boolean valueset = false;
synchronized int get()
{
if(!valueset)
try
{
wait();
}
catch(Exception e)
{
System.out.println("Exception occur at : "+e);
}
System.out.println("GET:"+n);
try
{
Thread.sleep(0);
}
catch(Exception e)
{
System.out.println("Exception occur at : "+e);
}
valueset=false;
notify();
return n;
}
synchronized int put(int n)
{
if(valueset)
{
try
{
wait();
}
catch(Exception e)
{
System.out.println("Exception occur at : "+e);
}
}
this.n=n;
valueset=true;
System.out.println("PUT:"+n);
try
{
Thread.sleep(0);
}
catch(Exception e)
{
System.out.println("Exception occur at : "+e);
}
notify();
return n;
