import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Comparator;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class FileSystem {

    BST<String, FileData> nameTree;
    BST<String, ArrayList<FileData>> dateTree;
    
    // TODO
    public FileSystem() {
    	nameTree = new BST<String, FileData>();
    	dateTree = new BST<String, ArrayList<FileData>>();
    }


    // TODO
    public FileSystem(String inputFile) {
    	// Add your code here
    	nameTree = new BST<String, FileData>();
    	dateTree = new BST<String, ArrayList<FileData>>();
    	ArrayList<FileData> second = new ArrayList<>();
    	FileData first;
        try {
            File f = new File(inputFile);
            Scanner sc = new Scanner(f);
            while (sc.hasNextLine()) {
                String[] data = sc.nextLine().split(", ");
                // Add your code here
                first = new FileData(data[0], data[1],data[2]);
                second.add(first);
                nameTree.set(data[0], first);
                dateTree.set(data[2], second);
            }
            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println(e);

        }
    }


    // TODO
    public void add(String name, String dir, String date) {
    	FileData add = new FileData(name,dir,date);
    	ArrayList<FileData> add2 = new ArrayList<>();
    	add2.add(add);
    	
    	if (name == null || dir == null || date == null)
    	{
    		return;
    	}
    	//dateTree still needs to be done
    	boolean containsName = false;
    	int dateComparison = 0;
    	String key = "";
    	FileData temp = null;
    	for (String e : nameTree.keys())
    	{
    		if (name.equals(e))
    		{
    			containsName = true;
    			
    			dateComparison = add.lastModifiedDate.compareTo(nameTree.get(e).lastModifiedDate);
    			temp = nameTree.get(e);
    			key = nameTree.get(e).lastModifiedDate;
    			System.out.println(dateComparison);
    		}
    		
    	}
    	if (containsName)
    	{
    		if (dateComparison == 0)
    		{
    			return;
    		}
    		else if (dateComparison > 0)
    		{
    			nameTree.set(name, add);
    			if (dateTree.get(key) != null && dateTree.get(key).size() > 1)
    			{
    				dateTree.get(key).remove(temp);
    				
    			}
    			else
    			{
    				dateTree.remove(key);
    				
    			}
    			if (dateTree.containsKey(date))
        		{
        			dateTree.get(date).add(add);
        		}
        		else
        		{
        			dateTree.set(date, add2);
        		}
    		}
    		else
    		{
    			
    			return;
    		}
    	}
    	else
    	{
    		nameTree.set(name, add);
    		if (dateTree.containsKey(date))
    		{
    			dateTree.get(date).add(add);
    		}
    		else
    		{
    			dateTree.set(date, add2);
    		}
    		//dateTree.set(date, add);
    	}
    	
    	
    	
    }


    // TODO
    public ArrayList<String> findFileNamesByDate(String date) {
    	if (date == null)
    	{
    		return null;
    	}
    	ArrayList<String> submit = new ArrayList<>();
    	if (dateTree.containsKey(date))
    	{
    		for (FileData e : dateTree.get(date))
    		{
    			submit.add(e.name);
    		}
    	}
    	return submit;
    }


    // TODO
    public FileSystem filter(String startDate, String endDate) {
    	FileSystem replacement = new FileSystem();
    	
    	for (String e : dateTree.keys())
    	{
    		if (e.compareTo(startDate) >= 0 && e.compareTo(endDate) < 0)
    		{
    			replacement.dateTree.set(e, dateTree.get(e));
    			
    		}
    	}
    	for (String e : replacement.dateTree.keys())
    	{
    		for (int i = 0; i < replacement.dateTree.get(e).size(); i++)
    		{
    			replacement.nameTree.set(replacement.dateTree.get(e).get(i).name, replacement.dateTree.get(e).get(i));
    		}
    		
    	}
    	return replacement;
    }
    
    
    // TODO
    public FileSystem filter(String wildCard) {
    	FileSystem replacement = new FileSystem();
    	ArrayList<FileData> newFileSystem = new ArrayList<>();
    	for (String e : nameTree.keys())
    	{
    		
    		if (e.contains(wildCard))
    		{
    			
    			newFileSystem.add(nameTree.get(e));
    		}
    	}
    	for (FileData f : newFileSystem)
    	{
    		replacement.add(f.name, f.dir, f.lastModifiedDate);
    		//relies on add method
    	}
    	return replacement;
    	
    }
    
    
    // TODO
    public List<String> outputNameTree(){
    	List<String> submit = new ArrayList<>();
    	String temp;
    	for (String e : nameTree.keys())
    	{
    		temp = e + ": " + nameTree.get(e).toString();
    		submit.add(temp);
    		
    	}
    	return submit;
    	
    }
    
    
    // TODO
    public List<String> outputDateTree(){
    	List<String> submit = new ArrayList<>();
    	List<String> reverse = new ArrayList<>();
    	String temp;
    	for (String e : dateTree.keys())
    	{
    		
    		
    		for (int i = 0; i < dateTree.get(e).size(); i++)
    		{
    			
    			temp = e + ": " + dateTree.get(e).get(i);
    			submit.add(temp);
    		}
    		
    		
    	}
    	for (int i = submit.size() - 1; i >= 0; i--)
    	{
    		reverse.add(submit.get(i));
    	}
    	return reverse;
    }
    

}

