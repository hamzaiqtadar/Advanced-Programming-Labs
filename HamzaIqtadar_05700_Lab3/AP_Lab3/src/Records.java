
public class Records implements java.io.Serializable
{
    public String uname,notes;
    public int op;
    public Records(String U,String N, int O)
    {
        uname = U ;
        notes = N;
        op = O;
    }
    public String getUser()
    {
        return uname;
    }
    public String getNotes()
    {
        return notes;
    } 
}
