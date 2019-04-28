package ClinicSoftware;
import java.io.*;
import com.opencsv.*;
public class PrescriptionFile extends ClinicFile
{
    private String prescriptionHeader[]={"Medicine Name","Instruction"};
    private String prescriptionFolder="Prescriptions/";
    private String fileName;

    PrescriptionFile(String fileName)
    {
        this.fileName=fileName;
    }

    PrescriptionFile(Prescription prescription)
    {
        fileName=prescription.getFileName();
        if(!isFilePresent(dir,prescriptionFolder,fileName))
        {
            System.out.println(createFileWithMessage(prescription));
        }
    }

    String getDirectory()
    {
        return dir+prescriptionFolder+fileName;
    }

    String getFileName()
    {
        return fileName;
    }

    String createFileWithMessage(Prescription prescription)
    {
        Exception e;
        if((e=createFile(prescription))==null)
            return "File Created Successfully!";
        else
           return e.getMessage();
    }

    Exception createFile(Prescription prescription)
    {
        try {
            FileWriter fw = new FileWriter(dir + prescriptionFolder + prescription.getPatientName() + " " + prescription.getDate() + ".csv");
            CSVWriter writer = new CSVWriter(fw);
            writer.writeNext(prescriptionHeader);
            for (int i = 0; i < prescription.getMedicines().size(); i++) {
                String temp[] = {prescription.getMedicines().get(i), prescription.getInstruction().get(i)};
                writer.writeNext(temp);
            }
            writer.close();
            fw.close();
        }
        catch(Exception e)
        {
            return e;
        }
        return null;
    }

    Prescription readFile(String patientName,String date)throws IOException
    {
        FileReader fr=new FileReader(dir+prescriptionFolder+patientName+" "+date+".csv");
        CSVReader reader=new CSVReader(fr);
        reader.readNext();
        Prescription pre=new Prescription();
        pre.setPatientName(patientName);
        pre.setDate(date);
        String temp[];
        while((temp=reader.readNext())!=null)
        {
            pre.addMedicine(temp[0]);
            pre.addInstruction(temp[1]);
        }
        return pre;
    }

    Prescription readFile(String fileName)throws IOException
    {
        if(!fileName.equals(" "))
            return readFile(fileName.split(" ")[0],fileName.split(" ")[1]);
        else
            return null;
    }

    boolean deleteFile(String fileName) {
        File file = new File(dir + prescriptionFolder + fileName + ".csv");
        return file.delete();
    }

    boolean deleteFile(String patientName,String date)
    {
        String fileName=patientName+" "+date;
        return deleteFile(fileName);
    }

    Exception deleteEntry(String fileName,int index)throws IOException
    {
        try {
            FileReader fr = new FileReader(dir + prescriptionFolder + fileName + ".csv");
            CSVReader reader = new CSVReader(fr);
            FileWriter fw = new FileWriter(dir + prescriptionFolder + "temp.csv");
            CSVWriter writer = new CSVWriter(fw);
            int ctr = 0;
            String temp2[];
            while ((temp2 = reader.readNext()) != null) {
                if (index != ctr) {
                    writer.writeNext(temp2);
                }
                ctr++;
            }
            boolean flag = true;
            reader.close();
            fr.close();
            writer.close();
            fw.close();
            File file = new File(dir + prescriptionFolder + fileName + ".csv");
            File newFile = new File(dir + prescriptionFolder + "temp.csv");
            deleteFile(fileName);
            newFile.renameTo(file);
        }
        catch(Exception e) {
            return e;
        }
        return null;
    }

    Exception deleteEntry(String patientName,String date,int index)throws IOException
    {
        String fileName=patientName+" "+date;
        return deleteEntry(fileName,index);
    }

    Exception addEntry(String fileName,String medicine,String instruction,int predecessorIndex)throws IOException
    {
        boolean flag =true;
        try
        {
            FileWriter fw = new FileWriter(dir+prescriptionFolder+"temp.csv");
            CSVWriter writer = new CSVWriter(fw);
            FileReader fr=new FileReader(dir+prescriptionFolder+fileName+".csv");
            CSVReader reader=new CSVReader(fr);
            String temp[];
            int ctr=0;
            writer.writeNext(reader.readNext());
            String medicinesArr[] = {medicine, instruction};
            while(true)
            {
                temp=reader.readNext();
                if(ctr==predecessorIndex) {
                    writer.writeNext(medicinesArr);
                    ctr++;
                    writer.writeNext(temp);
                }
                else
                {
                    writer.writeNext(temp);
                    ctr++;
                }
                if(temp==null) {
                    if(ctr<=predecessorIndex)
                        writer.writeNext(medicinesArr);
                    break;
                }
            }
            writer.close();
            fw.close();
            reader.close();
            fr.close();
            File file=new File(dir+prescriptionFolder+fileName+".csv");
            if(!file.delete())
                flag=false;
            File temp2=new File(dir+prescriptionFolder+"temp.csv");
            temp2.renameTo(file);
        }
        catch(Exception e)
        {
           return e;
        }
        return null;
    }

    Exception addEntry(String patientName,String date,String medicine,String instruction,int index)throws IOException
    {
        String fileName=patientName+" "+date;
        return addEntry(fileName,medicine,instruction,index);
    }

    Exception editEntry(String fileName,String medDel,String medEnter,String instructionEnter)throws IOException
    {
        Exception exception;
        int index=getIndex(fileName,medDel);
        if(!((exception=deleteEntry(fileName,index))==null))
            return exception;
        if(!((exception=addEntry(fileName,medEnter,instructionEnter,index))==null))
            return exception;
        return null;
    }

    int getIndex(String fileName,String medicine)throws IOException
    {
        int ctr=1;
        FileReader fr=new FileReader(dir+prescriptionFolder+fileName+".csv");
        CSVReader reader=new CSVReader(fr);
        String temp[];
        reader.readNext();
        while((temp=reader.readNext())!=null)
        {
            if(temp[0].equals(medicine)) {
                reader.close();
                fr.close();
                return ctr;
            }
            else
                ctr++;
        }
        reader.close();
        fr.close();
        return ctr;
    }

    int getLastIndex(String fileName)throws IOException
    {
        FileReader fr=new FileReader(dir+prescriptionFolder+fileName+".csv");
        CSVReader reader=new CSVReader(fr);
        int ctr=0;
        reader.readNext();
        while(reader.readNext()!=null)
        {
            ctr++;
        }
        return ctr;
    }
}
