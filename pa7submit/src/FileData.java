public class FileData {

    public String name;
    public String dir;
    public String lastModifiedDate;

    // TODO
    public FileData(String name, String directory, String modifiedDate) {
    	this.name = name;
    	this.dir = directory;
    	this.lastModifiedDate = modifiedDate;
    }

    // TODO
    public String toString() {
    	String submit = "{Name: " + name + ", Directory: " + dir + ", Modified Date: " + lastModifiedDate + "}";
    	return submit;
    }
}