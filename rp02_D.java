import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class rp02_D {
    static MyDoublyLinkedList linkedList;

    private static void initialize(String filename){
        try {
            FileReader inFile = new FileReader(filename);
            BufferedReader inBuffer = new BufferedReader(inFile);

            String line;
            while ((line = inBuffer.readLine()) != null) {
                commandExecute(line);
            }
            inBuffer.close();
            inFile.close();
        }catch(IOException e){
            System.out.println("Exception :" + e);
        }
    }

    private static boolean commandExecute(String line){
        StringTokenizer token = new StringTokenizer(line, " ");
        int tokenCount = token.countTokens();
        if (tokenCount > 0){
            String command = new String(token.nextToken());
            if       ((command.equals("insertLast") || command.equals("add"))
                    && tokenCount == 3 ){
                String newKey  = new String(token.nextToken());
                String newData = new String(token.nextToken());
                linkedList.insertLast(newKey, newData.toString());
            }else if ((command.equals("insertAfter") || command.equals("insA"))
                    && tokenCount > 2 ){
                String newKey  = new String(token.nextToken());
                String newData = new String(token.nextToken());
                linkedList.insertAfter(newKey, newData.toString());
            }else if ((command.equals("insertBefore") || command.equals("insB"))
                    && tokenCount > 2 ){
                String newKey  = new String(token.nextToken());
                String newData = new String(token.nextToken());
                linkedList.insertBefore(newKey, newData.toString());
            }else if (command.equals("head") && tokenCount == 1){
                linkedList.setCurrent("head");
            }else if (command.equals("tail") && tokenCount == 1){
                linkedList.setCurrent("tail");
            }else if (command.equals("info") && tokenCount == 1){
				linkedList.printInfo();
           }else if (command.equals("load") && tokenCount == 2){
                String targetFile = new String(token.nextToken());
                long startTime = System.currentTimeMillis();
                initialize(targetFile);
                long endTime = System.currentTimeMillis();
                System.out.printf("  %7.3f[s] for reading %s.\n",
                        (endTime - startTime)/1000.0, targetFile);


            }else if (command.equals("prev") && tokenCount == 1){
                boolean status = linkedList.movePrev();
                if (status == true){
                    System.out.println("  current is move to prev.");
                }
            }else if ((command.equals("delete") || command.equals("del")) 
                    && tokenCount == 1){
                MyDoublyLinkedData result = null;
                result = linkedList.delete();
                if (result != null){
                    System.out.println("  " + result.toStringSimple() + " deleted.");
                }else{
                    System.out.println("  not deleted.");
                }
            }else if ((command.equals("search") || command.equals("find")) 
                    && tokenCount == 2){
                String searchKey   = token.nextToken();
                MyDoublyLinkedData result = linkedList.searchByKey(searchKey);
                if (result != null){
                    System.out.println("  " + result.toStringSimple() + "(from key) is found.");
                }else {
                    result = linkedList.searchByData(searchKey);
                    if (result != null){       					
                        System.out.println("  " + result.toStringSimple() + "(from data) is found.");
                    }else{
                        System.out.println("  key (" + searchKey + ") or Data (" + searchKey + ") is not found.");
                    }
                }
            }else if ((command.equals("print") || command.equals("show"))
                    && tokenCount == 1){
                linkedList.printData();
            }else if (command.equals("count") && tokenCount == 1){
                System.out.println("  compareCount: " + linkedList.getCompareCount());
            }else if (command.equals("next") && tokenCount == 1){
                boolean status = linkedList.moveNext();
                if (status == true){
                    System.out.println("  current is move to next.");
                }
            }else if (command.equals("quit") && tokenCount == 1){
                System.out.println("  quit.");
                return false;
            }else{
                System.out.println("  don't understand your command: " + line);
            }
        }else{
            System.out.println("  don't understand your command: " + line);
        }
        return true;
    }

    public static void main(String argv[]) {
        linkedList = new MyDoublyLinkedList();
        if (argv.length > 0){
            for(int i = 0; i < argv.length; i++){
                long startTime = System.currentTimeMillis();
                initialize(argv[i]);
                long endTime = System.currentTimeMillis();
                System.out.printf("  %7.3f[s] for reading %s.\n",
                        (endTime - startTime)/1000.0, argv[i]);
            }
        }
        try{
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            while(true){
                System.out.println("current: " + linkedList.getCurrent().toStringSimple());
                System.out.print("  command: ");
                String line = br.readLine();
                if (line == null) break;
                if (commandExecute(line) == false) break;
            }
            br.close();
        }catch(IOException e){
            System.out.println("Exception :" + e);
        }
    }
}
