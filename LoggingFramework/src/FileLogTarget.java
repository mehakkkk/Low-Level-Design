import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class FileLogTarget implements ILogTarget{

    String filePath = null;

    public FileLogTarget(String filePath)
    {
        this.filePath = filePath;
    }

    @Override
    public void writeLog(Message message) {
        try {
            FileOutputStream fos = new FileOutputStream(filePath);
        }
        catch(FileNotFoundException ex)
        {
            System.out.println("Oops encountered an issue while logging, please find the info below\n"+ex.getMessage());
        }

    }
}
